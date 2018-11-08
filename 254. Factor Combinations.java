// 1. basic backtrack solution, exactly the same as combination sum.
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<Integer>(), n, 2);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> temp, int n, int start) {
        if (n == 1 && temp.size() > 1) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                temp.add(i);
                backtrack(res, temp, n / i, i);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

// reduce for loop by add upper bound for i, if i > upper, i = n directly.
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<Integer>(), n, 2, (int)Math.sqrt(n));
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> temp, int n, int start, int upper) {
        if (n == 1 && temp.size() > 1) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (i > upper) {
                i = n;
            }
            if (n % i == 0) {
                temp.add(i);
                backtrack(res, temp, n / i, i, (int)Math.sqrt(n / i));
                temp.remove(temp.size() - 1);
            }
        }
    }
}
