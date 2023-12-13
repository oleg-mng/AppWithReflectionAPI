package com.olegmng.annot;

import java.lang.reflect.Field;

public class Annotation {
    public static void main(String[] args) throws NoSuchFieldException {
        MyClass myClass = new MyClass();
        //        System.out.println(myClass.getValue());
        System.out.println(myClass.getNegative());
        System.out.println(myClass.getPositive());

        RandomIntegerProcessor.processObject(myClass);
        System.out.println(myClass.getNegative());
        System.out.println(myClass.getPositive());

//        var declaredField = MyClass.class.getDeclaredField("value");
//        var declaredFieldAnnotation = declaredField.getAnnotation(RandomInteger.class);
//        System.out.println(declaredFieldAnnotation == null);
//        System.out.println(declaredFieldAnnotation.minValue());
//        System.out.println(declaredFieldAnnotation.maxValue());

//        var declaredField = MyClass.class.getDeclaredField("negative");
//        var negativeFieldAnnotation = declaredField.getAnnotation(RandomInteger.class);
//        System.out.println(negativeFieldAnnotation.minValue());
//        System.out.println(negativeFieldAnnotation.maxValue());


    }

    static class MyClass {
        @RandomInteger(minValue = -15, maxValue = -3)
//        private int value;
        private int negative;
        @RandomInteger(minValue = 5, maxValue = 15)
//        private int value;
        private int positive;

        public int getNegative() {
            return negative;
        }

        public int getPositive() {
            return positive;
        }
    }
}
