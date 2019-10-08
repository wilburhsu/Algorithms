package wilburhsu.LeetCode;

public class _66_PlusOne {
    //注意：此方法只适用于+1的情况，不适用+2的情况
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1;i >= 0;i--){
            digits[i]++;//先将最末一位+1
            digits[i] = digits[i] % 10;//判断最末一位是否为9，通过+1后对10取余是否为0判断
            if(digits[i] != 0)
                return digits;//如果最末一位不为9，末位+1后直接返回
        }//如果末位是9，就向前遍历，直到遇到不是9的那一位再返回
        //如果for循环执行完毕还没有返回，说明所有位数均为9，则创建一个长度+1的新数组
        digits = new int[digits.length + 1];
        digits[0] = 1;//把第0个元素初始化为1，其他元素会默认初始化为0
        return digits;
    }
}
