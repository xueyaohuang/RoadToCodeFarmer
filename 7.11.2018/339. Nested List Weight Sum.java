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

class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            return 0;
        }
        int sum = 0;
        int depth = 1;
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nl = queue.poll();
                if (nl.isInteger()) {
                    sum += nl.getInteger() * depth;
                }
                else {
                    queue.addAll(nl.getList());
                }
            }
            depth++;
        }
        return sum;
    }
}
