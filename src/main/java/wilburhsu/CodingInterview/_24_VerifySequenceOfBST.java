package CodingInterview;

public class _24_VerifySequenceOfBST {
    public boolean verifySequenceOfBST(int[] sequence){
        if(sequence == null || sequence.length <= 0){
            return false;
        }

        if(sequence.length == 1)
            return  true;

        return judge(sequence,0,sequence.length -1);
    }

    private boolean judge(int[] array,int start,int end){
        if(start >= end)
            return true;
        int i = start;
        while(array[i] < array[end])
            i++;

        for(int j = i;j < end;j++)
            if(array[j] < array[end])
                return false;

        return judge(array,start,i-1) && judge(array,i,end-1);
    }

    public static void main(String[] args) {
        int[] arr1 = { 5,7,6,10,9,11,8 };
        int[] arr2 = { 5 };
        int[] arr3 = { 2,3,4,5 };
        int[] arr4 = { 7,4,6,5 };
        _24_VerifySequenceOfBST bst = new _24_VerifySequenceOfBST();
        System.out.println(bst.verifySequenceOfBST(arr1));
        System.out.println(bst.verifySequenceOfBST(arr2));
        System.out.println(bst.verifySequenceOfBST(arr3));
        System.out.println(bst.verifySequenceOfBST(arr4));
    }
}
