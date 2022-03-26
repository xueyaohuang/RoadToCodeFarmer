/*
before coding I also thought about this solution and I did’t think that will work, as it appears to be very naïve and greedy:
find first smallest, then find second smallest, then find the third and bingo. I argued myself it cannot pass the case like
[1,2,0,3] since c1 is changed.

But when I take a closer look, it does [1,2,0,3] very well. And I realize that c1 and c2 are indeed having the meaning of:

C1 = so far best candidate of end element of a one-cell subsequence to form a triplet subsequence

C2 = so far best candidate of end element of a two-cell subsequence to form a triplet subsequence

So c1 and c2 are the perfect summary of history.
*/
// 以[1,2,0,3]为例，如果能return true，说明之前已经找到（过）在i前面2个比nums[i]小的数，如果能更新second = nums[i]
// 说明在之前找到（过）在i前面1个比nums[i]小的数。这个题返回有没有 Increasing Triplet Subsequence，所以不求现在最小
// 的在first在second前面，只求曾今找到过first在second前面就可以了。
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int len = nums.length;
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            // 注意是<=
            if (nums[i] <= first) {
                first = nums[i];  // 先更新最小的，这样保证最小的一定（曾今）在第二小的之前
            } else if (nums[i] <= second) {
                second = nums[i];  // 再更新第二小的，这样保证第二小的一定在第三之前
            } else {
                return true;  // 跳过了前两个，这个比前两个大，又能保证顺序，返回true
            }
        }
        return false;
    }
}

// Extended to increasing k subsequence
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int k = 3;
        int[] arr = new int[k - 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        for (int i : nums) {
            for (int j = 0; j < arr.length; j++) {
                if (i <= arr[j]) {
                    arr[j] = i;
                    break;
                } else if (j == arr.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

// 怎样输出正确的triplet
// we update c1 and c2 as always, but only if when we update both of them, we update the result, 
// i.e. the pair(I store the value as result, but we can also change it to index
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int[] res = new int[3]; // here I assume we are just asked to record one of the result!
        int c1 = Integer.MAX_VALUE, c2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= c1) {
                c1 = num; 
            } else if (num <= c2) {
                c2 = num; 
                res[0] = c1; // update
                res[1] = c2; // update
            } else { 
                res[2] = num;
                // System.out.println(Arrays.toString(res));
                return true;
            }
        }
        return false;
    }
}
