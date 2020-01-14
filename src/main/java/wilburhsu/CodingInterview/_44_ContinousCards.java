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
                continue;//例如输入{0,0,1,2,3}，如果不加continue，就会把0==0当成对子处理，直接返回false;
            }
            if (numbers[i] == numbers[i + 1])
                return false;
            countOfGap += numbers[i + 1] - numbers[i] - 1;
        }

        if (countOfZero >= countOfGap)
            return true;
        return false;
    }

    public boolean isContinuous2(int [] numbers) {
        if(numbers.length != 5)
            return false;

        int[] count = new int[14];
        int max = -1;
        int min = 14;
        for(int number:numbers){
            if(number == 0)
                continue;
            if(++count[number] > 1)
                return false;
            max = Math.max(number,max);
            min = Math.min(number,min);
        }
        return max - min < 5;
    }
}
