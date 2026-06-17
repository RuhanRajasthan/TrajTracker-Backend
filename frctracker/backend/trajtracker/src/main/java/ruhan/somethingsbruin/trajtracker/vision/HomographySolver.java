package ruhan.somethingsbruin.trajtracker.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Core;

public class HomographySolver {

    private final Mat homography;

    public HomographySolver(
            List<Point2D> imagePoints,
            List<Point2D> fieldPoints) {

        if (imagePoints.size() != fieldPoints.size()) {
            throw new IllegalArgumentException(
                    "Image and field point counts must match.");
        }

        if (imagePoints.size() < 4) {
            throw new IllegalArgumentException(
                    "Need at least 4 point correspondences.");
        }

        MatOfPoint2f imageMat = toMatOfPoint2f(imagePoints);
        MatOfPoint2f fieldMat = toMatOfPoint2f(fieldPoints);

        homography = Calib3d.findHomography(
                imageMat,
                fieldMat,
                Calib3d.RANSAC);
    }

    /**
     * Convert a pixel coordinate into field coordinates.
     */
    public Point2D pixelToField(Point2D pixelPoint) {

        Mat src = new Mat(3, 1, CvType.CV_64F);

        src.put(0, 0, pixelPoint.x());
        src.put(1, 0, pixelPoint.y());
        src.put(2, 0, 1.0);

        Mat dst = new Mat();

        Core.gemm(
                homography,
                src,
                1.0,
                new Mat(),
                0.0,
                dst);

        double w = dst.get(2, 0)[0];

        double fieldX = dst.get(0, 0)[0] / w;
        double fieldY = dst.get(1, 0)[0] / w;

        return new Point2D(
                fieldX,
                fieldY,
                Point2D.CoordinateSystem.FIELD_METERS);
    }

    public Mat getHomography() {
        return homography;
    }

    private static MatOfPoint2f toMatOfPoint2f(
            List<Point2D> points) {

        List<Point> cvPoints = new ArrayList<>();

        for (Point2D point : points) {
            cvPoints.add(
                    new Point(
                            point.x(),
                            point.y()));
        }

        MatOfPoint2f result = new MatOfPoint2f();
        result.fromList(cvPoints);

        return result;
    }
}