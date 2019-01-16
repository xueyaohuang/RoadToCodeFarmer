class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        if (n < k || k <= 0 || n <= 0) {
            return list;
        }
        backtrack(n, k, 1, list, new ArrayList<>());
        return list;       
    }
    
    private void backtrack(int n, int k, int start, List<List<Integer>> list, ArrayList<Integer> temp) {   
        if (temp.size() == k) { // 与subsets那个题相比，就多了一个退出条件
            list.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            backtrack(n, k, i + 1, list, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
