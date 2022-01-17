/*
Idea

    How much profit we can get if we add one extraStudents to a particular class (pass, total)? This profit can be defined as: (pass+1)/(total+1) - pass/total.
    For each student from extraStudents, we try to add to a class which will increase its profit maximum.
    We can use maxHeap structure which can give us the class which has maximum profit after adding.

Complexity:

    Time:
        Python: O(M*logN + N), where M is extraStudents and N is number of classes.
        Java: O(M*logN + N*logN)
    Space: O(N)

*/
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                double delta1 = (double)(b[0] + 1) / (b[1] + 1) - (double)b[0] / b[1];
                double delta2 = (double)(a[0] + 1) / (a[1] + 1) - (double)a[0] / a[1];
                if (delta1 == delta2) {
                    return 0;
                }
                return delta1 - delta2 > 0 ? 1 : -1;
            }
        });       
        pq.addAll(Arrays.asList(classes));
        while (extraStudents > 0) {
            int[] cur = pq.poll();
            pq.offer(new int[]{cur[0] + 1, cur[1] + 1});
            extraStudents--;
        }
        double totalPassRatio = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            totalPassRatio += (double)cur[0] / (double)cur[1];
        }
        return totalPassRatio / classes.length;
    }
}
