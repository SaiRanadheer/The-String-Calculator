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
                return stringToInt(numbers);
            } else {
                if (numberValues.length == 2) {
                    return stringToInt(numberValues[0]) + stringToInt(numberValues[1]);
                } else {
                    int totalSum = 0;
                    for (String numberValue : numberValues) {
                        totalSum += stringToInt(numberValue);
                    }
                    return totalSum;
                }
            }
        }
    }

    private boolean isEmpty(String input) {
        return input.isEmpty();
    }

    private int stringToInt(String input) {
        return Integer.parseInt(input);
    }
}
