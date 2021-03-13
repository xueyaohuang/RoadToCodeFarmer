// O(nlgn)
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        Arrays.sort(nums);
        int res = 1;
        for (int i : nums) {
            if (res == i) {
                res++;
            }
        }
        return res;
    }
}

/*O(n)
Put each number in its right place.

For example:

When we find 5, then swap it with A[4].

At last, the first place where its number is not right, return the place + 1.
For anyone who doubts on the complexity of this algorithm: just consider position with A[i] = i+1 as a CORRECT SLOT.

    CORRECT SLOT will never be changed: for CORRECT SLOT, A[A[i] - 1] always equals to A[i], vice versa (1)
    For each swap, the number of CORRECT SLOT increases by at least 1: Position A[A[i] - 1] = A[i] becomes a CORRECT SLOT after swap, and according to (1), this must be a new CORRECT SLOT
    The maximum of CORRECT SLOT <= N

Therefore, the complexity is O(N)
*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            // 这里必须用while，因为可能当前不对的换走了，新来的还是不对
            // 比如[3,4,-1,1]
            // i=0，换一次后是[-1,4,3,1]
            // i=1，换一次后是[-1,1,3,4]，此时nums[1]=1，还需要把1换到nums[0]的位置
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }       
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
