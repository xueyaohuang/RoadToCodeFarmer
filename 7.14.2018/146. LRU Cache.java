class LRUCache {
    DLinkNode head, tail;
    HashMap<Integer, DLinkNode> cache = new HashMap<>();
    private int count;
    private int capacity;
        
    private void addNode(DLinkNode node){
        node.pre = head;
        node.post = head.post;
        
        head.post.pre = node;
        head.post = node;
    }
    
    private void removeNode(DLinkNode node){
        DLinkNode pre = node.pre;
        DLinkNode post = node.post;
        
        pre.post = post;
        post.pre = pre;
    }
    
    private void moveToHead(DLinkNode node){
        this.removeNode(node);
        this.addNode(node);
    }
    
    private DLinkNode popTail(){
        DLinkNode res =  tail.pre;
        this.removeNode(res);
        return res;
    }
    
    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.pre = null;
        head.post = tail;
        tail.pre = head;
        tail.post = null;
    }
    
    public int get(int key) {
        DLinkNode node = cache.get(key);
        if(node == null) {
            return -1;
        }
        this.moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if(node == null){
            DLinkNode newNode = new DLinkNode();
            newNode.key =key;
            newNode.val = value;
            
            this.cache.put(key, newNode);
            this.addNode(newNode);
            ++count;
            
            if(count > capacity){
                DLinkNode tail = popTail();
                this.cache.remove(tail.key);
                --count;
            }
        }
        else{
            node.val = value;
            this.moveToHead(node);
        }
    }
}

class DLinkNode{
    int val;
    int key;
    DLinkNode pre;
    DLinkNode post;
   
}
