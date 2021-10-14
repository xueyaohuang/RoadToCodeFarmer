// O(n)
// 转换思路，数每一位上面的Hamming Distance
// 一共32bits，对每一个bit，数有多少个1（x），0的个数就是len-x
// 这一位上的Hamming Distance只有在不同时才不是0，需要从1中选一个，0中选一个，所以就是x*(len-x)
class Solution {
    public int totalHammingDistance(int[] nums) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += getHD(nums, i);
        }
        return count;
    }
    public int getHD(int[] arr, int b) {
        int countOne = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            countOne += (arr[i] >> b) & 1;
        }
        return countOne * (len - countOne);
    }
}
