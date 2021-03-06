package saladilb.com.controller;

import saladilb.com.entity.Test;
import saladilb.com.entity.Sample;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MainController {

    public void run() {
        Class<Sample> clazz = Sample.class;
        Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .forEach(method -> {
                    method.setAccessible(true);
                    try {
                        method.invoke(clazz.newInstance());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }

                });
        ;
    }
}

