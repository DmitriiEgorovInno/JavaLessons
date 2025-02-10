package HomeWork1;

public class Reflection {
    @Test(priority = 8)
    public void method1(){
        System.out.println("Run method1");
    }
    @Test
    public void method2(){
        System.out.println("Run method2");
    }
    @Test(priority = 3)
    public void method3(){
        System.out.println("Run method3");
    }

    @Test(priority = 5)
    public void method4(){
        System.out.println("Run method4");
    }

    @BeforeSuite
    public static void bf(){
        System.out.println("Run bf");
    }
    @AfterSuite
    public static void af(){
        System.out.println("Run af");
    }
}
