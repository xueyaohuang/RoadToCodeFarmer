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
                backtrack(candidates, res, temp, target - candidates[i], i);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
/*
[10,1,2,7,6,1,5]
4
Your answer: [[1,1,1,1],[1,1,1,1],[1,1,2],[1,1,1,1],[1,2,1],[1,1,1,1],[2,2],[2,1,1],[1,1,1,1]]
Expected answer: [[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,2],[1,1,1,1],[1,1,2],[1,1,1,1],[1,1,2],[2,2]]
结果不考虑[]里的顺序。看似有很多重复的，比如[1,1,1,1],[1,1,1,1]，但是这些1是同一个1，比如一个是candidates[1],一个是candidates[5]。
*/
