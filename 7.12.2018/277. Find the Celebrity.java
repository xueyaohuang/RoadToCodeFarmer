/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int a = 0;
        int b = n - 1;
        while (a < b) {
            if (knows(a, b)) {
                a++;
            }
            else {
                b--;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != a &&(knows(a, i) || !knows(i, a))) {
                return -1;
            }
        }
        return a;
    }
}
