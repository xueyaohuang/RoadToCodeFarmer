/**Insert short person to tall person will not affect the relative order of the taller persons, thus sort from higher height to lower heigh
 * for persons with same height, sort them according to the k index
 * for example: for [7,0], [4,4], [7,1], [5,0], [6,1], [5,2]
 * first sort tallest persons [7,0], [7,1],
 * then insert lower height to the position of their k index
 * [7,0], [6,1] [7,1]
 * [5,0],[7,0], [6,1] [7,1]
 * [5,0],[7,0], [5,2] [6,1] [7,1]
 * [5,0],[7,0], [5,2] [6,1], [4,4] [7,1]
 * done.
 */
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> res = new ArrayList<int[]>();
        for (int[] p : people) {
            res.add(p[1], p);
        }
        return res.toArray(new int[people.length][people[0].length]); // res.toArray(people);
    }
}
