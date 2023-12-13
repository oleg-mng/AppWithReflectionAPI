package com.olegmng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PersonRef {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        //Reflection
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class);

        constructor.setAccessible(true);
        Person personViaReflection = constructor.newInstance("Oleg");
        System.out.println(personViaReflection);

        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(personViaReflection, 150);
        System.out.println(personViaReflection);

        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(personViaReflection, "Oleg_2.0");
        System.out.println(personViaReflection);

        var toStringMethod = personClass.getMethod("toString");
        Object ob = toStringMethod.invoke(personViaReflection);
        System.out.println(ob);




        Class<? super Head> superclass = Head.class.getSuperclass();
        System.out.println(superclass.getName());
        Constructor<? super Head> declaredConstructor = superclass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object aBcD = declaredConstructor.newInstance("aBcD");
        System.out.println(aBcD);

    }
}
