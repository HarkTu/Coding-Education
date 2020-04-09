size = int(input())

matrix = []
b_row = 0
b_col = 0

for x in range(size):
    array = input().split(' ')
    for y in array:
        if y == 'B':
            b_row = x
            b_col = array.index(y)
    matrix.append(array)

directions = {
    'up': [-1, 0],
    'down': [1, 0],
    'left': [0, -1],
    'right': [0, 1],
}

egg_indices = []
sum_collected = 0
best_direction = ''
for direction, step in directions.items():
    temp_collected = 0
    temp_steps = []
    new_row = b_row + step[0]
    new_col = b_col + step[1]
    while 0 <= new_row < size and 0 <= new_col < size and matrix[new_row][new_col].isdigit():
        temp_collected += int(matrix[new_row][new_col])
        temp_steps.append([new_row, new_col])
        new_row += step[0]
        new_col += step[1]
    if sum_collected < temp_collected or (temp_collected == sum_collected and len(temp_steps) > len(egg_indices)):
        sum_collected = temp_collected
        egg_indices = temp_steps
        best_direction = direction

print(best_direction)
for x in egg_indices:
    print(x)
print(sum_collected)
