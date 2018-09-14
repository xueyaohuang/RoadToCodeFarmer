// Permutations问题没有start/position这个参数，因为顺序matters，combination里面需要start/position，因为顺序改了算重复。
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, res, new ArrayList<>(), used);
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 与combination相比，多了一个检查used[i - 1]。因为combination不会loop 当前index之前的数，当前index之后的数一定是没有用过的
            // 这种写法是最后一次遇到这种permutation的时候加进去。
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1]) { // 跳过重复，考虑顺序就i > 0 && ...,不考虑顺序就i > start && ...
                continue;
            }
//             // 这种写法是第一次遇到这种permutation的时候加进去。
//             if (i < nums.length - 1 && nums[i] == nums[i + 1] && used[i + 1]) {
//                 continue;
//             }
            temp.add(nums[i]);
            used[i] = true;
            backtrack(nums, res, temp, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}
