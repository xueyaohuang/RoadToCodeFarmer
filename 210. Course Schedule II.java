// DFS
// 这就是topological sort，当完成对一个点的访问后，加入list中，dfs是逆序，所以要用stack。
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        int[] visited = new int[numCourses];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adj[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(adj, i, visited, stack)) {
                return new int[0];
            }
        }
        int i = 0;
        int[] res = new int[numCourses];
        while (!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        return res;
    }
    private boolean hasCycle(List<Integer>[] adj, int i, int[] visited, Deque<Integer> stack) {
        // visit[i] == 1 means is visiting
        if (visited[i] == 1) {
            return true;
        }    
        // visit[i] == 2 means has been visited
        if (visited[i] == 2) {
            return false;
        }      
        visited[i] = 1;
        for (int j = 0; j < adj[i].size(); j++) {
            if (hasCycle(adj, adj[i].get(j), visited, stack)) {
                return true;
            }
        }
        visited[i] = 2;
        stack.push(i);
        return false;
    }
}

// BFS
// 这就是topological sort，当完成对一个点的访问后，加入list中，bfs是正序，所以直接加。
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        Queue<Integer> finished = new LinkedList<>();
        int canFinish = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adj[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                finished.offer(i);
            }
        }
        while (!finished.isEmpty()) {
            int curCourse = finished.poll();
            res.add(curCourse);
            canFinish++;
            for (int i = 0; i < adj[curCourse].size(); i++) {
                int nextCourse = adj[curCourse].get(i);
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    finished.offer(nextCourse);
                }
            }
        }
        if (canFinish == numCourses) {
            int[] ans = new int[numCourses];
            int i = 0;
            for (int num : res) {
                ans[i++] = num;
            }
            return ans;
        }   
        else {
            return new int[0];
        }
    }
}
