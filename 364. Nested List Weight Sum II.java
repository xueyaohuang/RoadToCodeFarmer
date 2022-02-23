class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int weighted = 0;
        int unweighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    unweighted += ni.getInteger();
                }
                else {
                    nextLevel.addAll(ni.getList());
                }
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }
}

/*
Actually got this from the tagged company phone interview after hinted by the interviewer.
The idea is to deduct number depth - level times.
For example, 1x + 2y + 3z = (3 + 1) * (x + y + z) - (3x + 2y + z);
So we can convert this problem to Nested List Weight Sum I and just record max depth and flat sum at the same time.
*/
class Solution {
    int maxDepth;
    int flatSum;
    int weightedSum; // weightedSum in LC339
    public int depthSumInverse(List<NestedInteger> nestedList) {
        getSum(nestedList, 1);
        return flatSum * (maxDepth + 1) - weightedSum;
    }
    
    private void getSum(List<NestedInteger> nestedList, int depth) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                weightedSum += ni.getInteger() * depth;
                flatSum += ni.getInteger();
                maxDepth = Math.max(maxDepth, depth);
            } else {
                getSum(ni.getList(), depth + 1);
            }
        }
    }
}

class Solution {
    int maxDepth;
    int sum;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        getMaxDepth(nestedList, 1);
        getSumInverse(nestedList, 1);
        return sum;
    }
    
    private void getMaxDepth(List<NestedInteger> nestedList, int depth) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                maxDepth = Math.max(maxDepth, depth);
            } else {
                getMaxDepth(ni.getList(), depth + 1);
            }
        }
    }
    
    private void getSumInverse(List<NestedInteger> nestedList, int depth) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * (maxDepth - depth + 1);
            } else {
                getSumInverse(ni.getList(), depth + 1);
            }
        }
    }
}
