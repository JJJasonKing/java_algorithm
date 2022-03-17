package LRU_LFU;

import java.util.HashMap;

class LFUCache {
    // LRU的思路是一条双向链表存放优先级 越靠前优先级越高 map存放key对应的node
    // LFU的思路是除了原本的map外，还有一个map<cnt,node> 对应当前count的双向链表头结点
    // 双向链表存放优先级 越靠前优先级越高  这个是LRU、LFU共有的；
    // 注意每次更新时 都要同时考虑map和list
    class DNode {
        int key;
        int val;
        int count; //记录当前key被调用的次数
        DNode pre;
        DNode next;
        public DNode() {}
        public DNode(int key, int val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    private int cap;
    private DNode head = new DNode();
    private DNode tail = new DNode();
    private HashMap<Integer,DNode> keyMap = new HashMap<>(); // key -- node
    private HashMap<Integer,DNode> cntMap = new HashMap<>(); // cnt -- node

    public LFUCache(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (cap == 0 || !keyMap.containsKey(key)) {
            return -1;
        }
        DNode cur = keyMap.get(key);
        // count+1 更新
        updata(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if (cap == 0) return;
        if (keyMap.containsKey(key)) {
            DNode node = keyMap.get(key);
            node.val = value;
            updata(node);
        } else { // 不存在时要判断cap
            if (keyMap.size() >= cap) {
                removeLast();
            }
            //新节点
            DNode cur = new DNode(key,value,1);
            DNode next = cntMap.get(1);
            if (next == null) {
                next = tail;
            }
            cur.next = next;
            cur.pre = next.pre;
            next.pre.next = cur;
            next.pre = cur;
            cntMap.put(1, cur);
            keyMap.put(key, cur);
        }
    }

    /* remove更新的是map与list的值
    private void remove(DNode cur) {
        int cntVal = cur.count;
        if (cntMap.get(cntVal) == cur) { // 当前节点是某个频数的头节点
            if (cur.next.count == cur.count) { // 当前频数还有其他节点
                cntMap.put(cntVal, cur.next); // 更新map记录的头结点
            } else { // 只有一个当前节点
                cntMap.remove(cntVal); // 从map中删除当前的记录
            }
        }
        // 不是头结点 直接删除
        removeFromList(cur);
    }*/

    // updata负责更新当前node 发现不能把remove单独拆开，因为updata时会依赖前后节点
    // 比如插入count=3 的节点，如果cntMap没有k=3的记录 那么单独插入就不知道插在链表哪里了
    private void updata(DNode cur) {
        int oldCnt = cur.count;
        int newCnt = cur.count + 1;
        DNode next;
        if (cntMap.get(oldCnt) == cur) { // 当前节点是某个频数的头节点
            if (cur.next.count == cur.count) { // 当前频数还有其他节点
                cntMap.put(oldCnt, cur.next); // 更新map记录的头结点
            } else { // 只有一个当前节点
                cntMap.remove(oldCnt); // 从map中删除当前的记录
            }
            // 不包含newCnt的话 因为cur是头结点 直接更新map就行 不用动链表
            if (!cntMap.containsKey(newCnt)) {
                cur.count++;
                cntMap.put(newCnt, cur);
                return;
            } else {
                removeFromList(cur);
                next = cntMap.get(newCnt);
            }
        } else { // 当前节点是频数的中间节点
            removeFromList(cur);
            if (!cntMap.containsKey(newCnt)) {
                next = cntMap.get(oldCnt);
            } else {
                next = cntMap.get(newCnt);
            }
        }
        cur.count++;
        cur.next = next;
        cur.pre = next.pre;
        next.pre.next = cur;
        next.pre = cur;
        cntMap.put(newCnt, cur);
    }

    // 单纯从list中remove
    private void removeFromList(DNode cur) {
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
    }

    private void removeLast(){
        // tail节点前面那个节点即是需要删除的节点
        DNode delNode = tail.pre;
        if (cntMap.get(delNode.count) == delNode){
            // 如果当前的删除的节点是某个频数的头节点 cntMap删除
            cntMap.remove(delNode.count);
        }
        // list删除node节点
        delNode.pre.next = tail;
        tail.pre = delNode.pre;
        // keyMap删除
        keyMap.remove(delNode.key);
    }

}
