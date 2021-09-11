class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long heightMax = horizontalCuts[0], widthMax = verticalCuts[0];
        for (int i = 1; i < horizontalCuts.length; i++) {
            heightMax = Math.max(heightMax, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        heightMax = Math.max(heightMax, h - horizontalCuts[horizontalCuts.length - 1]);
        for (int i = 1; i < verticalCuts.length; i++) {
            widthMax = Math.max(widthMax, verticalCuts[i] - verticalCuts[i - 1]);
        }
        widthMax = Math.max(widthMax, w - verticalCuts[verticalCuts.length - 1]);
        return (int)((heightMax *  widthMax) % (1000000007));
    }
}
