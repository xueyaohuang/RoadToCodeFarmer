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
Arrays.sort(nums1);
Arrays.sort(nums2);
int len = nums.length;
int[] res = new int[len];
int idx = 0;
for (int i = 0; i < len; i++) {
    while (nums1[idx] <= nums2[i]) {
        idx++;
    }
    res[i] = idx;
}
```

* [数组排序，先按照频率，后按照数值](https://leetcode.com/problems/top-k-frequent-words/description/)
* [数组求最大和，最多允许跳过一个连续的元素，不能一次跳两个以上](https://www.geeksforgeeks.org/maximum-sum-subarray-removing-one-element/)
* [graph connected components](https://leetcode.com/problems/number-of-islands/description/)
* 一堆用户名,有一样的在后面加个数字以便区分。。。：[alice, alice, alice]->[alice, alice1, alice2]
```
    public static List<String> modifyNames(String[] names) {
        if (names == null || names.length == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String name : names) {
            if (!map.containsKey(name)) {
                res.add(name);
            } else {
                String surffix = String.valueOf(map.get(name));
                res.add(name + surffix);
            }
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        return res;
    }
```

* 给一个list，对其中一样的数字increment by 1，直到没有重复的数字为止。：[1,2,2,3,3]->[1,2,3,4,5]
sort, if nums[i+1]<=nums[i], nums[i+1]=nums[i] + 1
```
HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            set.add(num);
            sum += num;
        }
        int diff = 0;
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (freq > 1) {
                int y = key + 1;
                for (int i = 0; i < freq - 1; i++) {
                    while (set.contains(y)) {
                        y++;
                    }
                    set.add(y);
                    diff += y - key;
                }
            }
        }
        return sum + diff;
```
* 有很多火柴棍，每一次扔掉所有最小的棍子，然后剩余的棍子都减去扔掉的棍子，求直到没有棍子的时候 每次剩余棍子的数量
sort, 从小到大，len依次减去相同数字的个数
https://www.hackerrank.com/contests/101feb14/challenges/cut-the-sticks/submissions/code/1310424704
* zombies找相同, [lc friend cycle](https://leetcode.com/problems/friend-circles/description/)

* 给一个String s， 找s的substring中，字典序最大的substring

```
public static String largestSubstring(String s) {
    String ss = "";
    for (int i = 0; i < s.length(); i++) {
        String cur = s.substring(i);
        if (cur.compareTo(ss) > 0) {
            ss = cur;
        }
    }
    return ss;
}
```
