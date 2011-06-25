package devo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaExcersize {

     
     static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
     public static void main(String[] args)throws IOException {
      
      ContactManager manager = new ContactManager();
      
      while (true) {


       String selection = showMenu();


       if (selection.equals("1")) {//register contact
        

    //1.create contact object
        Contact contact = new Contact();
        

    //2. assign user input(name, phone number ,email, relationship) to object 
        System.out.print("Name : ");
        contact.setName(br.readLine());
        System.out.print("Phone Number : ");
        contact.setPhone(br.readLine());
        System.out.print("Email : ");
        contact.setEmail(br.readLine());
        System.out.print("Relationship : ");
        contact.setRelation(br.readLine());    
        

    //3.Manager object--> register
        manager.register(contact);
       
       } else if (selection.equals("5")) {//Contact list
        Contact[] list = manager.getContactList();
        for (int i = 0; i < list.length; i++) {
         System.out.println(list[i].getContactInfo());
        }
       
       } else if (selection.equals("4")) {//search contact, return index, 

           System.out.print("Name : ");
          // String searchName = manager.getContact(br.readLine());
           Contact contact = manager.getContact(br.readLine() );
               System.out.println(contact.getContactInfo());          
           
       } else if (selection.equals("2")) {//Edit
           Contact[] list = manager.getContactList();
           for (int i = 0; i < list.length; i++) {
            System.out.println(list[i].getContactInfo());
           }
           System.out.println("Select index number of contact to edit");
           
           String editContact = br.readLine();
           int editIndex = Integer.parseInt(editContact) - 1;
           System.out.print("Name : ");
           list[editIndex].setName(br.readLine());
           System.out.print("Phone Number : ");
           list[editIndex].setPhone(br.readLine());
           System.out.print("Email : ");
           list[editIndex].setEmail(br.readLine());
           System.out.print("Relationship : ");
           list[editIndex].setRelation(br.readLine());    
        
       } else if (selection.equals("3")) {//delete1
           System.out.println("Select index number of contact to edit");
           manager.delete(Integer.parseInt(br.readLine()) - 1);
        
       } else if (selection.equals("0")) {//contact done
        System.out.println("The program is exited.");
        break; // return..
       }
      }
     } 
     private static String showMenu() throws IOException {
      System.out.println("Work List");
      System.out.println("\t1. Contact Register");
      System.out.println("\t2. Contact Edit");
      System.out.println("\t3. Contact Delete");
      System.out.println("\t4. Contact Search");
      System.out.println("\t5. Contact List");
      System.out.println("\t0. Done");
      System.out.print("Select : ");
      return br.readLine();
     }

}
