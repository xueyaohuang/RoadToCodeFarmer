/* we need some observation:
if we have multiple choices to swap, which indices should we swap? for example
012345
]]][[[
there are 3 open [ that comes  after close ]
we can swap 0 and 3, we can also swap 0 and 5.
If we swap 0 and 3, we have []]][[, there are 2 [ that comes after ], we need 2 more swaps
If we swap 0 and 5, we have []][[], there are 1 [ that comes after ], we need 1 more swap
see the difference? we should always swap the last unpaired [ with the first unpaired ]
if we have x unpaired [, we also have x unpaired ]. say we have x unpaired [, the answer is (x+1)/2.
For example ]]][[[, x is 3, answer is 2.
and ]]]][[[[, x is 4, answer is 2.
we just need to count the number of unpaired [
*/ 
class Solution {
    public int minSwaps(String s) {
        int open = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                open++;
            } else if (open > 0) {
                open--;
            }
        }
        return (open + 1) / 2;
    }
}
