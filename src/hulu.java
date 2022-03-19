import java.util.Scanner;

class Node {
    int val;
    Node left;
    Node right;
    Node() {}
    Node(int val) { this.val = val; }
    Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class hulu {
    private static int res = 0;
    public static Node build(int[] arr, int i, int m) {
        if (i < m) {
            Node cur = new Node(arr[i]);
            int l = 2 * i + 1, r = l + 1;
            if (l < m && arr[l] != -1) {
                cur.left = build(arr, l, m);
            }
            if (r < m && arr[r] != -1) {
                cur.right = build(arr, r, m);
            }
            return cur;
        }
        return null;
    }
    public static void dfs(Node root) {
        if (root.left == null && root.right == null) {
            res++;
            return;
        }
        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        if (m < 2) {
            System.out.println(m);
            return;
        }
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }
        Node root = build(arr, 0, m);
        dfs(root);
        System.out.println(res);
    }

    public static void maintt(String[] args) {
        Scanner sc = new Scanner(System.in);
        // String ttt = sc.nextLine();
        // int m1 = Integer.parseInt(ttt.split(" ")[0]);
        // int n1 = Integer.parseInt(ttt.split(" ")[1]);
        int m1 = sc.nextInt(), n1 = sc.nextInt();
        sc.nextLine(); // https://blog.csdn.net/qq_45689267/article/details/108211220
        char[][] arr = new char[m1][n1];
        for (int i = 0; i < m1; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < n1; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int m2 = sc.nextInt(), n2 = sc.nextInt();
        sc.nextLine();
        if (m2 > m1 || n2 > n1) {
            System.out.println(0);
        }
        char[][] test = new char[m2][n2];
        for (int i = 0; i < m2; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < n2; j++) {
                test[i][j] = str.charAt(j);
            }
        }
        if (m1 == m2 && n1 == n2) {
            if (arr.equals(test)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

        for (int i = 0; i <= m1 - m2; i++) {
            for (int j = 0; j <= n1 - n2; j++) {
                if (arr[i][j] != test[0][0]) {
                    continue;
                }
                boolean f = true;
                outer: for (int a = 0; a < m2; a++) {
                    for (int b = 0; b < n2; b++) {
                        if (arr[a+i][b+j] != test[a][b]) {
                            f = false;
                            break outer;
                        }
                    }
                }
                if (f) {
                    res++;
                }
            }
        }

        System.out.println(res);
    }

    public static void main33(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        int []nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(k);
    }

    public static void main(String[] args) {

    }


































}
