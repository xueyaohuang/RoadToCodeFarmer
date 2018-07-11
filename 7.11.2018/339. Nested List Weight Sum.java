class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSumHelper(nestedList, 1);
    }
    private int depthSumHelper(List<NestedInteger> nestedList, int depth) {
        int res = 0;
        for (NestedInteger nl : nestedList) {
            if (nl.isInteger()) {
                res += nl.getInteger() * depth;
            }
            else {
                res += depthSumHelper(nl.getList(), depth + 1);
            }
        }
        return res;
    }
}
