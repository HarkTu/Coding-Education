class Lion:
    def __init__(self, name, gender, age):
        self.age = age
        self.gender = gender
        self.name = name
    
    def get_needs(self):
        return 50
    
    def __repr__(self):
        return f"Name: {self.name}, Age: {self.age}, Gender: {self.gender}"


class Tiger:
    def __init__(self, name, gender, age):
        self.age = age
        self.gender = gender
        self.name = name
    
    def get_needs(self):
        return 45
    
    def __repr__(self):
        return f"Name: {self.name}, Age: {self.age}, Gender: {self.gender}"


class Cheetah:
    def __init__(self, name, gender, age):
        self.age = age
        self.gender = gender
        self.name = name
    
    def get_needs(self):
        return 60
    
    def __repr__(self):
        return f"Name: {self.name}, Age: {self.age}, Gender: {self.gender}"


class Keeper:
    def __init__(self, name, age, salary):
        self.age = age
        self.salary = salary
        self.name = name
    
    def __repr__(self):
        return f"Name: {self.name}, Age: {self.age}, Salary: {self.salary}"


class Caretaker:
    def __init__(self, name, age, salary):
        self.age = age
        self.salary = salary
        self.name = name
    
    def __repr__(self):
        return f"Name: {self.name}, Age: {self.age}, Salary: {self.salary}"


class Vet:
    def __init__(self, name, age, salary):
        self.age = age
        self.salary = salary
        self.name = name
    
    def __repr__(self):
        return f"Name: {self.name}, Age: {self.age}, Salary: {self.salary}"


class Zoo:
    def __init__(self, name, budget, animal_capacity,
                 workers_capacity):  # in document says 'animlal_capacity'. 1 test fails without correction
        self.animals = []
        self.workers = []
        self.name = name
        self.__workers_capacity = workers_capacity
        self.__animal_capacity = animal_capacity
        self.__budget = budget
    
    def add_animal(self, animal, price):
        if len(self.animals) == self.__animal_capacity:
            return "Not enough space for animal"
        if price > self.__budget:
            return "Not enough budget"
        else:
            self.animals.append(animal)
            self.__budget -= price
            return f"{animal.name} the {type(animal).__name__} added to the zoo"
    
    def hire_worker(self, worker):
        if len(self.workers) < self.__workers_capacity:
            self.workers.append(worker)
            return f"{worker.name} the {type(worker).__name__} hired successfully"
        return "Not enough space for worker"
    
    def fire_worker(self, worker_name):
        for worker_x in self.workers:
            if worker_x.name == worker_name:
                self.workers.remove(worker_x)
                return f"{worker_name} fired successfully"
        return f"There is no {worker_name} in the zoo"  # on document there was mistaken double space on this line. 1 test fails without correction
    
    def pay_workers(self):
        sum_salary = sum([x.salary for x in self.workers])
        if sum_salary > self.__budget:
            return "You have no budget to pay your workers. They are unhappy"
        self.__budget -= sum_salary
        return f"You payed your workers. They are happy. Budget left: {self.__budget}"
    
    def tend_animals(self):
        sum_tend = sum([x.get_needs() for x in self.animals])
        if sum_tend > self.__budget:
            return "You have no budget to tend the animals. They are unhappy."
        self.__budget -= sum_tend
        return f"You tended all the animals. They are happy. Budget left: {self.__budget}"
    
    def profit(self, amount):
        self.__budget += amount
    
    def animals_status(self):
        result = ''
        result += f"You have {len(self.animals)} animals\n"
        result += f"----- {sum([1 for x in self.animals if type(x) == Lion])} Lions:\n"
        result += '\n'.join([x.__repr__() for x in self.animals if isinstance(x, Lion)])
        result += f"\n----- {sum([1 for x in self.animals if isinstance(x, Tiger)])} Tigers:\n"
        result += '\n'.join([x.__repr__() for x in self.animals if isinstance(x, Tiger)])
        result += f"\n----- {sum(isinstance(x, Cheetah) for x in self.animals)} Cheetahs:\n"
        result += '\n'.join([x.__repr__() for x in self.animals if isinstance(x, Cheetah)])
        return result + '\n'
    
    def workers_status(self):
        result = ''
        result += f"You have {len(self.workers)} workers\n"
        result += f"----- {sum([1 for x in self.workers if type(x) == Keeper])} Keepers:\n"
        result += '\n'.join([x.__repr__() for x in self.workers if isinstance(x, Keeper)])
        result += f"\n----- {sum([1 for x in self.workers if isinstance(x, Caretaker)])} Caretakers:\n"
        result += '\n'.join([x.__repr__() for x in self.workers if isinstance(x, Caretaker)])
        result += f"\n----- {sum(isinstance(x, Vet) for x in self.workers)} Vets:\n"
        result += '\n'.join([x.__repr__() for x in self.workers if isinstance(x, Vet)])
        return result + '\n'  # there is no new line in example solution. 1 test fails without correction


zoo = Zoo("Zootopia", 3000, 5, 8)

# Animals creation
animals = [Cheetah("Cheeto", "Male", 2), Cheetah("Cheetia", "Female", 1), Lion("Simba", "Male", 4),
           Tiger("Zuba", "Male", 3), Tiger("Tigeria", "Female", 1), Lion("Nala", "Female", 4)]

# Animal prices
prices = [200, 190, 204, 156, 211, 140]

# Workers creation
workers = [Keeper("John", 26, 100), Keeper("Adam", 29, 80), Keeper("Anna", 31, 95), Caretaker("Bill", 21, 68),
           Caretaker("Marie", 32, 105), Caretaker("Stacy", 35, 140), Vet("Peter", 40, 300), Vet("Kasey", 37, 280),
           Vet("Sam", 29, 220)]

# Adding all animals
for i in range(len(animals)):
    animal = animals[i]
    price = prices[i]
    print(zoo.add_animal(animal, price))

# Adding all workers
for worker in workers:
    print(zoo.hire_worker(worker))

# Tending animals
print(zoo.tend_animals())

# Paying keepers
print(zoo.pay_workers())

# Fireing worker
print(zoo.fire_worker("Adam"))

# Printing statuses
print(zoo.animals_status())
print(zoo.workers_status())
