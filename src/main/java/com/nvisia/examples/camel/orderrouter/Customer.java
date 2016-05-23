package com.nvisia.examples.camel.orderrouter;

/**
 * Bean for a Customer
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public class Customer {

   private int id;
   private String firstName;
   private String lastName;
   private String phoneNumber;
   
   /**
    * Default constructor
    */
   public Customer() {
      
   }

   /**
    * Full constructor
    * 
    * @param id
    * @param firstName
    * @param lastName
    * @param phoneNumber
    */
   public Customer(int id, String firstName, String lastName,
         String phoneNumber) {
      super();
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
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
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @param firstName the firstName to set
    */
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param lastName the lastName to set
    */
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   /**
    * @return the phoneNumber
    */
   public String getPhoneNumber() {
      return phoneNumber;
   }

   /**
    * @param phoneNumber the phoneNumber to set
    */
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Customer [id=");
      builder.append(id);
      builder.append(", ");
      if (firstName != null) {
         builder.append("firstName=");
         builder.append(firstName);
         builder.append(", ");
      }
      if (lastName != null) {
         builder.append("lastName=");
         builder.append(lastName);
         builder.append(", ");
      }
      if (phoneNumber != null) {
         builder.append("phoneNumber=");
         builder.append(phoneNumber);
      }
      builder.append("]");
      return builder.toString();
   }
}
