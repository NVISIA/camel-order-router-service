package com.nvisia.examples.camel.orderrouter;

import org.apache.camel.spring.boot.*;

/**
 * Extends Spring Boot's SpringBootServletInitializer for initializing the web
 * application, providing the ability to initialize the Camel router for order
 * routing.
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public class OrderRouterWarInitializer extends FatWarInitializer {

   @Override
   protected Class<? extends FatJarRouter> routerClass() {
      return OrderRouter.class;
   }

}
