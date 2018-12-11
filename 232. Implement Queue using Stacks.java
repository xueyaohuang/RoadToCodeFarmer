class MyQueue {
    
    Stack<Integer> sIn;
    Stack<Integer> sOut;
    // 一个用来接收push的新元素，一个用来pop和peek，只有当sOut为空时，才从in把元素都转到out

    /** Initialize your data structure here. */
    public MyQueue() {
        sIn = new Stack<Integer>();
        sOut = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        sIn.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (sOut.isEmpty()) { //往sOut里面push要注意，为了保持顺序，只有sOut是空的时候才能push新的元素。
            while (!sIn.isEmpty()) {
                sOut.push(sIn.pop());
            }
        }      
        return sOut.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (sOut.isEmpty()) { //往sOut里面push要注意，为了保持顺序，只有sOut是空的时候才能push新的元素。
            while (!sIn.isEmpty()) {
                sOut.push(sIn.pop());
            }
        }
        return sOut.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return sIn.isEmpty() && sOut.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
