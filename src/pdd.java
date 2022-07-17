import java.util.*;

public class pdd {
    public static void main(String[] args) {
        pdd3();
    }

    public static void pdd3() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        // t 组
        while (t-- > 0) {
            int n = sc.nextInt();
            sc.nextLine();
            Set<String> set = new HashSet<>();
            boolean f = false;
            StringBuilder sb = new StringBuilder();
            while (n-- > 0) {
                String cur = sc.nextLine();
                sb.append(cur);
                if (judge(sb.toString())) {
                    f = true;
                }
                if (judge(cur)) {
                    f = true;
                }
                if (!f) {
                    String rev = new StringBuilder(cur).reverse().toString();
                    if (set.contains(rev)) {
                        f = true;
                    }
                    set.add(rev);
                    set.add(rev.substring(1));
                }
            }
            if (f) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean judge(String cur) {
        int l = 0, r = cur.length() - 1;
        while (l < r) {
            if (cur.charAt(l) != cur.charAt(r)) {
                return false;
            }
            l++;r--;
        }
        return true;
    }



    public static void pdd2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tmps = new int[n];
        for (int i = 0; i < n; i++) {
            tmps[i] = sc.nextInt();
        }
        int[] nums = new int[n-1];
        for (int i = 0; i < n - 1; i++) {
            nums[i] = Math.abs(tmps[i+1] - tmps[i]);
        }
        Arrays.sort(nums);
        int max = nums[n - 2];
        int min = nums[0];
        boolean f = true;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i+1] - nums[i] != 1) {
                f = false;
                break;
            }
        }
        if (f) {
            System.out.println("YES");
            System.out.print(min + " " + max);
            return;
        }
        System.out.println("NO");
        // Map <Integer, Integer> map = new HashMap<>();
        int gap = 0;
        int rep = 1, cur = 1;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i+1] - nums[i] > 1) {
                gap += nums[i+1] - nums[i] - 1;
            }
            if (nums[i + 1] == nums[i]) {
                cur++;
                rep = Math.max(rep, cur);
            } else {
                cur = 1;
            }
        }
        System.out.print(rep + " " + gap);
    }

    public static void pdd4() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][2];
        // int max = 0, max_cnt = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i][0] = sc.nextInt();
            nums[i][1] = sc.nextInt();
            map.put(nums[i][0], map.getOrDefault(nums[i][0], 0) + 1);
            sum += nums[i][1];
        }
        int maxCnt = map.firstEntry().getValue();
        if (maxCnt * 2 > n) {
            System.out.println(0);
            return;
        }
        int move1 = n - maxCnt * 2 + 1;
        Random r = new Random();
        System.out.println(r.nextInt(sum));

    }

    public static void pdd1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = sc.nextInt();
            nums[i][1] = sc.nextInt();
        }
        // 运行时长排序
        Arrays.sort(nums, (int[] a, int[] b) -> (a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]));
        // infre时间
        int train = 0, res = 0;
        for (int i = 0; i < n; i++) {
            train += nums[i][1];
            res = Math.max(res, train + nums[i][0]);
        }
        System.out.println(res);
    }


    public static void pdd11() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = sc.nextInt();
            nums[i][1] = sc.nextInt();
        }
        // 运行时长排序
        // Arrays.sort(nums, (int[] a, int []b) -> (a[0] - b[0]));
        Arrays.sort(nums, (int[] a, int []b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        // 训练时间   infre时间
        int train = 0, infre = 0;
        for (int i = n - 1; i >= 0; i--) {
            train += nums[i][1];
            infre = Math.max(infre, train + nums[i][0]);
        }
        train += nums[0][0];
        // System.out.println(Math.max(train, infre));
        System.out.println(infre);
    }


}
