# Stack

1. why is Stack a class while Queue is a Interface: one reason is that there are variants of Queues that it is convenient to be able to swap in, like PriorityQueues. They fulfill the same interface but behave differently. I don't think there is anything like that for Stacks, or at least it isn't used nearly as often.
  
2. * Deque<Integer> stack = new ArrayDeque<Integer>();
   * JDK doc 中建议使用Deque代替Stack实现栈，因为Stack继承自Vector，需要synchronized，性能略低。
   * boolean isEmpty() - 判断栈是否为空，若使用 Stack 类构造则为 empty()
   * E peek() - 取栈顶元素，不移除
   * E pop() - 移除栈顶元素并返回该元素
   * E push(E item) - 向栈顶添加元素
  
