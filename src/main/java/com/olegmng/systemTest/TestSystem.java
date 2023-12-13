package com.olegmng.systemTest;

//Reflection API
//Расширить пример с запуском тестов следующими фичами:
//1. Добавить аннотации BeforeEach, AfterEach,
//которые ставятся над методами void без аругментов и запускаются ДО и ПОСЛЕ всех тестов соответственно.
//2. В аннотацию Test добавить параметр order() со значением 0 по умолчанию.
//Необходимо при запуске тестов фильтровать тесты по этому параметру (от меньшего к большему).
//Т.е. если есть методы @Test(order = -2) void first, @Test void second, Test(order = 5) void third,
//то порядок вызовов first -> second -> third
//3.* Добавить аннотацию @Skip, которую можно ставить над тест-методами. Если она стоит - то тест не запускается.

public class TestSystem {

    // создать систему запуска тестов
    public static void main(String[] args) {
        TestProcessor.runTest(MyTest.class);

    }

    static class MyTest {

        @Test(order = 11)
        public void firstTest() {
            System.out.println("firstTest() запущен");

        }

        @Test(order = 5)
        public void secondTest() {
            System.out.println("secondTest() запущен");

        }

        @BeforeEach
        public void beforeTest() {
            System.out.println("beforeTest() запущен");

        }

        @AfterEach
        public void afterTest() {
            System.out.println("afterTest() запущен");

        }

        @Test(order = 3)
        public void thirdTest() {
            System.out.println("thirdTest() запущен");

        }
        @Test(order = 13)
        @Skip
        public void thirteenTest() {
            System.out.println("thirteenTest() запущен");

        }

    }

}
