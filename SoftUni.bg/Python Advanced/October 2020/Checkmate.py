matrix = []

king_row = 0
king_column = 0
queens = False
for row in range(8):
    field = input().split()
    matrix.append(field)
    for column in range(8):
        if field[column] == 'K':
            king_row, king_column = row, column

directions = {'right': [0, 1],
              'right-down': [1, 1],
              'down': [1, 0],
              'down-left': [1, -1],
              'left': [0, -1],
              'left-up': [-1, -1],
              'up': [-1, 0],
              'up-right': [-1, 1],
              }

for value in directions.values():
    temp_row, temp_column = king_row, king_column
    while 8 > temp_row + value[0] >= 0 and 0 <= temp_column + value[1] < 8:
        temp_row += value[0]
        temp_column += value[1]
        if matrix[temp_row][temp_column] == 'Q':
            queens = True
            print(f"[{temp_row}, {temp_column}]")
            break

if not queens:
    print("The king is safe!")
