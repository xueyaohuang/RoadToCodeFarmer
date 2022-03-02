class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(n, k, 1, res, new ArrayList<>());
        return res;
    }
    
    private void backtracking(int n, int k, int start, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == k)  {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            backtracking(n, k, i + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
