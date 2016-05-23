package com.nvisia.examples.camel.orderrouter;

/**
 * Represents a catalog item attribute.
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public class CatalogItemAttribute {

   private String attributeName;
   private String attributeValue;

   /**
    * Default constructor
    */
   public CatalogItemAttribute() {
      super();
   }

   /**
    * Full constructor
    * 
    * @param attributeName
    * @param attributeValue
    */
   public CatalogItemAttribute(String attributeName, String attributeValue) {
      super();
      this.attributeName = attributeName;
      this.attributeValue = attributeValue;
   }

   /**
    * @return the attributeName
    */
   public String getAttributeName() {
      return attributeName;
   }

   /**
    * @param attributeName
    *           the attributeName to set
    */
   public void setAttributeName(String attributeName) {
      this.attributeName = attributeName;
   }

   /**
    * @return the attributeValue
    */
   public String getAttributeValue() {
      return attributeValue;
   }

   /**
    * @param attributeValue
    *           the attributeValue to set
    */
   public void setAttributeValue(String attributeValue) {
      this.attributeValue = attributeValue;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("CatalogItemAttribute [");
      if (attributeName != null) {
         builder.append("attributeName=");
         builder.append(attributeName);
         builder.append(", ");
      }
      if (attributeValue != null) {
         builder.append("attributeValue=");
         builder.append(attributeValue);
      }
      builder.append("]");
      return builder.toString();
   }

}
