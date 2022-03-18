class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        Comparator<Integer> comparator = (a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]);
        // 用一样的comparator，但是需要取left的最大和right的最小，所以对于left是last(), 对于right是first()
        // 保证left的size不小于right
        TreeSet<Integer> left = new TreeSet<>(comparator);
        TreeSet<Integer> right = new TreeSet<>(comparator);
        for (int i = 0; i < nums.length; i++) {
            left.add(i);
            right.add(left.pollLast());
            while (right.size() > left.size()) {
                left.add(right.pollFirst());
            }
            if (i + 1 >= k) {
                res[i + 1 - k] = k % 2 == 0 ? nums[left.last()] / 2.0 + nums[right.first()] / 2.0 : nums[left.last()];
                if (!left.remove(i + 1 - k)) {
                    right.remove(i + 1 - k);
                }
            }
        }
        return res;
    }
}
