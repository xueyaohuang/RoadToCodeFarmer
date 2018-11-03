class DoublyLinkedNode {
    
    int key;
    int value;
    DoublyLinkedNode prev;
    DoublyLinkedNode next;
    
    public DoublyLinkedNode() {}
    
    public DoublyLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    
    private DoublyLinkedNode head;
    private DoublyLinkedNode tail;
    
    public DoublyLinkedList() {
        this.head = new DoublyLinkedNode();
        this.tail = new DoublyLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public void removeNode(DoublyLinkedNode node) {
        DoublyLinkedNode prev = node.prev;
        DoublyLinkedNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }
    
    public void addToHead(DoublyLinkedNode node) {
        DoublyLinkedNode next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }
    
    public void moveToHead(DoublyLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }
    
    public DoublyLinkedNode popFromTail() {
        DoublyLinkedNode nodeBeforeTail = tail.prev;
        removeNode(nodeBeforeTail);
        return nodeBeforeTail;
    }
}

class LRUCache {
    
    private int count;
    private final int capacity;
    private DoublyLinkedList dl;
    private Map<Integer, DoublyLinkedNode> map;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        this.dl = new DoublyLinkedList();
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            DoublyLinkedNode node = map.get(key);
            dl.moveToHead(node);
            return node.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoublyLinkedNode node = map.get(key);
            node.value = value;
            dl.moveToHead(node);
        } else {
            DoublyLinkedNode node = new DoublyLinkedNode(key, value);
            map.put(key, node);
            count++;
            dl.addToHead(node);
            if (count > capacity) {
                DoublyLinkedNode nodeBeforeTail = dl.popFromTail();
                map.remove(nodeBeforeTail.key);
                count--;
            }
        }
    }
}

class LRUCache {
    
    Map<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return map.size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        map.put(key, value);
    }
}

class LRUCache {

    Map<Integer, Integer> map;
    final int CAPACITY;
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        // do not use access order
        map = new LinkedHashMap<Integer, Integer>(){
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return map.size() > CAPACITY;
            }
        };
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            moveToFront(key);
            return map.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        map.put(key, value);
        moveToFront(key);
    }
    
    public void moveToFront(int key) {
        int value = map.get(key);
        map.remove(key);
        map.put(key, value);
    }
}

class LRUCache {

    Map<Integer, Integer> map;
    final int CAPACITY;
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        // use access order
        map = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true){
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return map.size() > CAPACITY;
            }
        };
    }
    
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        map.put(key, value);
    }
}

class LRUCache {
    
    class DoubleLinkNode {
        int key;
        int value;
        DoubleLinkNode pre;
        DoubleLinkNode post;
        public DoubleLinkNode() {}
        public DoubleLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, DoubleLinkNode> map;
    private DoubleLinkNode head;
    private DoubleLinkNode tail;
    private int capacity;
    private int count;

    public LRUCache(int capacity) {
        map = new HashMap<Integer, DoubleLinkNode>();
        this.capacity = capacity;
        count = 0;
        head = new DoubleLinkNode();
        tail = new DoubleLinkNode();
        head.post = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        DoubleLinkNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DoubleLinkNode node = map.get(key);
        if (node == null) {
            DoubleLinkNode newNode = new DoubleLinkNode(key, value);
            map.put(key, newNode);
            addNode(newNode);
            count++;
            
            if (count > capacity) {
                DoubleLinkNode preTail = popTail();
                map.remove(preTail.key); // 注意要从map中移除
                count--;
            }
        }
        else {
            node.value = value;
            moveToHead(node);
        }
    }
    
    private void addNode(DoubleLinkNode node) {
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
    }
    
    private void removeNode(DoubleLinkNode node) {
        DoubleLinkNode post = node.post;
        DoubleLinkNode pre = node.pre;
        pre.post = post;
        post.pre = pre; 
    }
    
    private void moveToHead(DoubleLinkNode node) {
        removeNode(node);
        addNode(node);
    }
    
    private DoubleLinkNode popTail() {
        DoubleLinkNode node = tail.pre;
        removeNode(node);
        return node;
    }    
}
