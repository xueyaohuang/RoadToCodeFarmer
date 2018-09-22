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

* minimum unique array sum 给一个list，对其中一样的数字increment by 1，直到没有重复的数字为止。：[1,2,2,3,3]->[1,2,3,4,5]
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

* last substring 给一个String s， 找s的substring中，字典序最大的substring

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

* 给定一个数组counts, 每个index代表一个ID，每个element代表ID所在的组的大小，组里只能有element这么多个ID，把element一样的ID归到一个或多个组里，按从小到大顺序输出所有组

```
import java.util.*;

public class MyClass {
    
    public static List<List<Integer>> someSort(int[] count) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        int len = count.length;
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(count[i])) {
                map.put(count[i], new ArrayList<Integer>());
            }
            map.get(count[i]).add(i);
        }
        
        for (int i : map.keySet()) {
            Iterator<Integer> iter = map.get(i).iterator();
            int num = map.get(i).size();
            int numLists = num / i;
            for (int j = 0; j < numLists; j++) {
                List<Integer> temp = new ArrayList<>();
                for (int k = 0; k < i; k++) {
                    temp.add(iter.next());
                }
                res.add(temp);
            }
        }
        Collections.sort(res, (a, b) -> a.get(0) - b.get(0));
        return res;
    }
    
    public static void main(String args[]) {
        int[] count = new int[]{3,3,3,3,3,1,3};
        List<List<Integer>> res = someSort(count);

        System.out.println(res);
    }
}
```

* 把数字按binary格式里的1的个数排序 [5, 16,11,6] => [16, 5, 6, 11]


```
import java.util.*;

public class MyClass {
    
    public static List<Integer> someSort(int[] nums) {
        List<Integer>[] bucket = new ArrayList[32];
        List<Integer> res = new ArrayList<>();
        
        for (int i : nums) {
            int numBits = countBits(i);
            if (bucket[numBits] == null) {
                bucket[numBits] = new ArrayList<>();
            }
            bucket[numBits].add(i);
        }
        for (List<Integer> list : bucket) {
            if (list != null) {
                res.addAll(list);
            }
        }
        return res;
    }
    
    private static int countBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }
    
    public static void main(String args[]) {
        int[] nums = new int[]{3, 5, 6, 1, 2, 4 };
        List<Integer> res = someSort(nums);

        System.out.println(res);
    }
}
```
