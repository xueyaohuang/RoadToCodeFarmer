public class ZigzagIterator {
    
    List<Integer> list;
    Iterator<Integer> ltr;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list = new ArrayList<>();
        initializeIterator(v1, v2);
        ltr = list.iterator();
    }
    
    private void initializeIterator(List<Integer> v1, List<Integer> v2) {
        int len1 = v1.size();
        int len2 = v2.size();
        int i = 0, j = 0;
        while (i < len1 || j < len2) {
            if (i < len1) {
                list.add(v1.get(i));
                i++;
            }
            if (j < len2) {
                list.add(v2.get(j));
                j++;
            }
        }
    }

    public int next() {
        return ltr.next();
    }

    public boolean hasNext() {
        return ltr.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
