package lab1;

public class HorseRideStrategy implements MovementStrategy{
    public void move(String from, String to){
        System.out.println("Начальная точка " + from + " скачу на лошади в " + to);
    }
}
