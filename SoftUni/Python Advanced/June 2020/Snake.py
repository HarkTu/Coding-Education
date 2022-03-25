n = int(input())
matrix = []
directions = {'up': [-1, 0],
              'down': [1, 0],
              'left': [0, -1],
              'right': [0, 1]
              }
lair = []
food_eaten = 0
snake_position = []
for row in range(n):
    field = list(input())
    for_matrix = []
    for column in range(n):
        if field[column] == 'B':
            lair.append([row, column])
        if field[column] == 'S':
            field[column] = '.'
            snake_position = [row, column]
        for_matrix.append(field[column])
    matrix.append(for_matrix)

while food_eaten < 10:
    command = input()
    next_row = snake_position[0] + directions[command][0]
    next_column = snake_position[1] + directions[command][1]
    if 0 <= next_row < n and 0 <= next_column < n:
        if matrix[next_row][next_column] == 'B':
            matrix[next_row][next_column] = '.'
            lair.remove([next_row, next_column])
            next_row = lair[0][0]
            next_column = lair[0][1]
        elif matrix[next_row][next_column] == '*':
            food_eaten += 1
    else:
        break
    matrix[next_row][next_column] = '.'
    snake_position = [next_row, next_column]

if food_eaten == 10:
    matrix[snake_position[0]][snake_position[1]] = 'S'
    print('You won! You fed the snake.')
else:
    print('Game over!')
print(f"Food eaten: {food_eaten}")
# [print(''.join(x)) for x in matrix]
# [print(*x, sep='') for x in matrix]
for x in matrix:
    print(*x, sep='')
