package com.olegmng.systemTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestProcessor {
    record MyRecord(int x, int y) {

    }

    public static void main(String[] args) {
        MyRecord myRecord = new MyRecord(5, 7);
        var x = myRecord.x();
        var y = myRecord.y();
        System.out.println(myRecord);
    }

    // находит все void методы без аргументов в классе и запускает их
    // для запуска создается тестовый объект с помощью конструктора без аргументов
    public static void runTest(Class<?> testClass) {
        boolean flagAft = false;

        Method methodAft = null;

        final Constructor<?> declaredConstructor;

        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса: \"" + testClass.getClass() + "\" не найден конструктор без аргументов");
        }
        final Object testObj;
        try {
            testObj = declaredConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Не удалось составить объект класса\"" + testClass.getName() + "\"");

        }
        List<Method> list = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            //добавляем условие для поиска метода с аннотацией @BeforeEach для запуска в первую очередь
            if (method.isAnnotationPresent(BeforeEach.class)) {
                runTestR(method, testObj);
            }
            if (method.isAnnotationPresent(Test.class)) {
                //получаем параметр int для аннотации Test
                Test annoParam = method.getAnnotation(Test.class);
                System.out.println(annoParam.order());

                checkTestMethod(method);
                list.add(method);

            }
            //реализация не запуска метода с аннотацией @Skip
            if (method.isAnnotationPresent(Skip.class)){
                list.remove(method);
            }
            //добавляем условие для поиска метода с аннотацией @AfterEach - фиксируем метод если он есть и ставим флаг flagAft
            if (method.isAnnotationPresent(AfterEach.class)) {
                flagAft = true;
                methodAft = method;

            }
        }
        //реализуем сортировку по параметру order() в анотации @Test с помощью stream
        list.stream()
                .sorted((o1, o2)
                -> Integer.compare(o1.getAnnotation(Test.class).order(), o2.getAnnotation(Test.class).order()))
                .forEach(System.out::println);

        list.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                if (o1.getAnnotation(Test.class).order() < o2.getAnnotation(Test.class).order()) return -1;
                else if (o1.getAnnotation(Test.class).order() > o2.getAnnotation(Test.class).order()) return 1;
                return 0;
            }
        });

        list.forEach(it -> runTestR(it, testObj));

        // анализируем флаг flagAft после запуска всех методов
        if (flagAft) {
            runTestR(methodAft, testObj);

        }

    }

    public static void checkTestMethod(Method method) {
        if (method.getReturnType().isAssignableFrom(void.class) && method.getParameterCount() == 0) {

        } else {
            throw new IllegalArgumentException("Метод \"" + method.getName() + "\"должен быть void и не иметь аргументов");
        }
    }

    public static void runTestR(Method testMethod, Object testObj) {
        try {
            testMethod.invoke(testObj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");


        }
    }

}
