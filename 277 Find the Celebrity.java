public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == candidate) {
                continue;
            }
            if (knows(candidate, i) || !knows(i, candidate)) {
                return -1;
            }
        }
        return candidate;
    }
}

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int c = 0;
        for (int i = 1; i < n; i++) {
            if (knows(c, i)) {
                c = i;
            }
        }
        // indices before c and after c cannot be celebrity now.
        // indices before c knows other people; indices after c
        // are people c doesn't know.
        
        // 第一个for循环后，选出了候选人，并且知道c后面的人，c都不认识。还需要check'：
        // 1. c后面的人都认识c
        // 2. c前面的人都认识c
        // 3. c前面的人，c都不认识。
        
        
        
        // Now just check whether c is celebrity or not.
        for (int i = c + 1; i < n; i++) {
            if (!knows(i, c)) {
                return -1;
            }
        }
        for (int i = 0; i < c; i++) {
            if (knows(c, i) || !knows(i, c)) {
                return -1;
            }
        }
        return c;        
    }
}
