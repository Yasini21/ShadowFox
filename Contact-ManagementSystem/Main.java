import java.util.*;
public class Main {
    static Scanner scanner=new Scanner(System.in);
    static ArrayList<Contact>contacts=new ArrayList<>();
    public static void main(String[] args){
     
     while(true){
     System.out.println("1. Add Contact");
     System.out.println("2. View Contacts");
     System.out.println("3. Update Contact");
     System.out.println("4. Delete Contact");
     System.out.println("5. Exit");
     System.out.print("Choose option: ");
     int choice=scanner.nextInt();
     scanner.nextLine();
     switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("ThankYou!!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
     }
    }
    static void addContact(){
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email= scanner.nextLine();

        System.out.print("Enter PhoneNo: ");
        String phoneNo = scanner.nextLine();

        contacts.add(new Contact(name, email, phoneNo));
    }
    static void viewContact(){
        if(contacts.isEmpty()){
            System.out.println("Contact is empty");
            return;
        }
        for(int i=0;i<contacts.size();i++){
            System.out.println("Contact ID: "+i);
            contacts.get(i).display();
        }
    }
    static void updateContact(){
        viewContact();
        System.out.println("Enter Contact id to update: ");
        int id=scanner.nextInt();
        scanner.nextLine();
        if(id>=0 && id<contacts.size()){
            System.out.println("Enter the name to update: ");
            contacts.get(id).name=scanner.nextLine();
            System.out.println("Enter the email to update: ");
            contacts.get(id).email=scanner.nextLine();
            System.out.println("Enter the phone to update: ");
            contacts.get(id).phoneNo=scanner.nextLine();
            System.out.println("Contact updated successfully!");

        }
        else {
            System.out.println("Invalid ID.");
        }
    }
    static void deleteContact(){
        viewContact();
        System.out.println("Enter Contact id to delete: ");
        int id=scanner.nextInt();
        if(id>=0&&id<contacts.size()){
            contacts.remove(id);
            System.out.println("Contact deleted successfully!");

        }else{
            System.out.println("Invalid ID.");
        }

    }
}
