// naive answer, O(m + n)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] num = new int[len / 2 + 1];
        int idx = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length && idx <= len / 2) {
            if (nums1[i] >= nums2[j]) {
                num[idx++] = nums2[j++];
            } else {
                num[idx++] = nums1[i++];
            }
        }
        while (i < nums1.length && idx <= len / 2) {
            num[idx++] = nums1[i++];
        }
        while (j < nums2.length && idx <= len / 2) {
            num[idx++] = nums2[j++];
        }
        if (len % 2 == 0) {
            return (num[len / 2] + num[len / 2 - 1]) / 2.0;
        }
        return (double)num[len / 2];
    }
}

// optimize memory
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int med1 = 0, med2 = 0;
        int idx = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length && idx <= len / 2) {
            med1 = med2;
            if (nums1[i] >= nums2[j]) {
                med2 = nums2[j++];
            } else {
                med2 = nums1[i++];
            }
            idx++;
        }
        while (i < nums1.length && idx <= len / 2) {
            med1 = med2;
            med2 = nums1[i++];
            idx++;
        }
        while (j < nums2.length && idx <= len / 2) {
            med1 = med2;
            med2 = nums2[j++];
            idx++;
        }
        if (len % 2 == 0) {
            return (med1 + med2) / 2.0;
        }
        return (double)med2;
    }
}

// https://leetcode.com/problems/median-of-two-sorted-arrays/solution/

/*
nums1[0], nums1[1], .... nums1[i - 1],   nums1[i], nums1[i + 1], ... nums1[len1 - 1]
nums2[0], nums2[1], .... nums2[j - 1],   nums2[j], nums2[j + 1], ... nums2[len2 - 1]
1. find i, j, such that nums1[i - 1] <= nums2[j] and nums2[j - 1] <= nums1[i]

2. median = (max(left_part) + min(right_part)) / 2

3. i + j = len1 − i + len2 − j (or len1 − i + len2 − j + 1), so that  i = 0 ~ len1, j = (len1 + len2 + 1) / 2 - i (can cover both even and odd length case)

4. why we need len1 <= len2? -> make sure j is non-negative

5. so we need to Searching i in [0, len1], to find an index i such that:
nums1[i - 1] <= nums2[j] and nums2[j - 1] <= nums1[i], where j = (len1 + len2 + 1) / 2 - i

6. when i is found, res = max(nums1[i - 1] + nums2[j - 1]) if len1 + len2 is odd
res = (max(nums1[i - 1], nums2[j - 1]) + min(nums1[i], nums2[j])) / 2 if len1 + len2 is even

7. corner case: i = 0, i = len1, j = 0, j = len2 where nums1[i - 1] + nums2[j - 1], nums1[i], nums2[j] may not exist
if i = 0, then nums1[i−1] doesn't exist, then we don't need to check nums1[i-1] <= nums2[j]

8. so we need to Searching i in [0, len1], to find an index i such that:
(i = 0 || j = len2 || nums1[i - 1] <= nums2[j]) and (j = 0 || i = len1 || nums2[j - 1] <= nums1[i]), where j = (len1 + len2 + 1) / 2 - i

9. notice that if i > 0, then j must < len2, so for i = 0 || j = len2, we only need to check i = 0. for j = 0 || i = len1, we only need to check i = len1. aka. only check i.
*/ 

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len1 = nums1.length, len2 = nums2.length;
        int start = 0, end = len1;
        
        while (start <= end) {
            int i = (start + end) / 2;
            int j = (len1 + len2 + 1) / 2 - i;
            if (i > 0 && nums1[i - 1] > nums2[j]) { // i is too large
                end = i - 1;
            } else if (i < len1 && nums2[j - 1] > nums1[i]) { // i is too small
                start = i + 1;
            } else {  // find i
                int leftMax = 0;
                if (i == 0) { // 当i==0，nums2[j - 1]是否小于nums1[i]已经不重要了，反正nums1全部都是right part
                    leftMax = nums2[j - 1];
                } else if (j == 0) {
                    leftMax = nums1[i - 1];
                } else {
                    leftMax = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((len1 + len2) % 2 == 1) {
                    return leftMax;
                }
                
                int rightMin = 0;
                if (i == len1) {
                    rightMin = nums2[j];
                } else if (j == len2) {
                    rightMin = nums1[i];
                } else {
                    rightMin = Math.min(nums1[i], nums2[j]);
                }
                
                return (leftMax + rightMin) / 2.0;
            }
        }
        return 0.0;
    }
}



