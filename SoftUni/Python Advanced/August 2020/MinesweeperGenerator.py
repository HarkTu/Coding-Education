matrix_size = int(input())
matrix = [[0] * matrix_size for x in range(matrix_size)]

directions = {'right': [0, 1],
              'right-down': [1, 1],
              'down': [1, 0],
              'down-left': [1, -1],
              'left': [0, -1],
              'left-up': [-1, -1],
              'up': [-1, 0],
              'up-right': [-1, 1],
              }

bombs_count = int(input())
for x in range(bombs_count):
    bomb_position = [int(x) for x in (input()[1:-1]).split(", ")]
    bomb_row, bomb_column = bomb_position[0], bomb_position[1]
    matrix[bomb_row][bomb_column] = '*'

    for value in directions.values():
        number_row = bomb_row + value[0]
        number_column = bomb_column + value[1]
        if 0 <= number_row < matrix_size and 0 <= number_column < matrix_size and \
                matrix[number_row][number_column] is not "*":
            matrix[number_row][number_column] += 1

for row in matrix:
    print(*row)
