from abc import ABC, abstractmethod


class Food(ABC):
    @abstractmethod
    def __init__(self, quantity):
        self.quantity = quantity


class Vegetable(Food):
    def __init__(self, quantity):
        super(Vegetable, self).__init__(quantity)


class Fruit(Food):
    def __init__(self, quantity):
        super(Fruit, self).__init__(quantity)


class Meat(Food):
    def __init__(self, quantity):
        super(Meat, self).__init__(quantity)


class Seed(Food):
    def __init__(self, quantity):
        super(Seed, self).__init__(quantity)


class Animal(ABC):
    @abstractmethod
    def __init__(self, name, weight):
        self.food_eaten = 0
        self.weight = weight
        self.name = name
    
    @abstractmethod
    def make_sound(self): pass
    
    @abstractmethod
    def feed(self, food): pass


class Bird(Animal, ABC):
    
    def __init__(self, name, weight, wing_size):
        super(Bird, self).__init__(name, weight)
        self.wing_size = wing_size
    
    def __repr__(self):
        return f"{self.__class__.__name__} [{self.name}, {self.wing_size}, {self.weight}, {self.food_eaten}]"


class Owl(Bird):
    def __init__(self, name, weight, wing_size):
        super(Owl, self).__init__(name, weight, wing_size)
    
    def make_sound(self):
        return "Hoot Hoot"
    
    def feed(self, food):
        if not type(food) is Meat:
            return f"{self.__class__.__name__} does not eat {food.__class__.__name__}!"
        self.weight += food.quantity * 0.25
        self.food_eaten += food.quantity


class Hen(Bird):
    
    def __init__(self, name, weight, wing_size):
        super(Hen, self).__init__(name, weight, wing_size)
    
    def make_sound(self):
        return "Cluck"
    
    def feed(self, food):
        if not isinstance(food, Food):
            return f"{self.__class__.__name__} does not eat {food.__class__.__name__}!"
        self.weight += 0.35 * food.quantity
        self.food_eaten += food.quantity


class Mammal(Animal, ABC):
    
    def __init__(self, name, weight, living_region):
        super(Mammal, self).__init__(name, weight)
        self.living_region = living_region
    
    def __repr__(self):
        return f"{self.__class__.__name__} [{self.name}, {self.weight}, {self.living_region}, {self.food_eaten}]"


class Mouse(Mammal):
    def __init__(self, name, weight, living_region):
        super(Mouse, self).__init__(name, weight, living_region)
    
    def make_sound(self):
        return "Squeak"
    
    def feed(self, food):
        if not isinstance(food, (Vegetable, Fruit)):
            return f"{self.__class__.__name__} does not eat {food.__class__.__name__}!"
        self.weight += 0.10 * food.quantity
        self.food_eaten += food.quantity


class Dog(Mammal):
    def __init__(self, name, weight, living_region):
        super(Dog, self).__init__(name, weight, living_region)
    
    def make_sound(self):
        return "Woof!"
    
    def feed(self, food):
        if not type(food) is Meat:
            return f"{self.__class__.__name__} does not eat {food.__class__.__name__}!"
        self.weight += 0.40 * food.quantity
        self.food_eaten += food.quantity


class Cat(Mammal):
    def __init__(self, name, weight, living_region):
        super(Cat, self).__init__(name, weight, living_region)
    
    def make_sound(self):
        return "Meow"
    
    def feed(self, food):
        if not isinstance(food, (Vegetable, Meat)):
            return f"{self.__class__.__name__} does not eat {food.__class__.__name__}!"
        self.weight += 0.30 * food.quantity
        self.food_eaten += food.quantity


class Tiger(Mammal):
    def __init__(self, name, weight, living_region):
        super(Tiger, self).__init__(name, weight, living_region)
    
    def make_sound(self):
        return "ROAR!!!"
    
    def feed(self, food):
        if not type(food) is Meat:
            return f"{self.__class__.__name__} does not eat {food.__class__.__name__}!"
        self.weight += food.quantity * 1.0
        self.food_eaten += food.quantity


# Tests


veg = Vegetable(3)
fruit = Fruit(5)
meat = Meat(2)
seed = Seed(4)

# owl = Owl("Pip", 10, 10)
# print(owl)
# print(owl.make_sound())
# owl.feed(meat)
# print(owl.feed(veg))
# print(owl)

# tiger = Tiger("Harry", 10, 10)
# print(tiger)
# print(tiger.make_sound())
# print(tiger.feed(veg))
# print(tiger.feed(fruit))
# tiger.feed(meat)
# print(tiger)
#
mice = Mouse("Harry", 10, 10)
print(mice)
print(mice.make_sound())
mice.feed(veg)
mice.feed(fruit)
print(mice.feed(meat))
print(mice)

# cat = Cat("Harry", 10, 10)
# veg = Vegetable(3)
# fruit = Fruit(5)
# meat = Meat(1)
# print(cat)
# print(cat.make_sound())
# cat.feed(veg)
# print(cat.feed(fruit))
# cat.feed(meat)
# print(cat)

# tiger = Bird("Harry", 10, 10)
