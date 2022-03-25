customers = [int(x) for x in input().split(', ')]
taxis = [int(x) for x in input().split(', ')]
time = sum(customers)

while customers and taxis:
    customer = customers[0]
    taxi = taxis.pop()
    if taxi >= customer:
        customers.remove(customers[0])
if customers:
    print(
        f"Not all customers were driven to their destinations\nCustomers left: {', '.join([str(x) for x in customers])}")
else:
    print(f"All customers were driven to their destinations\nTotal time: {time} minutes")
