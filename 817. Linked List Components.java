class Solution {
    public int numComponents(ListNode head, int[] G) {
        int count = 0;
        ListNode node = head;
        Set<Integer> set = new HashSet<>();
        for (int i : G) {
            set.add(i);
        }
        while (node != null) {
            if (set.contains(node.val)) {
                count++;
                while (node != null && set.contains(node.val)) {
                    node = node.next;
                }
            } else {
                node = node.next;
            }
        }
        return count;
    }
}
