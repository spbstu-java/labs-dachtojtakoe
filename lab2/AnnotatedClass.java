package lab2;

public class AnnotatedClass {
    @Repeat(3)
    public void publicMethod1(String message) {
        System.out.println("Public method 1: " + message);
    }

    public void publicMethod2(int number) {
        System.out.println("Public method 2: " + number);
    }

    @Repeat(2)
    public void publicMethod3(String message, int number) {
        System.out.println("Public method 3: " + message + ", " + number);
    }

    @Repeat(4)
    protected void protectedMethod1(String message) {
        System.out.println("Protected method 1: " + message);
    }

    protected void protectedMethod2(int number) {
        System.out.println("Protected method 2: " + number);
    }

    @Repeat(1)
    protected void protectedMethod3(String message, int number) {
        System.out.println("Protected method 3: " + message + ", " + number);
    }

    @Repeat(4)
    private void privateMethod1(String message) {
        System.out.println("Private method 1: " + message);
    }

    private void privateMethod2(int number) {
        System.out.println("Private method 2: " + number);
    }

    @Repeat()
    private void privateMethod3(String message, int number) {
        System.out.println("Private method 3: " + message + ", " + number);
    }
}