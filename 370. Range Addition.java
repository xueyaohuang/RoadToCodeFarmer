/*
why put inc at startIndex and -inc at endIndex + 1?
we want to achieve the final result array in two passes:
Iterate through the k update operations and "somehow" mark them in the [0, 0, 0, 0, 0] array (using length 5 for example),
for each operation, only update startIndex and endIndex + 1. this is O(k) in total.
iterate through the marked array and "somehow" transforms it to the final result array. this is O(n) in total (n = length).
All in all it is O(n + k).
Now think in a simpler way first, if you have only one update operation, suppose input is (n = 5, updates = { {1, 3, 2} }), what does the O(n + k) solution do?

Initialize the result array as length of n + 1, because we will operate on endIndex + 1:
result = [0, 0, 0, 0, 0, 0]
Then marks index 1 as 2 and marks index 3+1 as -2:
result = [0, 2, 0, 0, -2, 0]
Next, iterate through result, and accumulates previous sum to current position, just like 303. Range Sum Query - Immutable:
result = [0, 0 + 2, 0 + 2, 0 + 2, 2 + (-2), 0] = [0, 2, 2, 2, 0, 0]
Finally, trivial work to discard the last element because we don't need it:
result = [0, 2, 2, 2, 0], which is the final result.
Now you might see why we do "puts inc at startIndex and -inc at endIndex + 1":

Put inc at startIndex allows the inc to be carried to the next index starting from startIndex when we do the sum accumulation.
Put -inc at endIndex + 1 simply means cancel out the previous carry from the next index of the endIndex, because the previous carry 
should not be counted beyond endIndex.
And finally, because each of the update operation is independent and the list operation is just an accumulation of the "marks" we do,
so it can be "makred" all at once first and do the range sum at one time at last step.
*/
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int inc = update[2];
            res[start] += inc;
            if (end + 1 < length) {
                res[end + 1] -= inc;
            }
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }
}

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            for (int i = update[0]; i <= update[1]; i++) {
                res[i] += update[2];
            }
        }
        return res;
    }
}

