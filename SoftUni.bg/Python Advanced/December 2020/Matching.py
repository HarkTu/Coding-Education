males = [int(x) for x in input().split()]
females = [int(x) for x in input().split()]
matches = 0
while males and females:
    current_m = males[-1]
    current_f = females[0]

    if current_m <= 0:
        del males[-1]
        continue
    elif current_m % 25 == 0:
        males = males[:-2]
        continue
    if current_f <= 0:
        del females[0]
        continue
    elif current_f % 25 == 0:
        females = females[2:]
        continue
    if males and females:
        if current_m == current_f:
            matches += 1
            del males[-1]
        else:
            males[-1] -= 2
        del females[0]
print(f"Matches: {matches}")
if males:
    print(f"Males left: {', '.join(str(x) for x in reversed(males))}")
else:
    print(f"Males left: none")
if females:
    print(f"Females left: {', '.join(str(x) for x in females)}")
else:
    print(f"Females left: none")
