def numbers_searching(*args):
    from collections import Counter

    def find_missing(lst):
        for x in range(lst[0], lst[-1] + 1):
            if x not in lst:
                return x

    def find_duplicates(lst):
        return [k for k, v in Counter(lst).items() if v > 1]

    numbers = sorted(list(args))
    repeating_numbers = find_duplicates(numbers)
    missing_number = find_missing(numbers)
    return [missing_number, repeating_numbers]