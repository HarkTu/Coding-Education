from project.factory.factory import Factory


class PaintFactory(Factory):
    def __init__(self, name: str, capacity: int):
        super().__init__(name, capacity)
    
    def add_ingredient(self, ingredient_type: str, quantity: int):
        valid_ingredients = ["white", "yellow", "blue", "green", "red"]
        if not self.can_add(quantity):
            raise ValueError("Not enough space in factory")
        elif ingredient_type not in valid_ingredients:
            raise TypeError(f"Ingredient of type {ingredient_type} not allowed in PaintFactory")
        elif ingredient_type not in self.ingredients:
            self.ingredients[ingredient_type] = 0
        self.ingredients[ingredient_type] += quantity
    
    def remove_ingredient(self, ingredient_type: str, quantity: int):
        if ingredient_type not in self.ingredients.keys():
            raise KeyError('No such ingredient in the factory')
        if self.ingredients[ingredient_type] < quantity:
            raise ValueError("Ingredient quantity cannot be less than zero")
        self.ingredients[ingredient_type] -= quantity
    
    @property
    def products(self):
        return self.ingredients

#
# pfac = PaintFactory('pfac', 3)
# pfac.add_ingredient('white', 2)
# pfac.remove_ingredient('whit', 3)
