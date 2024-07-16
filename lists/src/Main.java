public class Main {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }

        }

        return dummyHead.next;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur != null && cur.next != null) {
            if (cur.next.next == null) {
                break;
            }

            ListNode next = cur.next;
            ListNode nextNext = cur.next.next;
            ListNode nextNextNext = cur.next.next.next;

            cur.next = nextNext;
            nextNext.next = next;
            next.next = nextNextNext;

            cur = cur.next.next;
        }

        return dummyHead.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode left = dummyHead;
        ListNode right = dummyHead;

        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        if (left.next != null) {
            left.next = left.next.next;
        }

        return dummyHead.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 计算 headA 的长度和 headB 的长度然后求差值diff
        // 让长链表先走diff 个节点，然后两个链表同时后移直到相交
        if (headA == null || headB == null) {
            return null;
        }

        int lengthA = 0;
        int lengthB = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        // A 和 B 有一个走到null
        while (curA != null && curB != null) {
            lengthA++;
            curA = curA.next;

            lengthB++;
            curB = curB.next;
        }

        while (curA != null) {
            lengthA++;
            curA = curA.next;
        }
        while (curB != null) {
            lengthB++;
            curB = curB.next;
        }

        curA = headA;
        curB = headB;
        if (lengthA > lengthB) {
            for (int i = 0; i < (lengthA - lengthB); i++) {
                curA = curA.next;
            }
        } else {
            for (int i = 0; i < (lengthB - lengthA); i++) {
                curB = curB.next;
            }
        }

        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }

        return curA;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        // 快慢指针同时移动，直到相遇
        // 相遇后，一个节点从 head 走，一个节点从相遇节点接着走，直到两个节点相撞，说明找到环形入口
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 快慢指针相撞，说明一定有环
            if (slow == fast) {
                ListNode entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }

        return null;
    }
}
