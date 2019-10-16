package wilburhsu.LeetCode;

public class _88_MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;

        for(int p = m + n - 1;p >= 0;p--){
            //注意：要把 nums1 和 nums2 归并完成的逻辑写在前面，否则会出现数组下标越界异常
            //如果nums1已经遍历完而nums2未遍历完，nums2还未遍历的长度为p2+1
            //将nums2未遍历的元素直接复制到nums1的头部
            if(p1 < 0){
                System.arraycopy(nums2,0,nums1,0,p2 + 1);
                break;
            //如果nums2遍历完而nums1未遍历完，此时就是合并完成后的结果
            }else if(p2 < 0){
                break;
            }else if(nums1[p1] < nums2[p2]){
                nums1[p] = nums2[p2];
                p2--;
            }else{
                nums1[p] = nums1[p1];
                p1--;
            }
        }
    }

    //简洁写法
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n -1;

        while(p1 >= 0 && p2 >= 0){
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        System.arraycopy(nums2,0,nums1,0,p2 + 1);
    }
}
