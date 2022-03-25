def logged(function):
    def wrapper(*args):
        result = ''
        result += f"you called {function.__name__}{args}\n"
        result += f"it returned {function(*args)}"
        return result
    
    return wrapper


# Tests

@logged
def func(*args):
    return 3 + len(args)


print(func(4, 4, 4))
"""
you called func(4, 4, 4)
it returned 6
"""


@logged
def sum_func(a, b):
    return a + b


print(sum_func(1, 4))
"""
you called sum_func(1, 4)
it returned 5
"""
