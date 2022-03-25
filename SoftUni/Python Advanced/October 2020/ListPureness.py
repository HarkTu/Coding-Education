def best_list_pureness(numbers, K):
    list = numbers
    pureness_value = sum([list[x] * x for x in range(len(list))])
    count_rotations = 0

    for rot in range(K):
        list.insert(0, list.pop())
        pureness = 0
        for x in range(len(list)):
            pureness += list[x] * x
        if pureness > pureness_value:
            pureness_value = pureness
            count_rotations = rot + 1

    return f"Best pureness {pureness_value} after {count_rotations} rotations"