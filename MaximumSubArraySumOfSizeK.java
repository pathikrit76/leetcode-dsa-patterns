public class MaximumSubArraySumOfSizeK {
    public static void main(String[] args) {
        System.out.println(maxSubarraySum(new int[] {1, 4, 2, 10, 23, 3, 1, 0, 20}, 4));
    }
    public static int maxSubarraySum(int[] arr, int k) {
        // Code here
        int[] pre = new int[arr.length];
        pre[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            pre[i] = pre[i-1] + arr[i];
        }
        int left = 0;
        int right = k;
        int res = 0;
        while(right<=arr.length){
            if(left == 0){
                res = Math.max(res,pre[right-1]);
            } else{
                res = Math.max(res,pre[right-1]-pre[left-1]);
            }
            left++;
            right++;
        }
        return res;
    }
}
