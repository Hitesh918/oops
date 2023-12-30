import java.util.*;
class stop {
    int pincode;
    String name;
    String idealTime;
    String idealReturnTime;
    int onDay;
    int returnDay;
    int timeFromPrev;


}
class Route {
    int uniqueId;
    int journeyLength;
    stop s[];
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
        Scanner sc = new Scanner(System.in);
       /* stop[] s = new stop[5];
        for(int i = 0 ; i<5;i++){
            s[i] = new stop();

        }*/
        System.out.println("Enter the source city name ");
        String src = sc.next();
        System.out.println("Enter Destination city name");
        String dest = sc.next();
        System.out.println("Enter the starting time of delivery");
        int startTime = sc.nextInt();

    }
}
