package ListNode;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution_ListNode{
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeList(lists, 0, lists.length);
    }
    // mergeSort的两步 一步合并 一步合并
    public ListNode mergeList(ListNode[] lists, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return lists[l];
        }
        int mid = (l + r) >> 1;
        return merge(mergeList(lists, l, mid), mergeList(lists, mid + 1, r));
    }

    public ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        if (a != null) {
            cur.next = a;
        }
        if (b != null) {
            cur.next = b;
        }
        return dummy.next;
    }

}
