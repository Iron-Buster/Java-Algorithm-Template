package 基础算法;

public class BinarySearch {

    //====================二分查找，二分答案=============================
    // 二分答案主要是对答案进行二分，适合那种具有二段性质的问题。
    // 时间复杂度logN
    //================================================================

    private boolean check(int mid) {
        // 检查mid是否满足某种性质 一般用于二分答案
        // 假如mid满足 那么 [mid+1, r]也满足
        // 假如mid不满足 那么 [l, mid-1]也不满足
        return true;
    }
    // 模板1 二分最大值 最大值最小
    // 区间[l, r] 被划分为[l, mid] 和 [mid+1, r]时使用
    private int bSearch1(int l, int r) {
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    // 模板2 二分最小值 最小值最大
    // 区间[l, r] 被划分为[l, mid-1] 和 [mid, r]时使用
    private int bSearch2(int l, int r) {
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {

    }
}
