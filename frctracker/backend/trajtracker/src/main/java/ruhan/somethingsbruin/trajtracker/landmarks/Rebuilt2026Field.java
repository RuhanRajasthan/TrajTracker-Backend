package ruhan.somethingsbruin.trajtracker.landmarks;


import java.util.Map;

import ruhan.somethingsbruin.trajtracker.vision.Point2D;
import ruhan.somethingsbruin.trajtracker.vision.Point2D.CoordinateSystem;

public class Rebuilt2026Field {
    // assumes that blue alliance is on the left
    public enum Landmark {
        HUB,
        NEAR_TRENCH_BUMP_DIVIDER,
        FAR_TRENCH_BUMP_DIVIDER,
        TOWER_BASE,
        DEPOT,
        NEUTRAL_ZONE_MIDDLE_LINE,
        ALLIANCE_ZONE;

        

    }
    public enum Identifier {
            NEAR_LEFT_CORNER,
            NEAR_RIGHT_CORNER,
            FAR_LEFT_CORNER,
            FAR_RIGHT_CORNER
    }

    public enum FieldType {
        WELDED,
        ANDYMARK
    }

    public enum Alliance {
        RED,
        BLUE,
        NEUTRAL
    }

    public record LandmarkKey (FieldType fieldType, Landmark landmark, Identifier identifier, Alliance alliance){

    }

    public static final Map<LandmarkKey, Point2D> RebuiltPose = 
        Map.ofEntries(
            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.HUB, Identifier.NEAR_LEFT_CORNER, Alliance.BLUE), 
            new Point2D(4.077938, 3.508820, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.HUB, Identifier.NEAR_RIGHT_CORNER, Alliance.BLUE), 
            new Point2D(5.271738,3.508820, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.HUB, Identifier.FAR_LEFT_CORNER, Alliance.BLUE),
            new Point2D(4.077938, 4.696270, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.HUB, Identifier.FAR_RIGHT_CORNER, Alliance.BLUE), 
            new Point2D(5.271738, 4.696270, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.NEAR_TRENCH_BUMP_DIVIDER, Identifier.NEAR_LEFT_CORNER, Alliance.BLUE), 
            new Point2D(4.077938, 1.346645, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.NEAR_TRENCH_BUMP_DIVIDER, Identifier.NEAR_RIGHT_CORNER, Alliance.BLUE), 
            new Point2D((4.077938 + 1.193800), 1.346645, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.NEAR_TRENCH_BUMP_DIVIDER, Identifier.FAR_LEFT_CORNER, Alliance.BLUE), 
            new Point2D(4.077938, (1.346645 + 0.304800), CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.NEAR_TRENCH_BUMP_DIVIDER, Identifier.FAR_RIGHT_CORNER, Alliance.BLUE), 
            new Point2D((4.077938 + 1.193800), (1.346645 + 0.304800), CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.TOWER_BASE, Identifier.NEAR_LEFT_CORNER, Alliance.BLUE),
            new Point2D(0.048245,3.320230, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.TOWER_BASE, Identifier.NEAR_RIGHT_CORNER, Alliance.BLUE),
            new Point2D(1.193800, 3.320230, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.TOWER_BASE, Identifier.FAR_LEFT_CORNER, Alliance.BLUE),
            new Point2D(0.048245, 4.307009, CoordinateSystem.FIELD_METERS)),

            Map.entry(
            new LandmarkKey(FieldType.WELDED, Landmark.TOWER_BASE, Identifier.FAR_RIGHT_CORNER, Alliance.BLUE),
            new Point2D(1.193800, 4.307009, CoordinateSystem.FIELD_METERS)));
}
