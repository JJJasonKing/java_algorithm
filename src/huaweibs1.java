import java.util.HashMap;
import java.util.Scanner;

public class huaweibs1 {
    // 1. 100
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        String[] danci = sc.nextLine().split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < danci.length; i++) {
            danci[i] = danci[i].toLowerCase();
            map.put(danci[i], i);
        }
        StringBuilder sb = new StringBuilder();
        boolean syh = false;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("\"")) {
                syh = !syh;
            }
            if (syh || strs[i].length() == 0 || strs[i].equals(".") || strs[i].equals(",")) {
                sb.append(strs[i]).append(" ");
                continue;
            }
            String cur = strs[i].toLowerCase();
            char c = cur.charAt(cur.length() - 1);
            if (c == ',' || c == '.') {
                cur = cur.substring(0, cur.length() - 1);
            }
            if (map.containsKey(cur)) {
                if (c == ',' || c == '.') {
                    sb.append(map.get(cur)).append(c).append(" ");
                } else {
                    sb.append(map.get(cur)).append(" ");
                }
            } else {
                sb.append(strs[i]).append(" ");
            }
        }
         System.out.println(sb.toString().trim());
    }



}
