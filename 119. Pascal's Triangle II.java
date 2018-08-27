class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> lastLevel = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    temp.add(1);
                }
                else {
                    temp.add(lastLevel.get(j - 1) + lastLevel.get(j));
                }
            }
            lastLevel = temp;
        }
        return lastLevel;
    }
}

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> lastLevel = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            lastLevel.add(0, 1);
            for (int j = 1; j < lastLevel.size() - 1; j++) {
                lastLevel.set(j, lastLevel.get(j) + lastLevel.get(j + 1));
            }
        }
        return lastLevel;
    }
}
