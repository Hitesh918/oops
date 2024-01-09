//packages

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

/*
mon-1
tue-2
wed-3
thur-4
fri-5
sat-6
sun-7
*/

//class definitions

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
    stop stops[];
    Map<Integer, Integer> links;

    public Route(int uniqueId, int onEvery, int returnAfter ,stop[] stops ,Map<Integer, Integer> links) {
        this.uniqueId = uniqueId;
        this.onEvery = onEvery;
        this.returnAfter=returnAfter;
        this.stops = stops;
        this.links = links;

    } 
}

class Node {
    static Node[] allNodes = new Node[5];

    int id;
    int[] links;

    public Node(int id, int[] links) {
        this.id = id;
        this.links = links;
        allNodes[id] = this; 
    }
}


public class Main {
    
    //function to return all routes in which given stop is present
    
    public static List<Integer> findRoutesByPincode(int targetPincode, Route[] routes) {
        List<Integer> matchingRoutes = new ArrayList<>();

        for (Route route : routes) {
            for (stop stop : route.stops) {
                if (stop.pincode == targetPincode) {
                    matchingRoutes.add(route.uniqueId);
                    break;  
                }
            }
        }

        return matchingRoutes;
    }
    
    //funtion to find all possible ways between 2 routes
    
     private static List<List<Integer>> findAllRoutes(Node startNode, int startId, int endId) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(startId));

        while (!queue.isEmpty()) {
            List<Integer> currentRoute = queue.poll();
            int currentId = currentRoute.get(currentRoute.size() - 1);

            if (currentId == endId) {
                result.add(new ArrayList<>(currentRoute));
            } else {
                Node currentNode = Node.allNodes[currentId];
                for (int neighborId : currentNode.links) {
                    if (!currentRoute.contains(neighborId)) {
                        List<Integer> newRoute = new ArrayList<>(currentRoute);
                        newRoute.add(neighborId);
                        queue.add(newRoute);
                    }
                }
            }
        }

        return result;
    }
    
    // function to return number of minutes to wait till the desired time occurs in future
    
    public static int calculateMinutesUntilDesiredTime(String startTime, int startDay, String desiredTime, int desiredDay)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

        Date startingTime = sdf.parse(startTime);
        Date desiredTimeDate = sdf.parse(desiredTime);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startingTime);
        calendar.set(Calendar.DAY_OF_WEEK, startDay);

        long currentTimeMillis = calendar.getTimeInMillis();
        long desiredTimeMillis = calculateDesiredTimeMillis(calendar, desiredTimeDate, desiredDay);

        if (currentTimeMillis > desiredTimeMillis) {
            calendar.add(Calendar.DAY_OF_WEEK, 7);
            desiredTimeMillis = calculateDesiredTimeMillis(calendar, desiredTimeDate, desiredDay);
        }

        long timeDifferenceMillis = desiredTimeMillis - currentTimeMillis;
        return (int) (timeDifferenceMillis / (60 * 1000)); 
    }

    private static long calculateDesiredTimeMillis(Calendar calendar, Date desiredTime, int desiredDay) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != desiredDay) {
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        calendar.set(Calendar.HOUR_OF_DAY, desiredTime.getHours());
        calendar.set(Calendar.MINUTE, desiredTime.getMinutes());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }
    
    private static int findObjectIndexByPincode(stop[] objects, int targetPincode) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].pincode == targetPincode) {
                return i; 
            }
        }
        return -1; 
    }
    private static String formatAsHHmm(ZonedDateTime dateTime) {
        // Format as "HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
    }

    public static int getDayOfWeekNumber(ZonedDateTime dateTime) {
        // Get the day of the week as a number (Monday = 1, Tuesday = 2, ..., Sunday = 7)
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return dayOfWeek.getValue();
    }
    public static ZonedDateTime incrementMinutes(ZonedDateTime dateTime, int minutes) {
        // Increment the time by the given number of minutes
        return dateTime.plusMinutes(minutes);
    }
    
    private static ZonedDateTime findTimeTaken(int srcCode , int destCode , int [] arr , Route[] routes ){
        ZoneId timeZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime currentDateTime = ZonedDateTime.now(timeZone);
        ZonedDateTime ans = currentDateTime.withSecond(0);
        
        for (int i = 0; i < arr.length - 1; i++){
            Route presentRoute=routes[arr[i]-1];
            // Node obj = links[arr[i]-1];
            Map<Integer, Integer> obj = presentRoute.links;
            
            int tempSrc = srcCode;
            int tempGoal = obj.get(arr[i+1]);
            int courierIndex = findObjectIndexByPincode(presentRoute.stops , tempSrc);
            int junctionIndex = findObjectIndexByPincode(presentRoute.stops , tempGoal);
            
            if(courierIndex < junctionIndex){
                try{
                    int x= calculateMinutesUntilDesiredTime(formatAsHHmm(ans) ,getDayOfWeekNumber(ans) , presentRoute.stops[courierIndex].idealTime, presentRoute.stops[courierIndex].onDay );
                    ans = incrementMinutes(ans , x);
                }
                catch (ParseException e) {
                    // Handle the exception, e.g., print an error message
                    System.err.println("Error parsing time: " + e.getMessage());
                }
                for (int j = courierIndex + 1; j <= junctionIndex; j++){
                    ans = incrementMinutes(ans ,presentRoute.stops[j].timeFromPrev);
                }
            }
            else if (courierIndex > junctionIndex){
                
                try{
                    int x=calculateMinutesUntilDesiredTime(formatAsHHmm(ans) ,getDayOfWeekNumber(ans) , presentRoute.stops[courierIndex].idealReturnTime, presentRoute.stops[courierIndex].returnDay);
                    ans = incrementMinutes(ans , x);
                }
                catch (ParseException e) {
                    // Handle the exception, e.g., print an error message
                    System.err.println("Error parsing time: " + e.getMessage());
                }
                // ans = incrementMinutes(ans , calculateMinutesUntilDesiredTime(formatAsHHmm(ans) ,getDayOfWeekNumber(ans) , presentRoute.stops[courierIndex].idealReturnTime, presentRoute.stops[courierIndex].returnDay));
                for (int j = courierIndex; j >= junctionIndex + 1; j--){
                    ans = incrementMinutes(ans , presentRoute.stops[j].timeFromPrev);
                }
            }

            srcCode = tempGoal;
        }
        Route presentRoute=routes[arr[arr.length - 1]-1];
        int srcIndex=findObjectIndexByPincode(presentRoute.stops , srcCode);
        int destIndex=findObjectIndexByPincode(presentRoute.stops , destCode);
        
        if (srcIndex < destIndex){
            try{
                int x=calculateMinutesUntilDesiredTime(formatAsHHmm(ans) ,getDayOfWeekNumber(ans) , presentRoute.stops[srcIndex].idealTime, presentRoute.stops[srcIndex].onDay);
                ans = incrementMinutes(ans , x);                
            }
            catch (ParseException e) {
                // Handle the exception, e.g., print an error message
                System.err.println("Error parsing time: " + e.getMessage());
            }
            for (int i = srcIndex + 1; i <= destIndex; i++){
                ans = incrementMinutes(ans ,presentRoute.stops[i].timeFromPrev);
            }
        }
        else if (srcIndex > destIndex){
            try{
                int x=calculateMinutesUntilDesiredTime(formatAsHHmm(ans) ,getDayOfWeekNumber(ans) , presentRoute.stops[srcIndex].idealReturnTime, presentRoute.stops[srcIndex].returnDay);
                ans = incrementMinutes(ans , x);                
            }
            catch (ParseException e) {
                // Handle the exception, e.g., print an error message
                System.err.println("Error parsing time: " + e.getMessage());
            }  
            for (int i = srcIndex; i >= destIndex + 1; i--){
                ans = incrementMinutes(ans ,presentRoute.stops[i].timeFromPrev);
            }
        }

        
        return ans;
    }
    
    public static void exec(int srcCode ,int destCode) {
        stop[] stopsArray1 = {new stop(0, "stop0", "15:00", "11:00", 4, 5, -1),
                              new stop(1, "stop1", "16:00", "10:00", 4, 5, 60),
                              new stop(2, "stop2", "17:00", "09:00", 4, 5, 60),
                              new stop(3, "stop3", "19:00", "07:00", 4, 5, 120),
                              new stop(4, "stop4", "20:00", "06:00", 4, 5, 60),
                              new stop(5, "stop5", "21:00", "05:00", 4, 5, 60)};
        Map<Integer, Integer> linksMap1 = new HashMap<>();
        linksMap1.put(2, 4);
        linksMap1.put(4, 2);
        Route route1 = new Route(1, 4, 8 , stopsArray1 , linksMap1);

        Map<Integer, Integer> linksMap2 = new HashMap<>();
        linksMap2.put(1, 4);
        linksMap2.put(4, 8);
        stop[] stopsArray2 = {new stop(6, "stop6", "20:20", "13:40", 5, 1, -1),
                              new stop(7, "stop7", "10:30", "23:30", 6, 0, 850),
                              new stop(8, "stop8", "12:00", "22:00", 6, 0, 90),
                              new stop(4, "stop4", "17:00", "17:00", 6, 0, 300)};
        Route route2 = new Route(2, 5, 24 ,  stopsArray2 , linksMap2);

        stop[] stopsArray3 = {new stop(9, "stop9", "12:00", "06:00", 4, 5, -1),
                              new stop(10, "stop10", "14:00", "04:00", 4, 5, 120),
                              new stop(11, "stop11", "15:00", "03:00", 4, 5, 60),
                              new stop(12, "stop12", "18:00", "00:00", 4, 5, 180),
                              new stop(13, "stop13", "20:00", "22:00", 4, 4, 120)};
        Map<Integer, Integer> linksMap3 = new HashMap<>();
        linksMap3.put(4,11);                              
        Route route3 = new Route(3, 4 , 2 , stopsArray3 , linksMap3);

        stop[] stopsArray4 = {new stop(11, "stop11", "17:30", "06:30", 6, 0, -1),
                              new stop(8, "stop8", "18:00", "06:00", 6, 0, 30),
                              new stop(2, "stop2", "19:00", "05:00", 6, 0, 60),
                              new stop(14, "stop14", "21:00", "03:00", 6, 0, 120)};
        Map<Integer, Integer> linksMap4 = new HashMap<>();
        linksMap4.put(1, 2);
        linksMap4.put(2, 8);
        linksMap4.put(3, 11);                              
                              
        Route route4 = new Route(4, 6 , 6 ,  stopsArray4,linksMap4);
        
        Node node1 = new Node(1, new int[]{2, 4});
        Node node2 = new Node(2, new int[]{1, 4});
        Node node3 = new Node(3, new int[]{4});
        Node node4 = new Node(4, new int[]{1, 2, 3});

        Route[] routes = {route1, route2, route3, route4};
        
        Node[] links = {node1, node2, node3, node4};
        
        
        List<Integer> all_sources = findRoutesByPincode(srcCode, routes);
        List<Integer> all_dest = findRoutesByPincode(destCode, routes);
        
        Set<List<Integer>> uniqueResults = new HashSet<>();

        for (int s : all_sources) {
            for(int d : all_dest){
                List<List<Integer>> results = findAllRoutes(node1 ,s, d);
                uniqueResults.addAll(results);
            }
        }
        
        List<List<Integer>> resultList = new ArrayList<>(uniqueResults);

        for (List<Integer> result : resultList) {
            int[] resultArray = result.stream().mapToInt(Integer::intValue).toArray();
            System.out.println(result);
            System.out.println(findTimeTaken(srcCode , destCode , resultArray , routes));
        }

    }
    public static void main(String[] args){
        int srcCode=10;
        int destCode=3;
        exec(srcCode , destCode);
    }
}
