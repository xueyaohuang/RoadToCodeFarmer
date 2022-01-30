// Follow up: What if you are given k vectors? How well can your code be extended to such cases?
public class ZigzagIterator {
    
    Queue<Iterator> its;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        its = new LinkedList<>();
        if (!v1.isEmpty()) {
            its.offer(v1.iterator());
        }
        if (!v2.isEmpty()) {
            its.offer(v2.iterator());
        }
    }

    public int next() {
        Iterator cur = its.poll();
        int res = (Integer)cur.next();
        if (cur.hasNext()) {
            its.offer(cur);
        }
        return res;
    }

    public boolean hasNext() {
        return !its.isEmpty();
    }
}

public class ZigzagIterator {
    
    List<Integer> list;
    Iterator<Integer> it;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < v1.size() || j < v2.size()) {
            if (i < v1.size()) {
                list.add(v1.get(i++));
            }
            if (j < v2.size()) {
                list.add(v2.get(j++));
            }
        }
        it = list.iterator();
    }

    public int next() {
        return it.next();
    }

    public boolean hasNext() {
        return it.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
