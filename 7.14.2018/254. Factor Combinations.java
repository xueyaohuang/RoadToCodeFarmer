class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), n, -1);
        return result;
    }
    
    private void helper(List<List<Integer>> result, List<Integer> list, int n, int lastNum) {
        if (lastNum != -1) {
            list.add(n);
            result.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
        }
        
        int max = (int)Math.sqrt(n);
        
        for (int i = max; i >= lastNum && i > 1; i--) {
            if (n % i == 0) {
                list.add(i);
                helper(result, list, n / i, i);
                list.remove(list.size() - 1);
            }
        }
    }
}
