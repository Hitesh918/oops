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
    int onEvery;
    int returnAfter;
    stop s[];
    public Route(int uniqueId, int onEvery, int returnAfter ,stop[] stops) {
        this.uniqueId = uniqueId;
        this.onEvery = onEvery;
        this.returnAfter=returnAfter;
        this.s = stops;
    }
}



    //   Node node1 = new Node(1, new int[]{2, 4});
    //     Node node2 = new Node(2, new int[]{1, 4});
    //     Node node3 = new Node(3, new int[]{4});
    //     Node node4 = new Node(4, new int[]{1, 2, 3});
        
        
    //     Map<Integer, Node> graph = new HashMap<>();
    //     graph.put(node1.id, node1);
    //     graph.put(node2.id, node2);
    //     graph.put(node3.id, node3);
    //     graph.put(node4.id, node4);
// class randomidk{
//   public int[] array1 = {0,1,2,3,4,5};
//         int[] array2 = {6,7,8,4};
//         int[] array3 = {9,10,11,12,13};
//         int[] array4 = {2,8,11};
// }
public class newjava {
    public static void main(String[] args) {
        stop[] stopsArray1 = {new stop(0, "stop0", "15:00", "11:00", 4, 5, -1),
                              new stop(1, "stop1", "16:00", "10:00", 4, 5, 60),
                              new stop(2, "stop2", "17:00", "09:00", 4, 5, 60),
                              new stop(3, "stop3", "19:00", "07:00", 4, 5, 120),
                              new stop(4, "stop4", "20:00", "06:00", 4, 5, 60),
                              new stop(5, "stop5", "21:00", "05:00", 4, 5, 60)};
        Route route1 = new Route(1, 4, 8 , stopsArray1);

        stop[] stopsArray2 = {new stop(6, "stop6", "20:20", "13:40", 5, 1, -1),
                              new stop(7, "stop7", "10:30", "23:30", 6, 0, 850),
                              new stop(8, "stop8", "12:00", "22:00", 6, 0, 90),
                              new stop(4, "stop4", "17:00", "17:00", 6, 0, 300)};
        Route route2 = new Route(2, 5, 24 ,  stopsArray2);

        stop[] stopsArray3 = {new stop(9, "stop9", "12:00", "06:00", 4, 5, -1),
                              new stop(10, "stop10", "14:00", "04:00", 4, 5, 120),
                              new stop(11, "stop11", "15:00", "03:00", 4, 5, 60),
                              new stop(12, "stop12", "18:00", "00:00", 4, 5, 180),
                              new stop(13, "stop13", "20:00", "22:00", 4, 4, 120)};
        Route route3 = new Route(3, 4 , 2 , stopsArray3);

        stop[] stopsArray4 = {new stop(11, "stop11", "17:30", "06:30", 6, 0, -1),
                              new stop(8, "stop8", "18:00", "06:00", 6, 0, 30),
                              new stop(2, "stop2", "19:00", "05:00", 6, 0, 60),
                              new stop(14, "stop14", "21:00", "03:00", 6, 0, 120)};
        Route route4 = new Route(4, 6 , 6 ,  stopsArray4);
    }
}
