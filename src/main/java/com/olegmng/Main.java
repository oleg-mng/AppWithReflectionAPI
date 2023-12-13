package com.olegmng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> car = Class.forName("com.olegmng.Car");
        Constructor<?>[] constructors = car.getConstructors();
        System.out.println(constructors[0]);

        Object audi = constructors[0].newInstance("Audi");
        System.out.println(audi);

        Field[] fields = audi.getClass().getFields();
        int tmp = fields[fields.length - 1].getInt(audi);

        fields[fields.length - 1].setInt(audi, tmp * 2);

        System.out.println(audi);

        Method[] methods = audi.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}