class MedianFinder {

    // 保证largerHalf的最小比smallerHalf的最大还大,并且smallerHalf里的数不比largerHalf少
    private PriorityQueue<Integer> smallerHalf; 
    private PriorityQueue<Integer> largerHalf; 
    
    /** initialize your data structure here. */
    public MedianFinder() {
        this.smallerHalf = new PriorityQueue<>(Collections.reverseOrder());
        this.largerHalf = new PriorityQueue<>();
    }
    
    // 为了保证largerHalf的最小比smallerHalf的最大还大，num要先进smallerHalf，再poll出来放进largerHalf
    public void addNum(int num) {
        smallerHalf.add(num);
        largerHalf.add(smallerHalf.poll());
        if (smallerHalf.size() < largerHalf.size()) {
            smallerHalf.add(largerHalf.poll());
        }
    }
    
    public double findMedian() {
        if (smallerHalf.size() == largerHalf.size()) {
            return (smallerHalf.peek() + largerHalf.peek()) / 2.0;
        }
        return smallerHalf.peek();
    }
}

class MedianFinder {
    
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;

    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (small.isEmpty()) {
            small.offer(num);
            return;
        }
        if (small.size() == large.size()) {
            if (num <= large.peek()) {
                small.offer(num);
            } else {
                small.offer(large.poll());
                large.offer(num);
            }
        } else {
            if (num >= small.peek()) {
                large.offer(num);
            } else {
                large.offer(small.poll());
                small.offer(num);
            }
        }
    }
    
    public double findMedian() {
        if (small.size() > large.size()) {
            return small.peek();
        }
        return (small.peek() + large.peek()) / 2.0;
    }
}

/*
Follow up:
1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?

Bucket sort. We can maintain an integer array of length 101 to store the count of each number along with a total count. Then, we can iterate over the array to find the middle value to get our median.

Time and space complexity would be O(100) = O(1).

2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

in Java we can use a TreeMap to store numbers < 0 and > 100, with the value being their count. The iteration order would be:

    check TreeMap entries < 0
    check array of size 100
    check TreeMap of entries > 100

And you'd still need to keep a global counter to count amount of nums added in total.
*/

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
