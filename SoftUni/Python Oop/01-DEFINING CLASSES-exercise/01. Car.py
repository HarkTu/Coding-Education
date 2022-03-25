class Car:
    def __init__(self, name: str = None, model: str = None, engine: str = None):
        self.engine = engine
        self.model = model
        self.name = name
    
    def get_info(self):
        return f"This is {self.name} {self.model} with engine {self.engine}"


car = Car("Kia", "Rio", "1.3L B3 I4")
print(car.get_info())
