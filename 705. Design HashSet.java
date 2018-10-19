class MyHashSet {
    
    boolean[] set;

    /** Initialize your data structure here. */
    public MyHashSet() {
        set = new boolean[1000001];
    }
    
    public void add(int key) {
        set[key] = true;
    }
    
    public void remove(int key) {
        set[key] = false;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return set[key];
    }
}

class MyHashSet {
    
    // java 内部，hashset用hashmap实现
    ListNode[] set;
    int SIZE = 500;
    int CONSTANT = 1; // value for all hashmap entry

    /** Initialize your data structure here. */
    public MyHashSet() {
        set = new ListNode[SIZE];
    }
    
    public void add(int key) {
        int idx = key % SIZE;
        if (set[idx] == null) {
            set[idx] = new ListNode(-1, -1);
        }
        ListNode dummy = set[idx];
        ListNode prev = findPrev(dummy, key);
        
        if (prev.next == null) {
            prev.next = new ListNode(key, CONSTANT);
        }
    }
    
    public void remove(int key) {
        int idx = key % SIZE;
        if (set[idx] == null) {
            return;
        }
        ListNode dummy = set[idx];
        ListNode prev = findPrev(dummy, key);
        
        if (prev.next == null) {
            return;
        } else {
            prev.next = prev.next.next;
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int idx = key % SIZE;
        if (set[idx] == null) {
            return false;
        }
        ListNode dummy = set[idx];
        ListNode prev = findPrev(dummy, key);
        
        return prev.next != null;
    }
    
    public ListNode findPrev(ListNode node, int key) {
        ListNode cur = node;
        ListNode prev = null;
        while (cur != null && cur.key != key) {
            prev = cur;
            cur = cur.next;
        }
        return prev;
    }
    
    class ListNode {
        int key;
        int val;
        ListNode next;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */



/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
