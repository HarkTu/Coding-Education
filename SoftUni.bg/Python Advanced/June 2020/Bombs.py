from collections import deque

bombs_effect = deque(input().split(", "))
bombs_casing = list(input().split(", "))
datura_bombs = 0
cherry_bombs = 0
smoke_decoy_bombs = 0
first_bomb_cas = 0
last_bombs_eff = 0

while bombs_casing and bombs_effect:
    first_bomb_cas = int(bombs_casing.pop())
    last_bombs_eff = int(bombs_effect.popleft())
    sum_of_both = first_bomb_cas + last_bombs_eff
    if sum_of_both == 40:
        datura_bombs += 1
    elif sum_of_both == 60:
        cherry_bombs += 1
    elif sum_of_both == 120:
        smoke_decoy_bombs += 1
    else:
        bombs_casing.append(first_bomb_cas)
        bombs_effect.appendleft(str(last_bombs_eff - 5))
    if cherry_bombs >= 3 and smoke_decoy_bombs >= 3 and datura_bombs >= 3:
        print(f"Bene! You have successfully filled the bomb pouch!")
        break
else:
    print("You don't have enough materials to fill the bomb pouch.")

if bombs_effect:
    print(f"Bomb Effects: {', '.join(bombs_effect)}")
else:
    print("Bomb Effects: empty")
if bombs_casing:
    print(f"Bomb Casings: {', '.join(bombs_casing)}")
else:
    print("Bomb Casings: empty")

print(f"Cherry Bombs: {cherry_bombs}")
print(f"Datura Bombs: {datura_bombs}")
print(f"Smoke Decoy Bombs: {smoke_decoy_bombs}")
