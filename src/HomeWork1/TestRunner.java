package HomeWork1;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class TestRunner {
    public static void runTests(Class c){
        Class<?> clas = null;
        try {
            clas = Class.forName(c.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Object instance;
        try {
             instance = c.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        Method[] methods = clas.getDeclaredMethods();
        Method beforeSuit=null;
        Method afterSuit=null;

        Method[] tests = new Method[methods.length];
        int testCount = 0;

        for (Method method:methods){
           if (method.isAnnotationPresent(BeforeSuite.class)){
                if (beforeSuit!=null) throw new RuntimeException("Методов @BeforeSuite больше одного");

                beforeSuit=method;
           } else if (method.isAnnotationPresent(AfterSuite.class)) {
               if (afterSuit!=null) throw new RuntimeException("Методов @AfterSuite больше одного");

               afterSuit=method;
           } else if (method.isAnnotationPresent(Test.class)) {
               tests[testCount++]=method;
               System.out.println(method.getAnnotation(Test.class).priority());

              // System.out.println(method.getName());
               //System.out.println(testCount);
           }
        }
        //запуск BeforeSuite теста
        if (beforeSuit!=null){
            try {
                beforeSuit.invoke(instance);
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        Method[] curTests = new Method[testCount];

        System.arraycopy(tests,0,curTests,0,testCount);
        Arrays.sort(curTests, Comparator.comparingInt(m->m.getAnnotation(Test.class).priority()));
        for (Method meth:curTests){
            try {
                meth.invoke(instance);
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        //запуск AfterSuite теста
        if (afterSuit!=null){
            try {
                afterSuit.invoke(instance);
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
      runTests(Reflection.class);
    }
}

