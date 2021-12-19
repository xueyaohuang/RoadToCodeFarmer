/*
Sort events increased by start time.
Priority queue pq keeps the current open events.

Iterate from the day 1 to day 100000,
Each day, we add new events starting on day d to the queue pq.
Also we remove the events that are already closed.

Then we greedily attend the event that ends soonest.
If we can attend a meeting, we increment the result res.

Complexity

Time O(d + nlogn), where d is the range of A[i][1]
Space O(N)
*/
class Solution {
    public int maxEvents(int[][] events) {
        // sort events increasing by start time
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        // pq stores the end date of the events that can attend on current day
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = events.length;
        int idx = 0;
        int lastDay = events[n - 1][1];
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            // Add new events that can attend on day `i`
            while (idx < n && events[idx][0] == i) {
                pq.offer(events[idx++][1]);
            }
            // Remove events that are already closed
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            // Use day `i` to attend to the event that closes earlier
            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }
            // quit early
            if (pq.isEmpty() && idx >= n){
                break;
            }
        }
        return count;
    }
}


// no loop on each day
class Solution {
    public int maxEvents(int[][] events) {
        // sort events increasing by start time
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = events.length;
        int idx = 0;
        int day = 0;
        int lastDay = events[n - 1][1];
        int count = 0;
        while (!pq.isEmpty() || idx < n) {
            if (pq.isEmpty()) {
                day = events[idx][0];
            }
            // Add new events that can attend on day `day`
            while (idx < n && events[idx][0] == day) {
                pq.offer(events[idx++][1]);
            }
            // Remove events that are already closed
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }
            // Use day `i` to attend to the event that closes earlier
            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }
            day++;
            // quit early
            if (pq.isEmpty() && idx >= n){
                break;
            }
        }
        return count;
    }
}
