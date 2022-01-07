// sliding window
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        // idxLeftMostOdd:有k个odd的window的最左边odd的index
        // start:window的最左边
        // end:window的最右边
        // 比如nums = [2,2,2,1,2,2,1,2,2,2], k = 2这个例子中，2,2,2,1,2,2,1是一个满足条件的window
        // start=0, idxLeftMostOdd=3, end=6
        int count = 0, idxLeftMostOdd = 0, start = 0;
        // 找到第一个odd的index
        while (idxLeftMostOdd < nums.length && nums[idxLeftMostOdd] % 2 == 0) {
            idxLeftMostOdd++;
        }

        for (int end = 0; end < nums.length; end++) {
            if (nums[end] % 2 == 1) {
                k--;
            }
            
            if (k < 0) { // more than k odds in window, need to shrink from low bound.
                // 因为每次end前进一个idx，start这样更新后，新的window必定满足条件
                start = idxLeftMostOdd + 1;
            }
            //把k恢复到0
            while (k < 0) {
                idxLeftMostOdd++;
                if (nums[idxLeftMostOdd] % 2 == 1) {
                    k++;
                }
            }
            // 只有k=0时才更新count
            if (k == 0) {
                count += idxLeftMostOdd - start + 1;
            }
        }
        return count;
    }
}

// prefix sum
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int acc = 0;
        int count = 0;
        for (int n : nums) {
            if (n % 2 == 1) {
                acc++;
            }
            map.put(acc, map.getOrDefault(acc, 0) + 1);
            count += map.getOrDefault(acc - k, 0);
        }
        return count;
    }
}
