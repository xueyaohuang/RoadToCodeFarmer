public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        boolean[] visited = new boolean[numCourses];  
        List<Integer>[] adj = new List[numCourses];    
        for(int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<Integer>();
        for(int i = 0; i < prerequisites.length; i++)  
        {
            int curCourse = prerequisites[i][0];        
            int preCourse = prerequisites[i][1];        
            adj[preCourse].add(curCourse);
        }
        for(int i = 0; i < numCourses; i++)
        {
            if(!dfs(adj, visited, i))     
                return false;
        }
        return true;
    }

    private boolean dfs(List<Integer>[] adj, boolean[] visited, int course){

        if(visited[course])         // have circle
            return false;
        visited[course] = true;
        for (int i = 0; i < adj[course].size(); i++)
        {
            if(!dfs(adj, visited, adj[course].get(i)))
                return false;
            adj[course].remove(i);  // delete edge
        }
        visited[course] = false;    
        return true;
    }
}

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<Integer>[] adj = new List[numCourses];    
        for(int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<Integer>();
        int[] indegree = new int[numCourses];          
        Queue<Integer> readyCourses = new LinkedList(); 
        int finishCount = 0;                        
        for (int i = 0; i < prerequisites.length; i++)  
        {
            int curCourse = prerequisites[i][0];        
            int preCourse = prerequisites[i][1];        
            adj[preCourse].add(curCourse);
            indegree[curCourse]++;
        }
        for (int i = 0; i < numCourses; i++) 
        {
            if (indegree[i] == 0) 
                readyCourses.offer(i);           
        }
        while (!readyCourses.isEmpty()) 
        {
            int course = readyCourses.poll();        // finish
            finishCount++;
            for (int nextCourse : adj[course]) 
            {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0)    
                    readyCourses.offer(nextCourse);  // ready
            }
        }
        return finishCount == numCourses;
    }
}
