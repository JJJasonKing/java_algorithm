import TreeNode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FreeWheel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }

    public TreeNode reConstructBST (int[] preSlice) {
        // write code here
        return dfs(preSlice, 0, preSlice.length - 1);
    }
    public TreeNode dfs(int[] pre, int left, int right) {
        if (left > right || right >= pre.length || left < 0) return null;
        TreeNode root = new TreeNode(pre[left]);
        int idx = left;
        for (; idx <= right; idx++) {
            if (pre[idx] > pre[left]) break;
        }
        // left
        root.left = dfs(pre, left + 1, idx - 1);
        // right
        root.right = dfs(pre, idx, right);
        return root;
    }


    public int[] antiSpiralOrder (int[][] matrix) {
        // write code here
        if (matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        // m行n列
        int m = matrix.length, n = matrix[0].length;
        /*
        // 水平翻转
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[m - 1 - i][j];
                matrix[m - 1 - i][j] = t;
            }
        }
        // 对角线翻转
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        */
        // 从左下角开始
        int[] res = new int[m * n];
        // int l = 0, r = n - 1, u = 0, d = m - 1;
        int l = 0, r = n - 1, u = 0, d = m - 1;
        int idx = 0;
        /*
        while (true) {
            // 左
            for (int i = u; i <= d; i++) res[idx++] = matrix[i][l];
            if (++l > r) break;
            // 底
            for (int i = l; i <= r; i++) res[idx++] = matrix[d][i];
            if (--d < u) break;
            // 右
            for (int i = d; i >= u; i--) res[idx++] = matrix[i][r];
            if (--r < l) break;
            // 上
            for (int i = r; i >= l; i--) res[idx++] = matrix[u][i];
            if (++u > d) break;
        }*/
        while (true) {
            // 下
            for (int i = l; i <= r; i++) res[idx++] = matrix[d][i];
            if (--d < u) break;
            // 右
            for (int i = d; i >= u; i--) res[idx++] = matrix[i][r];
            if (--r < l) break;
            // 上
            for (int i = r; i >= l; i--) res[idx++] = matrix[u][i];
            if (++u > d) break;
            // 左
            for (int i = u; i <= d; i++) res[idx++] = matrix[i][l];
            if (++l > r) break;
        }
        return res;
    }


    public int longestGeometricSeqLength (ArrayList<Integer> nums) {
        // write code here
        if (nums.isEmpty() || nums.size() <= 1) {
            return 0;
        }
        int res = 0;
        int n = nums.size();
        // q dp[i][q]
        int[][] dp = new int[n][1002];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums.get(i) % nums.get(j) == 0 || nums.get(j) % nums.get(i) == 0) {
                    int d = nums.get(i) % nums.get(j) == 0 ? nums.get(i) % nums.get(j) : nums.get(j) % nums.get(i);
                    dp[i][d] = Math.max(dp[i][d], dp[j][d] + 1);
                    res = Math.max(res, dp[i][d]);
                }
            }
        }
        return res;
    }

}

// [[1,2,3,4,5,6],[4,5,6,5,6,7],[7,4,4,8,9,10]]