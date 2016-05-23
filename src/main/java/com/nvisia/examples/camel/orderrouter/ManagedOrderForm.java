package com.nvisia.examples.camel.orderrouter;

import java.util.*;

/**
 * Bean for an order
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public class ManagedOrderForm {

   private Date orderDate;
   private Customer customer;
   private CatalogItem catalogItem;

   /**
    * Default constructor
    */
   public ManagedOrderForm() {
      super();
   }

   /**
    * Full constructor
    * 
    * @param orderDate
    * @param customerId
    * @param customer
    * @param catalogItemId
    * @param catalogItem
    */
   public ManagedOrderForm(Date orderDate, Customer customer, CatalogItem catalogItem) {
      super();
      this.orderDate = orderDate;
      this.customer = customer;
      this.catalogItem = catalogItem;
   }

   /**
    * @return the orderDate
    */
   public Date getOrderDate() {
      return orderDate;
   }

   /**
    * @param orderDate
    *           the orderDate to set
    */
   public void setOrderDate(Date orderDate) {
      this.orderDate = orderDate;
   }

   /**
    * @return the customer
    */
   public Customer getCustomer() {
      return customer;
   }

   /**
    * @param customer
    *           the customer to set
    */
   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   /**
    * @return the catalogItem
    */
   public CatalogItem getCatalogItem() {
      return catalogItem;
   }

   /**
    * @param catalogItem
    *           the catalogItem to set
    */
   public void setCatalogItem(CatalogItem catalogItem) {
      this.catalogItem = catalogItem;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Order [");
      if (orderDate != null) {
         builder.append("orderDate=");
         builder.append(orderDate);
         builder.append(", ");
      }
      if (customer != null) {
         builder.append("customer=");
         builder.append(customer);
         builder.append(", ");
      }
      if (catalogItem != null) {
         builder.append("catalogItem=");
         builder.append(catalogItem);
         builder.append(", ");
      }
      builder.append("]");
      return builder.toString();
   }

}
