processes = [int(x) for x in input().split(', ')]
target_index = int(input())

target_cycle = processes[target_index]
total_cycle = target_cycle

for index in range(len(processes)):
    if processes[index] < target_cycle or (processes[index] == target_cycle and index < target_index):
        total_cycle += processes[index]
print(total_cycle)
