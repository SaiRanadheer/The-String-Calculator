public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || isEmpty(numbers)) {
            return 0;
        } else {
            String[] numberValues = numbers.split(",");
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
