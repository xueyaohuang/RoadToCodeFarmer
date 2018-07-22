// Permutations问题没有start/position这个参数，因为顺序matters，combination里面需要start/position，因为顺序改了算重复。
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();       
        List<Integer> list = new ArrayList<>();
        int[] visited = new int[nums.length];
        Arrays.sort(nums);
        dfs(nums, result, list, visited);
        return result;
    }
    
    void dfs(int[] nums, List<List<Integer>> result, List<Integer> list, int[] visited) {       
        if(list.size() == nums.length) {
            result.add(new ArrayList(list));
            return;
        }
        
        for(int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i - 1] == nums[i] && visited[i - 1] == 1) { // 跳过重复，考虑顺序就i > 0 && ...,不考虑顺序就i > start && ...
                continue;
            }
            if(visited[i] == 0) {
                visited[i] = 1;
                list.add(nums[i]);
                dfs(nums, result, list, visited);
                list.remove(list.size() - 1);
                visited[i] = 0;
            }
        }
    }
}
