class Solution {
    public boolean isBipartite(int[][] graph) {
        if (graph == null) {
            return false;
        }
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (hasOddCycle(graph, i, color, 1)) {
                    return false;
                }
            }
        }
        return true;
    }
    // color:   0: not visited
    //          1: 
    //         -1: 
    private boolean hasOddCycle(int[][] graph, int i, int[] color, int curColor) {
        if (color[i] != 0) {
            return color[i] == -curColor; // 只有2种颜色，如果当前点有颜色，并且不是他该有的颜色，就是有odd cycle
        }
        color[i] = curColor;  // 给他图上该有的颜色
        for (int j : graph[i]) {
            if (hasOddCycle(graph, j, color, -curColor)) {
                return true;
            }
        }
        return false;
    }
}

class Solution {
    public boolean isBipartite(int[][] graph) {
        if (graph == null) {
            return false;
        }
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (hasOddCycle(graph, i, color)) {
                    return false;
                }
            }
        }
        return true;
    }
    // color:   0: not visited
    //          1: 
    //         -1: 
    private boolean hasOddCycle(int[][] graph, int i, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        color[i] = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curColor = color[cur];
            for (int adj : graph[cur]) {
                if (color[adj] == curColor) {
                    return true;
                }
                if (color[adj] == 0) {
                    queue.offer(adj);
                    color[adj] = -curColor;
                }
            }
        }
        return false;
    }
}
