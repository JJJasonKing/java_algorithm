import java.util.*;

public class paypal {
    
    public static void main(String[] args) {
        // int [] test = new int[]{1,2,3,0,2};
        // System.out.println(getMax(test));
        // Integer[] test = new Integer[]{1,1,2};
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        sc.nextLine();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 有序map存储数据
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            if (key == 0) continue;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        // 计算每个元素的前k个数值和 存在dp中
        int pre_sum = 0;
        int len = map.entrySet().size();
        int[][] dp = new int[len][2];
        int i = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry iter : map.entrySet()) {
            int key = (int) iter.getKey(), val = (int) iter.getValue();
            pre_sum += val;
            queue.offer(key);
            while (queue.peek() < key - k) {
                pre_sum -= map.get(queue.poll());
            }
            dp[i][0] = key;
            dp[i][1] = pre_sum;
            i++;
        }
        int res = 0;
        // 再对dp进行二分查找
        for (int j = 0; j < len; j++) {
            int l = 0, r = j - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (dp[mid][0] < dp[j][0] - k) l = mid + 1;
                else r = mid - 1;
            }
            if (l > -1) {
                res = Math.max(res, dp[i][1] + dp[l][1]);
                if (j > 1) dp[i][1] = Math.max(dp[i][1], dp[i-1][1]);
            }
        }
        System.out.println(res);
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
}
