// Dutch national flag problem
// threeway partition
// 把1放中间，0往左边扔，2往右边扔
/*
An efficient solution is based on Dutch National Flag based QuickSort. We traverse given array elements from left. 
We keep track of two pointers, first (called start in below code) to store next position of smaller element (smaller than range) from beginning; 
and second (called end in below code) to store next position of greater element from end. 
*/
class Solution {
    public void sortColors(int[] nums) {
        int start = 0, end = nums.length - 1;
        for (int i = 0; i <= end;) {
            if (nums[i] == 0) {
                swap(nums, i, start);
                start++;
                i++;
            } else if (nums[i] == 2)  {
                swap(nums, i, end);  // 不知道nums[idx2]是什么，所以idx1不能++
                end--;
            } else {
                i++;
            }
        }
        
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 2在最后，所以要多移动num of 0 和 num of 1 个距离。
/*
我们用三个指针 idx0，idx1，idx2，分别代表已排好序的数组当前 0 的末尾，1 的末尾，2 的末尾
0  0  1  2  2  2  0  2  1
   ^  ^        ^  ^
  n0 n1       n2  i

然后当前遍历到 i 的位置，等于 0，我们想把这个0移动到idx0的下一位，
这样做以后之前的1被覆盖了，所以要把idx1往前移动并且设置成1，
这样又覆盖了之前的2，同理需要把idx2往前移动以为并设置为2

这里假设了当前的数组已经排好了
*/
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int idx0 = 0, idx1 = 0, idx2 = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                nums[idx2++] = 2;
                nums[idx1++] = 1;
                nums[idx0++] = 0;
            } else if (nums[i] == 1) {
                nums[idx2++] = 2;
                nums[idx1++] = 1;
            } else {
                nums[idx2++] = 2;
            }
        }
    }
}

// counting sort
class Solution {
    public void sortColors(int[] nums) {
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                a++;
            } else if (nums[i] == 1) {
                b++;
            } else {
                c++;
            }
        }
        for (int i = 0; i < a; i++) {
            nums[i] = 0;
        }
        for (int i = a; i < a + b; i++) {
            nums[i] = 1;
        }
        for (int i = a + b; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}
