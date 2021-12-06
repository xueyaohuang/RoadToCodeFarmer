/*

Find the difference between 2 consecutive integer in array. (start is the first element and arr[i] is the next element)
If the difference is greater or equal to than current k, substract the difference from k.
If difference is less than current k, return (start + k) Since the answer is between current consecutive elements.

Example 1 -
[2,3,7,11], k = 5
Initially, start = 0

    start = 0, end = 2. Between 0 and 2, there is 1 missing positive integer. Hence Decrement 1 from k = 5-1, k = 4
    start = 2, end = 3. Between 2 and 3 there is no missing positive integer.
    start = 3, end = 7. Between 3 and 7, there are 3 missing positive integer, substract 3 from k = 4-3, k = 1
    start = 7, end = 11. Between 7 and 11, there are 3 missing positive integer. But now since k is already less than 3, our answer lies between 7 and 11.
    Return start + k = 7 + 1 = 8
    Answer = 8

*/
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int start = 0;
        for (int i : arr) {
            if (i - (start + 1) < k) {
                k -= i - (start + 1);
            } else {
                return start + k;
            }
            start = i;
        }
        return start + k;
    }
}

/* we know it starts from 1, so arr[idx] - (idx + 1) will be the count of number that is missed.
1. We are maintaining such invariant throughout the loop: l + k <= ans <= r + k.
Obviously when the array is missing k or more elements in the beginning, ans == k;
when there is no missing elements, ans is arr.length + k;
2. When we update l = mid + 1, there are already mid + 1 non-missed elements on the left, and we still need k missed elements, so l + k <= ans still holds true;
3. When we update r = mid, we know ans is less than arr[mid], and on the left of mid, there are mid non-missed elements, plus k or more missed elements,
so ans is at most mid + k;
4. Finally when l == r, we get l + k == ans == r + k.
*/
class Solution {
    public int findKthPositive(int[] arr, int k) {
        // the result is between [k, k + arr.length], that is [k + 0, k + arr.length]
        // we want to find the number x, which is between [0, arr.length], at last we just return x + k
        int start = 0, end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] - (mid + 1) >= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start + k;
    }
}

class Solution {
    public int findKthPositive(int[] arr, int k) {
        for (int i : arr) {
            if (i <= k) {
                k++;
            } else {
                return k;
            }
        }
        return k;
    }
}

class Solution {
    public int findKthPositive(int[] arr, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }
        int miss = 0;
        for (int i = 1; i <= arr.length + k; i++) {
            if (!set.contains(i)) {
                miss++;
            }
            if (miss == k) {
                return i;
            }
        }
        return -1;
    }
}
