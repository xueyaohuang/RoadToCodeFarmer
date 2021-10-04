/*

Idea / Proof:

    The widest container (using first and last line) is a good candidate, because of its width. Its water level is the height of the smaller one of first and last line.
    All other containers are less wide and thus would need a higher water level in order to hold more water.
    The smaller one of first and last line doesn't support a higher water level and can thus be safely removed from further consideration.


The max area is calculated by the following formula:

S = (j - i) * min(ai, aj)

We should choose (i, j) so that S is max. Note that i, j go through the range (1, n) and j > i. That's it.

The simple way is to take all possibilities of (i, j) and compare all obtained S. The time complexity is n * (n-1) / 2

What we gonna do is to choose all possibilities of (i, j) in a wise way. I noticed that many submitted solutions here can't explain why when :

    ai < aj we will check the next (i+1, j) (or move i to the right)
    ai >= aj we will check the next (i, j-1) (or move j to the left)

Here is the explaination for that:

    When ai < aj , we don't need to calculate all (i, j-1), (i, j-2), .... Why? because these max areas are smaller than our S at (i, j)

Proof: Assume at (i, j-1) we have S'= (j-1-i) * min(ai, aj-1)
S'< (j-1-i) * ai < (j-i) * ai = S, and when S'<S, we don't need to calculate
Similar at (i, j-2), (i, j-3), etc.

So, that's why when ai < aj, we should check the next at (i+1, j) (or move i to the right)

    When ai >= aj, the same thing, all (i+1, j), (i+2, j), .... are not needed to calculate.

We should check the next at (i, j-1) (or move j to the left)
*/

class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            max = Math.max(max, h * (j - i));
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}
