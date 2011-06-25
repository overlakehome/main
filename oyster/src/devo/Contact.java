package devo;

public class Contact {

     
     private static int nextNumber = 1;
     private int number;
     private String name;
     private String phone;
     private String email;
     private String relation;
     
     public Contact() {
      number = nextNumber++;
     }
     
     public String getContactInfo() {
      return String.format("[%2d][%s][%s][%s][%s]",
        number, name, phone, email, relation);
     }
     
     public String getName() {
      return name;
     }
     public void setName(String name) {
      this.name = name;
     }

     


     public String getPhone() {
      return phone;
     }
     public void setPhone(String phone) {
      this.phone = phone;
     }

     


     public String getEmail() {
      return email;
     }
     public void setEmail(String email) {
      this.email = email;
     }

     


     public String getRelation() {
      return relation;
     }
     public void setRelation(String relation) {
      this.relation = relation;
     }

     


     public static int getNextNumber() {
      return nextNumber;
     }
     public int getNumber() {
      return number;
     }
     
}
