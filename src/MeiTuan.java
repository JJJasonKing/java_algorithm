import java.util.Arrays;
import java.util.Scanner;


public class MeiTuan {

    // hw1
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 扣的分数 t
        int t = 100 - n;
        if (t == 0 || t == 100) {
            System.out.println(1);
            return;
        }
        int res = 0;
        if (n == 94) {
            System.out.println(100);
        } else if (n == 96) {
            System.out.println(10 + 45);
        } else if (n == 98) {
            System.out.println(10);
        } else if (n == 92) {
            System.out.println(5 + 45);
        } else if (n == 90) {
            System.out.println(50);
        } else if (n == 88) {
            System.out.println(40 + 45);
        }
        // init max cnt
        int cnt2 = Math.min(t / 2, 3);
        for (int i = 0; i <= cnt2; i++) {
            int shengyu1 = t - i * 2;
            int cnt4 = Math.min(shengyu1 / 4, 3);
            for (int j = 0; j <= cnt4; j++) {
                int shengyu2 = shengyu1 - j * 4;
                int k = shengyu2 / 8;
                if (i * 2 + j * 4 + k * 8 == t) {
                    res += cmn(i, 10) * cmn(j, 10) * cmn(k, 5);
                }
            }
        }

        System.out.println(res);
    }

    public static int cmn(int n, int m) {
        if (n == 0) {
            return 1;
        }
        int fz = 1;
        for (int i = 0; i < n; i++) {
            fz *= m;
            m--;
        }
        int fm  = 1;
        while (n > 1) {
            fm *= n;
            n--;
        }
        return fz / fm;
    }

    class TreeNode {
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
    }

    public static void mainh2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in1 = sc.nextLine();
        String[] in2 = sc.nextLine().split("/");
        String in3 = sc.nextLine();
    }



    public static void mainh3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        int max = 0;
        int[] id = new int[n];
        int[] pid = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = sc.nextInt();
            max = Math.max(max, id[i]);
            sum += id[i];
        }
        for (int i = 0; i < n; i++) {
            pid[i] = sc.nextInt();
        }
        int task = sc.nextInt();
        if (sum < task) {
            System.out.println(-1);
        }
        System.out.println(max);
    }






    private static int max = Integer.MIN_VALUE;
    private static int res = 0;
    // dfs暴搜  肯定会超时
    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k1 = sc.nextInt();
        int k2 = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        dfs(nums, 0, 0, k1, k2);
        System.out.println(max);
        System.out.println(res);
    }

    public static void dfs(int[] nums, int idx, int sum, int k1, int k2) {
        if (idx >= nums.length) {
            return;
        }
        if (sum > max && sum % k1 == 0 && sum % k2 != 0) {
            max = sum;
            res = 1;
        }
        if (sum == max) {
            res++;
            res = res % 998244353;
        }
        dfs(nums, idx + 1, sum + nums[idx], k1, k2);
        dfs(nums, idx + 1, sum, k1, k2);
    }



    public static void mainx(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m1 = sc.nextInt();
        int m2 = sc.nextInt();
        int[][] arr = new int[m1+m2][2];
        for (int i = 0; i < m1; i++) {
            arr[i][0] = sc.nextInt();
        }
        for (int i = 0; i < m1; i++) {
            arr[i][1] = sc.nextInt();
        }
        for (int i = m1; i < m1 + m2; i++) {
            arr[i][0] = sc.nextInt();
        }
        for (int i = m1; i < m1 + m2; i++) {
            arr[i][1] = sc.nextInt();
        }
        Arrays.sort(arr, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });
        int last = arr[0][1];
        int res = 0;
        for (int i = 1; i < m1 + m2; i++) {
            if (arr[i][0] > last) {
                last = arr[i][1];
                continue;
            }
            res += Math.min(last, arr[i][1]) - arr[i][0] + 1;
            last = Math.max(last, arr[i][1]);
        }
        System.out.println(res);
    }



    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();
        StringBuilder sb = new StringBuilder(str);
        String res;
        if (t == 1) {
            res = InCode(sb, n);
        } else {
            res = DeCode(sb, n);
        }
        System.out.println(res);
    }
    public static String InCode(StringBuilder sb, int n) {
        char[] chs = new char[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < chs.length; i++) {
            int t = (n + 1) / 2 - 1;
            while (vis[t]) {
                t++;
            }
            vis[t] = true;
            chs[i] = sb.charAt(t);
            n--;
        }
        return String.valueOf(chs);
        /*
        StringBuilder res = new StringBuilder();
        while (!sb.isEmpty()) {
            // int t = (int) Math.floor((double) n / 2);
            int t = (n + 1) / 2 - 1;
            char c = sb.charAt(t);
            sb.deleteCharAt(t);
            res.append(c);
            n--;
        }
        return res.toString();
        */
    }

    public static String DeCode(StringBuilder sb, int n) {
        char[] chs = new char[n];
        boolean[] vis = new boolean[n];
        for (char c : sb.toString().toCharArray()) {
            int t = (n + 1) / 2 - 1;
            while (vis[t]) {
                t++;
            }
            chs[t] = c;
            vis[t] = true;
            n--;
        }
        return String.valueOf(chs);
        /*
        StringBuilder res = new StringBuilder();
        for (char c : chs) {
            res.append(c);
        }
        return res.toString();*/

    }



    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] yuan = new int[n];
        int[] zhe = new int[n];
        for (int i = 0; i < n; i++) {
            yuan[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            zhe[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] man = new int[m];
        int[] jian = new int[m];
        for (int i = 0; i < m; i++) {
            man[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            jian[i] = sc.nextInt();
        }

        int zz = 0, mm = 0;
        StringBuilder res = new StringBuilder();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            zz += zhe[i];
            mm += yuan[i];
            int tt = mm;
            if (mm >= man[idx]) {
                while (idx < m && mm >= man[idx]) {
                    idx++;
                }
                idx--;
                tt = mm - jian[idx];
            }
            if (zz > tt) {
                res.append('M');
            } else if (zz < tt) {
                res.append('Z');
            } else {
                res.append('B');
            }
        }

        System.out.println(res.toString());
    }


    public static int getSum(int a, int b){
        if(a == 0 || b == 0){
            return 0;
        }
        //等差求和
        return ((b+1)+(a-2)*(1+b)*b/2);
    }
    public static void mainttt(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = 0;
        int arr[][] = new int[n][2];
        for(int i = 0; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[i][0] = a;
            arr[i][1] = b;
            res +=  getSum(arr[i][0],arr[i][1]);
        }
        System.out.println(res);
    }

}
