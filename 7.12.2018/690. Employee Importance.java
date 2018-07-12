/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        Queue<Employee> queue = new LinkedList<Employee>();
        queue.add(map.get(id));
        int importance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Employee emp = queue.poll();
                importance += emp.importance;
                for (int j : emp.subordinates) {
                    queue.add(map.get(j));
                }
            }
        }
        return importance;
    }
}

class Solution {
    int importance = 0;
    public int getImportance(List<Employee> employees, int id) {
        Employee manager = employees.stream().filter(e -> e.id == id).collect(Collectors.toList()).get(0);
        importance += manager.importance;
        manager.subordinates.forEach(sub -> getImportance(employees, sub));
        return importance;
    }
}
