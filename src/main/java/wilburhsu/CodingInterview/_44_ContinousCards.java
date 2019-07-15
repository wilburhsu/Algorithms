package wilburhsu.CodingInterview;

import java.util.Arrays;
public class _44_ContinousCards {
    public boolean isContinuous(int[] numbers) {
        if (numbers.length != 5)
            return false;
        int countOfZero = 0;
        int countOfGap = 0;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                countOfZero++;
                continue;
            }
            if (numbers[i] == numbers[i + 1])
                return false;
            countOfGap += numbers[i + 1] - numbers[i] - 1;
        }

        if (countOfZero >= countOfGap)
            return true;
        return false;
    }
}
