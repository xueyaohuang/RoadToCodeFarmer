// 1.
// 时间复杂度是O(n), 因为i每前进一步，idx也前进至少一步
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] res = new int[len - k + 1];
        // 最大值的index
        int idx = -1;
        for (int i = 0; i < len - k + 1; i++) {
            // 若index小于sliding window的起始，就超出了范围，在window
            // size k以内更新最大值的index
            if (idx < i) {
                idx = i;
                for (int j = i + 1; j < i + k; j++) {
                    if (nums[j] > nums[idx]) {
                        idx = j;
                    }
                }
            } else { // 走了一步，更新一步
                if (nums[i + k - 1] > nums[idx]) {
                    idx = i + k - 1;
                }
            }
            res[i] = nums[idx];
        }
        return res;
    }
}

// 2
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int idx = 0;
        // dq存的是index，不是nums[i]。
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            // 删除超出window范围的index
            while (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll();
            }
            // 删除小于nums[i]的index
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i >= k - 1) {
                res[idx++] = nums[dq.peek()];
            }
        }
        return res;
    }
}

// 3
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] res = new int[len - k + 1];
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        res[0] = queue.peek();
        for (int i = k; i < len; i++) {
            queue.remove(nums[i - k]);
            queue.add(nums[i]);
            res[i - k + 1] = queue.peek();
        }
        return res;
    }
}
