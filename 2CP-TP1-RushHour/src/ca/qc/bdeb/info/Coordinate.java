package ca.qc.bdeb.info;

public class Coordinate {

    public static class MoveResult{
        public enum moveresult {
            Success,
            Failure
        }
    }

    public static class Direction{
        public enum direction{
            North,
            South,
            East,
            West
        }
    }


    public static class Orientation{
        public enum orientation{
            Horizontal,
            Vertical
        }
    }
}
