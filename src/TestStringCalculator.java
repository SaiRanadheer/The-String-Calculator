import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class TestStringCalculator {

    private StringCalculator calculator;

    @BeforeTest
    public void init(){
        calculator = new StringCalculator();
    }

    public void emptyStringReturnsZero(){
        assertEquals(calculator.add(""), 0);
    }

    public void singleValueIsReplied(){
        assertEquals(calculator.add("10"), 10);
    }

    public void singleCommaDelimiter(){
        assertEquals(calculator.add("123,15"), 138);
    }
}
