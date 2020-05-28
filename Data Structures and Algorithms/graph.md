# Graph

## 图的表示

1. 图的表示通常使用邻接矩阵和邻接表，前者易实现但是对于稀疏矩阵会浪费较多空间，后者使用链表的方式存储信息但是对于图搜索时间复杂度较高。

2. Java 邻接矩阵: ```int[][] g = new int[V][V];```

3. Java 邻接表
```

class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}
```

Adj list也可以用现成的数据结构，比如HashMap<Node, ArrayList<Node>>, ArryList<Node>[], node有时可以用Integer表示。
    
## Graph detect cycle

1. directed graph detect cycle
detect back edge
lc 207 Course Schedule

2. undirected graph detect cycle
directed 和 undirected 判断cycle不同，比如1，2，3三个点两两相连，在undirected中使cycle。但是如果在directed中，1指向2，1指向3，2指向3，就不是cycle。
need a parameter parent  
lc 261 Graph Valid Tree
dfs or union find  

3. 数connected component
[lc323](https://github.com/xueyaohuang/RoadToCodeFarmer/blob/master/323.%20Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.java)
union find  
dfs
