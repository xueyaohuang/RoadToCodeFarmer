class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, res, new ArrayList<Integer>(), target, 0);// Permutations问题没有start/position这个参数，因为顺序matters，combination里面需要start/position，因为顺序改了算重复。不需要visited，因为可以有重复元素。
        return res;
    }
    private void backtrack(int[] candidates, List<List<Integer>> res, List<Integer> temp, int target, int start) {
        if (target == 0) { // target随加入的candidate改变，退出条件是target == 0，这样就不用检查list的元素和是target。
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target >= candidates[i]) { // target >= candidates[i]才能加，避免检查target < 0 的情况。
                temp.add(candidates[i]);
                backtrack(candidates, res, temp, target - candidates[i], i); // 由于set中的元素可以重复使用多次，所以recursio还是从i开始
                temp.remove(temp.size() - 1); // 返回上一级recursion，所以要移除当前level递归加入的元素
            }
        }
    }
}
//没有重复结果是由Combination，在recursion里i从start开始，而不是从0开始保证的。
//与Combination Sum 2的区别还在于这个题输入是没有重复元素的，所以不用sort，然后检查if (i > start && candidates[i] == candidates[i - 1])。

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(candidates, target, res, new ArrayList<>(), 0, 0);
        return res;
    }
    
    private void backtracking(int[] candidates, int target, List<List<Integer>> res, List<Integer> temp, int start, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }
            temp.add(candidates[i]);
            sum += candidates[i];
            backtracking(candidates, target, res, temp, i, sum);
            temp.remove(temp.size() - 1);
            sum -= candidates[i];
        }
    }
}
