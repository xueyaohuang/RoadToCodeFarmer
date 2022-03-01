/*
when a number has the same value with its previous, we can use this number only if his previous is used.

First of all, the conclusion is both !use[i - 1] and use[i - 1] work in the second continue if condition, but the !use[i - 1] is more efficient.

Here goes the explanation:
If we use
if(i > 0 && nums[i] == nums[i - 1] && use[i - 1]) continue;
Chances are that we have the same value,and we will never have chance to use the second one, which causes the list will never grow to the length of nums.length, and waste exists.
Here we have an example of the simple [1, 2, 2']. We start at list = [], and in the first level of backtrack, we have:
[1]
[2]
[2']
In the second level, when list = [1], ve:
[1, 2]
[1, 2']
When list = [2], when i = 0 we have [2, 1] without difficulty, when i = 1, "2" is already used so we simply continue. But when i = 2, we found that nums[2] = nums[1] and nums[1] is already used, so we discard it and the list is still [1]. The problem is that when we have to figure out the next element of this track, we will find that the "2'" will never be used because nums[2] == nums[1] && used(nums[1]) is always true. This track will never reach the recursive base and be added to the final answer.

If we use
if(i > 0 && nums[i] == nums[i - 1] && !use[i - 1]) continue;
When we figure out the first element of the list,when i = 2 we find that nums[2] == nums[1] true and used(nums[1]) false, so we directly skip the list starting with "2'", and we get
[1] and
[2]
only. The same rule continues on the next steps so every list we generated will reach to an end and return.
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, res, new ArrayList<>(), used);
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            used[i] = true;
            backtrack(nums, res, temp, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}

// Permutations问题没有start/position这个参数，因为顺序matters，combination里面需要start/position，因为顺序改了算重复。
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, res, new ArrayList<>(), used);
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 与combination相比，多了一个检查used[i - 1]。因为combination不会loop 当前index之前的数，当前index之后的数一定是没有用过的
            // 这种写法是最后一次遇到这种permutation的时候加进去。
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1]) { // 跳过重复，考虑顺序就i > 0 && ...,不考虑顺序就i > start && ...
                continue;
            }
//             // 这种写法是第一次遇到这种permutation的时候加进去。
//             if (i < nums.length - 1 && nums[i] == nums[i + 1] && used[i + 1]) {
//                 continue;
//             }
            temp.add(nums[i]);
            used[i] = true;
            backtrack(nums, res, temp, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}
