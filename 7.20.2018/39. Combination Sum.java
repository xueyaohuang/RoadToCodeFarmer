class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // pos for the index is needed but the visited array is not nesseary B/C duplicates are find
        List<List<Integer>> res=new ArrayList<>();
        combinationSum(0, new ArrayList<>(), candidates, target, res);
        return res;
    }
    private void combinationSum(int pos, List<Integer> cur, int[] candidates, int target, List<List<Integer>> res){
        // if(target<0) return;
        if(target==0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        
        for(int i=pos; i<candidates.length; i++){
            if(target>=candidates[i]){
                cur.add(candidates[i]);
                combinationSum(i, cur, candidates, target-candidates[i], res);
                cur.remove(cur.size()-1);
            }
        }
    }
}
