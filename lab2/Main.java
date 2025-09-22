package lab2;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
            AnnotatedClass annotatedInstance = new AnnotatedClass();

            Class<?> clazz = annotatedInstance.getClass();

            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Repeat.class)) {
                    Repeat repeat = method.getAnnotation(Repeat.class);
                    int times = repeat.value();

                    method.setAccessible(true);

                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Object[] parameters = new Object[parameterTypes.length];

                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (parameterTypes[i] == String.class) {
                            parameters[i] = "test";
                        } else if (parameterTypes[i] == int.class) {
                            parameters[i] = 11;
                        } else if (parameterTypes[i] == double.class) {
                            parameters[i] = 1.11;
                        } else if (parameterTypes[i] == boolean.class) {
                            parameters[i] = true;
                        } else {
                            parameters[i] = null;
                        }
                    }

                    System.out.println("Вызываем метод " + method.getName() + " " + times + " раз(а):");
                    for (int i = 0; i < times; i++) {
                        try {
                            method.invoke(annotatedInstance, parameters);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            System.err.println("Ошибка при вызове метода: " + e.getMessage());
                        }
                    }
                    System.out.println();
                }
            }

        } catch (SecurityException e) {
            System.err.println("Ошибка безопасности: " + e.getMessage());
        }
    }
}
