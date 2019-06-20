package wilburhsu.CodingInterview;

/**
 * 面试题51：数组中重复的数字
 */

public class _51_DuplicationInArray {
    public static int duplication;

    public static boolean duplicate(int[] numbers){
        if(numbers == null || numbers.length<0)
            return false;

        for(int i=0;i<numbers.length;i++){
            if(numbers[i]<0 || numbers[i]>=numbers.length)
                return false;
        }

        for(int i=0;i<numbers.length;i++){
            while(numbers[i] != i){
                if(numbers[i] == numbers[numbers[i]]){
                    duplication = numbers[i];
                    System.out.println(numbers[i]);
                    return true;
                }
                int tmp = numbers[i];
                numbers[i] = numbers[tmp];
                numbers[tmp] = tmp;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,0,2,5,3};
        boolean bool = duplicate(arr);
        System.out.println(bool);

    }

}
