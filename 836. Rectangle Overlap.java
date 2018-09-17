

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        
        double rec1CenX = ((double)(rec1[0] + rec1[2])) / 2;
        double rec1CenY = ((double)(rec1[1] + rec1[3])) / 2;
        double rec2CenX = ((double)(rec2[0] + rec2[2])) / 2;
        double rec2CenY = ((double)(rec2[1] + rec2[3])) / 2;
        
        double rec1Width = rec1[2] - rec1[0];
        double rec2Width = rec2[2] - rec2[0];
        double rec1Height = rec1[3] - rec1[1];
        double rec2Height = rec2[3] - rec2[1];
        
        return Math.abs(rec1CenX - rec2CenX) < rec1Width / 2 + rec2Width / 2 && Math.abs(rec1CenY - rec2CenY) < rec1Height / 2 + rec2Height / 2;
    }
    
}

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return rec1[0] < rec2[2] && rec1[2] > rec2[0] && rec1[1] < rec2[3] && rec1[3] > rec2[1];
    }
}
