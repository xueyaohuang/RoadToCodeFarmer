class MedianFinder {

    private PriorityQueue<Integer> smallerHalf; 
    private PriorityQueue<Integer> largerHalf; 
    
    /** initialize your data structure here. */
    public MedianFinder() {
        this.smallerHalf = new PriorityQueue<>(Collections.reverseOrder());
        this.largerHalf = new PriorityQueue<>();
    }
    
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

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
