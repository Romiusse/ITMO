package ru.romiusse.box_store.src.Deliver;

public class RoadType {

    private String type;
    private int time;

    public RoadType(int time){
        this.time = time;
    }

    public void setType(String type){
        this.type = type;
    }

    public static class Forest extends RoadType {
        public Forest(int time) {
            super(time);
            setType("Forest");
        }

    }

    public static class Swamp extends RoadType {
        public Swamp(int time) {
            super(time);
            setType("Swamp");
        }

    }

    public static class Bumps extends RoadType {
        public Bumps(int time) {
            super(time);
            setType("Bumps");
        }

    }
    
    public static class Ravines extends RoadType {
        public Ravines(int time) {
            super(time);
            setType("Ravines");
        }

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Type: " + type + " Will arrive in " + Integer.toString(time) + " hours";
    }

}
