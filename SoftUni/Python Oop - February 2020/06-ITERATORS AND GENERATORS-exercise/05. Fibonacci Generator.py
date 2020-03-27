def fibonacci():
    first = 1
    second = 0
    addition = 0
    while 1:
        current = addition
        addition = first + second
        first, second = second, addition
        yield current



generator = fibonacci()
for i in range(5):
    print(next(generator))
