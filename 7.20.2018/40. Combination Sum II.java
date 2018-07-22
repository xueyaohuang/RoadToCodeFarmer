class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 必须sort，下面才能判断candidates[i] == candidates[i - 1]
        combinationSum2Helper(candidates, target, res, new ArrayList<Integer>(), 0);
        return res;
    }
    private void combinationSum2Helper(int[] candidates, int target, List<List<Integer>> res, List<Integer> temp, int start) { // 由于是combination，所以需要start记录位置。
        if (target == 0) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) { // i > start && ...跳过重复，如果是permutation，则是i > 0 && ...
                continue;
            }
            if (target >= candidates[i]) {
                temp.add(candidates[i]);
                combinationSum2Helper(candidates, target - candidates[i], res, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
