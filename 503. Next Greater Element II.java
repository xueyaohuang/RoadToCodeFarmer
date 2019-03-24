class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                    int idx = stack.pop();
                    res[idx] = nums[i];
                }
                stack.push(i);
            }
        }
        return res;
    }
}
