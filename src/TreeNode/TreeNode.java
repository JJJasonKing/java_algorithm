package TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static void main(String[] args) {
        // int [] test = new int[]{1,2,3,0,2};
        // System.out.println(getMax(test));
        Integer[] test = new Integer[]{1,1,2};
        permuteUnique(test);
    }


    public static int getMax(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int n = prices.length;
        // 第i天 3个状态: 0 buy, 1 cooldown / , 2 sell
        int[][] dp = new int[n][3];
        // init
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            // dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + prices[i]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }


    public static List<List<Integer>> permuteUnique(Integer[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, nums.length - 1, res);
        return res;
    }

    public static void dfs(Integer[] nums, int l, int r, List<List<Integer>> res) {
        if (l == r) {
            res.add(Arrays.asList(nums));
        }
        for (int i = l; i <= r; i++) {
            if (i != l && nums[i] == nums[l]) continue;
            swap(nums, i, l);
            dfs(nums, l + 1, r, res);
            swap(nums, i, l);
        }
    }
    public static void swap(Integer[] nums, int l, int r) {
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }

    
}




