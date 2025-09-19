//References
//OpenAI. (2023). ChatGPT (Mar 14 version) [Large language model]. https://chat.openai.com/chat
//Copilot. (2025, September 18). Java credential validation code [AI-generated code]. Personal communication.




public class Login {

    public String userName;
    public String password;
    public String cellNumbers;
    public String fisrtName;
    public String lastName;

    public Login(String userName, String password, String cellNumbers) {
        this.userName = userName;
        this.password = password;
        this.cellNumbers = cellNumbers;
        this.fisrtName = fisrtName;
        this.lastName = lastName;
    }

    // static methods for username
    public static boolean checkUserName(String userName) {
        return userName.contains("_") && userName.length() <= 5;
    }
    //generated with assistance from microsoft copilot on 2025-09-18
    //The purpose of the method is to validate password complexity during user registration.
    // static methods for password
    public static boolean checkPasswordComplexity(String password) {
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecialCharacters = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return password.length() >= 8 && hasUpper && hasDigit && hasSpecialCharacters;
    }

    

    // static methods for cell numbers
    public static boolean checkCellNumber(String cellNumbers) {
        return cellNumbers.startsWith("+27") && cellNumbers.length() == 12;
    }

    // Generated with assistance from Microsoft Copilot on 2025-09-18
    //The purppose of the method is to validate user credentials during registration.
    // Reference: Copilot. (2025, September 18). Java credential validation code [AI-generated code]. 
    // Method to register user

    public static String registerUser(String userName, String password, String cellNumbers) {
        StringBuilder errors = new StringBuilder();

        if (!checkUserName(userName)) {
            errors.append("The username does not contain an _ and or should be five characters or less long.\n");
        }

        if (!checkPasswordComplexity(password)) {
            errors.append(
                    "The password does not meet the complexity requirements. Password should be 8 characters long, have a capital letter, a special character, and a number\n");
        }

        if (!checkCellNumber(cellNumbers)) {
            errors.append(
                    "The cell number is incorrectly formatted. The cell number should be 10 numbers long and should start with +27\n");
        }

        if (errors.length() > 0) {
            return errors.toString().trim(); // Return all errors
        } else {

            return "Account registered successfully.";
        }
    }

    public String returnLoginStatus(String userName, String password) {
        if (userName.equals(getUserName()) && password.equals(getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid username or password. Please try again.";
        }
    }

    // Attempt to log in the user
    public boolean LoginUser(String userNameAttempt, String passwordAttempt) {
        return userNameAttempt.equals(this.userName) && passwordAttempt.equals(this.password);
    }


    //The getters and setters method.
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCellNumbers() {
        return cellNumbers;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCellNumbers(String cellNumbers) {
        this.cellNumbers = cellNumbers;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
