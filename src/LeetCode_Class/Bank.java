package LeetCode_Class;

import TreeNode.TreeNode;

import javax.swing.*;
import java.util.*;

public class Bank {
    long[] balance;
    public Bank(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > balance.length || account2 > balance.length || balance[account1-1]<money) {
            return false;
        }
        balance[account1-1] -= money;
        balance[account2-1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if(account > balance.length) {
            return false;
        }
        balance[account-1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if(account > balance.length || balance[account-1] < money) {
            return false;
        }
        balance[account-1] -= money;
        return true;
    }



}

// ------------------
class solution_1 {
    // 面试题 17.18. 最短超串  短的元素均不相同
    // 滑动窗口 like 76.最小覆盖字串
    public int[] shortestSeq(int[] big, int[] small) {
        int[] res = new int[2];
        return res;
    }

    public String toGoatLatin(String sentence) {
        Set<Character> set = new HashSet<>();
        Collections.addAll(set,'a','e','i','o','u','A','E','I','O','U');
        String[] tmp = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            String s = tmp[i];
            if (set.contains(s.charAt(0))) {
                res.append(s);
            } else {
                res.append(s.substring(1)).append(s.charAt(0));
            }
            res.append("ma");
            for (int j = 0; j < i + 1; j++) {
                res.append("a");
            }
            res.append(" ");
        }
        return res.toString().trim();
    }


    // 606. 根据二叉树创建字符串
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        res.append(root.val);
        if (root.left == null && root.right == null) {
            return res.toString();
        }
        if (root.left != null) {
            res.append("(" + tree2str(root.left) + ")");
        } else {
            res.append("()");
        }
        if (root.right != null) {
            res.append("(" + tree2str(root.right) + ")");
        }
        return res.toString();
    }


    // 1985. 找出数组中的第 K 大整数
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (String a, String b) -> {
            if (a.length() == b.length()) {
                for (int i = 0; i < a.length(); i++) {
                    if (a.charAt(i) != b.charAt(i)) {
                        return a.charAt(i) - b.charAt(i);
                    }
                }
            }
            return a.length() - b.length();
        });
        return nums[nums.length - k];
    }


    // 985. 查询后的偶数和
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int sum = 0;
        for (int t : nums) {
            if (t % 2 == 0) {
                sum += t;
            }
        }
        int n  = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][1], val = queries[i][0];
            if (nums[idx] % 2 == 0) {
                sum -= nums[idx];
            }
            nums[idx] += val;
            if (nums[idx] % 2 == 0) {
                sum += nums[idx];
            }
            res[i] = sum;
        }
        return res;
    }


    // 30. 串联所有单词的子串
    // 多起点 && 步长>1 的滑动窗口 注意"长度相同"
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int len = s.length();
        int n = words.length, m = words[0].length();
        int w_len = m * n; // window len
        Map<String, Integer> need = new HashMap<>();
        for (String w : words) {
            need.put(w, need.getOrDefault(w, 0) + 1);
        }
        // 直接滑动窗口是暴力法，因为滑动窗口复杂度应该是线性的，即窗口内的计算应为O(1)
        for (int i = 0; i < m; i++) { // 遍历m长度内的所有起点 相当于所有可能
            int l = i, r = i;
            Map<String, Integer> window = new HashMap<>();
            while (r + m <= len) {
                String rStr = s.substring(r, r + m);
                r += m;
                if (!need.containsKey(rStr)) {
                    window.clear();
                    l = r;
                    continue;
                }
                window.put(rStr, window.getOrDefault(rStr, 0) + 1);
                while (window.get(rStr) > need.get(rStr)) {
                    String lStr = s.substring(l, l + m);
                    window.put(lStr, window.get(lStr) - 1 );
                    l += m;
                }
                if (r - l == w_len) { // window长度==need 且 window所有元素数量<=need
                    res.add(l); // window == need
                }
            }
        }
        return res;
    }


    // 796. 旋转字符串
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }


    // 999. 可以被一步捕获的棋子数
    public int numRookCaptures(char[][] board) {
        int res = 0, m = board.length, n = board[0].length;
        outer: for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'R') {
                    for (int k = i; k >= 0 && board[k][j] != 'B'; k--) {
                        if (board[k][j] == 'p') {
                            res++;
                            break;
                        }
                    }
                    for (int k = i; k < m && board[k][j] != 'B'; k++) {
                        if (board[k][j] == 'p') {
                            res++;
                            break;
                        }
                    }
                    for (int k = j; k >= 0 && board[i][k] != 'B'; k--) {
                        if (board[i][k] == 'p') {
                            res++;
                            break;
                        }
                    }
                    for (int k = j; k < n && board[i][k] != 'B'; k++) {
                        if (board[i][k] == 'p') {
                            res++;
                            break;
                        }
                    }
                    break outer;
                }
            }
        }
        return res;
    }


    // 1071. 字符串的最大公因子  like 796. 旋转字符串
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    // 面试题 08.14. 布尔运算
    // 区间dp
    public int countEval(String s, int result) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return Integer.parseInt(s) == result ? 1 : 0;
        }
        char[] chs = s.toCharArray();
        // dp数组[i][j][0/1]是0或1的count
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i += 2) {
            dp[i][i][chs[i]-'0'] = 1;
        }
        // [i][j] 从i到j 注意遍历顺序
        for (int j = 2; j < n; j += 2) {
            for (int i = j - 2; i >= 0; i -= 2) {
                for (int k = i + 1; k < j; k++) {
                    if (chs[k] == '&') {
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0] + dp[i][k - 1][0] * dp[k + 1][j][1] + dp[i][k - 1][1] * dp[k + 1][j][0];
                        dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][1];
                    } else if (chs[k] == '|') {
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0];
                        dp[i][j][1] += dp[i][k - 1][0] * dp[k + 1][j][1] + dp[i][k - 1][1] * dp[k + 1][j][0] + dp[i][k - 1][1] * dp[k + 1][j][1];
                    } else {
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0] + dp[i][k - 1][1] * dp[k + 1][j][1];
                        dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][0] + dp[i][k - 1][0] * dp[k + 1][j][1];
                    }
                }
            }
        }
        return dp[0][n-1][result];
    }





    static class LinkNode {
        LinkNode next;
        int val;
        public LinkNode(int v) {
            val = v;
        }
    }


    public static void mainn(String[] args) {
        LinkNode head = new LinkNode(0);
        int[] tmp = new int[]{1,2,3,4,5};
        LinkNode cur = head;
        for (int i = 0; i < tmp.length; i++) {
            cur.next = new LinkNode(tmp[i]);
            cur = cur.next;
        }
        cur.next = head.next.next;
        System.out.println(getLen(head));
    }

    public static int getLen(LinkNode head) {
        LinkNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                break;
            }
        }
        // 无环
        if (fast == null || fast.next == null) {
            return 0;
        }
        int len = 1;
        fast = fast.next;
        while (slow != fast) {
            fast = fast.next;
            len++;
        }
        return len;
    }

    public static void mainali1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Set<String> set = new HashSet<>();
        while (n-- > 0) {
            String cur = sc.nextLine();
            if (cur.length() < 6 || cur.length() > 12) {
                System.out.println("illegal length");
            } else if (!check(cur)) {
                System.out.println("illegal charactor");
            } else if (set.contains(cur)) {
                System.out.println("acount existed");
            } else {
                System.out.println("registration complete");
                set.add(cur);
            }
        }
    }

    public static boolean check(String s) {
        for (char c : s.toCharArray()) {
            if (!(Character.isUpperCase(c) || Character.isLowerCase(c))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            int res = 0;
            int[] arr = new int[5];
            for (int i = 0; i < 5; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            int t = arr[1];
            res += t;
            arr[1] = 0;
            for (int i = 2; i < 5; i++) {
                arr[i] -= t;
            }
            Arrays.sort(arr);
            res += arr[1];
            System.out.println(res);
        }
    }

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (; j < 3; j++) {
                arr[i][i] = sc.nextInt();
            }
        }
        System.out.println(n - 2);
    }


}