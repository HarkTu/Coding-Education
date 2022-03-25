from Workers import Worker
import unittest


class WorkerTests(unittest.TestCase):
    def setUp(self):
        self.worker = Worker('pesho', 2000, 1)
    
    def test_init(self):
        expected = {'name': 'pesho', 'salary': 2000, 'energy': 1, 'money': 0}
        actual = self.worker.__dict__
        self.assertEqual(actual, expected)
    
    def test_energy(self):
        self.worker.rest()
        expected = 2
        actual = self.worker.energy
        self.assertEqual(actual, expected)
    
    def test_raise_error_if_worker_work_without_energy(self):
        self.worker.work()
        with self.assertRaises(Exception) as cm:
            self.worker.work()
        self.assertEqual(str(cm.exception), "Not enough energy.")
    
    def test_work__money_must_increas_by_salary(self):
        self.worker.work()
        expected = 2000
        actual = self.worker.money
        self.assertEqual(actual, expected)
    
    def test_work__energy_must_decrease(self):
        self.worker.work()
        expected = 0
        actual = self.worker.energy
        self.assertEqual(actual, expected)
    
    def test_get_info__should_return_correct_text(self):
        self.worker.work()
        expected = "pesho has saved 2000 money."
        actual = self.worker.get_info()
        self.assertEqual(actual, expected)


if __name__ == "__main__":
    unittest.main()
