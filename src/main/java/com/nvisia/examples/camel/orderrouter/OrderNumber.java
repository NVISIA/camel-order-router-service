package com.nvisia.examples.camel.orderrouter;

/**
 * Order number response
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public class OrderNumber {

   private String orderNumber;

   /**
    * Default constructor
    */
   public OrderNumber() {
      super();
   }

   /**
    * Full constructor
    * 
    * @param orderNumber
    */
   public OrderNumber(String orderNumber) {
      super();
      this.orderNumber = orderNumber;
   }

   /**
    * @return the orderNumber
    */
   public String getOrderNumber() {
      return orderNumber;
   }

   /**
    * @param orderNumber
    *           the orderNumber to set
    */
   public void setOrderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("OrderNumber [");
      if (orderNumber != null) {
         builder.append("orderNumber=");
         builder.append(orderNumber);
      }
      builder.append("]");
      return builder.toString();
   }

}
