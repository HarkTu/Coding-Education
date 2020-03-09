class Music:
    def __init__(self, title: str = None, artist: str = None, lyrics: str = None):
        self.title = title
        self.lyrics = lyrics
        self.artist = artist
    
    def play(self):
        return self.lyrics
    
    def print_info(self):
        return f'This is "{self.title}" from "{self.artist}"'


song = Music("Title", "Artist", "Lyrics")
print(song.print_info())
print(song.play())
