class Time:
    max_hours = 24
    max_minutes = 60
    max_seconds = 60
    
    def __init__(self, hours: int or float, minutes: int or float, seconds: int or float):
        self.seconds = seconds
        self.minutes = minutes
        self.hours = hours
    
    def set_time(self, hours, minutes, seconds):
        self.seconds = seconds
        self.minutes = minutes
        self.hours = hours
    
    def get_time(self):
        if self.seconds >= Time.max_seconds:
            self.minutes += self.seconds // Time.max_seconds
            self.seconds -= ((self.seconds // Time.max_seconds) * Time.max_seconds)
        if self.minutes >= Time.max_minutes:
            self.hours += self.minutes // Time.max_minutes
            self.minutes -= ((self.minutes // Time.max_minutes) * Time.max_minutes)
        if self.hours >= Time.max_hours:
            self.hours = 0
            """
                # author wants it that way
            my solution was:
            self.hours -= ((self.hours // Time.max_hours) * Time.max_hours)
            """
        return f"{self.hours:02d}:{self.minutes:02d}:{self.seconds:02d}"
    
    def next_second(self):
        self.seconds += 1
        return self.get_time()


time = Time(9, 30, 60)
print(time.next_second())

time = Time(10, 59, 59)
print(time.next_second())

time = Time(24, 59, 59)
print(time.next_second())

time = Time(49, 59, 60)
print(time.get_time())

time.set_time(49, 69, 61)
print(time.get_time())
