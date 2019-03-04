// Union Find
class Solution {
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int rowMax = 0;
        int colMax = 0;
        for (int[] stone : stones) {
            rowMax = Math.max(rowMax, stone[0] + 1);
            colMax = Math.max(colMax, stone[1] + 1);
        }
        UnionFind uf = new UnionFind(rowMax + colMax);
        for (int[] stone : stones) {
            if (uf.parent[stone[0]] == -1) {
                uf.parent[stone[0]] = stone[0];
                uf.count++;
            }
            if (uf.parent[stone[1] + rowMax] == -1) {
                uf.parent[stone[1] + rowMax] = stone[1] + rowMax;
                uf.count++;
            }
            uf.union(stone[0], stone[1] + rowMax);
        }
        return stones.length - uf.count;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    int count;
    
    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }
    
    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
    
    public boolean union(int p, int q) {
        int rootp = find(p);
        int rootq = find(q);
        if (rootp == rootq) {
            return false;
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
        return true;
    }
}

// OOP Union Find
class Solution {
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int rowMax = 0;
        int colMax = 0;
        for (int[] stone : stones) {
            rowMax = Math.max(rowMax, stone[0] + 1);
            colMax = Math.max(colMax, stone[1] + 1);
        }
        UnionFind uf = new UnionFind(rowMax + colMax);
        for (int[] stone : stones) {
            if (uf.getParent(stone[0]) == -1) {
                uf.setParent(stone[0], stone[0]);
                uf.increaseCount();
            }
            if (uf.getParent(stone[1] + rowMax) == -1) {
                uf.setParent(stone[1] + rowMax, stone[1] + rowMax);
                uf.increaseCount();
            }
            uf.union(stone[0], stone[1] + rowMax);
        }
        return stones.length - uf.getCount();
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;
    
    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }
    
    public int getParent(int p) {
        return parent[p];
    }
    
    public void setParent(int p, int parentP) {
        parent[p] = parentP;
    }
    
    public int getCount() {
        return count;
    }
    
    public void increaseCount() {
        count++;
    }
    
    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
    
    public boolean union(int p, int q) {
        int rootp = find(p);
        int rootq = find(q);
        if (rootp == rootq) {
            return false;
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
        return true;
    }
}


class Solution {
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        Map<Integer, Set<Integer>> rowMap = new HashMap<>();
        Map<Integer, Set<Integer>> colMap = new HashMap<>();
        for (int[] stone : stones) {
            int row = stone[0];
            int col = stone[1];
            rowMap.putIfAbsent(row, new HashSet<>());
            colMap.putIfAbsent(col, new HashSet<>());
            rowMap.get(row).add(col);
            colMap.get(col).add(row);
        }
        Set<String> visited = new HashSet<>();
        int count = 0;
        for (int[] stone : stones) {
            String cur = stone[0] + "," + stone[1];
            if (!visited.contains(cur)) {
                count++;
                findConnectedParts(stone[0], stone[1], rowMap, colMap, visited);
            }
        }
        return stones.length - count;
    }
    
    private void findConnectedParts(int row, int col, Map<Integer, Set<Integer>> rowMap, Map<Integer, Set<Integer>> colMap, Set<String> visited) {
        if (visited.contains(row + "," + col)) {
            return;
        }
        visited.add(row + "," + col);
        for (int c : rowMap.get(row)) {
            findConnectedParts(row, c, rowMap, colMap, visited);
        }
        for (int r : colMap.get(col)) {
            findConnectedParts(r, col, rowMap, colMap, visited);
        }
    }
}
