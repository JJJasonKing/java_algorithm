import java.util.List;
import java.util.Scanner;

public class Baidu {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        int nn = n * k;
        int [][] res = new int[nn][nn];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                int t = sc.nextInt();
                for (int r = i * k; r < i * k + k; r++) {
                    for (int c = j * k; c < j * k + k; c++) {
                        res[r][c] = t;
                    }
                }
            }
        }
        for (int i = 0; i < nn; i++) {
            for (int j = 0; j < nn; j++) {
                System.out.print(res[i][j] + ' ') ;
            }
            System.out.println();
        }
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chs = str.toCharArray();
        int n = chs.length;
        int[] pre = new int[n+1];
        for (int i = 0; i < n; i++) {
            int t = chs[i] == '1' ? 1 : -1;
            pre[i + 1] = pre[i] + t;
        }
        int len = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {

            }
        }
    }

    int mod = 1000000007;
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chs = str.toCharArray();
        int n = chs.length;
        System.out.println(n);
    }
}
