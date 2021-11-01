/**
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.

Example 1:

Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
Example 2:

Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.
Example 3:

Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.
Example 4:

Input: heights = [2,2,2,2]
Output: [3]
Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.
 

Constraints:

1 <= heights.length <= 105
1 <= heights[i] <= 109

**/

class Solution {
    public int[] findBuildings(int[] heights) {
        List<Integer> list = new ArrayList<>();
        int highest = heights[heights.length - 1];
        list.add(heights.length - 1);
        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i] > highest) {
                list.add(i);
            }
            highest = Math.max(highest, heights[i]);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(res.length - 1 - i);
        }
        return res;
    }
}

// monotonic stack, although it's an overkill
class Solution {
    public int[] findBuildings(int[] heights) {
        List<Integer> list = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
            }
            stack.push(heights[i]);
            if (stack.size() == 1) {
                list.add(i);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(res.length - 1 - i);
        }
        return res;
    }
}
