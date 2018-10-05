class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                count += (nums[j] >> i) & 1;
            }
            count %= 3;
            res |= (count << i);
        }
        return res;
    }
}
/*
The usual bit manipulation code is bit hard to get and replicate. I like to think about the number in 32 bits and just count
how many 1s are there in each bit, and count %= 3 will clear it once it reaches 3. After running for all the numbers for each
bit, if we have a 1, then that 1 belongs to the single number, we can simply move it back to its spot by doing ans |= count << i;

This has complexity of O(32n), which is essentially O(n) and very easy to think and implement.
Plus, you get a general solution for any times of occurrence. Say all the numbers have 5 times, just do count %= 5.
*/
