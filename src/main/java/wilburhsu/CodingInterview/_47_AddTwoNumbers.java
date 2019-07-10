package wilburhsu.CodingInterview;

public class _47_AddTwoNumbers {
    public int Add(int num1,int num2){
        while(num2 != 0){
            int sum = num1 ^ num2;
            int carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

    public int Add2(int num1,int num2){
        return Integer.sum(num1, num2);
    }
}
