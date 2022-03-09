// parent不是0的点，都是1
/*
Time complexity : O(m×n+L) where L is the number of operations, m is the number of rows and n is the number of columns.
it takes O(m×n) to initialize UnionFind, and O(L) to process positions.
Note that Union operation takes essentially constant time when UnionFind is implemented with both path compression and union by rank.

Space complexity : O(m×n) as required by UnionFind data structure.
*/
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        if (positions == null || positions.length == 0) {
            return new ArrayList<>();
        }
        
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        UnionFind uf = new UnionFind(m, n);
        List<Integer> res = new ArrayList<>();
        for (int[] pos : positions) {
            int x = pos[0];
            int y = pos[1];
            int p = uf.index(x, y);
            uf.add(x, y);
            for (int[] dir : dirs) {
                int xx = x + dir[0];
                int yy = y + dir[1];
                if (xx >= 0 && xx < m && yy >= 0 && yy < n) {
                    int q = uf.index(xx, yy);
                    if (uf.getParentValue(q) > 0) {
                        uf.union(p, q);
                    }
                }
            }
            res.add(uf.count());
        }
        return res;
    }
}

class UnionFind {
    int count;
    int[] parent;
    int[] rank;
    int row;
    int col;

    public UnionFind(int m, int n) {
        count = 0;
        parent = new int[m * n + 1];
        rank = new int[m * n + 1];
        row = m;
        col = n;
        for (int i = 1; i <= m * n; i++) {
            parent[i] = 0;
            rank[i] = 0;
        }
    }
    
    public void add(int x, int y) {
        int p = index(x, y);
        parent[p] = p;
        rank[p] = 0;
        count++;
    }
    
    public int index(int x, int y) {
        return col * x + y + 1;
    }
    
    public int getParentValue(int p) {
        return parent[p];
    }

    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootp = find(p);
        int rootq = find(q);
        if (rootp == rootq) {
            return;
        }
        if (rank[rootp] > rank[rootq]) {
            parent[rootq] = rootp;
        } else if (rank[rootp] < rank[rootq]) {
            parent[rootp] = rootq;
        } else {
            parent[rootq] = rootp;
            rank[rootp]++;
        }
        count--;
    }

    public int count() {
        return count;
    }
}
