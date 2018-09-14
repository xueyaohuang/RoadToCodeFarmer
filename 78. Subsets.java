// backtrack, 如果设置了start参数，就是遍历所有可能的combination。如果没有start，每次递归都从第一个element开始，则便利所有可能的permutation。
// 这个题就是枚举所有可能的组合，没有任何限制，相当于没有退出条件，上来就把temp加入res。
// 这种方法，从头到尾一直用同一个temp在尝试各种组合。
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<>(), 0);
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp, int start) {
        res.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(nums, res, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
