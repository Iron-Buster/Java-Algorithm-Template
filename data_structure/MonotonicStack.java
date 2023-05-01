package data_structure;

import java.util.Arrays;
import java.util.LinkedList;

public class MonotonicStack {

    //============================单调栈========================

    // 下一个更大的元素
    static void nexGreaterElement(int[] arr) {
        int n = arr.length;
        var st = new LinkedList<Integer>();
        var next = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() > 0 && st.peek() <= arr[i]) {
                st.pop();
            }
            next[i] = st.size() > 0 ? st.peek() : -1;
            st.push(arr[i]);
        }
        System.out.println(Arrays.toString(next));
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2};
        nexGreaterElement(arr);
    }
}
