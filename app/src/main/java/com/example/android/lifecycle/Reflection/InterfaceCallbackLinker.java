package com.example.android.lifecycle.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InterfaceCallbackLinker {

    public InterfaceCallbackLinker() {

    }

    public void printActivityAInfo() {

        String className = "com.example.android.lifecycle.ActivityA";

        try {
            Class classToInvestigate = Class.forName(className);

            String name = classToInvestigate.getName();
            System.out.println("Name: " + name);

            String canonicalName = classToInvestigate.getCanonicalName();
            System.out.println("Canonical Name: " + canonicalName);

            String simpleName = classToInvestigate.getSimpleName();
            System.out.println("Simple Name: " + simpleName);

            Field[] declaredFields = classToInvestigate.getDeclaredFields();
            System.out.println("Declared Fields Include: ");
            for(Field field : declaredFields) {
                System.out.println(field.getName());
            }

//            Field[] fields = classToInvestigate.getFields();
//            System.out.println("Fields Include: ");
//            for(Field field : fields) {
//                System.out.println(field.getName());
//            }

//            boolean assertionStatus = classToInvestigate.desiredAssertionStatus();
//            System.out.println("Assertion Status: " + assertionStatus);

//            Annotation[] annotations = classToInvestigate.getAnnotations();
//            System.out.println("Annotations Include: ");
//            for (Annotation annotation: annotations) {
//                System.out.println(annotation.toString());
//            }

            Class[] classes = classToInvestigate.getClasses();
            System.out.println("Classes Include: ");
            for(Class classElement : classes) {
                System.out.println(classElement.getName());
            }

            Class[] declaredClasses = classToInvestigate.getDeclaredClasses();
            System.out.println("Declared Classes Include: ");
            for(Class classElement : declaredClasses) {
                System.out.println(classElement.getName());
            }

            ClassLoader classLoader = classToInvestigate.getClassLoader();
            if (classLoader != null) {
                System.out.println("Class Loader: " + classLoader.toString());
            }

            Method[] declaredMethods = classToInvestigate.getDeclaredMethods();
            System.out.println("Declared Methods Include: ");

            Method onCreate = null;

            for(Method method : declaredMethods) {
                System.out.println(method.getName());

                if(method.getName().equals("onCreate")) {
                    onCreate = method;
                }
            }

            //noinspection StatementWithEmptyBody
            if(onCreate != null) {
                //TODO: Implement!!!
            }

//            Method[] methods = classToInvestigate.getMethods();
//            System.out.println("Methods Include: ");
//            for(Method method : methods) {
//                System.out.println(method.getName());
//            }

//            Constructor[] constructors = classToInvestigate.getConstructors();
//            System.out.println("Constructors Include: ");
//            for(Constructor constructor : constructors) {
//                System.out.println(constructor.getName());
//            }

//            Constructor[] declaredConstructors = classToInvestigate.getDeclaredConstructors();
//            System.out.println("Declared Constructors Include: ");
//            for(Constructor constructor : declaredConstructors) {
//                System.out.println(constructor.getName());
//            }

//            Object instance = classToInvestigate.newInstance();

//            Class declaringClass = classToInvestigate.getDeclaringClass();
//            if (declaringClass != null) {
//                System.out.println("Declaring Class: " + declaringClass.getName());
//            }

//            Class enclosingClass = classToInvestigate.getEnclosingClass();
//            System.out.println("Enclosing Class: " + enclosingClass);

//            Constructor enclosingConstructor = classToInvestigate.getEnclosingConstructor();
//            System.out.println("Enclosing Constructor: " + enclosingConstructor);

//            Method enclosingMethod = classToInvestigate.getEnclosingMethod();
//            System.out.println("Enclosing Method: " + enclosingMethod);

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found!!");
        } catch (Exception e) {
            System.out.println("Unknown Exception!!");
        }
    }

    public void printViewInfo() {

        String className = "com.example.android.lifecycle.ActivityA";

        try {
            Class classToInvestigate = Class.forName(className);

            String name = classToInvestigate.getName();
            System.out.println("Name: " + name);

            String canonicalName = classToInvestigate.getCanonicalName();
            System.out.println("Canonical Name: " + canonicalName);

            String simpleName = classToInvestigate.getSimpleName();
            System.out.println("Simple Name: " + simpleName);

            Field[] declaredFields = classToInvestigate.getDeclaredFields();
            System.out.println("Declared Fields Include: ");
            for(Field field : declaredFields) {
                System.out.println(field.getName());
            }

//            Field[] fields = classToInvestigate.getFields();
//            System.out.println("Fields Include: ");
//            for(Field field : fields) {
//                System.out.println(field.getName());
//            }

//            boolean assertionStatus = classToInvestigate.desiredAssertionStatus();
//            System.out.println("Assertion Status: " + assertionStatus);

//            Annotation[] annotations = classToInvestigate.getAnnotations();
//            System.out.println("Annotations Include: ");
//            for (Annotation annotation: annotations) {
//                System.out.println(annotation.toString());
//            }

            Class[] classes = classToInvestigate.getClasses();
            System.out.println("Classes Include: ");
            for(Class classElement : classes) {
                System.out.println(classElement.getName());
            }

            Class[] declaredClasses = classToInvestigate.getDeclaredClasses();
            System.out.println("Declared Classes Include: ");
            for(Class classElement : declaredClasses) {
                System.out.println(classElement.getName());
            }

            ClassLoader classLoader = classToInvestigate.getClassLoader();
            if (classLoader != null) {
                System.out.println("Class Loader: " + classLoader.toString());
            }

            Method[] declaredMethods = classToInvestigate.getDeclaredMethods();
            System.out.println("Declared Methods Include: ");

            Method onCreate = null;

            for(Method method : declaredMethods) {
                System.out.println(method.getName());

                if(method.getName().equals("onCreate")) {
                    onCreate = method;
                }
            }

            //noinspection StatementWithEmptyBody
            if(onCreate != null) {
                //TODO: Implement!!!
            }

//            Method[] methods = classToInvestigate.getMethods();
//            System.out.println("Methods Include: ");
//            for(Method method : methods) {
//                System.out.println(method.getName());
//            }

//            Constructor[] constructors = classToInvestigate.getConstructors();
//            System.out.println("Constructors Include: ");
//            for(Constructor constructor : constructors) {
//                System.out.println(constructor.getName());
//            }

//            Constructor[] declaredConstructors = classToInvestigate.getDeclaredConstructors();
//            System.out.println("Declared Constructors Include: ");
//            for(Constructor constructor : declaredConstructors) {
//                System.out.println(constructor.getName());
//            }

//            Object instance = classToInvestigate.newInstance();

//            Class declaringClass = classToInvestigate.getDeclaringClass();
//            if (declaringClass != null) {
//                System.out.println("Declaring Class: " + declaringClass.getName());
//            }

//            Class enclosingClass = classToInvestigate.getEnclosingClass();
//            System.out.println("Enclosing Class: " + enclosingClass);

//            Constructor enclosingConstructor = classToInvestigate.getEnclosingConstructor();
//            System.out.println("Enclosing Constructor: " + enclosingConstructor);

//            Method enclosingMethod = classToInvestigate.getEnclosingMethod();
//            System.out.println("Enclosing Method: " + enclosingMethod);

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found!!");
        } catch (Exception e) {
            System.out.println("Unknown Exception!!");
        }
    }
}
