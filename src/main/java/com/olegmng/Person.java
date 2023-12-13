package com.olegmng;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Person {
    private final String name;
    private final int age;
    private static long count = 1L;


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Person person = new Person("Oleg");
        System.out.println(person);

        Person personR = Person.randomPerson();
        System.out.println(personR);

        //Reflection
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class);

        Person personViaReflection = constructor.newInstance("Oleg");
        constructor.setAccessible(true);
        System.out.println(personViaReflection);

        //при статическом методе необходимо передавать null
        var randomPersonMethod = personClass.getMethod("randomPerson");
        var invokePerson = randomPersonMethod.invoke("null");
        System.out.println(invokePerson);


    }

    private Person(String name) {
        this(name, 20);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public static Person randomPerson(){
        return new Person("Person #" + count++);
    }

    @Override
    public String toString() {
        return String.format("%s, - [%d]", name, age);
    }
}
