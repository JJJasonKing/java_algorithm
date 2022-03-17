package LRU_LFU;

import java.util.HashMap;

public class LRUCache {
    class DNode{
        int key;
        int val;
        DNode next;
        DNode pre;
        public DNode(){}
        public DNode(int k, int v){
            key = k;
            val = v;
        }
    }


    HashMap<Integer, DNode> map;
    // dummy node
    DNode head;
    DNode tail;
    int cap;
    public LRUCache(int capacity) {
        cap = capacity;
        map = new HashMap<>();
        head = new DNode();
        tail = new DNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        DNode node = map.get(key);
        remove(node);
        insert(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DNode node = map.get(key);
            node.val = value;
            remove(node);
            insert(node);
        } else {
            DNode node = new DNode(key, value);
            if (map.size() >= cap) {
                // 不要忘了map的删除
                map.remove(tail.pre.key);
                remove(tail.pre);
            }
            map.put(key,node);
            insert(node);
        }
    }

    // 一般应该返回一个 RC_SUCC 这样的
    // remove 删除指定及node
    public void remove(DNode cur) {
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
    }

    //把node放到队首(不在这里remove)
    public void insert(DNode cur) {
        cur.next = head.next;
        head.next.pre = cur;
        cur.pre = head;
        head.next = cur;
    }

}
