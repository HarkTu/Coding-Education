def fibonacci():
    first = 1
    second = 0
    collection = 0
    while 1:
        yield collection
        collection = first + second
        first, second = second, collection


generator = fibonacci()
for i in range(5):
    print(next(generator))
