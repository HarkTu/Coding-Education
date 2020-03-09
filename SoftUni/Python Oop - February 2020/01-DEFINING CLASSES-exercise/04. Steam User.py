class SteamUser:
    played_hours = 0
    
    def __init__(self, username: str = None, games: list = None):
        self.games = games
        self.username = username
    
    def play(self, game, hours):
        if game in self.games:
            SteamUser.played_hours += hours
            return f"{self.username} is playing {game}"
        return f"{game} is not in library"
    
    def buy_game(self, game):
        if game not in self.games:
            self.games.append(game)
            return f"{self.username} bought {game}"
        return f"{game} is already in your library"
    
    def stats(self):
        return f"{self.username} has {len(self.games)} games. Total play time: {SteamUser.played_hours}"


user = SteamUser("Peter", ["Rainbow Six Siege", "CS:GO", "Fortnite"])
print(user.play("Fortnite", 3))
print(user.play("Oxygen Not Included", 5))
print(user.buy_game("CS:GO"))
print(user.buy_game("Oxygen Not Included"))
print(user.play("Oxygen Not Included", 6))
print(user.stats())
