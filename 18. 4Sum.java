// k-sum
// O(n^(k-1))
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, 0, 4, target);
    }
    
    private List<List<Integer>> kSum (int[] nums, int start, int k, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k == 2) { 
            //two pointers from left and right
            int left = start, right = len - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> path = new ArrayList<>();
                    path.add(nums[left]);
                    path.add(nums[right]);
                    res.add(path);
                    
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
                left++;
                right--;
            }
        } else {
            for(int i = start; i < len - (k - 1); i++) {
                if(i > start && nums[i] == nums[i - 1]) continue;
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for(List<Integer> t : temp) {
                   t.add(0, nums[i]);
                }                    
                res.addAll(temp);
            }
        }
        return res;
    }
}


class Solution {
    public void twoSum(int[] nums, int target, int left, List<List<Integer>> res, int firstNum, int secondNum){
        int n = nums.length;
        if(nums[left]*2 > target || nums[n-1]*2 < target){
            return;
        }
        int l = left+1, r = n-1;
        while(l<r){
            if(nums[l]+nums[r]==target){
                res.add(Arrays.asList(firstNum, secondNum, nums[l], nums[r]));
                while(l<r && nums[l]==nums[l+1]){
                    l++;
                }
                while(l<r && nums[r]==nums[r-1]){
                    r--;
                }
                l++;
                r--;
            }else if(nums[l]+nums[r]<target){
                l++;
            }else{
                r--;
            }
        }
    }
    
    public void threeSum(int[] nums, int target, int left, List<List<Integer>> res, int firstNum){
        int n = nums.length;
        if(nums[left]*3 > target || nums[n-1]*3 < target){
            return;
        }
        for(int i=left; i< n-2; i++){
            if(nums[i]*3 > target){
                return;
            }
            if(i>left && nums[i]==nums[i-1]){
                continue;
            }
            twoSum(nums, target-nums[i], i, res, firstNum, nums[i]);
        }
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null ||nums.length==0){
            return res;
        }
        Arrays.sort(nums);
        if(nums[0]*4 > target || nums[nums.length-1]*4 < target){
            return res;
        }
        
        for(int i=0; i<nums.length-3; i++){
            if(nums[i]*4 > target){
                return res;
            }
            if(i>0 && nums[i]==nums[i-1]){
                continue; //avoid duplicate
            }
            threeSum(nums, target-nums[i], i+1, res, nums[i]);
        }
        return res;
    }
}
