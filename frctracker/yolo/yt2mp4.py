from pathlib import Path
import yt_dlp


class yt2mp4:

    def __init__(
            self,
            event_name,
            match,
            output_directory="videos"):

        self.event_name = event_name
        self.match = match

        self.output_directory = Path(output_directory)
        self.output_directory.mkdir(
            parents=True,
            exist_ok=True
        )

    def download(self, url):

        ydl_opts = {
            "format": "best[ext=mp4]",
            "outtmpl": str(
                self.output_directory /
                "%(title)s.%(ext)s"
            ),
            "quiet": False
        }

        with yt_dlp.YoutubeDL(ydl_opts) as ydl:

            info = ydl.extract_info(
                url,
                download=True
            )

            filename = ydl.prepare_filename(
                info
            )

            if filename.endswith(".webm"):
                filename = filename[:-5] + ".mp4"

            elif filename.endswith(".mkv"):
                filename = filename[:-4] + ".mp4"

            return filename