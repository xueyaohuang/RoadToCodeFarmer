class MyHashMap {
    
    int[] map;

    /** Initialize your data structure here. */
    public MyHashMap() {
        map = new int[1000001];
        Arrays.fill(map, -1);
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        map[key] = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return map[key];
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        map[key] = -1;
    }
}

class MyHashMap {
    
    // hashing, use linked list to avoid collision. 模拟真实HashMap
    private ListNode[] map;
    private final int SIZE = 500;

    /** Initialize your data structure here. */
    public MyHashMap() {
        map = new ListNode[SIZE];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        // hashing
        int idx = key % size;
        if (map[idx] == null) {
            map[idx] = new ListNode(-1, -1); // dummy node
        }
        ListNode dummy = map[idx];
        // prev不可能是null，最少是dummy
        ListNode prev = findPrev(dummy, key);
        // no such key.
        if (prev.next == null) {
            prev.next = new ListNode(key, value);
        } else {
            prev.next.value = value;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = key % size;
        if (map[idx] == null) {
            return -1;
        }
        ListNode dummy = map[idx];
        ListNode prev = findPrev(dummy, key);
        // no such key
        if (prev.next == null) {
            return -1;
        } else {
            return prev.next.value;
        }
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int idx = key % size;
        if (map[idx] == null) {
            return;
        }
        ListNode dummy = map[idx];
        ListNode prev = findPrev(dummy, key);
        // no such key
        if (prev.next == null) {
            return;
        } else {
            prev.next = prev.next.next;
            return;
        }
    }
    
    private ListNode findPrev(ListNode node, int key) {
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
        int value;
        ListNode next;
        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
