/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    
    Iterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        List<Integer> list = new ArrayList<>();
        unnest(nestedList, list);
        it = list.iterator();
    }
    
    private void unnest(List<NestedInteger> nestedList, List<Integer> list) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                list.add(ni.getInteger());
            } else {
                unnest(ni.getList(), list);
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}

public class NestedIterator implements Iterator<Integer> {
    
    List<Integer> list;
    int cur;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        constructHelper(nestedList);
        cur = 0;
    }
    
    private void constructHelper(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                list.add(ni.getInteger());
            } else {
                constructHelper(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(cur++);
    }

    @Override
    public boolean hasNext() {
        return cur < list.size();
    }
}

public class NestedIterator implements Iterator<Integer> {
    
    private List<Integer> list;
    private Iterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        flattenList(nestedList);
        it = list.iterator();
    }
    
    private void flattenList(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                list.add(ni.getInteger());
            }
            else {
                flattenList(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
