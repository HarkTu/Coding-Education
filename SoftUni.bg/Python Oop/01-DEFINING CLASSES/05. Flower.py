class Flower:
    is_happy = False
    
    def __init__(self, name, water_requirements):
        self.water_requirements = water_requirements
        self.name = name
    
    def water(self, quantity):
        if not quantity < self.water_requirements:
            Flower.is_happy = True
    
    def status(self):
        if Flower.is_happy:
            return f"{self.name} is happy"
        return f"{self.name} is not happy"


flower = Flower("Lilly", 100)
flower.water(50)
print(flower.status())
flower.water(100)
print(flower.status())