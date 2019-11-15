package wilburhsu.LeetCode;

public class _11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        if(height.length == 0)
            return 0;
        int i = 0,j = height.length - 1;
        int maxArea = Math.min(height[0],height[height.length - 1]) * (height.length - 1);
        int tmpArea = 0;
        while(i < j){
            if(height[i] <= height[j])
                i++;
            else
                j--;
            tmpArea = Math.min(height[i],height[j])*(j-i);
            if(tmpArea > maxArea)
                maxArea = tmpArea;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        _11_ContainerWithMostWater container = new _11_ContainerWithMostWater();
        int[] height = {1,1};
        System.out.println(container.maxArea(height));
    }

}
