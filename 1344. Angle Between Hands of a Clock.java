class Solution {
    public double angleClock(int hour, int minutes) {
        double mAngle = minutes * 6;
        double hAngle = (hour % 12) * 30 + ((double)minutes / 60) * 30 ;
        double res = Math.abs(mAngle - hAngle);
        return res > 180 ? 360 - res : res;
    }
}
