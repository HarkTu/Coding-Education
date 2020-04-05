import unittest

from project.factory.paint_factory import PaintFactory


class TestPaintFactory(unittest.TestCase):
    
    def test_init_base(self):
        pfac = PaintFactory('pfac', 1)
        self.assertEqual(pfac.name, "pfac")
        self.assertEqual(pfac.capacity, 1)
        self.assertEqual(pfac.ingredients, dict())
    
    def test_paintFactory_inherits_factory(self):
        self.assertTrue("Factory" == PaintFactory.__bases__[0].__name__)
    
    def test_add_ingredient_raise_error_if_capacity_not_enough(self):
        pfac = PaintFactory('pfac', 1)
        with self.assertRaises(ValueError) as cm:
            pfac.add_ingredient('white', 2)
        self.assertEqual(str(cm.exception), "Not enough space in factory")
    
    def test_add_ingredient_raise_error_if_ingreient_not_valid(self):
        pfac = PaintFactory('pfac', 2)
        with self.assertRaises(TypeError) as cm:
            pfac.add_ingredient('cghfg', 1)
        self.assertEqual(str(cm.exception), "Ingredient of type cghfg not allowed in PaintFactory")
    
    def test_add_ingredient_increase_quantity_if_exist(self):
        pfac = PaintFactory('pfac', 5)
        pfac.add_ingredient('white', 2)
        pfac.add_ingredient('white', 2)
        self.assertEqual(pfac.products['white'], 4)
    
    def test_add_ingredient_create_and_increase_quantity_if_not_exist(self):
        pfac = PaintFactory('pfac', 2)
        pfac.add_ingredient('white', 2)
        self.assertEqual(pfac.products['white'], 2)
    
    def test_remove_raise_error_if_quantity_bigger(self):
        pfac = PaintFactory('pfac', 3)
        pfac.add_ingredient('white', 2)
        with self.assertRaises(ValueError) as cm:
            pfac.remove_ingredient('white', 3)
        self.assertEqual(str(cm.exception), "Ingredient quantity cannot be less than zero")
    
    def test_remove_raise_error_if_ingredient_not_in(self):
        pfac = PaintFactory('pfac', 3)
        pfac.add_ingredient('white', 2)
        with self.assertRaises(KeyError) as cm:
            pfac.remove_ingredient('blue', 1)
        self.assertEqual("'No such ingredient in the factory'", str(cm.exception))
    
    def test_remove_decrease_quantity(self):
        pfac = PaintFactory('pfac', 3)
        pfac.add_ingredient('white', 2)
        pfac.remove_ingredient('white', 1)
        self.assertEqual(1, pfac.products['white'])
		
	def test_1(self):
        pfac = PaintFactory('pfac', 3)
        self.assertEqual(pfac.can_add(1),True)

    def test_2(self):
        pfac = PaintFactory('pfac', 1)
        self.assertEqual(pfac.can_add(2), False)
    
    def test_products(self):
        pfac = PaintFactory('pfac', 3)
        pfac.add_ingredient('yellow', 1)
        pfac.add_ingredient('white', 1)
        pfac.add_ingredient('blue', 1)
        actual = pfac.products
        expected = {'blue': 1, 'white': 1, 'yellow': 1}
        self.assertEqual(expected, actual)


if __name__ == "__main__":
    unittest.main()
