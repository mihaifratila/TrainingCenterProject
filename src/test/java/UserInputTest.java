import org.example.UserInputHandler;
import org.junit.Assert;

import org.junit.Test;

public class UserInputTest {

    private final UserInputHandler userInput = new UserInputHandler();

    @Test
    public void userCanInputStudentName() {

        Assert.assertEquals("Mihai", userInput.getStudentName());
    }
}
