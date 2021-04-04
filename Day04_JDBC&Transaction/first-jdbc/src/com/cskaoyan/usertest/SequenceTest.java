package com.cskaoyan.usertest;

import org.junit.*;

public class SequenceTest {

    @Test
    public void test01(){
        System.out.println("test....");
    }

    @Test
    public void test02(){
        System.out.println("test....");
    }

    @Test
    public void test03(){
        System.out.println("test....");
    }

    // 在执行@Test修饰的方法之前执行，有一个@Test注解修饰的方法，就执行一次，可以执行多次
    @Before
    public void testBefore() {
        System.out.println("testBefore");
    }

    // 在执行@Test修饰的方法之后执行，有一个@Test注解修饰的方法，就执行一次，可以执行多次
    @After
    public void testAfter(){
        System.out.println("After...");
    }

    // 是在类初始化的时候执行
    @BeforeClass
    public static void testBeforeClass(){
        System.out.println("testBeforeClass...");
    }

    // 是在类被销毁的时候执行
    @AfterClass
    public static void afterClass(){
        System.out.println("afterClass...");
    }

}
