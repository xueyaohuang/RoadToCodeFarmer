class MyCircularQueue {
    
    int[] arr;
    int start;
    int end;
    int count;

    public MyCircularQueue(int k) {
        arr = new int[k];
        start = 0;
        end = -1;
        count = 0;
    }
    
    public boolean enQueue(int value) {
        if (this.isFull()) {
            return false;
        }
        end = (end + 1) % arr.length;
        arr[end] = value;
        count++;
        return true;
    }
    
    public boolean deQueue() {
        if (this.isEmpty()) {
            return false;
        }
        start = (start + 1) % arr.length;
        count--;
        return true;
    }
    
    public int Front() {
        if (this.isEmpty()) {
            return -1;
        }
        return arr[start];
    }
    
    public int Rear() {
        if (this.isEmpty()) {
            return -1;
        }
        return arr[end];
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public boolean isFull() {
        return count == arr.length;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
