import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String userNameAttempt;
        String passwordAttempt;
        String registrationStatus;
        
        Scanner scanner = new Scanner(System.in);

        // User Registration
        // prompting the user for input
        System.out.println("*****REGISTER*****");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your username with no more than 5 characters containing an _ : ");
        String Name = scanner.nextLine();

        System.out.print(
                "Enter your password. it needs to contain at least 8 characters, a capital letter, a number and a special character: ");
        String userPassword = scanner.nextLine();

        System.out.print("Enter your South African phone number (in the format +27): ");
        String cellNumber = scanner.nextLine();

        // Register the user
        //generated with assistance from Microsoft Copilot on 2025-09-18
        //The purppose of the method is to validate user credentials during registration.
        // Reference: Copilot. (2025, September 18). Java credential validation code
        registrationStatus = Login.registerUser(Name, userPassword, cellNumber);
        System.out.println(registrationStatus);
        if (!registrationStatus.equals("Account registered successfully.")) {
            // System.out.println(registrationMessage);
            return; // Exit if registration fails
        }
 
        // Create a Login object for the registered user
        Login user1 = new Login(Name, userPassword, cellNumber);

        // Attempt to log in the user
        System.out.println("*****LOGIN*****");

        System.out.println("Please enter your Username");
        userNameAttempt = scanner.nextLine();

        System.out.println("Please enter your Password");
        passwordAttempt = scanner.nextLine();

        // Check login status
        if (user1.LoginUser(userNameAttempt, passwordAttempt) == user1.LoginUser(Name, userPassword)) {
            user1.returnLoginStatus(userNameAttempt, passwordAttempt);
            System.out.println("Login successful. Welcome back " + firstName + " " + lastName + "!");
        }

       
        

    }

}
