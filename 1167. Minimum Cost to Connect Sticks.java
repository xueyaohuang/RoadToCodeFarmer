// greedy, 先合并长度短的
class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : sticks) {
            pq.offer(i);
        }
        int res = 0;
        while (pq.size() > 1) {
            int cur = pq.poll() + pq.poll();
            res += cur;
            pq.offer(cur);
        }
        return res;
    }
}
