/*
Cycle detection problem

Given an array nums containing n + 1 integers: the index of the array ranges from 0 to n.

Each integer is between 1 and n, so each element can be used as a pointer (an index of the array).

I use the index as a pointer, so that the number whose index appears twice is just like the starting point of the linked list loop (who has 2 pointers point to it).

用index作为pointer，[1,3,4,2,2]，nums[3] 和nums[4]都是2，所以index 3，4都指向2，就像linkedlist cycle有2个next pointer都指向cycle的起始点一样。
*/

class Solution {
    public int findDuplicate(int[] nums) {
        int fast = 0;
        int slow = 0;
        int len = nums.length;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (slow == fast) {
                fast = 0;
                while (fast != slow) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return slow;
            }
        }
    }
}

// class Solution {
//     public int findDuplicate(int[] nums) {
//         int fast = 0;
//         int slow = 0;
//         int len = nums.length;
//         while (fast < len && nums[fast] < len) {
//             fast = nums[nums[fast]];
//             slow = nums[slow];
//             if (slow == fast) {
//                 fast = 0;
//                 while (fast != slow) {
//                     fast = nums[fast];
//                     slow = nums[slow];
//                 }
//                 return slow;
//             }
//         }
//         return -1;
//     }
// }

// binary search, trial and error method.
// 已知duplicate number的范围在1到n-1之间，具体哪个不知道，用binary search找。
// 每次尝试时，猜duplicate number是mid，那么小于等于mid的数的个数 如果大于mid，那么duplicate number一定小于等于mid
// 否则大于mid
class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
}
