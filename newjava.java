import java.util.*;
class stop {
    int pincode;
    String name;
    String idealTime;
    String idealReturnTime;
    int onDay;
    int returnDay;
    int timeFromPrev;

    public stop(int pincode, String name, String idealTime, String idealReturnTime, int onDay, int returnDay, int timeFromPrev) {
        this.pincode = pincode;
        this.name = name;
        this.idealTime = idealTime;
        this.idealReturnTime = idealReturnTime;
        this.onDay = onDay;
        this.returnDay = returnDay;
        this.timeFromPrev = timeFromPrev;
    }

}
class Route {
    int uniqueId;
    int journeyLength;
    stop s[];
    public Route(int uniqueId, int journeyLength, stop[] stops) {
        this.uniqueId = uniqueId;
        this.journeyLength = journeyLength;
        this.s = stops;
    }
}
class links{
    int id;
    
}
class randomidk{
   public int[] array1 = {0,1,2,3,4,5};
        int[] array2 = {6,7,8,4};
        int[] array3 = {9,10,11,12,13};
        int[] array4 = {2,8,11};
}
public class newjava {
    public static void main(String[] args) {
        stop[] stopsArray1 = {new stop(0, "stop0", "9:00", "17:00", 1, 2, 0),
                              new stop(1, "stop1", "10:30", "16:30", 1, 2, 1),
                              new stop(2, "stop2", "12:00", "15:45", 1, 2, 1),
                              new stop(3, "stop3", "13:30", "14:30", 1, 2, 1),
                              new stop(4, "stop4", "15:00", "13:45", 1, 2, 1),
                              new stop(5, "stop5", "16:30", "12:00", 1, 2, 1)};
        Route route1 = new Route(1, 6, stopsArray1);

        stop[] stopsArray2 = {new stop(6, "stop6", "8:30", "18:00", 1, 2, 0),
                              new stop(7, "stop7", "10:00", "16:00", 1, 2, 2),
                              new stop(8, "stop8", "12:30", "15:15", 1, 2, 1),
                              new stop(4, "stop4", "14:00", "14:45", 1, 2, 1)};
        Route route2 = new Route(2, 4, stopsArray2);

        stop[] stopsArray3 = {new stop(9, "stop9", "7:45", "18:30", 1, 2, 0),
                              new stop(10, "stop10", "9:15", "15:30", 1, 2, 1),
                              new stop(11, "stop11", "11:30", "15:00", 1, 2, 1),
                              new stop(12, "stop12", "13:45", "13:45", 1, 2, 1),
                              new stop(13, "stop13", "15:15", "12:30", 1, 2, 1)};
        Route route3 = new Route(3, 5, stopsArray3);

        stop[] stopsArray4 = {new stop(2, "stop2", "14:30", "12:00", 1, 2, 0),
                              new stop(8, "stop8", "16:00", "10:30", 1, 2, 2),
                              new stop(11, "stop11", "18:15", "9:45", 1, 2, 1)};
        Route route4 = new Route(4, 3, stopsArray4);
    }
}

