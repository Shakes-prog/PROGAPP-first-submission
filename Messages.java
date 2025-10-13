import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

public class Messages {

    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private int totalMessagesSent = 0;
    private int totalMessagesStored = 0;

    // Generate random 10-digit Message ID
    public void createMessageID() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        messageID = sb.toString();
    }

    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() == 10;
    }

    public boolean checkRecipientCell(String recipient) {
        if (recipient == null || !recipient.startsWith("+27") || recipient.length() != 12) {
            return false;
        }
        String digits = recipient.substring(3);
        return digits.matches("\\d{9}");
    }

    public boolean checkMessageLength(String message) {
        return message != null && message.length() <= 250;
    }

    public String createMessageHash() {
        if (message == null || message.trim().isEmpty()) {
            messageHash = "INVALID:EMPTYMESSAGE";
            return messageHash;
        }
        String idPart = (messageID != null && messageID.length() >= 2) ? messageID.substring(0, 2) : "00";
        String numPart = Integer.toString(totalMessagesSent + 1);
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        messageHash = (idPart + ":" + numPart + ":" + firstWord + lastWord).toUpperCase();
        return messageHash;
    }

    public String sendMessage(int choice) {
        switch (choice) {
            case 1:
                totalMessagesSent++;
                return "Message sent.";
            case 2:
                totalMessagesStored++;
                storeMessage();
                return "Message stored.";
            case 3:
                return "Message disregarded.";
            default:
                return "Invalid choice.";
        }
    }

    public String printMessages() {
        return "\nMessage ID: " + messageID +
               "\t Message Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + message;
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    public int returnTotalStoredMessages() {
        return totalMessagesStored;
    }

    // Store message in JSON format
    public void storeMessage() {
        String jsonMessage = "{\n" +
                "  \"MessageID\": \"" + messageID + "\",\n" +
                "  \"MessageHash\": \"" + messageHash + "\",\n" +
                "  \"Recipient\": \"" + recipient + "\",\n" +
                "  \"Message\": \"" + message + "\"\n" +
                "}\n";

        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(jsonMessage);
            writer.write(System.lineSeparator());
            System.out.println("Message saved to JSON file.");
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    public void showMessageDetails() {
        String details = "Message Details:\n"
                + "------------------------\n"
                + "Message ID: " + getMessageID() + "\n"
                + "Message Hash: " + getMessageHash() + "\n"
                + "Recipient: " + getRecipient() + "\n"
                + "Message: " + getMessage();
        JOptionPane.showMessageDialog(null, details, "Message Sent", JOptionPane.INFORMATION_MESSAGE);
    }

    // Setters and Getters
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public void setMessage(String message) { this.message = message; }
    public void setMessageID(String id) { this.messageID = id; }
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
}
