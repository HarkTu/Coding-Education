def list_manipulator(list_of_numbers, *others):
    numbers = list_of_numbers

    if others[0] == 'remove':
        if others[1] == 'beginning':
            if len(others) < 3:
                numbers.pop(0)
            else:
                for x in range(others[2]):
                    numbers.pop(0)
        else:
            if len(others) < 3:
                numbers.pop()
            else:
                for x in range(others[2]):
                    numbers.pop()
    else:
        manipulation = list(others[2:])
        if others[1] == 'beginning':
            numbers = manipulation + numbers
        else:
            numbers += manipulation
    return numbers