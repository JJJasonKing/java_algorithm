import java.util.Scanner;


public class MeiTuan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
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



    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m1 = sc.nextInt();
        int m2 = sc.nextInt();
        int[] arr11 = new int[m1];
        int[] arr12 = new int[m1];
        int[] arr21 = new int[m2];
        int[] arr22 = new int[m2];
        for (int i = 0; i < m1; i++) {
            arr11[i] = sc.nextInt();
        }
        for (int i = 0; i < m1; i++) {
            arr12[i] = sc.nextInt();
        }
        for (int i = 0; i < m2; i++) {
            arr21[i] = sc.nextInt();
        }
        for (int i = 0; i < m2; i++) {
            arr22[i] = sc.nextInt();
        }

        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < m1; i++) {
            for (int l = arr11[i]; l <= arr12[i]; l++) {
                vis[l] = true;
            }
        }
        int res = 0;
        for (int i = 0; i < m2; i++) {
            for (int l = arr21[i]; l <= arr22[i]; l++) {
                if (vis[l]) {
                    res++;
                }
            }
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



class MNode {
    int val;
    MNode left;
    MNode right;
    MNode() {}
    MNode(int val) { this.val = val; }
    MNode(int val, MNode left, MNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

