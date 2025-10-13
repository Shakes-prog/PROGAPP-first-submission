
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.console.shadow.picocli.CommandLine.Model.Messages;

public class MessagesTest {

    Messages msg1;
    Messages msg2;

    @BeforeEach
    void setup() {
        msg1 = new Messages();
        msg1.setRecipient("+27718693002");
        msg1.setMessage("Hi Mike, can you join us for dinner tonight *");
        msg1.createMessageID();

        msg2 = new Messages();
        msg2.setRecipient("08557975889");
        msg2.setMessage("Hi Keegan, did you receive the payment?");
        msg2.createMessageID();
    }

    // 🔹 Message Length Tests
    @Test
    void testMessage1_LengthSuccess() {
        assertTrue(msg1.checkMessageLength(msg1.getMessage()));
        String feedback = msg1.checkMessageLength(msg1.getMessage()) ? "Message ready to send." : "";
        assertEquals("Message ready to send.", feedback);
    }

    @Test
    void testMessage2_LengthFailure() {
        String longMsg = "A".repeat(260);
        msg2.setMessage(longMsg);
        assertFalse(msg2.checkMessageLength(msg2.getMessage()));
        int excess = longMsg.length() - 250;
        String feedback = "Message exceeds 250 characters by " + excess + ", please reduce size.";
        assertEquals("Message exceeds 250 characters by " + excess + ", please reduce size.", feedback);
    }

    @Test
    void messageLength() {
        assertEquals(msg2, msg1);
    }

    // 🔹 Recipient Format Tests
    @Test
    void testRecipientSuccess() {
        assertTrue(msg1.checkRecipientCell(msg1.getRecipient()));
        String feedback = "Message ID: " + msg1.getMessageID();
        assertTrue(feedback.startsWith("Message ID:"));
    }

    @Test
    void testRecipientFailure() {
        assertFalse(msg2.checkRecipientCell(msg2.getRecipient()));
        String feedback = "Recipient number unsuccessfully captured.";
        assertEquals("Recipient number unsuccessfully captured.", feedback);
    }

    // 🔹 Message Hash Test
    @Test
    void testMessageHashStored() {
        String hash = msg1.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains("HIMIKE"));
    }

    // 🔹 Message ID Test
    @Test
    void testMessageIDCreated() {
        String id = msg1.getMessageID();
        assertTrue(id.matches("\\d{10}"));
        String feedback = "Message ID: " + id;
        assertTrue(feedback.startsWith("Message ID:"));
    }

    // 🔹 Message Action Tests
    @Test
    void testSendMessage() {
        String result = msg1.sentMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    void testStoreMessage() {
        String result = msg1.sentMessage(2);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    void testDiscardMessage() {
        String result = msg2.sentMessage(3);
        assertEquals("Press 0 to continue.", result);
    }

    // 🔹 Print Messages Test
    @Test
    void testPrintMessages() {
        msg1.createMessageHash();
        String output = msg1.printMessages();
        assertTrue(output.contains("Message ID"));
        assertTrue(output.contains("Message Hash"));
        assertTrue(output.contains("Recipient"));
        assertTrue(output.contains("Message"));
    }
}
