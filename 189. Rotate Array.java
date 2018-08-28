* Solution 1, best solution.
```
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k % nums.length == 0) {
            return;
        }
        int len = nums.length;
        k %= len;
        int count = 0;
        for (int start = 0; count < len; start++) {
            int currIdx = start;
            int prevVal = nums[currIdx];
            do {
                int nextIdx = (currIdx + k) % len;
                int currVal = nums[nextIdx];
                nums[nextIdx] = prevVal;
                prevVal = currVal;
                currIdx = nextIdx;
                count++;
            } while (currIdx != start);
        }
    }
}
```
* Solution 2
```
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k % nums.length == 0) {
            return;
        }
        int len = nums.length; // len不变
        int n = len;  // n代表还剩多长没有搞好，会更新
        k %= n; // 每次把最后k个换到正确的位置，然后下一个循环时，搞剩下n-k个
        int j = 0; // n-k个的起始idx
        while (k % n > 0) { // 一旦 k % n == 0, 往右移k是个循环，所以退出循环
            for (int i = 0; i < k; i++) {
                int val = nums[j + i];
                nums[j + i] = nums[len - k + i]; // 注意是len - k + i，不是n - k + i
                nums[len - k + i] = val;
            }
            n -= k;
            j += k;
            k %= n; //没搞好的部分需要向右移动多少
        }
    }
}
/*
put the last k elements in correct position (ahead) and do the remaining n - k. 
Once finish swap, the n and k decrease.
*/
```

* Solution 3
```
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```
* Solution 4, O(k) extra memory.
```
class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = nums[nums.length - k + i];
        }
        for (int i = nums.length - 1; i >= nums.length - 1 - k; i--) {
            nums[i] = nums[i - k];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
}
```



