public class Vector2D implements Iterator<Integer> {

    private Iterator<List<Integer>> matrix;
    private Iterator<Integer> vector;
    
    public Vector2D(List<List<Integer>> vec2d) {
        matrix = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return vector.next();
    }

    @Override
    public boolean hasNext() { // 必须检查vector有下一个，而不是matrix有下一个
        while ((vector == null || !vector.hasNext()) && (matrix.hasNext())) {
            vector = matrix.next().iterator();
        }
        if (vector == null) {
            return false;
        }
        return vector.hasNext();
    }
}

public class Vector2D implements Iterator<Integer> {

    Iterator<Integer> it;
    public Vector2D(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        List<Integer> arr = new ArrayList<>();
        for (List<Integer> list : vec2d) {
            for (Integer i : list) {
                arr.add(i);
            }
        }
        it = arr.iterator();
    }

    @Override
    public Integer next() {
        // Write your code here
        return it.next();
    }

    @Override
    public boolean hasNext() {
        // Write your code here
        return it.hasNext();
    }

    @Override
    public void remove() {}
}

public class Vector2D implements Iterator<Integer> {

    int cur;
    int size;
    List<Integer> arr;
    public Vector2D(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        arr = new ArrayList<>();
        cur = 0;
        for (List<Integer> list : vec2d) {
            for (Integer i : list) {
                arr.add(i);
            }
        }
        size = arr.size();
    }

    @Override
    public Integer next() {
        // Write your code here
        return arr.get(cur++);
    }

    @Override
    public boolean hasNext() {
        // Write your code here
        return cur < size;
    }

    @Override
    public void remove() {}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
