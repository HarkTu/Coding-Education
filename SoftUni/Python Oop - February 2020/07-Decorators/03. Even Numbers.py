def even_numbers(function):
    def wrapper(numbers):
        return list(filter(lambda x: x % 2 == 0, [5,8,9]))
    
    return wrapper


@even_numbers
def get_numbers(numbers):
    return numbers


print(get_numbers([1, 2, 3, 4, 5]))
# [2, 4]
