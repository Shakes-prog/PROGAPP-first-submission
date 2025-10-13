import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        String userNameAttempt;
        String passwordAttempt;
        String registrationStatus;
        String response = "";
        int numMessagesSent = 0;
        int maxMessages;
        String recipient;

        // ===== USER REGISTRATION =====
        JOptionPane.showMessageDialog(null, "***** REGISTER *****", "QuickChat", JOptionPane.INFORMATION_MESSAGE);

        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String Name = JOptionPane.showInputDialog("Enter your username (max 5 characters, must contain _ ):");
        String userPassword = JOptionPane.showInputDialog("Enter your password (8+ chars, capital, number, special char):");
        String cellNumber = JOptionPane.showInputDialog("Enter your South African phone number (in the format +27):");

        // Register the user
        registrationStatus = Login.registerUser(Name, userPassword, cellNumber);
        JOptionPane.showMessageDialog(null, registrationStatus);

        if (!registrationStatus.equals("Account registered successfully.")) {
            return; // Exit if registration fails
        }

        // Create a Login object
        Login user1 = new Login(Name, userPassword, cellNumber);

        // ===== LOGIN =====
        JOptionPane.showMessageDialog(null, "***** LOGIN *****", "QuickChat", JOptionPane.INFORMATION_MESSAGE);

        userNameAttempt = JOptionPane.showInputDialog("Enter your username:");
        passwordAttempt = JOptionPane.showInputDialog("Enter your password:");

        Messages Message1 = null;

        boolean loginSuccess = user1.LoginUser(userNameAttempt, passwordAttempt);
        if (loginSuccess) {
            user1.returnLoginStatus(userNameAttempt, passwordAttempt);
            JOptionPane.showMessageDialog(null, "Login successful. Welcome back " + firstName + " " + lastName + "!\nWelcome to QuickChat.");
            Message1 = new Messages();

            // Ask for number of messages
            maxMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting program.");
            return;
        }


        //generated with assistance from Chatgpt on 2025-10-11
        //The purpose of the method is to store the message in a JSON format. and use JoptionPane for interaction.
        // Reference: Chatgpt. (2025, October 11). Java JSON storage code [AI-generated code].
        // ===== MAIN MENU =====
        boolean running = true;
        while (running) {
            String menu = """
                    ===== QuickChat Menu =====
                    1) Send Messages
                    2) Show Recently Sent Messages
                    3) Quit
                    """;

            int choice = Integer.parseInt(JOptionPane.showInputDialog(menu + "\nEnter your choice (1-3):"));

            switch (choice) {
                case 1:
                    while (numMessagesSent < maxMessages) {
                        Message1.createMessageID();

                        recipient = JOptionPane.showInputDialog("Enter recipient cellphone number (+27xxxxxxxxx):");

                        if (!Message1.checkRecipientCell(recipient)) {
                            JOptionPane.showMessageDialog(null, "Invalid number format. Try again.");
                            continue;
                        }
                        Message1.setRecipient(recipient);

                        String messageContent = JOptionPane.showInputDialog("Enter message (max 250 characters):");

                        if (!Message1.checkMessageLength(messageContent)) {
                            JOptionPane.showMessageDialog(null, " Message too long. Try again.");
                            continue;
                        }
                        Message1.setMessage(messageContent);
                        Message1.createMessageHash();

                        String[] options = {"Send", "Store", "Disregard"};
                        int sendChoice = JOptionPane.showOptionDialog(
                                null,
                                "Choose an action for this message:",
                                "Send or Store Message",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                options[0]
                        );

                        response = Message1.sendMessage(sendChoice + 1); // +1 because buttons start at index 0
                        JOptionPane.showMessageDialog(null, response);

                        if (response.equals("Message sent.") || response.equals("Message stored.")) {
                            numMessagesSent++;

                            String messageDetails =
                                    "Message Details:\n\n" +
                                    "Message ID: " + Message1.getMessageID() + "\n" +
                                    "Message Hash: " + Message1.getMessageHash() + "\n" +
                                    "Recipient: " + Message1.getRecipient() + "\n" +
                                    "Message: " + Message1.getMessage();

                            JOptionPane.showMessageDialog(null, messageDetails, "Message Summary", JOptionPane.INFORMATION_MESSAGE);

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Message " + numMessagesSent + " of " + maxMessages + " processed.\n\n" +
                                            Message1.printMessages() + "\n" +
                                            "Total messages sent: " + Message1.returnTotalMessages() + "\n" +
                                            "Total messages stored: " + Message1.returnTotalStoredMessages(),
                                    "Message Stats",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }

                        if (numMessagesSent >= maxMessages) {
                            JOptionPane.showMessageDialog(null, " You have reached your message limit (" + maxMessages + ").");
                            break;
                        }
                    }
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    running = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }
}
