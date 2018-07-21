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

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
