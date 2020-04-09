from collections import deque


def find_strongest_eggs(sequence, sub):
    found = []
    sublists = []
    for i in range(sub):
        sublist = deque()
        for x in range(i, len(sequence), sub):
            sublist.append(sequence[x])
        sublists.append(sublist)
    for x in sublists:
        mid_ind = len(x) // 2
        if x[mid_ind - 1] < x[mid_ind + 1] < x[mid_ind]:
            while len(x) > 3:
                right = x.pop()
                left = x.popleft()
                if not right > left:
                    break
            else:
                found.append(x[1])
    return found


test = ([-1, 7, 3, 15, 2, 12], 2)
print(find_strongest_eggs(*test))
test = ([-1, 0, 2, 5, 2, 3], 2)
print(find_strongest_eggs(*test))
test = ([51, 21, 83, 52, 55], 1)
print(find_strongest_eggs(*test))
