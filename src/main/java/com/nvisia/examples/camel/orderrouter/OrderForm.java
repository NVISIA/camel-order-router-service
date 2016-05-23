package com.nvisia.examples.camel.orderrouter;

/**
 * Form for posting a new order
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public class OrderForm {

   private int customerId; 
   private int catalogItemId;
   
   /**
    * Default constructor
    */
   public OrderForm() {
      super(); 
   }

   /**
    * Full constructor
    */
   public OrderForm(int customerId, int catalogItemId) {
      super();
      this.customerId = customerId;
      this.catalogItemId = catalogItemId;
   }

   /**
    * @return the customerId
    */
   public int getCustomerId() {
      return customerId;
   }

   /**
    * @param customerId the customerId to set
    */
   public void setCustomerId(int customerId) {
      this.customerId = customerId;
   }

   /**
    * @return the catalogItemId
    */
   public int getCatalogItemId() {
      return catalogItemId;
   }

   /**
    * @param catalogItemId the catalogItemId to set
    */
   public void setCatalogItemId(int catalogItemId) {
      this.catalogItemId = catalogItemId;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("OrderForm [customerId=");
      builder.append(customerId);
      builder.append(", ");
      builder.append("catalogItemId=");
      builder.append(catalogItemId);
      builder.append("]");
      return builder.toString();
   }
   
   
}
