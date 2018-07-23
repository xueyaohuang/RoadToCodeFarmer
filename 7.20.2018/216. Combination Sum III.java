class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(k, n, 1, res, new ArrayList<Integer>());
        return res;
    }

    private void helper(int k, int n, int start, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == k && n == 0) { // 修改了recursion退出条件
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (temp.size() < k && n >= i) { // 添加temp.size() < k的判定条件
                temp.add(i);
                helper(k, n - i, i + 1, res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
