# from secrets import choice
from random import randint


class RandomList(list):
    def get_random_element(self):
        index = randint(0, len(self) - 1)
        return self.pop(index)
        # return self.pop(choice(self))


a = RandomList()
[a.append(x) for x in range(10)]
print(a)
print(a.get_random_element())
print(a.get_random_element())
print(a.get_random_element())
print(a.get_random_element())
print(a.get_random_element())
print(a.get_random_element())
print(a.get_random_element())
print(a.get_random_element())
print(a.get_random_element())
print(a.get_random_element())
print(a)
