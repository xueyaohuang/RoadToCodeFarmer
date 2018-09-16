class Solution {
    //follow the highest voted post
    //
    
    //total time complexity? N * 2 ^ N?
    //can we optimize the complexity since the string operation is expensive
    //you should also be able to write BFS and simplied version(two pass, one pass)
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        removeHelper(s, res, 0, 0, '(', ')');
        return res;
    }
    
    private void removeHelper(String s, List<String> res, int start, int lastRemove, char plusChar, char minusChar){
        int count = 0;
        for (int i = start; i < s.length(); i++){
            if (s.charAt(i) == plusChar) count++;
            if (s.charAt(i) == minusChar) count--;
            
            if (count < 0){
                for (int j = lastRemove; j <= i; j++){
                    if (s.charAt(j) == minusChar && (j == lastRemove || s.charAt(j) != s.charAt(j - 1))){
                        removeHelper(s.substring(0, j) + s.substring(j + 1), res, i, j, plusChar, minusChar);
                    }
                }
                return; //should be return here, since the previous part has no wrong minusChar
            }
        }
        
        if (plusChar == '(' && count == 0){
            res.add(s);
            return;
        }
        
        StringBuilder sb = new StringBuilder(s);
        String reversedS = sb.reverse().toString();
        
        if (plusChar == '('){
            removeHelper(reversedS, res, 0, 0, ')', '(');
        } else {
            res.add(reversedS);
        }
        
    }
}

class Solution {
public List<String> removeInvalidParentheses(String s) {
    int rmL = 0, rmR = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '(') {
            rmL++;
        } else if (s.charAt(i) == ')') {
            if (rmL != 0) {
                rmL--;
            } else {
                rmR++;
            }
        }
    }
    Set<String> res = new HashSet<>();
    dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
    return new ArrayList<String>(res);
}

public void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
    if (rmL < 0 || rmR < 0 || open < 0) {
        return;
    }
    if (i == s.length()) {
        if (rmL == 0 && rmR == 0 && open == 0) {
            res.add(sb.toString());
        }        
        return;
    }

    char c = s.charAt(i); 
    int len = sb.length();

    if (c == '(') {
        dfs(s, i + 1, res, sb, rmL - 1, rmR, open);		    // not use (
    	dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);       // use (

    } else if (c == ')') {
        dfs(s, i + 1, res, sb, rmL, rmR - 1, open);	            // not use  )
    	dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);  	    // use )

    } else {
        dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);	
    }

    sb.setLength(len);        
}
}


public class Solution {
    public List<String> removeInvalidParentheses(String s) {
      List<String> res = new ArrayList<>();
      
      // sanity check
      if (s == null) return res;
      
      Set<String> visited = new HashSet<>();
      Queue<String> queue = new LinkedList<>();
      
      // initialize
      queue.add(s);
      visited.add(s);
      
      boolean found = false;
      
      while (!queue.isEmpty()) {
        s = queue.poll();
        
        if (isValid(s)) {
          // found an answer, add to the result
          res.add(s);
          found = true;
        }
      
        if (found) continue;
      
        // generate all possible states
        for (int i = 0; i < s.length(); i++) {
          // we only try to remove left or right paren
          if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;
        
          String t = s.substring(0, i) + s.substring(i + 1);
        
          if (!visited.contains(t)) {
            // for each state, if it's not visited, add it to the queue
            queue.add(t);
            visited.add(t);
          }
        }
      }
      
      return res;
    }
    
    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
      int count = 0;
    
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') count++;
        if (c == ')' && count-- == 0) return false;
      }
    
      return count == 0;
    }
}

()))(
