# Queue
1. Queue 是一个 FIFO（先进先出）的数据结构，并发中使用较多，可以安全地将对象从一个任务传给另一个任务。

2. Queue 在 Java 中是 Interface, 一种实现是 LinkedList, LinkedList 向上转型为 Queue, Queue 通常不能存储 null 元素，否则与 poll() 等方法的返回值混淆。
3. 优化用offer，poll，peek，相比于add，remove，element。

## Priority Queue - 优先队列
1. 优先队列中的每个元素都有各自的优先级，优先级最高的元素最先得到服务。优先队列可以使用数组或链表实现，从时间和空间复杂度来说，往往用二叉堆来实现。

2. Java中提供PriorityQueue类，基于 priority heap 实现，非synchronized，故多线程下应使用PriorityBlockingQueue. 默认为自然序（小根堆），需要其他排序方式可自行实现Comparator接口，选用合适的构造器初始化。使用迭代器遍历时不保证有序！有序访问时需要使用Arrays.sort(pq.toArray()).
3. * enqueuing and dequeuing: offer, poll, remove() and add - O(logn)
   * Object: remove(Object) and contains(Object) - O(n)
   * retrieval: peek, element, and size - O(1).
   
4. Priority Queue并不是说，add一个元素，queue里面的所有元素都按照Priority排序好（如果是这样，那么使用迭代器遍历就是有序的）。他只是保证了以下几个操作： 
      * Every item has a priority associated with it
      * An element with high priority is dequeued before an element with low priority
      * insert(item, priority): Inserts an item with given priority -- add, offer
      * getHighestPriority(): Returns the highest priority item -- peek
      * deleteHighestPriority(): Removes the highest priority item -- poll




## Deque - 双端队列
1. 双端队列（deque，全名double-ended queue）可以让你在任何一端添加或者移除元素，因此它是一种具有队列和栈性质的数据结构。

2. Java 中提供了 Deque 接口，既可使用ArrayDeque（数组）来实现，也可以使用LinkedList（链表）来实现。前者是一个数组外加首尾索引，后者是双向链表。
offerFirst(e)，offerLast(e)，pollFirst()，pollLast()，peekFirst()，peekLast()


## Monotonic queue

