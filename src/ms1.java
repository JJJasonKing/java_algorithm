import java.util.*;

class tmpNode {
    int val;
    List<tmpNode> next = new ArrayList<>();
    public tmpNode(int val) {
        this.val = val;
    }
}


public class ms1 {

    public boolean graphSearch(int[][] graph, int start, int end) {
        tmpNode begin = null;
        /*
        for (int i = 0 ; i < graph.length; i++) {
            node cur = new node(i);
            if (i == start) {
                head = cur;
            }
            for (int nxt : graph[i]) {
                cur.next.add(new node(nxt));
            }
        }*/
        LinkedList<tmpNode> queue = new LinkedList<>();


        boolean [] visit = new boolean[graph.length];
        return dfs(begin, end, visit);
    }
    public boolean dfs(tmpNode cur, int end, boolean [] visit) {
        if (cur.val == end) {
            return true;
        }
        visit[cur.val] = true;
        for (var t : cur.next) {
            if (!visit[t.val] && dfs(t, end, visit)) {
                return true;
            }
        }
        return false;
    }



    // 邻接矩阵  节点
    class adjNode {
        int val;
        int weight;
        adjNode next;
        public adjNode(int val, int weight){
            this.val = val;
            this.weight = weight;
            next = null;
        }
    }

    // 要有size为点数量的顶点矩阵
    ArrayList<adjNode> heads = new ArrayList<>(20);
    // 邻接矩阵初始化
    // graph 输入[1,2,10]表示节点1和节点2相连 权重是10
    public void adjGraph(int[][] graph, int n) {

        for (int i = 0; i < n; i++) {
            adjNode cur = new adjNode(i,0);
            heads.add(i, cur);
        }
        for (int [] edge : graph) {
            // buildGraph(edge[0], edge[1], edge[2]);
            // buildGraph(edge[1], edge[0], edge[2]);
            adjNode cur = heads.get(edge[0]);
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new adjNode(edge[1], edge[2]);
        }
    }

    public void buildGraph(int a, int b, int w) {
        adjNode cur = heads.get(a);
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new adjNode(b, w);
    }

    public boolean search(int start, int end) {
        for (adjNode cur = heads.get(start).next; cur != null; cur = cur.next) {
            if (cur.val == end) {
                return true;
            }
            if (search(cur.val, end)) {
                return true;
            }
        }
        return false;
    }



    // 滑动窗口最大值
    public int[] getMax(int[] arr, int k) {
        int n = arr.length;
        int [] res = new int[n - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (pq.size() >= k) {
                res[j++] = pq.peek();
                pq.remove(arr[i-k]);
            }
            pq.offer(arr[i]);
        }
        return res;
    }

    public int[] getMax2(int[] arr, int k) {
        int n = arr.length;
        int [] res = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(arr[0]);
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (deque.peekLast() <= arr[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            arr[j++] = deque.peekFirst();
            if (deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
        }
        return res;
    }








    public static void main(String[] args) {
        int[][] graph = {{0, 1, 1}, {1, 4, 5}, {1, 3, 6}, {2, 4, 9}, {3, 4, 8}, {3, 2, 7}};
        ms1 tmp = new ms1();
        tmp.adjGraph(graph, 5);
        boolean res = tmp.search(3, 0);
    }
}
