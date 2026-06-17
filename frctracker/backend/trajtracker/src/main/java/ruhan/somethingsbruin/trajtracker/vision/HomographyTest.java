package ruhan.somethingsbruin.trajtracker.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class HomographyTest {

        private JFrame window;
        private JLabel screen;

        public static void main(String[] args) {
            // 1. Load the native OpenCV library
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            // 2. Start the player
            new HomographyTest().startPlaying("C:\\Users\\LENOVO\\Documents\\GitHub\\TrajTracker\\frctracker\\backend\\trajtracker\\car.mp4");
        }

        public void startPlaying(String videoPath) {
            // 3. Initialize VideoCapture with the file path
            VideoCapture capture = new VideoCapture(videoPath);

            if (!capture.isOpened()) {
                System.err.println("Error: Could not open the video file.");
                return;
            }

            // 4. Fetch video specifications for playback speed
            double fps = capture.get(Videoio.CAP_PROP_FPS);
            int delay = (fps > 0) ? (int) (1000 / fps) : 33; // Default to ~30 FPS delay

            // 5. Setup Swing Window Layout
            setupGUI();

            // 6. Run playback loop inside a separate background thread
            new Thread(() -> {
                Mat frame = new Mat();
                while (capture.read(frame)) { // Read next frame
                    if (frame.empty()) break;

                    // Convert OpenCV Mat to Java BufferedImage
                    BufferedImage img = matToBufferedImage(frame);

                    // Update the Swing UI safely on the Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> screen.setIcon(new ImageIcon(img)));

                    try {
                        Thread.sleep(delay); // Control video playback speed
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                // Release resources when video ends
                capture.release();
                System.out.println("Playback finished.");
            }).start();
        }

        private void setupGUI() {
            window = new JFrame("OpenCV Java Video Player");
            screen = new JLabel();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.getContentPane().add(screen, BorderLayout.CENTER);
            window.setSize(800, 600); // Base window size
            window.setVisible(true);
        }

        /**
         * Efficiently converts an OpenCV Mat (BGR color space) to a Java BufferedImage.
         */
        private BufferedImage matToBufferedImage(Mat matrix) {
            int width = matrix.width();
            int height = matrix.height();
            int channels = matrix.channels();
            byte[] sourcePixels = new byte[width * height * channels];
            matrix.get(0, 0, sourcePixels);

            BufferedImage image;
            if (channels > 1) {
                image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            } else {
                image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            }

            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
            return image;
        }
    }

