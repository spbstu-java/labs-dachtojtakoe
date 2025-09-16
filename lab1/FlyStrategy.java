package lab1;

public class FlyStrategy implements MovementStrategy{
    public void move(String from, String to){
        System.out.println("Начальная точка " + from + " лечу в " + to);
    }
}
