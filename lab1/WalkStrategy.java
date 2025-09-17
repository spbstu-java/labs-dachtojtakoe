package lab1;

public class WalkStrategy implements MovementStrategy{
    public void move(String from, String to){
        System.out.println("Начальная точка " + from + " иду пешком в "  + to);
    }
}
