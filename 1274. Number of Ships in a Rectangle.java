/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */
/*
Time complexity: O(n) where n is total number of points inside the rectangle
T(n) = 4xT(n/4) + O(1)
Apply master theorem: n^(log(4)4) = n is O(O(1)). So T(n) = O(n).

The above time complexity is incorrect if we take into consideration the problem constraint that there can be only 10 ships on the plane.
Assume T(M,N,K) represents counting ships in a plane of size MxN where maximum number of ships is K.
Then in worst case assuming all ships are spread out in different partitions, (i) T(M,N,10) = 10 T(M/2, N/2, 1) + 6C1 [Divided plane into
16 parts atmost 10 can have a ship while atleast 6 will have no ships.]
and (ii) T(M,N,1) = T(M/2, N/2, 1) + 3C2 [This equation is similar to binary search but in two dimensions.]
From equation (ii) similar to derivation of binary search complexity, T(M,N,1) = O(logMN)
Therefore substituting in equation (i), T(M,N,K) = O(K logMN)
*/
class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
            return sea.hasShips(topRight, bottomLeft) ? 1 : 0;
        }
        int midX = (topRight[0] + bottomLeft[0]) / 2;
        int midY = (topRight[1] + bottomLeft[1]) / 2;
        return countShips(sea, new int[]{midX, topRight[1]}, new int[]{bottomLeft[0], midY + 1}) +
            countShips(sea, topRight, new int[]{midX + 1, midY + 1}) +
            countShips(sea, new int[]{midX, midY}, bottomLeft) +
            countShips(sea, new int[]{topRight[0], midY}, new int[]{midX + 1, bottomLeft[1]});
    }
}
