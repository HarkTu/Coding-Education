def type_check(arg_type):
    def decorator(function):
        def wrapper(arg):
            if type(arg) == arg_type:
                return function(arg)
            return "Bad Type"
        
        return wrapper
    
    return decorator


# Tests
@type_check(int)
def times2(num):
    return num * 2


print(times2(2))
print(times2('Not A Number'))
"""
4
Bad Type
"""


@type_check(str)
def first_letter(word):
    return word[0]


print(first_letter('Hello World'))
print(first_letter(['Not', 'A', 'String']))

"""
H
Bad Type
"""
