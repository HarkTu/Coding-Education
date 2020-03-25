class Stack():
    def __init__(self):
        self.__data = []
    
    def push(self, item):
        self.__data.append(item)
    
    def pop(self):
        return self.__data.pop()
    
    def peek(self):
        return self.__data[-1]
    
    def is_empty(self):
        return not self.__data
    
    def __str__(self):
        return f"{self.__data[::-1]}"


strings = Stack()

strings.push('asf')
strings.push('1')
strings.push('2')
strings.push('3')
print(strings.is_empty())

print(strings)
