initial = input()
size = int(input())
matrix = []
p_row = 0
p_column = 0
for row in range(size):
    temp = input()
    add_row = []
    for column in range(len(temp)):
        if temp[column] == 'P':
            p_row = row
            p_column = column
            add_row.append('-')
            continue
        add_row.append(temp[column])
    matrix.append(add_row)

directions = {'up': [-1, 0],
              'down': [1, 0],
              'left': [0, -1],
              'right': [0, 1]
              }

commands_count = int(input())
for _ in range(commands_count):
    command = input()
    next_row = p_row + directions[command][0]
    next_column = p_column + directions[command][1]
    if 0 <= next_row < size and 0 <= next_column < size:
        p_row, p_column = next_row, next_column
        if not matrix[next_row][next_column] == '-':
            initial += matrix[p_row][p_column]
            matrix[p_row][p_column] = '-'
    else:
        initial = initial[:-1]
matrix[p_row][p_column] = 'P'
print(initial)
for row in matrix:
    print(*row, sep='')
