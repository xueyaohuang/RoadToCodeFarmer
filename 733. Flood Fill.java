class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] != newColor) {
            floodFill(image, sr, sc, image[sr][sc], newColor);
        }
        return image;
    }
    
    private void floodFill(int[][] image, int i, int j, int curColor, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != curColor) {
            return;
        }
        image[i][j] = newColor;
        floodFill(image, i - 1, j, curColor, newColor);
        floodFill(image, i + 1, j, curColor, newColor);
        floodFill(image, i, j - 1, curColor, newColor);
        floodFill(image, i, j + 1, curColor, newColor);
    }
}
