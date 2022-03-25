from project.factory.chocolate_factory import ChocolateFactory
from project.factory.egg_factory import EggFactory
from project.factory.paint_factory import PaintFactory


class EasterShop:
    def __init__(self, name, chocolate_factory: ChocolateFactory, egg_factory: EggFactory, paint_factory: PaintFactory):
        self.storage = dict()
        self.paint_factory = paint_factory
        self.chocolate_factory = chocolate_factory
        self.egg_factory = egg_factory
        self.name = name
    
    def add_chocolate_ingredient(self, type: str, quantity: int):
        self.chocolate_factory.add_ingredient(type, quantity)
    
    def add_egg_ingredient(self, type: str, quantity: int):
        self.egg_factory.add_ingredient(type, quantity)
    
    def add_paint_ingredient(self, type: str, quantity: int):
        self.paint_factory.add_ingredient(type, quantity)
    
    def make_chocolate(self, recipe: str):
        self.chocolate_factory.make_chocolate(recipe)
        if recipe not in self.storage.keys():
            self.storage[recipe] = 0
        self.storage[recipe] += 1
    
    def paint_egg(self, color: str, egg_type: str):
        if color not in self.paint_factory.ingredients or egg_type not in self.egg_factory.ingredients:
            raise ValueError("Invalid commands")
        product = f"{color} {egg_type}"
        if product not in self.storage:
            self.storage[product] = 0
        self.storage[product] += 1
    
    def __repr__(self):
        result = f"Shop name: {self.name}\nShop Storage:\n"
        for key, value in self.storage.items():
            result += f"{key}: {value}\n"
        return result
