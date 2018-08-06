# Set

1. Java中，Set有HashSet, TreeSet 或 LinkedHashSet三种实现。
2. HashSet基于散列函数实现，无序，查询速度最快；TreeSet基于红-黑树实现，有序。
3. HashSet的基本操作，add, remove, contains and size都是O(1)的复杂度。
4. * Underlying data structure for HashSet is hashtable.
   * Objects that you insert in HashSet are not guaranteed to be inserted in same order. 
   * Objects are inserted based on their hash code.
   * NULL elements are allowed in HashSet.

## TreeSet

1. TreeSet is basically implementation of a self-balancing binary search tree like Red-Black Tree. 
2. Operations like add, remove and search take O(Log n) time. 
3. Operations like printing n elements in sorted order takes O(n) time.
