## 数connected component
[lc323](https://github.com/xueyaohuang/RoadToCodeFarmer/blob/master/323.%20Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.java)
union find  
dfs

## directed graph detect cycle
detect back edge  
lc 207 Course Schedule  

## undirected graph detect cycle
directed 和 undirected 判断cycle不同，比如1，2，3三个点两两相连，在undirected中使cycle。但是如果在directed中，1指向2，1指向3，2指向3，就不是cycle。
need a parameter parent  
lc 261 Graph Valid Tree  
