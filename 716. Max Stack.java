class MaxStack {
    
    Deque<Integer> stack;
    Deque<Integer> max;

    public MaxStack() {
        stack = new ArrayDeque<>();
        max = new ArrayDeque<>();
    }
    
    public void push(int x) {
        max.push(max.isEmpty() ? x : Math.max(max.peek(), x));
        stack.push(x);
    }
    
    public int pop() {
        max.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return max.peek();
    }
    
    public int popMax() {
        int curMax = max.peek();
        Deque<Integer> temp = new ArrayDeque<>();
        while (stack.peek() != curMax) {
            temp.push(stack.pop());
            max.pop();
        }
        stack.pop();
        max.pop();
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
            max.push(max.isEmpty() ? stack.peek() : Math.max(max.peek(), stack.peek()));
        }
        return curMax;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

/*
Follow up: Could you come up with a solution that supports O(1) for each top call and O(logn) for each other call?
Using doubly linked list and treemap
TreeMap is: A Red-Black tree based NavigableMap implementation.
This implementation provides guaranteed log(n) time cost for the containsKey, get, put, lastKey and remove operations.
*/
class MaxStack {

    Node head;
    Node tail;
    TreeMap<Integer, List<Node>> map;
    
    public MaxStack() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.pre = head;
        map = new TreeMap<>();
    }
    
    public void push(int x) {
        Node newNode = new Node(x);
        newNode.pre = tail.pre;
        newNode.next = tail;
        tail.pre.next = newNode;
        tail.pre = newNode;
        if(!map.containsKey(x))    map.put(x, new ArrayList<Node>());
        map.get(x).add(newNode);
    }
    
    public int pop() {
        int value = tail.pre.val;
        removeNode(tail.pre);
        int listSize = map.get(value).size();
        map.get(value).remove(listSize - 1);
        if(listSize == 1)    map.remove(value);
        return value;
    }
    
    public int top() {
        return tail.pre.val;
    }
    
    public int peekMax() {
        return map.lastKey();
    }
    
    public int popMax() {
        int maxVal = map.lastKey();
        int listSize = map.get(maxVal).size();
        Node node = map.get(maxVal).remove(listSize - 1);
        removeNode(node);
        if(listSize == 1)    map.remove(maxVal);
        return maxVal;
    }
    
    private void removeNode(Node n){
        Node preNode = n.pre;
        Node nextNode = n.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
    }
    
    class Node{
        Node pre;
        Node next;
        int val;
        public Node(int x){
            this.val = x;
            this.pre = null;
            this.next = null;
        }
    }
}

class MaxStack {

    Deque<Integer> stack;
    PriorityQueue<Integer> maxPQ;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new ArrayDeque<>();
        maxPQ = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void push(int x) {
        stack.push(x);
        maxPQ.offer(x);
    }
    
    public int pop() {
        int res = stack.pop();
        maxPQ.remove(res);
        return res;
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxPQ.peek();
    }
    
    public int popMax() {
        int res = maxPQ.poll();
        Deque<Integer> temp = new ArrayDeque<>();
        while (stack.peek() != res) {
            temp.push(stack.pop());
        }
        
        stack.pop();
        while (!temp.isEmpty()) {
            int cur = temp.pop();
            stack.push(cur);
        }
        return res;
    }
}

class MaxStack {
    
    Deque<Integer> dqStack;
    PriorityQueue<Integer> pqMax;

    /** initialize your data structure here. */
    public MaxStack() {
        dqStack = new ArrayDeque<>();
        pqMax = new PriorityQueue<>( (a,b) ->(b - a) );
    }
    
    public void push(int x) {
        dqStack.addLast(x);
        pqMax.offer(x);
    }
    
    public int pop() {
        int deleteE = dqStack.removeLast();
        pqMax.remove(deleteE);
        return deleteE;
    }
    
    public int top() {
        return dqStack.getLast();
    }
    
    public int peekMax() {
        return pqMax.peek();
    }
    
    public int popMax() {
        int deleteE = pqMax.poll();
        dqStack.removeLastOccurrence(deleteE);
        return deleteE;
    }
}

class MaxStack {
    
    class ListNode{
        int val;
        int max;
        ListNode next;
        ListNode prev;
        public ListNode(int val, int max){
            this.val = val;
            this.max = max;
        }
    }
    
    ListNode head;
    ListNode tail;

    /** initialize your data structure here. */
    public MaxStack() {
        head = new ListNode(Integer.MIN_VALUE, Integer.MIN_VALUE);
        tail = new ListNode(Integer.MAX_VALUE, Integer.MAX_VALUE);
        
        head.next = tail;
        tail.prev = head;
    }
    
    public void push(int x) {
        ListNode node = new ListNode(x, Math.max(x, tail.prev.max));
        node.next = tail;
        node.prev = tail.prev;
        
        node.next.prev = node;
        node.prev.next = node;
        
    }
    
    public int pop() {
        ListNode node = tail.prev;
        
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
        
        return node.val;
    }
    
    public int top() {
        return tail.prev.val;
    }
    
    public int peekMax() {
        return tail.prev.max;
    }
    
    public int popMax() {
        int max = peekMax();
        ListNode curr = tail.prev;
        
        while(curr.val != max){
            curr = curr.prev;
        }
        
        ListNode update = curr.next;
        
        
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.next = null;
        curr.prev = null;
        
        while(update != tail){
            update.max = Math.max(update.val, update.prev.max);
            update = update.next;
        }
        
        return max;
    }
}
