import java.util.Arrays;
import java.util.PriorityQueue;

public class dji {
    public static void main(String[] args) {

    }

    public int findMinDifference(String[] timePoints) {
        int n = timePoints.length * 2;
        int[] mins = new int[n];
        for (int i = 0, idx = 0; i < timePoints.length; i++, idx += 2) {
            String[] tmps = timePoints[i].split(":");
            int hour = Integer.parseInt(tmps[0]), min = Integer.parseInt(tmps[1]);
            mins[idx] = hour * 60 + min;
            mins[idx + 1] = hour * 60 + min + 1440;
        }
        Arrays.sort(mins);
        int res = 10000;
        for (int i = 1; i < n; i++) {
            res = Math.min(res, mins[i] - mins[i - 1]);
        }
        return res;
    }


    public int minimumDeviation(int[] nums) {
        // 大根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int min = Integer.MAX_VALUE;
        // 都变偶数
        for (int t : nums) {
            if (t % 2 != 0) {
                t *= 2;
            }
            pq.offer(t);
            min = Math.min(min, t);
        }
        int res = pq.peek() - min;
        while (!pq.isEmpty() && pq.peek() % 2 == 0) {
            int cur = pq.poll() / 2;
            pq.offer(cur);
            min = Math.min(min, cur);
            res = Math.min(res, pq.peek() - min);
        }
        return res;
    }

}
