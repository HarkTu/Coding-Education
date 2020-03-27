def solution():
    def integers():
        number = 1
        while 1:
            yield number
            number += 1
    
    # TODO: Implement
    
    def halves():
        for i in integers():
            yield i / 2
    
    # TODO: Implement
    
    def take(n, seq):
        result = []
        for i in range(n):
            result.append(next(seq))
        return result
    
    # TODO: Implement
    
    return (take, halves, integers)


taken = solution()[0]
halvess = solution()[1]
print(taken(5, halvess()))
# [0.5, 1.0, 1.5, 2.0, 2.5]
