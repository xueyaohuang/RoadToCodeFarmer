/*
The application for this implementation is to separate read & write of a queue in multi-processing.
One of the stack is for read, and another is for write. They only interfere each other when the former
one is full or latter is empty. When there's only one thread doing the read/write operation to the stack,
there will always one stack empty. However, in a multi-thread application, if we have only one queue,
for thread-safety, either read or write will lock the whole queue. In the two stack implementation, as
long as the second stack is not empty, push operation will not lock the stack for pop.
*/

class MyQueue {
    
    Stack<Integer> sIn;
    Stack<Integer> sOut;

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
