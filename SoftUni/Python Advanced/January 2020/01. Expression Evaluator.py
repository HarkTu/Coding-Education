def operation(operator, *args):
    result = args[0]
    for num in args[1:]:
        result = eval(f"{result} {operator} {num}")
    return int(result)


last_result = []
params = []
func = ''
expression = input()
for x in list(expression.split()):
    if not x == '*' and not x == '/' and not x == '+' and not x == '-':
        params.append(x)
    else:
        func = x
    if func:
        params = [operation(func, *params)]
        func = ''

print(*params)
