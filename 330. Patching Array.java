// greddy
/*
[1,2,3,5,10,50,70], n=100

    Seeing 1, we know [1,1] can be covered
    Seeing 2, we know [1,3] can be covered
    Similarly for 3, [1,6] can be covered
    for 5, [1,11] can be covered
    for 10, [1, 21] can be covered
    for 50, however, we have to add a patch, if the patch is 1, the range can be extended to [1, 22], if the patch is 2, the range can be extended to [1, 23]...
    From the observation we know in order to extend the range as longer as possible, we need to add 22, so that we get [1,43]. Why not add 23?
    Because [1,2,3,5,10,23] can NOT cover 22!
    [1,43] does not cover 50 yet. Following a similar way of thinking, we know this time we need to add 44, extending the range to [1, 87]
    for 70, it's already in [1,87], add 70 would extend the range to [1,157]
    157 > 100, done

In conclusion, we need 2 patches, i.e., 22 and 44.
So the key point is if the current range is [1,m], and the current number > m, we need to add m+1 as a patch, to extend the range to [1, 2m+1].
*/
class Solution {
    public int minPatches(int[] nums, int n) {
        long sum = 0;
        int count = 0;
        int idx = 0;
        while (sum < n) {
            if (idx < nums.length && nums[idx] <= sum + 1) {
                sum += nums[idx];
                idx++;
            } else {
                sum += sum + 1;
                count++;
            }
        }
        return count;
    }
}
