class custom_range:
    def __init__(self, start, end):
        self.end = end
        self.index = start
    
    def __iter__(self):
        return self
    
    def __next__(self):
        if self.index > self.end:
            raise StopIteration
        index = self.index
        self.index += 1
        return index


# Tests
cr = custom_range(1, 10)
it1= iter(cr)
print(next(it1))
it2= iter(cr)
print(next(it2))
print(next(it2))
print(next(it1))
# for num in one_to_ten:
#     print(num)
