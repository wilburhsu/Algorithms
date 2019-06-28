package wilburhsu.CodingInterview;

public class _40_NumbersAppearOnce {
    public void FindNumsAppearOnce(int [] array,int[] num1 , int[] num2) {
        if(array == null || array.length < 2)
            return;

        //step1：所有元素依次异或
        int exclusiveORResult = 0;
        for(int i = 0; i < array.length; i++)
            exclusiveORResult ^= array[i];

        //step2：找到异或结果的第一个为1的位置index
        int index = findFirst1(exclusiveORResult);

        //step3：将数组中的元素按照第index个元素是否为1依次异或
        for(int i = 0; i < array.length; i++){
            if(isBit1(array[i],index))
                num1[0] ^= array[i];
            else
                num2[0] ^= array[i];
        }

        //steo4：对两子数组分别取异或得结果
    }

    public int findFirst1(int exclusiveORResult){
        int index = 0;
        while ((exclusiveORResult & 1) == 0 && index < 32){
            exclusiveORResult = exclusiveORResult >> 1;
            index++;
        }
        return index;
    }

    public boolean isBit1(int num ,int index){
        num = num >> index;
        return (num & 1) == 1;
    }
}