import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.FileAssert.fail;

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

    public void unlimitedNumbersCommaDelimiter(){
        assertEquals(calculator.add("123,15,42,66"), 246);
    }

    public void numbersWithLineSeparatorsCommaDelimiter(){
        assertEquals(calculator.add("1\n2,3"), 6);
    }

    public void differentDelimitersSupported(){
        assertEquals(calculator.add("//;\n1;2"), 3);
    }

    public void negativeNumbersNotSupported(){
        try{
            calculator.add("1,2,-3,-7,8");

        } catch (Exception e){
            assertEquals(e.getMessage(), "negatives not allowed -3 -7");
        }
    }

    public void numberAboveThreshold(){
        assertEquals(calculator.add("//;\n2;1001"), 2);
    }

    public void biggerDelimiter(){
        assertEquals(calculator.add("//[***]\n1***2***3"), 6);
    }

    public void multipleDelimiters(){
        assertEquals(calculator.add("//[*][%]\n1*2%3"), 6);
    }
}
