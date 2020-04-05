from project.factory.factory import Factory


class EggFactory(Factory):
    def __init__(self, name: str, capacity: int):
        super().__init__(name, capacity)
    
    def add_ingredient(self, ingredient_type: str, quantity: int):
        valid_ingredients = ["chicken egg", "quail egg"]
        if not self.can_add(quantity):
            raise ValueError("Not enough space in factory")
        elif ingredient_type not in valid_ingredients:
            raise TypeError(f"Ingredient of type {ingredient_type} not allowed in EggFactory")
        elif ingredient_type not in self.ingredients:
            self.ingredients[ingredient_type] = 0
        self.ingredients[ingredient_type] += quantity
    
    def remove_ingredient(self, ingredient_type: str, quantity: int):
        if self.ingredients[ingredient_type] < quantity:
            raise ValueError("Ingredient quantity cannot be less than zero")
        if ingredient_type not in self.ingredients:
            raise KeyError("No such ingredient in the factory")
        else:
            self.ingredients[ingredient_type] -= quantity
    
    @property
    def products(self):
        return self.ingredients
