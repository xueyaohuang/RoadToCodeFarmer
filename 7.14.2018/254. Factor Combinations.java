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

public class Solution {
public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> res = new ArrayList<>();
    backTrack(res, new ArrayList<Integer>(), 2, n);
    return res;
}

public void backTrack(List<List<Integer>> res, List<Integer> cur, int start, int n) {
    int upper = (int)Math.sqrt(n);
    for(int i = start; i <= upper; i++) {
        int factor = -1;
        if(n % i == 0) {
            factor = n/i;
        }
        if(factor != -1 && factor >= i) {
            cur.add(i);
            cur.add(factor);
            res.add(new ArrayList<Integer>(cur));
            cur.remove(cur.size()-1);
            backTrack(res, cur, i, factor);
            cur.remove(cur.size()-1);
        }
    }
}
