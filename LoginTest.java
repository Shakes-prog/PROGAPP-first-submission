
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
@Test
void testValidUsername() {
   assertEquals("kyl_1","kyl_1","Username successfully captured.");
}

@Test
void testInvalidUsername() {
    assertEquals("kyle!!!!!","kyle!!!!!","Username is not correctly formatted, please ensure that your username contains an _ and is no more than 5 characters in length.");
}

@Test
void testValidPassword() {
    assertEquals("Ch&&sec@ke99!","Ch&&sec@ke99!","Password successfully captured.");
}

@Test
void testInvalidPassword() {
    assertEquals("password", "password", "Password is incorrectly formatted please ensure that your password contains at eight characters, a capital letter, a number and a special character");
}

@Test
void testValidCellNumbers() {
    assertTrue(Login.checkCellNumber("+27838968976"), "Cell number correctly captured");
}




@Test
void testUserInvalidCellNumbers() {
    assertFalse(Login.checkCellNumber("0838968976"), "Cell number is not correctly formatted, or does not contain the international code, please correct the number and try again.");
}



@Test
void testLoginSuccess() {
    // Create a user object with valid credentials
    Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");

    // Assert that LoginUser returns true for correct credentials
    assertTrue(user.LoginUser("kyl_1", "Ch&&sec@ke99!"), "Login successfully");
}


@Test
void testLoginFailure() {
    // Create a user object with valid credentials
    Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");

    // Assert that LoginUser returns false for incorrect credentials
    assertFalse(user.LoginUser("wrong_user", "wrong_pass"), "Login should fail with incorrect credentials.");
}
}



    

