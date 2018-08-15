# Graph

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

参见LC
