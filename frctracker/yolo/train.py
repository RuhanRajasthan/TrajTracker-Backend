from yt2mp4 import yt2mp4

downloader = yt2mp4("2026utwv", "sm1", "videos")

path = downloader.download("https://www.youtube.com/watch?v=m4fB1KPT3Pk")

print(path)