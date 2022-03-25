from abc import ABC, abstractmethod


class Factory(ABC):
    def __init__(self, name: str, capacity: int):
        self.capacity = capacity
        self.name = name
        self.ingredients = dict()
    
    @abstractmethod
    def add_ingredient(self, ingredient_type: str, quantity: int):
        pass
    
    @abstractmethod
    def remove_ingredient(self, ingredient_type: str, quantity: int):
        pass
    
    @property
    def ingredients_quantity(self):
        return sum(x for y, x in self.ingredients.items())
    
    def can_add(self, value: int):
        return self.capacity-self.ingredients_quantity >= value
