from project.factory.factory import Factory


class ChocolateFactory(Factory):
    def __init__(self, name: str, capacity: int):
        super().__init__(name, capacity)
        self.recipes = dict()
        self.products = dict()
    
    def add_ingredient(self, ingredient_type: str, quantity: int):
        valid_ingredients = ["white chocolate", "dark chocolate", "milk chocolate", "sugar"]
        if not self.can_add(quantity):
            raise ValueError("Not enough space in factory")
        elif ingredient_type not in valid_ingredients:
            raise TypeError(f"Ingredient of type {ingredient_type} not allowed in ChocolateFactory")
        elif ingredient_type not in self.ingredients:
            self.ingredients[ingredient_type] = 0
        self.ingredients[ingredient_type] += quantity
    
    def remove_ingredient(self, ingredient_type: str, quantity: int):
        if self.ingredients[ingredient_type] < quantity:
            raise ValueError("Ingredient quantity cannot be less than zero")
        if ingredient_type not in self.ingredients:
            raise KeyError("No such product in the factory")
        self.ingredients[ingredient_type] -= quantity
    
    def add_recipe(self, recipe_name: str, recipe: dict):
        self.recipes[recipe_name] = recipe
    
    def make_chocolate(self, recipe_name: str):
        if recipe_name not in self.recipes:
            raise TypeError("No such recipe")
        if recipe_name not in self.products:
            self.products[recipe_name] = 0
        self.products[recipe_name] += 1
        for key, value in self.recipes[recipe_name]:
            self.ingredients[key] -= value
