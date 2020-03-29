import time


def exec_time(func):
    def wrapper(*args):
        # start = time.perf_counter()
        # start = time.process_time()
        start = time.time()
        func(*args)
        end = time.time()
        result = end - start
        return result
    
    return wrapper


# Tests
import unittest


class ExecTimeTests(unittest.TestCase):
    def test_zero_first(self):
        @exec_time
        def loop(start, end):
            total = 0
            for x in range(start, end):
                total += x
            return total
        
        self.assertEqual(0 < round(loop(1, 10000000)) < 3, True)
    
    def test_zero_second(self):
        @exec_time
        def concatenate(strings):
            result = ""
            for string in strings:
                result += string
            return result
        
        self.assertEqual(round(concatenate(["a" for i in range(1000000)])), 0)


if __name__ == '__main__':
    unittest.main()
