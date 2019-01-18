//  O(N) memory, O(N) init, O(1) pick
class Solution {
    
    Map<Integer, List<Integer>> map;
    Random rand;

    public Solution(int[] nums) {
        map = new HashMap<>();
        rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
    }
    
    public int pick(int target) {
        List<Integer> list = map.get(target);
        int size = list.size();
        int idx = rand.nextInt(size);
        return list.get(idx);
    }
}

// O(N) memory, O(1) init, O(N) pick
// reservoir sampling
class Solution {
    
    int[] nums;
    Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }
    
    public int pick(int target) {
        int res = -1;
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (rand.nextInt(count++) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
