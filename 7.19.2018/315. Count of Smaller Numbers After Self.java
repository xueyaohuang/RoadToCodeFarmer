class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
        	return res;
        }
        for (int i = 0; i < nums.length - 1; i++) {
        	res.add(countAfter(nums, i));
        }
        res.add(0);
        return res;
    }
    private int countAfter(int[] nums, int i) {
    	int len = nums.length;
    	int count = 0;
    	for (int j = i + 1; j < len; j++) {
            if (nums[i] > nums[j]) {
            	count++;
            }
    	}
    	return count;
    }
}
