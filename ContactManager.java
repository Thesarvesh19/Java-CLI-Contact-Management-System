import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(ANSI_CYAN + "--- Welcome to the Contact Management System by Sarvesh Soumil ---" + ANSI_RESET);
        
        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1: addContact(); break;
                case 2: viewContacts(); break;
                case 3: searchContact(); break;
                case 4: deleteContact(); break;
                case 5: editContact(); break;
                case 6:
                    System.out.println(ANSI_YELLOW + "Thank you for using the Contact Manager. Goodbye!" + ANSI_RESET);
                    scanner.close();
                    return;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please enter a number between 1 and 6." + ANSI_RESET);
            }
        }
    }

    private static void printMenu() {
        System.out.println(ANSI_CYAN + "\n--- Sarvesh Soumil's Contact Manager ---" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "------------- MAIN MENU --------------");
        System.out.println("1. Add a new contact");
        System.out.println("2. View all contacts");
        System.out.println("3. Search for a contact");
        System.out.println("4. Delete a contact");
        System.out.println("5. Edit an existing contact");
        System.out.println("6. Exit" + ANSI_RESET);
        System.out.print("Please enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            scanner.next();
            return -1;
        }
    }

    private static void addContact() {
        scanner.nextLine(); 
        
        System.out.print(ANSI_YELLOW + "Enter name: " + ANSI_RESET);
        String name = scanner.nextLine();
        
        String phone = getValidatedPhoneNumber(null);
        String email = getValidatedEmail();
        
        Contact newContact = new Contact(name, phone, email);
        contacts.add(newContact);
        
        System.out.println(ANSI_GREEN + "Contact added successfully!" + ANSI_RESET);
    }
    
    private static void editContact() {
        viewContacts();
        if (contacts.isEmpty()) {
            return;
        }

        System.out.print(ANSI_YELLOW + "\nEnter the number of the contact you want to edit: " + ANSI_RESET);
        int contactNumber;
        try {
            contactNumber = scanner.nextInt();
            if (contactNumber < 1 || contactNumber > contacts.size()) {
                System.out.println(ANSI_RED + "Invalid number. Please try again." + ANSI_RESET);
                return;
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
            scanner.next();
            return;
        }

        Contact contactToEdit = contacts.get(contactNumber - 1);

        System.out.println(ANSI_BLUE + "\nWhat do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Email Address" + ANSI_RESET);
        System.out.print("Please enter your choice: ");
        int editChoice = getUserChoice();
        scanner.nextLine();

        switch (editChoice) {
            case 1:
                System.out.print(ANSI_YELLOW + "Enter new name: " + ANSI_RESET);
                String newName = scanner.nextLine();
                contactToEdit.setName(newName);
                break;
            case 2:
                String newPhone = getValidatedPhoneNumber(contactToEdit);
                contactToEdit.setPhoneNumber(newPhone);
                break;
            case 3:
                String newEmail = getValidatedEmail();
                contactToEdit.setEmail(newEmail);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
                return;
        }
        System.out.println(ANSI_GREEN + "Contact updated successfully!" + ANSI_RESET);
    }
    
    private static String getValidatedPhoneNumber(Contact editingContact) {
        while (true) {
            System.out.print(ANSI_YELLOW + "Enter phone number: " + ANSI_RESET);
            String phone = scanner.nextLine();

            if (!phone.matches(".*\\d.*")) {
                System.out.println(ANSI_RED + "Invalid format. Phone number must contain digits." + ANSI_RESET);
                continue;
            }

            boolean isDuplicate = false;
            for (Contact contact : contacts) {
                if (contact != editingContact && contact.getPhoneNumber().equals(phone)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println(ANSI_RED + "This phone number already exists in your contacts." + ANSI_RESET);
            } else {
                return phone;
            }
        }
    }

    private static String getValidatedEmail() {
        while (true) {
            System.out.print(ANSI_YELLOW + "Enter email address: " + ANSI_RESET);
            String email = scanner.nextLine();
            if (email.contains("@") && email.contains(".")) {
                return email;
            } else {
                System.out.println(ANSI_RED + "Invalid format. Please enter a valid email address." + ANSI_RESET);
            }
        }
    }
    
    private static void viewContacts() {
        System.out.println(ANSI_CYAN + "\n--- ALL CONTACTS ---" + ANSI_RESET);
        if (contacts.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Your contact list is empty." + ANSI_RESET);
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    private static void searchContact() {
        scanner.nextLine();
        System.out.print(ANSI_YELLOW + "Enter the name to search for: " + ANSI_RESET);
        String searchName = scanner.nextLine();
        
        ArrayList<Contact> foundContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(searchName.toLowerCase())) {
                foundContacts.add(contact);
            }
        }
        
        System.out.println(ANSI_CYAN + "\n--- SEARCH RESULTS ---" + ANSI_RESET);
        if (foundContacts.isEmpty()) {
            System.out.println(ANSI_RED + "No contacts found matching '" + searchName + "'." + ANSI_RESET);
        } else {
            for (Contact contact : foundContacts) {
                System.out.println(contact);
            }
        }
    }

    private static void deleteContact() {
        viewContacts(); 
        if (contacts.isEmpty()) {
            return;
        }
        
        System.out.print(ANSI_YELLOW + "\nEnter the number of the contact to delete: " + ANSI_RESET);
        try {
            int contactNumber = scanner.nextInt();
            if (contactNumber > 0 && contactNumber <= contacts.size()) {
                Contact removedContact = contacts.remove(contactNumber - 1);
                System.out.println(ANSI_GREEN + "Successfully deleted contact: " + removedContact.getName() + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Invalid number. Please try again." + ANSI_RESET);
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
            scanner.next();
        }
    }
}
  