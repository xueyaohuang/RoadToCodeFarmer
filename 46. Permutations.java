// backtrack
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // nums[i]是否用过，记录的其实是idx，而不是value
        boolean[] used = new boolean[nums.length];
        backtracking(nums, res, new ArrayList<>(), used);
        return res;
    }
    
    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            temp.add(nums[i]);
            used[i] = true;
            backtracking(nums, res, temp, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<Integer>());
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<Integer>(temp));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!temp.contains(nums[i])) {
                temp.add(nums[i]);
                backtrack(nums, res, temp);
                temp.remove(temp.size() - 1); // 以nums[i]开头的都穷举完了，所以删了换下一个开头的。
            }
        }
    }
}
