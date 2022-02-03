/*
这题是239. Sliding Window Maximum的变种
Sliding Window的题目都对window有一定的限制，比如239是window的长度是k
这个题的限制是window内最大值和最小值的差不能大于limit
为什么联想到sliding window，因为题目要找的是Continuous Subarray。。。

所以问题的本质是Sliding Window Maximum and minimum，做滑窗的同时，能随时获得窗口中最大值和最小值。
可以用treemap，因为treemap的key是sorted，可以很容易获得最大和最小
也可以像239一样用monotonic queue，在monotonic queue的head处保留最大或最小
*/
// treemap, O(nlgn)
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int max = 0, min = 0;
        int res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            max = map.lastKey();
            min = map.firstKey();
            while (max - min > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
                max = map.lastKey();
                min = map.firstKey();
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}

// monotonic queue, O(n)
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int res = 0, left = 0;
        Deque<Integer> maxDq = new ArrayDeque<>();
        Deque<Integer> minDq = new ArrayDeque<>();
        for (int right = 0; right < nums.length; right++) {
            while (!maxDq.isEmpty() && nums[right] > maxDq.peekLast()) {
                maxDq.pollLast();
            }
            maxDq.offerLast(nums[right]);
            while (!minDq.isEmpty() && nums[right] < minDq.peekLast()) {
                minDq.pollLast();
            }
            minDq.offerLast(nums[right]);
            while (maxDq.peekFirst() - minDq.peekFirst() > limit) {
                if (maxDq.peekFirst() == nums[left]) {
                    maxDq.pollFirst();
                }
                if (minDq.peekFirst() == nums[left]) {
                    minDq.pollFirst();
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
