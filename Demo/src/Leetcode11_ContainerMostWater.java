public class Leetcode11_ContainerMostWater {

    public int maxArea1(int[] height){
        int n=height.length;
        if(n<2)
            return 0;

        int mostwater=0;
        for (int i = 0; i <n ; i++) {
            for (int j = i+1; j <n ; j++) {
                int water=(height[j]<height[i])?height[j]:height[i];
                water=water*(j-i);
                if(water>mostwater) {
                    mostwater = water;
                }
            }
        }
        return mostwater;
    }

    public int maxArea(int[] height) {
        int n = height.length;
        if (n < 2)
            return 0;

        int a=0,b=n-1;
        int mostwater=0;
        while(a<b){
            int water=(height[b]<height[a])?height[b]:height[a];
            water=water*(b-a);
            if(water>mostwater) {
                mostwater = water;
            }
            if(height[a]<height[b]){  //左边的高度较小，从左边开始扫描找到第一个最小值
                int i=a+1;
                while(i<n && height[a]>=height[i]){
                    i++;
                }
                a=i;
            }else{
                int j=b-1;
                while(j>=0 && height[b]>=height[j]){
                    j--;
                }
                b=j;
            }
        }
        return mostwater;
    }

    public static void main(String[] args) {
        Leetcode11_ContainerMostWater leetcode11_containerMostWater=new Leetcode11_ContainerMostWater();
        int[] a={1,8,6,2,5,4,8,3,7};
        System.out.println(leetcode11_containerMostWater.maxArea(a));
    }
}
