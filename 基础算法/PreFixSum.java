package 基础算法;

public class PreFixSum {

    //==========================一维前缀和===========================================
    // 主要用于查询某一个区间的和，知道区间的左端点和右端点，就可以在O(1)的时间复杂度下求出区间和
    // sum[l:r] => s[r+1] - s[l]
    //==============================================================================

    private int prefixSum(int[] arr, int l, int r) {
        int n = arr.length;
        var sum = new int[n+1];
        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + arr[i];
        }
        return sum[r+1] - sum[l];
    }

    public static void main(String[] args) {
        var pf = new PreFixSum();
        int[] arr = {1, 3, 5, 2, 6};
        int sum = pf.prefixSum(arr, 0, 2);
        System.out.println(sum);
    }
}
