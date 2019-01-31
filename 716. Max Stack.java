class MaxStack {

    Deque<Integer> stack;
    Deque<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new ArrayDeque<>();
        maxStack = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        } else {
            maxStack.push(maxStack.peek());
        }
    }
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int res = maxStack.peek();
        Deque<Integer> temp = new ArrayDeque<>();
        while (stack.peek() != res) {
            temp.push(stack.pop());
            maxStack.pop();
        }
        
        stack.pop();
        maxStack.pop();
        
        while (!temp.isEmpty()) {
            int cur = temp.pop();
            stack.push(cur);
            if (maxStack.isEmpty() || cur >= maxStack.peek()) {
                maxStack.push(cur);
            } else {
                maxStack.push(maxStack.peek());
            }
        }
        return res;
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
