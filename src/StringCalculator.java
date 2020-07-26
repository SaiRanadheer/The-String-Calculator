import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || isEmpty(numbers)) {
            return 0;
        } else {

            long multipleDelimitersCount = numbers.chars().filter(ch -> ch == '[').count();
            boolean biggerDelimiter = numbers.startsWith("//[");
            boolean delimiterChanged = numbers.startsWith("//");

            String delimiter = ",";
            if(multipleDelimitersCount > 1){
                int startIndex = numbers.indexOf("[") + 1;
                int endIndex = numbers.indexOf("]");
                List<String> delimiters = new ArrayList<>();
                for(int i = 0; i < multipleDelimitersCount; i++){
                    delimiters.add(numbers.substring(startIndex, endIndex));
                    startIndex = numbers.indexOf("[", startIndex) + 1;
                    endIndex = numbers.indexOf("]", startIndex);
                }
                for(int i = 0; i < multipleDelimitersCount; i++){
                    numbers = numbers.replace(delimiters.get(i), delimiter);
                }
                numbers = numbers.substring(numbers.indexOf("\n")+1);
            } else if(biggerDelimiter){
                numbers = numbers.replace("*", ",");
                numbers = numbers.replace("+", ",");
                numbers = numbers.replace("^", ",");
                delimiter = numbers.substring(3, numbers.indexOf("\n")-1);
                numbers = numbers.substring(numbers.indexOf("\n")+1);
            } else if(delimiterChanged){
                numbers = numbers.replace("*", ",");
                numbers = numbers.replace("+", ",");
                numbers = numbers.replace("^", ",");
                delimiter = numbers.substring(2, numbers.indexOf("\n"));
                numbers = numbers.substring(numbers.indexOf("\n")+1);
            }
            numbers = numbers.replace("\n", delimiter);
            String[] numberValues = numbers.split(delimiter);
            if (numberValues.length == 1) {
                int number = stringToInt(numbers);
                if(number > 0) {
                    if(number < 1000)
                        return number;
                    else
                        return 0;
                } else {
                    throw new IllegalArgumentException("negatives not allowed " + number);
                }
            } else {
                if (numberValues.length == 2) {
                    int firstNumber = stringToInt(numberValues[0]);
                    int secondNumber = stringToInt(numberValues[1]);
                    if(firstNumber > 0 && secondNumber > 0){
                        if (firstNumber > 1000){
                            return secondNumber;
                        } else if(secondNumber > 1000){
                            return firstNumber;
                        }
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
                        if(number < 1000) {
                            totalSum += number;
                        }
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
