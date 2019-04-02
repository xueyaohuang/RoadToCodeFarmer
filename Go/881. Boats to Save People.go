func numRescueBoats(people []int, limit int) int {
    sort.Ints(people)
    i, j := 0, len(people) - 1
    count := 0
    for i <= j {
        if people[i] + people[j] <= limit {
            i++
        }
        j--
        count++
    }
    return count
}
