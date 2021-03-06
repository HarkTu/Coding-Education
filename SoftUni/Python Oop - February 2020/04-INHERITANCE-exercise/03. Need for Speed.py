class Vehicle:
    DEFAULT_FUEL_CONSUMPTION = 1.25
    
    def __init__(self, fuel, horse_power):
        self.horse_power = horse_power
        self.fuel = fuel
        self.fuel_consumption = self.DEFAULT_FUEL_CONSUMPTION
    
    def drive(self, kilometers):
        if kilometers * self.fuel_consumption <= self.fuel:
            self.fuel -= kilometers * self.fuel_consumption
    
    def __repr__(self):
        return f"{self.fuel_consumption} of type {self.__class__.__name__} has fuel {self.fuel}"


class Motorcycle(Vehicle):
    pass


class RaceMotorcycle(Motorcycle):
    DEFAULT_FUEL_CONSUMPTION = 8


class CrossMotorcycle(Motorcycle):
    pass


class Car(Vehicle):
    DEFAULT_FUEL_CONSUMPTION = 3


class FamilyCar(Car):
    pass


class SportCar(Car):
    DEFAULT_FUEL_CONSUMPTION = 10
