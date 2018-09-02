class MovingAverage {
    
    List<Integer> list;
    int left;
    int size;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        list = new ArrayList<>();
        left = 0;
        this.size = size;
    }
    
    public double next(int val) {
        list.add(val);
        int sum = 0;
        for (int i = left; i < Math.min(list.size(), size) + left; i++) {
            sum += list.get(i);
        }
        if (list.size() >= size) {
            left++;
        }
        
        return ((double)sum) / Math.min(list.size(), size);
    }
}

class MovingAverage {
    
    Queue<Integer> queue;
    int size;
    int sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        sum = 0;
        this.size = size;
    }
    
    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        sum += val;
        queue.add(val);
        return ((double)sum) / queue.size();
    }
}

class MovingAverage {

    int[] window;
    int len;
    int insert;
    long sum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        window = new int[size];
        len = 0;
        insert = 0;
        sum = 0;
    }
    
    public double next(int val) {
        if (len < window.length) {
            len++;
        }
        sum -= window[insert];
        sum += val;
        window[insert] = val;
        insert = (insert + 1) % window.length;
        return (double)sum / len;
    }
}



/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
