# 给一个array，找到第k小（大）的xxx

1. binary search  
典型题目：378 Kth Smallest Element in a Sorted Matrix， 719 Find K-th Smallest Pair Distance  
通常需要sort一遍array，然后确定要找的值的范围，然后在这个范围内用binary search。对于一个mid，若数的个数小于k，end=mid，反之start=mid+1.
一个主要的问题是，怎么用一个O(n)的方法，数出小于mid的个数。

举个栗子,lc287
```
class Solution {
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int start = 1, end = len - 1;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int i = 0; i < len; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
```
这里没有sort array，确定下来duplicate的范围在start = 1, end = len - 1之内，然后在这个范围内用binary search。  
然后需要一个O(n)的方法，确定往mid的哪边缩减。
2. heap  
典型题目：373 Find K Pairs with Smallest Sums， 378 Kth Smallest Element in a Sorted Matrix  
