// First turn the input into a set of numbers. 
// That takes O(n) and then we can ask in O(1) whether we have a certain number.

// Then go through the numbers. If the number x is the start of a streak (i.e., x-1 is not in the set), 
// then test y = x+1, x+2, x+3, ... and stop at the first number y not in the set. 
// The length of the streak is then simply y-x and we update our global best with that. 
// Since we check each streak only once, this is overall O(n)

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int res = 1;
        int len = nums.length;
        // 每个数都只会被检查一次，所以是O(n)的复杂度
        for (int i = 0; i < len; i++) {
            // !set.contains(num - 1) 表明 num是当前Consecutive Sequence的起始位置
            if (!set.contains(nums[i] - 1)) {
                int end = nums[i];
                while (set.contains(end + 1)) {
                    end++;
                }
                res = Math.max(res, end - nums[i] + 1);
            }
        }
        return res;
    }
}


// We will use HashMap. The key thing is to keep track of the sequence length and store 
// that in the boundary points of the sequence. For example, as a result, 
// for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.

// Whenever a new element n is inserted into the map, do two things:

// 1. See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing
// sequence next to n. Variables left and right will be the length of those two sequences,
// while 0 means there is no sequence and n will be the boundary point later. 
// Store (left + right + 1) as the associated value to key n into the map.

// 2. Use left and right to locate the other end of the sequences to the left and 
// right of n respectively, and replace the value with the new length.


class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        // key: Consecutive Sequence 的开头或结尾
        // value: Consecutive Sequence 的长度
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int len = left + right + 1;
                map.put(num, len);
                res = Math.max(res, len);
                map.put(num - left, len);
                map.put(num + right, len);
            }
        }
        return res;
    }
}

// naive nlg(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 1, cur = 1;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] == nums[i - 1] + 1) {
                cur++;
            } else {
                res = Math.max(res, cur);
                cur = 1;
            }
        }
        res = Math.max(res, cur);
        return res;
    }
}
