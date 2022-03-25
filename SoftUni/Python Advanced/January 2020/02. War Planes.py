targets = 0
targets_destroyed = 0

field = []
plane_row = None
plane_column = None

for i in range(int(input())):
    line = input().split()
    field.append(line)
    for j in range(len(line)):
        if line[j] == 't':
            targets += 1
        elif not plane_row and line[j] == 'p':
            plane_row, plane_column = i, j
field[plane_row][plane_column] = '.'

for _ in range(int(input())):
    command = input().split()
    target_row, target_column = plane_row, plane_column
    if command[1] == 'up':
        target_row -= int(command[2])
    elif command[1] == 'down':
        target_row += int(command[2])
    elif command[1] == 'left':
        target_column -= int(command[2])
    elif command[1] == 'right':
        target_column += int(command[2])
    if 0 <= target_row < len(field) and 0 <= target_column < len(field[target_row]):
        if command[0] == 'shoot':
            if field[target_row][target_column] == 't':
                targets_destroyed += 1
            field[target_row][target_column] = 'x'
            if targets_destroyed == targets:
                break
        elif field[target_row][target_column] == '.':
            plane_row = target_row
            plane_column = target_column

field[plane_row][plane_column] = 'p'
if targets_destroyed < targets:
    print(f"Mission failed! {targets - targets_destroyed} targets left.")
else:
    print(f"Mission accomplished! All {targets} targets destroyed.")
for row in field:
    print(*row, sep=' ')
