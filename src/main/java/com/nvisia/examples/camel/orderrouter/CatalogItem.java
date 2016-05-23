package com.nvisia.examples.camel.orderrouter;

import java.util.*;

/**
 * Bean for catalog item
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public class CatalogItem {
   private int id;
   private double price;
   private CatalogItemType catalogItemType;
   private List<CatalogItemAttribute> attributes;

   /**
    * Default constructor
    */
   public CatalogItem() {
      super();
   }

   /**
    * Full constructor
    * 
    * @param id
    * @param price
    * @param catalogItemType
    * @param attributes
    */
   public CatalogItem(int id, double price, CatalogItemType catalogItemType,
         List<CatalogItemAttribute> attributes) {
      super();
      this.id = id;
      this.price = price;
      this.catalogItemType = catalogItemType;
      this.attributes = attributes;
   }

   /**
    * @return the id
    */
   public int getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(int id) {
      this.id = id;
   }

   /**
    * @return the price
    */
   public double getPrice() {
      return price;
   }

   /**
    * @param price the price to set
    */
   public void setPrice(double price) {
      this.price = price;
   }

   /**
    * @return the catalogItemType
    */
   public CatalogItemType getCatalogItemType() {
      return catalogItemType;
   }

   /**
    * @param catalogItemType the catalogItemType to set
    */
   public void setCatalogItemType(CatalogItemType catalogItemType) {
      this.catalogItemType = catalogItemType;
   }

   /**
    * @return the attributes
    */
   public List<CatalogItemAttribute> getAttributes() {
      return attributes;
   }

   /**
    * @param attributes the attributes to set
    */
   public void setAttributes(List<CatalogItemAttribute> attributes) {
      this.attributes = attributes;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("CatalogItem [id=");
      builder.append(id);
      builder.append(", price=");
      builder.append(price);
      builder.append(", ");
      if (catalogItemType != null) {
         builder.append("catalogItemType=");
         builder.append(catalogItemType);
         builder.append(", ");
      }
      if (attributes != null) {
         builder.append("attributes=");
         builder.append(attributes);
      }
      builder.append("]");
      return builder.toString();
   }
}
