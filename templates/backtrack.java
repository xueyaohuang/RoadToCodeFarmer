// 46. Permutations
// backtrack
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<Integer>());
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<Integer>(temp));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!temp.contains(nums[i])) {
                temp.add(nums[i]);
                backtrack(nums, res, temp);
                temp.remove(temp.size() - 1); // 以nums[i]开头的都穷举完了，所以删了换下一个开头的。
            }
        }
    }
}

// DFS 和backtrack一样，只不过用visited代替!temp.contains(nums[i])来检查nums[i]是否用过了
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, res, new ArrayList<Integer>(), visited);
        return res;
    }
    
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<Integer>(temp));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp.add(nums[i]);
                dfs(nums, res, temp, visited);
                temp.remove(temp.size() - 1);
                visited[i] = false;
            }
        }
    }
}

// 47. Permutations II
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
            if (i > 0 && nums[i - 1] == nums[i] && visited[i - 1] == 1) {
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

// 
