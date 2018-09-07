class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int forward = 1;
        for (int i = 0; i < len; i++) {
            res[i] = forward;
            forward *= nums[i];
        }
        int backward = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= backward;
            backward *= nums[i];
        }
        return res;
    }
}


class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int forward = 1;
        for (int i = 0; i < len; i++) {
            res[i] = forward;
            forward *= nums[i];
        }
        int backward = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= backward;
            backward *= nums[i];
        }
        return res;
    }
}
//    example:      1    2   3   4   5  
//    forward     : 1    1   2   6   24
//  forward result: 1    1   2   6   24
//    backward    : 120  60  20  5   1
// backward result: 120  60  40  30  24
