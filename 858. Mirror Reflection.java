// 运动的分解，分解到水平垂直2个方向上。
// 1. 根据三角形相似，光线水平走了p，垂直一定走q。
// 2. verticalDist代表在垂直方向上累计走过的距离。折返往回的也是加上，不是减。
// 3. i代表光线走了几个水平p的距离。
// 4. 0和出发点在水平方向同一侧，要到0，垂直走过的距离必定是p的偶数倍。同理，要到1或2，垂直走过的距离必定是p的奇数倍。
// 5. 怎么判断是1还是2？由于2和出发点在垂直方向同一侧，要到2，水平方向走过的距离必定是p的偶数倍。同理，1的话，水平方向走过距离是p的奇数倍。
class Solution {
    public int mirrorReflection(int p, int q) {
        int verticalDist = 0;
        for (int i = 1; ; i++) {
            verticalDist += q;
            verticalDist %= 2 * p;
            if (verticalDist == 0) {
                return 0;
            }
            if (verticalDist == p) {
                if (i % 2 == 1) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
    }
}
