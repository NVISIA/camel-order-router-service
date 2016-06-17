package com.nvisia.examples.camel.orderrouter;

import java.util.*;

import org.apache.camel.*;
import org.apache.camel.component.servlet.*;
import org.apache.camel.model.dataformat.*;
import org.apache.camel.model.rest.*;
import org.apache.camel.processor.aggregate.*;
import org.apache.camel.spring.boot.*;
import org.apache.camel.swagger.servlet.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.*;
import org.springframework.context.annotation.*;

/**
 * Spring boot application that defines the routes available for order routing.
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
@SpringBootApplication
public class OrderRouter extends FatJarRouter {

   @Override
   public void configure() {
      initializeRestConfiguration();
      initializeOrderRoute();
   }

   protected void initializeRestConfiguration() {
      // Start by building an instance of RestConfigurationDefinition. Need to
      // specify the component we are going to use for enabling REST endpoints,
      // specifically CamelServlet in this case. Set the binding mode to JSON.
      restConfiguration()
            // Leverage the CamelServlet component for the REST DSL
            .component("servlet")
            // Bind using JSON
            .bindingMode(RestBindingMode.json)
            // I like pretty things...
            .dataFormatProperty("prettyPrint", "true")
            // This is the context path to be used for Swagger API documentation
            .apiContextPath("api-doc")
            // Properties for Swagger
            // Title of the API
            .apiProperty("api.title", "Order Router API")
            // Version of the API
            .apiProperty("api.version", "1.0.0")
            // CORS (resource sharing) enablement
            .apiProperty("cors", "true")
            // Use localhost for calls
            .apiProperty("host", "localhost:8083")
            // Set base path
            .apiProperty("base.path", "camel-order-router-service/api");
   }

   protected void initializeOrderRoute() {
      // Definition of the post order endpoint
      rest("/orderRouter")
            // The POST method call for routing an order using the order form
            .post()
            // Description of what the method does
            .description("Routes a new order to the order management service")
            // Define the type used for input
            .type(OrderForm.class)
            // Define the type used for output, in this case the order
            .outType(Order.class)
            // Now, process the order
            .to("direct:processOrder");

      // This is the route that processes the order sent. First, we need to take
      // the data from the order form passed, put it in the header and empty out
      // the body of our incoming exchange.
      from("direct:processOrder")
            // Set header for customer ID
            .setHeader("customerId", simple("${body.customerId}"))
            // Set header for catalog item ID
            .setHeader("catalogItemId", simple("${body.catalogItemId}"))
            // Empty the body
            .setBody(constant(""))
            // Now, aggregate the data to an order type
            // Use multicasting to call the customer and catalog item services
            // in parallel. Then, use a strategy that groups the exchanges
            // returned from the service calls into a single list for
            // processing.
            .multicast(new GroupedExchangeAggregationStrategy())
            // Use parallel processing
            .parallelProcessing()
            // Send to two direct components to get the data
            .to("direct:getCustomerData", "direct:getCatalogItemData")
            // End the multicast call
            .end()
            // Now process the exchange
            .process(new Processor() {
               @Override
               public void process(Exchange exchange) throws Exception {
                  List<Exchange> exchanges = exchange.getIn().getBody(List.class);
                  ManagedOrderForm order = new ManagedOrderForm();
                  for (Exchange exchangeToProcess : exchanges) {
                     if (exchangeToProcess.getIn().getBody() instanceof Customer) {
                        order.setCustomer(
                              exchangeToProcess.getIn().getBody(Customer.class));
                     } else if (exchangeToProcess.getIn()
                           .getBody() instanceof CatalogItem) {
                        order.setCatalogItem(
                              exchangeToProcess.getIn().getBody(CatalogItem.class));
                     } else {
                        // Ignore it for now.
                     }
                  }
                  order.setOrderDate(new Date(System.currentTimeMillis()));
                  exchange.getIn().setBody(order);
               }
            })
            // Need to marshal the body to JSON
            .marshal()
            // Need to use JSON for marshalling
            .json(JsonLibrary.Jackson)
            // Then convert it to a string
            .convertBodyTo(String.class)
            // We can now send the order to order management. Need to define the
            // content type on the header
            .setHeader(org.apache.camel.Exchange.CONTENT_TYPE,
                  constant("application/json"))
            // Be safe and define this as a post
            .setHeader(Exchange.HTTP_METHOD,
                  constant(org.apache.camel.component.http4.HttpMethods.POST))
            // Set the HTTP uri to be used.
            .setHeader("CamelHttpUri", simple(
                  "http://localhost:8082/camel-order-management-service/api/order"))
            // Finally, send the order to be managed and get back the order ID
            .to("http4://localhost:8082/camel-order-management-service/api/order")
            // Next, convert the input stream returned to a string
            .convertBodyTo(String.class)
            // Finally, unmarshal the string to an object
            .unmarshal().json(JsonLibrary.Jackson, Order.class);

      // Retrieves the customer data from the REST service for customer.
      from("direct:getCustomerData")
            // Set the http method as GET
            .setHeader("CamelHttpMethod", constant("GET"))
            // Set the HTTP uri to be used.
            .setHeader("CamelHttpUri", simple(
                  "http://localhost:8081/camel-customer-service/api/customer/${header.customerId}"))
            // Define the endpoint; though, url will be ignored in favor of
            // header
            .to("http4://localhost:8081/camel-customer-service/api/customer/${header.customerId}")
            // Next, convert the input stream returned to a string
            .convertBodyTo(String.class)
            // Finally, unmarshal the string to an object
            .unmarshal().json(JsonLibrary.Jackson, Customer.class);

      // Retrieves the catalog item data from the REST service for catalog
      // items.
      from("direct:getCatalogItemData")
            // Set the http method as GET
            .setHeader("CamelHttpMethod", constant("GET"))
            // Set the HTTP uri to be used.
            .setHeader("CamelHttpUri", simple(
                  "http://localhost:8080/camel-catalog-service/api/catalogItem/${header.catalogItemId}"))
            // Define the endpoint; though, url will be ignored in favor of
            // header
            .to("http4://localhost:8080/camel-catalog-service/api/catalogItem/${header.catalogItemId}")
            // Next, convert the input stream returned to a string
            .convertBodyTo(String.class)
            // Finally, unmarshal the string to an object
            .unmarshal().json(JsonLibrary.Jackson, CatalogItem.class);
   }

   @Bean
   public ServletRegistrationBean camelServletRegistrationBean() {
      ServletRegistrationBean registration = new ServletRegistrationBean(
            new CamelHttpTransportServlet(), "/api/*");
      registration.setName("CamelServlet");
      return registration;
   }

   @Bean
   public ServletRegistrationBean swaggerServletRegistrationBean() {
      ServletRegistrationBean registration = new ServletRegistrationBean(
            new RestSwaggerServlet(), "/api-docs/*");
      registration.setName("SwaggerServlet");
      return registration;
   }

   @Bean
   public FilterRegistrationBean filterRegistrationBean() {
      FilterRegistrationBean filterBean = new FilterRegistrationBean();
      filterBean.setFilter(new RestSwaggerCorsFilter());
      List<String> urlPatterns = new ArrayList<String>();
      urlPatterns.add("/camel-order-router-service/api-docs/*");
      urlPatterns.add("/camel-order-router-service/api/*");
      filterBean.setUrlPatterns(urlPatterns);
      return filterBean;
   }

}
