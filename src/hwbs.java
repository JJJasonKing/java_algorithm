import java.util.*;

public class hwbs {
    private static HashMap<Integer, Integer> map = new HashMap<>();
    public static int get(int t) {
        if (map.containsKey(t)) {
            return map.get(t);
        }
        int res = t - 1;
        for (int i = 2; i * i <= t; ++i) {
            if (t % i == 0) {
                res = Math.min(get(t / i) + get(i) + 1, res);
            }
        }
        map.put(t, res);
        return res;
    }
    public static void main22(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int res = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int t = in.nextInt();
            res += get(t);
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> right = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            right.add(nums[i]);
        }
        for (int i = 0; i < k / 2; i++) {
            left.add(right.poll());
        }
        double[] res = new double[n - k + 1];
        res[0] = getMid(left, right);
        for (int i = k; i < n; i++) {
            int add = nums[i], del = nums[i - k];
            if (add >= right.peek()) {
                right.add(add);
            } else {
                left.add(add);
            }
            if (del >= right.peek()) {
                right.remove(del);
            } else {
                left.remove(del);
            }
            adjust(left, right);
            res[i - k  +1] = getMid(left, right);
        }

        int[] maxs = new int[n - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (dq.peekFirst() + k <= i) {
                dq.pollFirst();
            }
            if (i >= k - 1) {
                maxs[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        dq.clear();
        int[] mins = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && nums[dq.peekLast()] >= nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (dq.peekFirst() + k <= i) {
                dq.pollFirst();
            }
            if (i >= k - 1) {
                mins[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] - mins[i] >= maxs[i] - res[i]) {
                System.out.print(maxs[i] + " ");
            } else {
                System.out.print(mins[i] + " ");
            }
        }
    }

    public static void adjust(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) {
            right.add(left.poll());
        }
        while (right.size() > left.size() + 1) {
            left.add(right.poll());
        }
    }

    public static double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) {
            return left.peek() / 2.0 + right.peek() / 2.0;
        } else {
            return right.peek() * 1.0;
        }
    }
}