package devo;

public class ContactManager {
    
    private Contact[] contacts;
    private int nextIndex;
    
    public ContactManager() {//Array contains 100
     nextIndex = 0;
     contacts = new Contact[100];
    }
    
    public void register(Contact contact) {//static nextIndex -> adding 1 -> can register addressbook  ..??
     contacts[nextIndex] = contact;
     nextIndex++;
    } 
    
    public void delete(int number) {
        for(int i=number; i<nextIndex; i++){
            contacts[i] = contacts[i+1];
            }
            nextIndex--; 
    } 
    public Contact getContact(String name) { //insert search method  -> return index
        for(int i=0; i<nextIndex; i++){
            if( contacts[i].getName().equals(name)){ 
            return contacts[i];
            }
        }
        return null;
    }
    
    public Contact[] getContactList() {
     Contact[] list = new Contact[nextIndex];
     for (int i = 0; i < list.length; i++) {
      list[i] = contacts[i];
     }
     return list;
    }
   

   }
