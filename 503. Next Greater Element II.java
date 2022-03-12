class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.fill(res, -1);
        for (int j = 0; j < 2; j++) { // loop nums twice
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                    res[stack.pop()] = nums[i];
                }
                stack.push(i);
            }
        }
        return res;
    }
}
