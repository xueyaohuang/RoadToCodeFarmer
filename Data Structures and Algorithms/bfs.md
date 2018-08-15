# BFS

1. [gks4gks](https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/)

2. [Slides](https://github.com/xueyaohuang/RoadToCodeFarmer/blob/master/Data%20Structures%20and%20Algorithms/BFS.pdf)

3. 应用BFS注意一点，区分是对tree做BFS试试对一般的图做BFS。
    * 对Tree做BFS，不需要构造图的adj list，并且tree都是没有cycle的，不需要一个boolean array来记录node是否被访问过。
    * 对一般的图做BFS，有时图是以2D matrix给出的，有时没有给出图的结构，一般先构造adj list，可以用的数据结构有 HashMap<Node, ArrayList<Node>>，或者ArrayList<Node>[]，有时node直接用Integer表示。一般的图会有cycle，需要一个boolean[]或者类似的array记录每个node的访问情况，跳过已经访问过的node。
  
4. BFS一般都是用Queue实现。

5. 应用：
    * Shortest Path for unweighted graph
    * Finding all nodes within one connected component. We can either use Breadth First or Depth First Traversal to find all nodes reachable from a given node.
    * Cycle detection in undirected graph: In undirected graphs, either Breadth First Search or Depth First Search can be used to detect cycle. In directed graph, only depth first search can be used.
    * To test if a graph is Bipartite We can either use Breadth First or Depth First Traversal.
    * Path Finding We can either use Breadth First or Depth First Traversal to find if there is a path between two vertices.
