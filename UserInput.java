import java.util.Scanner;

public class UserInput {
    private static String registeredUsername;
    private static String registeredPassword;
    private static String registeredFirstName;

    public static boolean CheckUserName(String username) {
        // Ensure the username contains an underscore and is no longer than five characters
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean checkCellPhoneNumber(String phoneNumber) {
        // Ensure the phone number starts with "+27" and is exactly 13 characters long
        return phoneNumber.startsWith("+27") && phoneNumber.length() == 10; // "+27" + 9 digits = 13
    }

    public static boolean checkPasswordComplexity(String password) {
        // Check for password complexity requirements
        return password.length() >= 8 
               && password.matches(".[A-Z].") // At least one uppercase letter
               && password.matches(".\\d.")   // At least one number
               && password.matches(".[^a-zA-Z0-9]."); // At least one special character
    }

    public static String registerUser(Scanner scanner) {
        // Registration process
        System.out.print("Please Enter your first name: ");
        registeredFirstName = scanner.nextLine();
        
        System.out.print("Please Enter your username (must contain an underscore and be no longer than five characters): ");
        String username = scanner.nextLine();

        if (!CheckUserName(username)) {
            return "Username is not correctly formatted. It must contain an underscore and be no longer than five characters.";
        }

        System.out.print("Please Enter your password: ");
        String password = scanner.nextLine();
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. Please ensure that it contains at least eight characters, one capital letter, one number, and one special character.";
        }

        System.out.print("Please Enter your South African cell phone number (must start with '+27' and be 11 digits long): ");
        String phoneNumber = scanner.nextLine();

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Invalid phone number. Please ensure it starts with '+27' and is exactly 11 digits long.";
        }

        // The use must register
        registeredUsername = username; // Store the registered username
        registeredPassword = password; // Store the registered password
        return "Successfully registered! You can now log in.";
    }

    public static String returnLoginStatus(String username, String password) {
        // Please ensure the login credentials
        if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
            return "Welcome " + registeredFirstName + "!";
        } else {
            return "Username or password incorrect. Please try again.";
        }
    }

    public static void login(Scanner scanner) {
        int attempts = 3; // Allow up to 3 login attempts
        while (attempts > 0) {
        System.out.print("Please log in with your credentials.\nEnter your username: ");
    String loginUsername = scanner.nextLine();

        System.out.print("Enter  password: ");
    String loginPassword = scanner.nextLine();

    String loginMessage = returnLoginStatus(loginUsername, loginPassword);
        System.out.println(loginMessage);

    if (loginMessage.startsWith("Welcome")) {
        break; // Exit the loop on successful login
    } else {
        attempts--;
    if (attempts > 0) {
        System.out.println("You have " + attempts + " attempt(s) left.");
    } else {
        System.out.println("No attempts left. Exiting login.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Registration process through registerUser method
        String registrationMessage = registerUser(scanner);
        System.out.println(registrationMessage);
        
        // ensure if registration is successful before proceeding to login
        if (!registrationMessage.equals("Successfully registered! You can now log in.")) {
            scanner.close();
            return;
        }

        // call  the login method
        login(scanner);

        scanner.close();
    }
}