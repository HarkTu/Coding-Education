from collections import deque

array = deque([x for x in input().split()])

colors = ['yellow', 'blue', 'red', 'orange', 'purple', 'green']
found = []
while array:
    left = array.popleft()
    right = ''
    if array:
        right = array.pop()
        substring = f"{left}{right}"
        substring2 = f"{right}{left}"
        if substring in colors:
            found.append(substring)
        elif substring2 in colors:
            found.append(substring2)
        else:
            substring2 = right[:-1]
            substring = left[:-1]
            mid_ind = len(array) // 2
            if substring2:
                array.insert(mid_ind, substring2)
            if substring:
                array.insert(mid_ind, substring)
    elif left in colors:
        found.append(left)

for x in found:
    if x == 'orange':
        if 'red' not in found or 'yellow' not in found:
            found.remove(x)
    if x == 'purple':
        if 'red' not in found or 'blue' not in found:
            found.remove(x)
    if x == 'green':
        if 'blue' not in found or 'yellow' not in found:
            found.remove(x)

print(found)
