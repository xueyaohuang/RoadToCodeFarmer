// 2 O(n)每个idx都enqueue和dequeue一次
// deque,index进queue都是从tail进去，出去时分2种情况
// 删除超出window范围的index从head出去，因为先进先出
// 比nums[i]小的index从tail出去，monotonic queue的出法，都是从tail出
// 因为有两个方向的dequeue操作，所以需要deque
/*
We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.

At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means

    If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array

    Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.

    As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int idx = 0;
        // dq存的是index，不是nums[i]。
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // 删除超出window范围的index，从head删除
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            // 删除小于nums[i]的index，从tail删除
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.pollLast();
            }
            // 从tail进去
            dq.offerLast(i);
            if (i >= k - 1) {
                res[idx++] = nums[dq.peekFirst()];
            }
        }
        return res;
    }
}

// 2.
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
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[] res = new int[nums.length - k + 1];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.remove(nums[i - k]);
            }
            if (pq.size() == k) {
                res[count++] = pq.peek();
            }
        }
        return res;
    }
}
