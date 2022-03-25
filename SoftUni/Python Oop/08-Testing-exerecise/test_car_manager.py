from car_manager import Car
import unittest


class TestCar(unittest.TestCase):
    def setUp(self) -> None:
        self.car = Car('Audi', 'Q8', 6.7, 85)
    
    def test_init_(self):
        expected = {'_Car__make': 'Audi', '_Car__model': 'Q8', '_Car__fuel_consumption': 6.7, '_Car__fuel_capacity': 85,
                    '_Car__fuel_amount': 0}
        actual = self.car.__dict__
        self.assertEqual(actual, expected)
    
    def test_make__set_to_given_value(self):
        self.car.make = 'BMW'
        expected = 'BMW'
        actual = self.car.make
        self.assertEqual(actual, expected)
    
    def test_make__raise_Exception_if_given_value_empty(self):
        with self.assertRaises(Exception) as cm:
            self.car.make = ''
        self.assertEqual(str(cm.exception), "Make cannot be null or empty!")
    
    def test_model__set_to_given_value(self):
        self.car.model = 'BMW'
        expected = 'BMW'
        actual = self.car.model
        self.assertEqual(actual, expected)
    
    def test_model__raise_Exception_if_given_value_empty(self):
        with self.assertRaises(Exception) as cm:
            self.car.model = ''
        self.assertEqual(str(cm.exception), "Model cannot be null or empty!")
    
    def test_fuel_consumption__set_to_given_value(self):
        self.car.fuel_consumption = 6.4
        expected = 6.4
        actual = self.car.fuel_consumption
        self.assertEqual(actual, expected)
    
    def test_fuel_consumption__raise_Exception_if_given_value_less_or_equal_zero(self):
        with self.assertRaises(Exception) as cm:
            self.car.fuel_consumption = 0
        self.assertEqual(str(cm.exception), "Fuel consumption cannot be zero or negative!")
    
    def test_fuel_capacity__set_to_given_value(self):
        self.car.fuel_capacity = 60.4
        expected = 60.4
        actual = self.car.fuel_capacity
        self.assertEqual(actual, expected)
    
    def test_fuel_capacity__raise_Exception_if_given_value_less_or_equal_zero(self):
        with self.assertRaises(Exception) as cm:
            self.car.fuel_capacity = 0
        self.assertEqual(str(cm.exception), "Fuel capacity cannot be zero or negative!")
    
    def test_fuel_amount__set_to_given_value(self):
        self.car.fuel_amount = 60.4
        expected = 60.4
        actual = self.car.fuel_amount
        self.assertEqual(actual, expected)
    
    def test_fuel_amount__raise_Exception_if_given_value_less_than_zero(self):
        with self.assertRaises(Exception) as cm:
            self.car.fuel_amount = -1
        self.assertEqual(str(cm.exception), "Fuel amount cannot be negative!")
    
    def test_refuel__raise_Exception_if_given_value_less_or_equal_zero(self):
        with self.assertRaises(Exception) as cm:
            self.car.refuel(0)
        self.assertEqual(str(cm.exception), "Fuel amount cannot be zero or negative!")
    
    def test_refuel__increase_fuel_amount_by_given_value(self):
        self.car.fuel_amount = 60.4
        self.car.refuel(20)
        expected = 80.4
        actual = self.car.fuel_amount
        self.assertEqual(actual, expected)
    
    def test_refuel__set_to_fuel_capacity_if_new_value_exceeds_it(self):
        self.car.fuel_amount = 60.4
        self.car.refuel(30)
        expected = 85
        actual = self.car.fuel_amount
        self.assertEqual(actual, expected)
    
    def test_drive__raise_Exception_if_needed_fuel_exceeds_fuel_amount(self):
        with self.assertRaises(Exception) as cm:
            self.car.drive(10000)
        self.assertEqual(str(cm.exception), "You don't have enough fuel to drive!")
    
    def test_drive__decrease_fuel_amount_by_given_value(self):
        self.car.fuel_amount = 85
        self.car.drive(100)
        expected = 78.3
        actual = self.car.fuel_amount
        self.assertEqual(actual, expected)


if __name__ == "__main__":
    unittest.main()
