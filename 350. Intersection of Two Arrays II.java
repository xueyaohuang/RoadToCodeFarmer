// https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/439955/Java-Solution-%2B-4-FOLLOW-UPS

// original
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                list.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }
        int[] res = new int[list.size()];
        int j = 0;
        for (int i : list) {
            res[j++] = i;
        }
        return res;
    } 
}

// followup 1
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int k = 0;
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res[k++] = nums1[i++];
                j++;
            }
            else if (nums1[i] < nums2[j]) {
                i++;
            }
            else {
                j++;
            }
        }
        return Arrays.copyOfRange(res, 0, k);
    }
}

// followup 2
// If the arrays are not sorted, then the HashMap solution is faster.
// If the arrays are sorted, then we can loop through nums1 (the smaller array), and for each value, binary search it in
// nums2 (the larger array). Implementation becomes tricky since duplicate values are allowed.
// Time Complexity: O(m log n) if arrays are already sorted for us, where m is size of smaller array and n is size of
// larger array. Space Complexity: O(m) to generate the intersection.
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        } else if (nums1.length > nums2.length) {
            // ensures 1st array is shorter than 2nd.
            return intersect(nums2, nums1);
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> intersection = new ArrayList();
        int leftIndex = 0;
        for (int i = 0; i < nums1.length; i++) {
            int index = binarySearch(nums2, nums1[i], leftIndex);
            if (index != -1) {
                intersection.add(nums1[i]);
                leftIndex = index + 1;
            }
        }

        int[] result = new int[intersection.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = intersection.get(i);
        }
        return result;
    }

    // nums is sorted in non-descending order.
    // Binary search from 'low' to end of array.
    // If duplicates exist, return the index for the match furthest left.
    private int binarySearch(int[] nums, int target, int low) {
        int start = low;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (start >= nums.length) {
            return -1;
        }
        return nums[start] == target ? start : -1;
    }
}

// followup 3
/*
What if elements of nums2 are stored on disk, and the memory is
limited such that you cannot load all elements into the memory at
once?

If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory,
and record the intersections.
If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2
elements from each array at a time in memory, record intersections.

Thanks for the solution. I think the second part of the solution is impractical, if you read 2 elements at a time, this
procedure will take forever.
In principle, we want minimize the number of disk access during the run-time.
An improvement can be sort them using external sort, read (let's say) 2G of each into memory and then using the 2 pointer
technique, then read 2G more from the array that has been exhausted. Repeat this until no more data to read from disk.
But I am not sure this solution is good enough for an interview setting. Maybe the interviewer is expecting some solution
using Map-Reduce paradigm.

I think the goal of this question is to test whether the interview understands some of the data engineering techniques.
From a data engineer's perspective, basically there are three ideas to solve the question:
1. Store the two strings in distributed system(whether self designed or not), then using MapReduce technique to solve
the problem;
2. Processing the Strings by chunk, which fits the memory, then deal with each chunk of data at a time;
3. Processing the Strings by streaming, then check.
*/
