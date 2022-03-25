from Cat import Cat
import unittest


class CatTests(unittest.TestCase):
    def setUp(self):
        self.cat = Cat('pesho')
    
    def test_cat_size__must_increase_after_eat(self):
        self.cat.eat()
        expected = 1
        actual = self.cat.size
        self.assertEqual(actual, expected)
    
    def test_fed_must_be_true_after_eat(self):
        self.cat.eat()
        expected = True
        actual = self.cat.fed
        self.assertEqual(actual, expected)
    
    def test_eat__must_raise_error_if_already_fed(self):
        self.cat.eat()
        with self.assertRaises(Exception) as cm:
            self.cat.eat()
        self.assertEqual(str(cm.exception), "Already fed.")
    
    def test_sleep__must_raise_error_if_not_fed(self):
        with self.assertRaises(Exception) as cm:
            self.cat.sleep()
        self.assertEqual(str(cm.exception), "Cannot sleep while hungry")
    
    def test_sleepy__must_be_false_if_slept(self):
        self.cat.eat()
        self.cat.sleep()
        expected = False
        actual = self.cat.sleepy
        self.assertEqual(actual, expected)


if __name__ == "__main__":
    unittest.main()
