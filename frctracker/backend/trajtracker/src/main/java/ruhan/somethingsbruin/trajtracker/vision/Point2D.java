package ruhan.somethingsbruin.trajtracker.vision;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Point2D {


    static {
        try {
            nu.pattern.OpenCV.loadShared(); // Or System.loadLibrary(...)
            System.out.println("OpenCV loaded successfully via ImageProcessor!");
        } catch (Exception e) {
            System.err.println("Failed to load OpenCV native libraries: " + e.getMessage());
        }
    }

    private double x;
    private double y;

    
    public enum CoordinateSystem {
        FIELD_METERS,
        IMAGE_PIXELS
    }
    

    private CoordinateSystem fieldStatus;

    public Point2D (double x, double y, CoordinateSystem fieldStatus){
        this.x = x;
        this.y = y;

        this.fieldStatus = fieldStatus;

    }

    public double x(){
        return x;

    }

    public double y(){
        return y;
    }

    public CoordinateSystem fieldStatus(){
        return fieldStatus;
       
    }

    @Override
    public String toString(){
        return "Point2D{ x: " + x()+" y: "+y()+"}";
    }
}
