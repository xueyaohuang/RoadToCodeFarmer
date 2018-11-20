unionfind 的构造函数，根据题目会有不同。
LC 200, 547, 684, 305

Time complexity:
union and find operation: O(log*n) amortized time.
log*n is never larger than 5 in our universe! So union and find is amortized O(1).
https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2%80%93find  
https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
```
public class UnionFind {
    private int[] parent;  // parent[i] = parent of i
    private int[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components

    public UnionFind(int n) {
        this.count = n;
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        // while (p != parent[p]) {
        //     parent[p] = parent[parent[p]];    // path compression by halving
        //     p = parent[p];
        // }
        // return p;
        
        // recursive way        
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public int count() {
        return count;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public boolean union(int p, int q) {  // return true if not connected， o/w false
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return false;
        }

        // make root of smaller rank point to root of larger rank
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
        return true;
    }
}
```
