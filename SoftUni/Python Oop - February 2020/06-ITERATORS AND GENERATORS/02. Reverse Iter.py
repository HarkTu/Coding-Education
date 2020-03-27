class reverse_iter:
    def __init__(self, iterable):
        self.iterable = iterable
        self.index = len(iterable)
    
    def __iter__(self):
        return self
    
    def __next__(self):
        if self.index < 1:
            raise StopIteration
        i = self.index
        self.index -= 1
        return self.iterable[self.index]


# Tests
reversed_list = reverse_iter([2, 3, 4, 5, 6])
for item in reversed_list:
    print(item)
