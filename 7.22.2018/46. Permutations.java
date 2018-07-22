// backtrack
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(nums, list, new ArrayList<Integer>());
        return list;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> list, List<Integer> temp) {
        if (temp.size() == nums.length) {
            list.add(new ArrayList<Integer>(temp));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!temp.contains(nums[i])) {
                temp.add(nums[i]);
                backtrack(nums, list, temp);
                temp.remove(temp.size() - 1);
            } 
        }
    }
}
