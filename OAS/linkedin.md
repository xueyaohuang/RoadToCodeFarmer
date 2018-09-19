* [sum of all subarrays](https://www.geeksforgeeks.org/sum-of-all-subarrays/)
* [Maximum Difference in an Array](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/)
* [Braces](https://leetcode.com/problems/valid-parentheses/description/)
* [coin change](https://leetcode.com/problems/coin-change/description/)
* parity permutation

```
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
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
            if (temp.size() == 0 || nums[i] % 2 != temp.get(temp.size() - 1) % 2) {
                temp.add(nums[i]);
                used[i] = true;
                backtrack(nums, res, temp, used);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }
}
```

* 给定一个string s, 两个integer L和R，先把s往左shift L次，再把s往右shift R次；举例说的话就是s = 'abcd', L = 1, R = 2, 输出结果就是'bcda' (wrong??)我用了做差后加上长度，取关于长度余数得到了左移步数，然后就是部分reverse再整体reverse

* simple queries. 我是对2个数组排序，遍历第二个数组，two pointers

```
array nums1, nums2
int[] res = new int[len];
int idx = 0;
for (int i = 0; i < len; i++) {
    while (nums1[idx] <= nums2[i]) {
        idx++;
    }
    res[i] = idx;
}
```
