class dictionary_iter:
    def __init__(self, dictionary):
        self.iter_dict = [i for i in dictionary.items()]
        self.index = 0
    
    def __iter__(self):
        return self
    
    def __next__(self):
        if self.index == len(self.iter_dict):
            raise StopIteration
        current = self.iter_dict[self.index]
        self.index += 1
        return current


result = dictionary_iter({1: "1", 2: "2"})
for x in result:
    print(x)
