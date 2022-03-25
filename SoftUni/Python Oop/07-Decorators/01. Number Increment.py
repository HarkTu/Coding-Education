def number_increment(numbers):
    def increase():
        return [x + 1 for x in numbers]
    
    return increase()


print(number_increment([1, 2, 3]))

# [2, 3, 4]

"""
def number_increment(numbers):
    def increase():
        nonlocal numbers
        numbers = [x + 1 for x in numbers]
    
    def get():
        return numbers
    
    return {
        'increase': increase,
        'get': get,
    }


operations = number_increment([1, 2, 3])
operations['increase']()
print(operations['get']())
"""
