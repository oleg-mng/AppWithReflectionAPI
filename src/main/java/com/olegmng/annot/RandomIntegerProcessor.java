package com.olegmng.annot;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerProcessor {
    public static void processObject(Object object) {
        Class<?> objectClass = object.getClass();
        for (Field declaredField : objectClass.getDeclaredFields()) {
            if (int.class.isAssignableFrom(declaredField.getType()) && declaredField.isAnnotationPresent(RandomInteger.class)) {
                var randomInteger = declaredField.getAnnotation(RandomInteger.class);
                int minValue = randomInteger.minValue();
                int maxValue = randomInteger.maxValue();

                //[4,7] = 4 + (0,3)
                var randomValue = minValue + ThreadLocalRandom.current().nextInt(maxValue - minValue);
                declaredField.setAccessible(true);
                try {
                    declaredField.set(object, randomValue);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }


            }


        }

    }
}
