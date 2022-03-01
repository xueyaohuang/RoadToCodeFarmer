// Time: O(C(9,k))-> O(9^k), space: k
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(k, n, 0, 1, res, new ArrayList<>());
        return res;
    }
    
    private void backtracking(int k, int n, int sum, int start, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = start; i < 10; i++) {
            if (sum + i > n) {
                continue;
            }
            sum += i;
            temp.add(i);
            backtracking(k, n, sum, i + 1, res, temp);
            sum -= i;
            temp.remove(temp.size() - 1);
        }
    }
}

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(k, n, res, new ArrayList<>(), 1);
        return res;
    }
    
    private void dfs(int k, int n, List<List<Integer>> res, List<Integer> temp, int start) {
        if (temp.size() == k) { // size == k一定退出
            if (n == 0) { // 只有n == 0才加入res
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i <= n) {
                temp.add(i);
                dfs(k, n - i, res, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

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
