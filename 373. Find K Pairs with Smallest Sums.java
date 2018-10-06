class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return new ArrayList<>();
        }
        
        int len1 = nums1.length, len2 = nums2.length;
        List<int[]> res = new ArrayList<>();
        // min queue, sorted by pair sum
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        // offer initial pairs {num1, num2, index_of_num2}
        // get first k elem into result
        for (int i = 0; i < Math.min(len1, k); i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        // each time, offer potential better pairs into queue
        // if there r not enough pair, just return all pairs
        for (int i = 0; i < Math.min(k, len1 * len2); i++) {
            // get the best pair and put into res
            int[] cur = pq.poll();
            res.add(new int[]{cur[0], cur[1]});
            // next better pair could with be A: {after(num1), num2} or B: {num1. after(num2)}
            // for A, we've already added top possible k into queue, so A is either in the queue already, or not qualified
            // for B, it might be a better choice, so we offer it into queue
            int idx2 = cur[2];
            // add B, but if there if no element in nums2, continue next iteration
            if (idx2 != len2 - 1) {
                pq.offer(new int[]{cur[0], nums2[idx2 + 1], idx2 + 1});
            }
        }
        return res;
    }
}
