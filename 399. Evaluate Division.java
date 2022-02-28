// dfs，转换成图
// 每一个variable是一个vertex，两个variable的quotient是edge的weight。
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<String>> variables = new HashMap<>();
        Map<String, List<Double>> quotients = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);
            
            variables.putIfAbsent(var1, new ArrayList<>());
            variables.get(var1).add(var2);
            variables.putIfAbsent(var2, new ArrayList<>());
            variables.get(var2).add(var1);
            
            quotients.putIfAbsent(var1, new ArrayList<>());
            quotients.get(var1).add(values[i]);
            quotients.putIfAbsent(var2, new ArrayList<>());
            quotients.get(var2).add(1.0 / values[i]);
        }
        int n = queries.size();
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = calculate(variables, quotients, queries.get(i).get(0), queries.get(i).get(1), 1.0, new HashSet<>());
        }
        return res;
    }
    
    private double calculate(
        Map<String, List<String>> variables,
        Map<String, List<Double>> quotients,
        String start,
        String end,
        double cur,
        Set<String> visited
    ) {
        if (visited.contains(start)) {
            return -1.0;
        }
        if (!variables.containsKey(start)) {
            return -1.0;
        }
        if (start.equals(end)) {
            return cur;
        }
        visited.add(start);
        for (int i = 0; i < variables.get(start).size(); i++) {
            String next = variables.get(start).get(i);
            double val = quotients.get(start).get(i);
            double temp = calculate(variables, quotients, next, end, cur * val, visited);
            if (temp != -1.0) {
                return temp;
            }
        }
        return -1.0;
    }
}

class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, List<String>> variable = new HashMap<>();
        Map<String, List<Double>> quotient = new HashMap<>();
        int len = equations.length;
        // build graph
        for (int i = 0; i < len; i++) {
            String[] equation = equations[i];
            double value = values[i];
            
            variable.putIfAbsent(equation[0], new ArrayList<String>());
            variable.get(equation[0]).add(equation[1]);
            variable.putIfAbsent(equation[1], new ArrayList<String>());
            variable.get(equation[1]).add(equation[0]);
            
            quotient.putIfAbsent(equation[0], new ArrayList<Double>());
            quotient.get(equation[0]).add(value);
            quotient.putIfAbsent(equation[1], new ArrayList<Double>());
            quotient.get(equation[1]).add(1.0 / value);
        }
        
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = calculate(queries[i][0], queries[i][1], variable, quotient, 1.0, new HashSet<>());
        }
        
        return res;
    }
    
    private double calculate(String start, String end,
                             Map<String, List<String>> variable,
                             Map<String, List<Double>> quotient, 
                             double curRes, Set<String> visited) {
                             
        // 如果两个变量在图中不是联通的，那么dfs后start会回到自己，要返回-1.0
        if (visited.contains(start)) {
            return -1.0;
        }
        visited.add(start);
        
        if (!variable.containsKey(start)) {
            return -1.0;
        }
        
        if (start.equals(end)) {
            return curRes;
        }
        
        List<String> nextVar = variable.get(start);
        List<Double> nextVal = quotient.get(start);
        double temp = 1.0;
        
        for (int i = 0; i < nextVar.size(); i++) {
            temp = calculate(nextVar.get(i), end, variable, quotient, curRes * nextVal.get(i), visited);
            if (temp != -1.0) {
                return temp;
            }
        }
        
        return temp;
    }
}

class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        UnionFind uf = new UnionFind();
        for (int i = 0; i < equations.length; i++) {
            uf.union(equations[i][0], equations[i][1], values[i]);
        }
        
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Node var1 = uf.find(queries[i][0]);
            Node var2 = uf.find(queries[i][1]);
            if (var1 == null || var2 == null || !var1.parent.equals(var2.parent)) {
                res[i] = -1.0;
            } else {
                res[i] = var1.ratio / var2.ratio;
            }
        }
        return res;
    }
    
    class Node {
        String parent;
        double ratio;
        int rank;
        public Node(String parent, double ratio) {
            this.parent = parent;
            this.ratio = ratio;
            this.rank = 0;
        }
    }
    
    class UnionFind {
        // key: string, value: 这个string的相关信息，parent是一连串除法最底层的变量
        // 比如： a/b/c/d = 3.0
        // key: a, value中: parent是d，ratio是3.0
        Map<String, Node> map = new HashMap<>();
        // 更新他的爹（连除的最底层）和ratio
        public Node find(String p) {
            if (!map.containsKey(p)) {
                return null;
            }
            Node node = map.get(p);
            if (!p.equals(node.parent)) {
                Node cur = find(node.parent);
                node.parent = cur.parent;
                node.ratio *= cur.ratio;
            }
            return node;
        }
        
        public void union(String p, String q, double ratio) {
            boolean hasP = map.containsKey(p);
            boolean hasQ = map.containsKey(q);
            if (!hasP && !hasQ) {
                map.put(p, new Node(q, ratio));
                map.put(q, new Node(q, 1.0));
            } else if (!hasP) {
                map.put(p, new Node(q, ratio));
            } else if (!hasQ) {
                map.put(q, new Node(p, 1.0 / ratio));
            } else {
                Node nodeP = map.get(p);
                Node nodeQ = map.get(q);
                
                if (nodeP.rank > nodeQ.rank) {
                    nodeQ.parent = nodeP.parent;
                    nodeQ.ratio = 1.0 / ratio * nodeP.ratio;
                } else if (nodeP.rank < nodeQ.rank) {
                    nodeP.parent = nodeQ.parent;
                    nodeP.ratio = ratio * nodeQ.ratio;
                } else {
                    nodeP.parent = nodeQ.parent;
                    nodeP.ratio = ratio * nodeQ.ratio;
                    nodeQ.rank++;
                }
            }
        }
    }
}
