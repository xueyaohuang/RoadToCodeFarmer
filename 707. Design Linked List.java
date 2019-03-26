class MyLinkedList {
    
    int len;
    DLL head;
    DLL tail;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        len = 0;
        head = new DLL(0);
        tail = new DLL(0);
        head.next = tail;
        tail.prev = head;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= len) {
            return -1;
        }
        DLL cur = head.next;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        DLL newHeadNext = new DLL(val);
        DLL curHeadNext = head.next;
        head.next = newHeadNext;
        newHeadNext.prev = head;
        newHeadNext.next = curHeadNext;
        curHeadNext.prev = newHeadNext;
        len++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        DLL newTailPrev = new DLL(val);
        DLL curTailPrev = tail.prev;
        curTailPrev.next = newTailPrev;
        newTailPrev.prev = curTailPrev;
        newTailPrev.next = tail;
        tail.prev = newTailPrev;
        len++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > len) {
            return;
        }
        if (index == len) {
            addAtTail(val);
            return;
        }
        DLL cur = head;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        DLL node = new DLL(val);
        DLL curNext = cur.next;
        cur.next = node;
        node.prev = cur;
        node.next = curNext;
        curNext.prev = node;
        len++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= len) {
            return;
        }
        DLL cur = head;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        DLL next = cur.next;
        DLL nextTwo = cur.next.next;
        cur.next = nextTwo;
        nextTwo.prev = cur;
        next.next = null;
        next.prev = null;
        len--;
    }
}

class DLL {
    int val;
    DLL prev;
    DLL next;
    public DLL(int val) {
        this.val = val;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
