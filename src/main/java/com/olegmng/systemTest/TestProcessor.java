package com.olegmng.systemTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestProcessor {
    record MyRecord(int x, int y){

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
            if (method.isAnnotationPresent(Test.class)) {
                checkTestMethod(method);
                list.add(method);

            }
        }
        list.forEach(it -> runTestR(it, testObj));

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
