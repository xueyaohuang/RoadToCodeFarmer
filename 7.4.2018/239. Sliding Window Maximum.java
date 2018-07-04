// Using monotonic queue implemented by deque.

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return new int[0];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int len = nums.length;
        int idx = 0;
        int[] res = new int[len - k + 1];
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1) {
                res[idx++] = nums[deque.peek()];
            }         
        }
        return res;
    }
}
