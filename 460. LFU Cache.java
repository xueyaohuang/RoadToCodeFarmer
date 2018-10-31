public class LFUCache {
    
    class DoublyLinkedNode {
        
        int key, val, count;
        DoublyLinkedNode prev, next;
        
        public DoublyLinkedNode() {};
        public DoublyLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.count = 1;
        }
    }
    
    class DoublyLinkedList {
        
        DoublyLinkedNode head, tail;
        int length;
        
        public DoublyLinkedList() {
            head = new DoublyLinkedNode();
            tail = new DoublyLinkedNode();
            head.next = tail;
            tail.prev = head;
        }
        
        public void addToHead(DoublyLinkedNode node) {
            length++;
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
        }
        
        public void removeNode(DoublyLinkedNode node) {
            length--;
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        public DoublyLinkedNode popFromTail() {
            if (length > 0) {
                DoublyLinkedNode node = tail.prev;
                removeNode(node);
                return node;
            } else {
                return null;
            }  
        }
    }
    
    int capacity, length, min;
    Map<Integer, DoublyLinkedNode> nodeMap;
    Map<Integer, DoublyLinkedList> countMap;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        this.countMap = new HashMap<>();
    }
    
    public int get(int key) {
        DoublyLinkedNode node = nodeMap.get(key);
        if (node == null) {
            return -1;
        } else {
            update(node);
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        DoublyLinkedNode node = nodeMap.get(key);
        if (node != null) {
            node = nodeMap.get(key);
            node.val = value;
            update(node);
        } else {
            node = new DoublyLinkedNode(key, value);
            nodeMap.put(key, node);
            if (length == capacity) {
                DoublyLinkedList lastList = countMap.get(min);
                nodeMap.remove(lastList.popFromTail().key);
                length--;
            }
            length++;
            min = 1;
            DoublyLinkedList newList = countMap.getOrDefault(node.count, new DoublyLinkedList());
            newList.addToHead(node);
            countMap.put(node.count, newList);
        }
    }
    
    private void update(DoublyLinkedNode node) {
        DoublyLinkedList oldList = countMap.get(node.count);
        oldList.removeNode(node);
        if (node.count == min && oldList.length == 0) {
            min++; 
        } 
        node.count++;
        DoublyLinkedList newList = countMap.getOrDefault(node.count, new DoublyLinkedList());
        newList.addToHead(node);
        countMap.put(node.count, newList);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
