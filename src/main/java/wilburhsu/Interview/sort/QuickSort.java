package wilburhsu.Interview.sort;

public class QuickSort {

    public void quickSort(int[] nums,int N){
        sort(nums,0,N-1);
    }

    private void sort(int[] nums,int left,int right){
        if(right <= left)
            return;
        int j = partition(nums,left,right);
        sort(nums,left,j-1);
        sort(nums,j+1,right);
    }

    private int partition(int[] nums,int left,int right){
        /*选取最左端元素为主元，在for循环中移动i和j之前，i要自增+1，j要自减-1，所以扫描元素的范围为
         *[left+1,right]，所以i的起始值为left，j的起始值为right+1
         */
        int i = left,j = right + 1;
        int pivot = nums[left];
//        int pivot = median3(nums,left,right);
        for(;;) {
            while (nums[++i] <= pivot)
                if(i == right)
                    break;
            while (nums[--j] >= pivot)
                if(j == left)
                    break;
            if(i >= j)
                break;
            swap(nums,i,j);
        }
        /* 当i和j相遇时，必然有j<=i，且nums[j]<=nums[i]，
         * j及其左边的元素（除了最左端的left）全都小于等于pivot，
         * i及其右边的元素全都大于等于pivot，
         * 所以将left与j交换而不是与i交换
         */
        swap(nums,left,j);
        return j;
    }

    private void swap(int[] nums,int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //三取样选主元
    private int median3(int[] nums, int left, int right){
        int center = (left + right)/2;
        if(nums[left] > nums[center])
            swap(nums, left, center);
        if(nums[left] > nums[right])
            swap(nums, left, right);
        if(nums[center] > nums[right])
            swap(nums, center, right);
        swap(nums, center, left);
        return nums[left];
    }

    public static boolean isSorted(int[] a){
        for (int i = 1;i<a.length;i++)
            if(a[i] < a[i-1])
                return false;
        return true;
    }


    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = {39,878,466,9,5,8,7,40,545,4,9,65765,5478,313};
        quickSort.quickSort(nums,nums.length);
        for(int num:nums){
            System.out.print(num + ",");
        }
        if(isSorted(nums)){
            System.out.println("已排序");
        }else {
            System.out.println("排序不成功");
        }

    }
}
