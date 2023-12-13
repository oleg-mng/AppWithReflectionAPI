package com.olegmng;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonRefTest {
    @AfterEach
    void afterEachTest(){

    }

    @Test
    void testToString(){
        Person personTest = new Person("OlegTest", 20);

        Assertions.assertEquals(personTest.toString(), "OlegTest, - [20]");

    }

}