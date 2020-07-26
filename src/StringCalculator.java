import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || isEmpty(numbers)) {
            return 0;
        } else {
            boolean delimiterChanged = numbers.startsWith("//");
            String delimiter = ",";
            if(delimiterChanged){
                delimiter = numbers.substring(2, numbers.indexOf("\n"));
                numbers = numbers.substring(numbers.indexOf("\n")+1);
            }
            numbers = numbers.replace("\n", delimiter);
            String[] numberValues = numbers.split(delimiter);
            if (numberValues.length == 1) {
                int number = stringToInt(numbers);
                if(number > 0) {
                    return number;
                } else {
                    throw new IllegalArgumentException("negatives not allowed " + number);
                }
            } else {
                if (numberValues.length == 2) {
                    int firstNumber = stringToInt(numberValues[0]);
                    int secondNumber = stringToInt(numberValues[1]);
                    if(firstNumber > 0 && secondNumber > 0){
                        return firstNumber + secondNumber;
                    }else if (firstNumber < 0){
                        if (secondNumber < 0){
                            throw new IllegalArgumentException("negatives not allowed " + firstNumber + " " + secondNumber);
                        } else {
                            throw new IllegalArgumentException("negatives not allowed " + firstNumber);
                        }
                    } else if(secondNumber < 0){
                        throw new IllegalArgumentException("negatives not allowed " + secondNumber);
                    }
                } else {
                    int totalSum = 0;
                    int number;
                    String negativeNumbersStr = "";
                    for (String numberValue : numberValues) {
                        number = stringToInt(numberValue);
                        if (number < 0){
                            negativeNumbersStr = negativeNumbersStr.concat(String.valueOf(number)).concat(" ");
                            continue;
                        }
                        totalSum += number;
                    }
                    negativeNumbersStr = negativeNumbersStr.trim();
                    if(negativeNumbersStr.isEmpty()) {
                        return totalSum;
                    } else{
                        throw new IllegalArgumentException("negatives not allowed " + negativeNumbersStr);
                    }
                }
            }
        }
        return -1;
    }

    private boolean isEmpty(String input) {
        return input.isEmpty();
    }

    private int stringToInt(String input) {
        return Integer.parseInt(input);
    }
}
