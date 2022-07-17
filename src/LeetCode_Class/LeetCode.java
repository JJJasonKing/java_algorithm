package LeetCode_Class;

import java.util.*;

public class LeetCode {

    // 378. 有序矩阵中第 K 小的元素
    public int kthSmallest1(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        if (k == 0 || m * n < k) {
            return 0;
        }
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (queue.size() < k) {
                    queue.offer(matrix[i][j]);
                } else {
                    if (queue.peek() > matrix[i][j]) {
                        queue.poll();
                        queue.offer(matrix[i][j]);
                    } else {
                        break; // 往后更大
                    }
                }
            }
        }
        return queue.peek();
    }

    // 二分法 取左边界
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        if (k == 0 || m * m < k) {
            return 0;
        }
        // 这里 left right取的不是下标 而是值
        // 因为取的是左边界 所以值一定存在
        int left = matrix[0][0], right = matrix[m-1][m-1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int cnt = getLesser(matrix, mid, m);
            if (cnt >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    int getLesser(int[][] matrix, int target, int m) {
        int x = m - 1, y = 0;
        int cnt = 0;
        while (x >= 0 && y < m) {
            if (matrix[x][y] <= target) {
                cnt += (x+1);
                y++;
            } else {
                x--;
            }
        }
        return cnt;
    }

    // 64. 最小路径和
    // DP 因为向右向下 如果四周任意方向呢
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j-1];
                } else if (j == 0) {
                    grid[i][j] += grid[i-1][j];
                } else {
                    grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        return grid[m-1][n-1];
    }



    // 743. 网络延迟时间
    // 单源最短路径问题
    public int networkDelayTime(int[][] times, int n, int k) {
        int inf = Integer.MAX_VALUE;
        int res = 0;
        // 1. 初始化邻接矩阵
        int [][] graph = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                graph[i][j] = i == j ? 0 : inf;
            }
        }
        for (int[] edge : times) {
            graph[edge[0]][edge[1]] = edge[2];
        }

        // 2. 邻接矩阵查找各个顶点的极值
        // 初始化 距离数组 和 visited
        int[] distance = new int[n+1];
        Arrays.fill(distance, inf);
        for (int i = 1; i <= n; i++) {
            distance[i] = graph[k][i];
        }
        boolean[] visited = new boolean[n+1];
        // 初始化源点
        visited[k] = true;
        distance[k] = 0;
        for (int i = 1; i <= n; i++) { // 代表循环次数为节点数量(每次找到一个最近点) 无实际意义
            int min = inf; // 当前最短路
            int idx = -1; // 最短路的节点
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && distance[j] < min) {
                    min = distance[j];
                    idx = j;
                }
            }
            if (idx == -1) {
                break;
            }
            visited[idx] = true;
            for (int j = 1; j <= n; j++) {
                // 这里要加idx能到j的判断  不然有越界问题
                if (!visited[j] && graph[idx][j] != inf && distance[idx] + graph[idx][j] < distance[j]) {
                    distance[j] = distance[idx] + graph[idx][j];
                }
            }
        }

        // 3. 取最大路径
        for (int i = 1; i <= n; i++) {
            res = Math.max(res, distance[i]);
        }

        return res == inf ? -1 : res;
    }


    // 2047. 句子中的有效单词数
    // 正则表达式法  什么时候用小 中括号 和转义字符？小括号代表全选 中括号代表选择之一
    public int countValidWords(String sentence) {
        String regex = "([,.!]|[a-z]+(-[a-z]+)?[,.!]?)";
        int res = 0;
        for (String str : sentence.split(" ")) {
            if (str.equals(" ")) {
                continue;
            }
            if (str.matches(regex)) {
                res++;
            }
        }
        return res;
    }


    public static long findMaximumSum(List<Integer> stockPrice, int k) {
        // Write your code here
        int n = stockPrice.size();
        long res = -1, cur = 0;

        HashSet<Integer> set = new HashSet<>();
        int l = 0, r = 0;
        while (r < n) {
            int t = stockPrice.get(r);
            if (!set.isEmpty() && set.contains(t)) {
                l = 0;
            }
            set.add(t);
            cur += t;
        }
        for (int i = 0; i < n; i++) {
            set.add(stockPrice.get(i));
            cur += stockPrice.get(i);
            if (set.size() == k) {
                res = Math.max(res, cur);
            }
            if (i >= k - 1) {
                set.remove(stockPrice.get(i-k+1));
                cur -= stockPrice.get(i-k+1);
            }

        }
        return res;
    }

    public static long findMaximumSum2(List<Integer> stockPrice, int k) {
        // Write your code here
        int n = stockPrice.size();
        long res = -1, cur = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(stockPrice.get(i));
            cur += stockPrice.get(i);
            if (set.size() == k) {
                res = Math.max(res, cur);
            }
            if (i >= k - 1) {
                set.remove(stockPrice.get(i-k+1));
                cur -= stockPrice.get(i-k+1);
            }

        }
        return res;
    }


    // 1817. 查找用户活跃分钟数
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int[] res = new int[k];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            int id = log[0], time = log[1];
            if (!map.containsKey(id)) {
                map.put(id, new HashSet<>());
            }
            map.get(id).add(time);
        }
        for (var t : map.keySet()) {
            res[map.get(t).size() - 1]++;
        }
        return res;
    }

    // 6. Z 字形变换
    public String convert(String s, int n) {
        if (n == 1 || s.length() <= n) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>(); // 按行存储
        for (int i = 0; i < n; i++) {
            rows.add(new StringBuilder());
        }
        int r = 0;
        int f = -1;
        for (char c: s.toCharArray()) {
            rows.get(r).append(c);
            if (r == 0 || r == n - 1) {
                f = -f;
            }
            r += f;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder b : rows) {
            res.append(b);
        }
        return res.toString();
    }

    // 994. 腐烂的橘子
    // bfs
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> queue = new ArrayDeque<>();
        int cnt = 0; // 好橘子数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    cnt++;
                }
            }
        }

        int step = 0;
        while (!queue.isEmpty() && cnt > 0) { // cnt==0 直接break，单isEmpty的判断条件是错的
            int len = queue.size();
            while (len-- > 0) {
                int[] tmp = queue.poll();
                int x = tmp[0], y = tmp[1];
                for (int [] d: dirs) {
                    int nx = x + d[0], ny = y + d[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        queue.offer(new int[]{nx, ny});
                        grid[nx][ny] = 2;
                        cnt--;
                    }
                }
            }
            step++;
        }
        return cnt == 0 ? step : -1;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int t : nums) {
            sum += t;
            if (map.containsKey(sum - goal)) {
                res += map.get(sum - goal);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    // 1358. 包含所有三种字符的子字符串数目
    public int numberOfSubstrings(String s) {
        int res = 0;
        int l = 0, r = 0, n = s.length();
        char[] chs = s.toCharArray();
        int[] cnts = new int[3];
        while (r < n) {
            cnts[chs[r] - 'a']++;
            while (cnts[0] > 0 && cnts[1] > 0 && cnts[2] > 0) {
                res += n - r;
                cnts[chs[l++] - 'a']--;
            }
            r++;
        }
        return res;
    }

    // 1234. 替换子串得到平衡字符串
    public int balancedString(String s) {
        int n = s.length();
        int avg = n / 4;
        int l = 0, r = 0;
        int[] cnts = new int[26];
        // 'Q','W','E','R'  16 22 4 17
        for (char c : s.toCharArray()) {
            cnts[c-'A']++;
        }
        int res = n;
        while (r < n) {
            // 窗口外的cnts - 1
            cnts[s.charAt(r) - 'A']--;
            while (l < n && cnts[16] <= avg && cnts[22] <= avg && cnts[4] <= avg && cnts[17] <= avg) {
                res = Math.min(res, r - l + 1);
                // 窗口外的cnts + 1
                cnts[s.charAt(l++) - 'A']++;
            }
            r++;
        }
        return res;
    }

    // 基础计算器1
    public int calculate2(String s) {
        // Write your code here
        Stack<Integer> stack = new Stack<>();
        char op = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                switch (op) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                op = c;
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    // 基础计算器2
    public int calculate(String s) {
        // Write your code here
        Deque<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            q.offer(c);
        }
        return dfs_cal(q);
    }
    // 得到括号内的结果
    public int dfs_cal(Deque<Character> q) {
        Stack<Integer> stack = new Stack<>();
        char op = '+';
        int num = 0;
        while (!q.isEmpty()) {
            char c = q.pollFirst();
            if (c == '(') {
                num = dfs_cal(q);
            }
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            // 读取到最后一位 要处理最后的逻辑
            if (q.isEmpty() || (!Character.isDigit(c) && c != ' ')) {
                if (op == '+') {
                    stack.push(num);
                } else if (op == '-') {
                    stack.push(-num);
                } else if (op == '*') {
                    stack.push(stack.pop() * num);
                } else if (op == '/') {
                    stack.push(stack.pop() / num);
                }
                op = c;
                num = 0;
            }
            // 右括号判断只能在最后 前面要处理计算逻辑
            if (c == ')') {
                break;
            }

        }
        int res = 0;
        for (int t : stack) {
            res += t;
        }
        return res;
    }

    // 1696. 跳跃游戏 VI
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        // 单调递减队列
        Deque<Integer> dq = new LinkedList<>();
        dq.offerLast(0);
        for (int i = 1; i < n; i++) {
            while (!dq.isEmpty() && i - dq.peekFirst() > k) {
                dq.pollFirst();
            }
            nums[i] += nums[dq.peekFirst()];
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return nums[n-1];
    }
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        // 单调递减队列
        Deque<Integer> dq = new LinkedList<>();
        dq.offerLast(0);
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            while (!dq.isEmpty() && i - dq.peekFirst() > k) {
                dq.pollFirst();
            }
            nums[i] = Math.max(nums[i], nums[i] + nums[dq.peekFirst()]);
            res = Math.max(res, nums[i]);
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return res;
    }
    class node {
        node left;
        node right;
        node next;
    };


    public void insert(node root, node parent, boolean isLeft, node child) {
        Queue<node> queue = new LinkedList<>();
        List<node> list = new ArrayList<>();
        queue.offer(root);
        boolean f = false;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                node cur = queue.poll();
                if (cur == parent) {
                    if (isLeft) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                    // 设置一个pre节点，if cur == child  if pre == child
                    f = true;
                } else {
                    if (cur.left != null) queue.offer(cur.left);
                    if (cur.right != null) queue.offer(cur.right);
                }
                if (f) {
                    list.add(cur);
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {

        }
    }

    // 大数乘法
    public static void product(String a, String b) {
        char[] chs1 = a.toCharArray();
        char[] chs2 = b.toCharArray();
        int n1 = chs1.length, n2 = chs2.length;
        int[] res = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                res[i + j + 1] += (chs1[i] - '0') * (chs2[j] - '0');
            }
        }
        for (int i = n1 + n2 - 1; i > 0; i--) {
            if (res[i] >= 10) {
                res[i-1] += res[i] / 10;
                res[i] %= 10;
            }
        }
        for (int c : res) {
            System.out.print(c);
        }
        System.out.println();
    }



    public static void mainx(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [][] arr = new int[n][5];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt(); // cpu
            arr[i][2] = sc.nextInt(); // mem
            arr[i][3] = sc.nextInt(); // arch
            arr[i][4] = sc.nextInt(); // np
        }
        int cnt = sc.nextInt(), stra = sc.nextInt(), cpu = sc.nextInt(), mem = sc.nextInt(), cpua = sc.nextInt(), sup = sc.nextInt();
        if (stra == 1) { // cpu
            Arrays.sort(arr, (int[] a, int[] b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        } else {
            Arrays.sort(arr, (int[] a, int[] b) -> a[2] == b[2] ? a[1] - b[1] : a[2] - b[2]);
        }
        int sum = 0;
        for (int [] cur: arr) {
            if (cur[1] < cpu || cur[2] < mem || (cpua != 9 && cur[3] != cpua) || (sup != 2 && cur[4] != sup)) {
                continue;
            }
            sum++;
        }
        sum = Math.min(sum, cnt);
        System.out.print(sum + " ");
        for (int [] cur: arr) {
            if (cur[1] < cpu || cur[2] < mem || (cpua != 9 && cur[3] != cpua) || (sup != 2 && cur[4] != sup)) {
                continue;
            }
            if (sum > 0) {
                if (sum > 1)
                    System.out.print(cur[0] + " ");
                else
                    System.out.print(cur[0]);
                sum--;
            }
        }
    }


    public static void maintt(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [][] arr = new int[n][2];
        int time = 0;
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            time = Math.max(time, arr[i][1]);
        }
        Arrays.sort(arr, (int[] a, int[] b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int res = 0;
        int idx = 0;
        for (int[] cur : arr) {
            if (cur[0] > idx) {
                res += cur[1];
                idx++;
            }
        }
        System.out.println(res);
    }


    public static void maint(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        if (sum % 2 != 0 || n == 1) {
            System.out.println(-1);
            return;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        // 记忆化
        int[] memp = new int[target + 1];
        Arrays.fill(memp, -1);
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= arr[i]; j--) {
                if (dp[j] < dp[j - arr[i]] + arr[i]) {
                    dp[j] = dp[j - arr[i]] + arr[i];
                    memp[j] = i;
                }
            }
        }

        if (dp[target] != target) {
            System.out.println(-1);
        } else {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            Set<Integer> set = new HashSet<>();
            System.out.println(target);
            int t = target;
            while (memp[t] != -1) {
                // System.out.print(arr[memp[t]] + " ");
                pq.offer(arr[memp[t]]);
                set.add(memp[t]);
                t = t - arr[memp[t]];
            }
            while (!pq.isEmpty()) {
                if (pq.size() > 1)
                    System.out.print(pq.poll() + " ");
                else
                    System.out.print(pq.poll());
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                if (!set.contains(i)) {
                    // System.out.print(arr[i] + " ");
                    pq.offer(arr[i]);
                }
            }
            while (!pq.isEmpty()) {
                if (pq.size() > 1)
                    System.out.print(pq.poll() + " ");
                else
                    System.out.print(pq.poll());
            }
        }

    }

    // 699. 掉落的方块
    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        // 此时的res为以j为结尾的最高高度
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            int left1 = positions[j][0], right1 = left1 + positions[j][1] - 1;
            int hj = positions[j][1];
            for (int i = 0; i < j; i++) {
                int left2 = positions[i][0], right2 = left2 + positions[i][1] - 1;
                // 保证i在j的左边且重叠
                if (right1 >= left2 && left1 <= right2) {
                    hj = Math.max(hj, res.get(i) + positions[j][1]);
                }
            }
            res.add(hj);
        }
        // 转化为全局
        for (int i = 1; i < n; i++) {
            res.set(i, Math.max(res.get(i-1), res.get(i)));
        }
        Random random =  new Random();
        return res;
    }

    // 外星语
    int[] map = new int[26];
    public boolean isAlienSorted(String[] words, String order) {

        for(int i = 0; i < 26; i++){
            map[order.charAt(i) - 'a'] = i;
        }
        int n = words.length;
        for (int i = 1; i < n; i++) {
            if (!compareString(words[i - 1], words[i])) {
                return false;
            }
        }
        return true;
    }
    public boolean compareString(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int min = Math.min(n1, n2);
        for (int i = 0; i < min; i++) {
            int t1 = map[s1.charAt(i) - 'a'], t2 = map[s2.charAt(i) - 'a'];
            if (t1 != t2) {
                return t1 < t2;
            }
        }
        return n1 <= n2;
    }








    public static void main222(String[] args) {
        int[][] matrix2 = {{2,1,1},{2,3,1},{3,4,1}};
        LeetCode leetCode = new LeetCode();
        // leetCode.networkDelayTime(matrix2, 4, 2);
        String a = "99", b = "12345";
        product(a, b);
    }




}


// 2034. 股票价格波动
// 设计题
// 1. 要根据情形 考虑存储数据的结构
// 2. 如何确定存储结构 模拟
class StockPrice {
    int lastStamp;
    // key:timestamp  val:price
    HashMap<Integer, Integer> map = new HashMap<>();
    // key:price  val:count
    TreeMap<Integer, Integer> tm = new TreeMap<>();
    public StockPrice() {
        lastStamp = 0;
    }

    public void update(int timestamp, int price) {
        lastStamp = Math.max(timestamp, lastStamp);
        // 更新两个map
        if (map.containsKey(timestamp)) {
            int oldPrice = map.get(timestamp);
            int count = tm.get(oldPrice);
            if (count == 1) {
                tm.remove(oldPrice);
            } else {
                tm.put(oldPrice, count-1);
            }
        }
        map.put(timestamp, price);
        tm.put(price, tm.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return map.get(lastStamp);
    }

    public int maximum() {
        return tm.lastKey();
    }

    public int minimum() {
        return tm.firstKey();
    }

}


class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    int cap;
    HashMap<Integer, DLinkedNode> map = new HashMap<>();;
    // dummy node
    DLinkedNode head = new DLinkedNode();
    DLinkedNode tail = new DLinkedNode();
    public LRUCache(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        DLinkedNode cur = map.get(key);
        int res = cur.value;
        remove(cur);
        insert(cur);
        return res;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DLinkedNode cur = map.get(key);
            cur.value = value;
            remove(cur);
            insert(cur);
        } else {
            if (map.size() >= cap) {
                // 先删map 再删链表
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            DLinkedNode cur = new DLinkedNode(key, value);
            map.put(key, cur);
            insert(cur);
        }
    }
    // 删除链表中的cur节点
    private void remove(DLinkedNode cur) {
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
    }
    // cur插入链表头
    private void insert(DLinkedNode cur) {
        cur.next = head.next;
        cur.prev = head;
        head.next.prev = cur;
        head.next = cur;
    }
}


class AnimalShelf {
    Deque<int[]> dogs = new LinkedList<>();
    Deque<int[]> cats = new LinkedList<>();
    public AnimalShelf() {

    }

    public void enqueue(int[] animal) {
        if (animal[1] == 0) {
            cats.offer(animal);
        } else {
            dogs.offer(animal);
        }
    }

    public int[] dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) {
            return new int[]{-1, -1};
        }
        if (dogs.isEmpty()) {
            return cats.pollFirst();
        }
        if (cats.isEmpty()) {
            return dogs.pollFirst();
        }
        if (cats.peekFirst()[0] < dogs.peekFirst()[0]) {
            return cats.pollFirst();
        } else {
            return dogs.pollFirst();
        }
    }

    public int[] dequeueDog() {
        if (dogs.isEmpty()) {
            return new int[]{-1, -1};
        }
        return dogs.pollFirst();
    }

    public int[] dequeueCat() {
        if (cats.isEmpty()) {
            return new int[]{-1, -1};
        }
        return cats.pollFirst();
    }
}

class Codec {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x;}
    }
    // 先序遍历
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs_serialize(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void dfs_serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val).append(",");
        dfs_serialize(root.left, sb);
        dfs_serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        return deserialize(data.split(","));
    }
    // 不用idx的话就得用 LinkedList，或者cpp传引用
    int idx;
    public TreeNode deserialize(String[] dataArray) {
        if (dataArray[idx].equals("#")) {
            return null;
        }
        TreeNode cur = new TreeNode(Integer.parseInt(dataArray[idx]));
        idx++;
        cur.left = deserialize(dataArray);
        idx++;
        cur.right = deserialize(dataArray);
        return cur;
    }
}

class Solution695 {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i ,j));
                }
            }
        }
        return res;
    }

    public int dfs(int[][] grid, int x, int y) {
        if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0) return 0;
        int area = 1;
        grid[x][y] = 0;
        for (int[] d : dirs) {
            area += dfs(grid, x + d[0], y + d[1]);
        }
        return area;
    }
}

class Solution785 {
    // 1. 二分图染色 dfs
    public boolean isBipartite1(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                colors[i] = 0;
                if (!dfs(graph, i, colors)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dfs(int[][] graph, int cur, int[] colors) {
        for (int nxt : graph[cur]) {
            if (colors[nxt] < 0) {
                colors[nxt] = 1 - colors[cur];
                if (!dfs(graph, nxt, colors)) {
                    return false;
                }
            } else if (colors[nxt] == colors[cur]) {
                return false;
            }
        }
        return true;
    }

    // 2. bfs
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (colors[i] < 0) queue.offer(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int nxt : graph[cur]) {
                    if (colors[nxt] < 0) {
                        colors[nxt] = 1 - colors[cur];
                        queue.offer(nxt);
                    } else if (colors[nxt] == colors[cur]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}