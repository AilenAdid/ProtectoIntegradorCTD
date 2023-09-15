package com.CTD.dhBooking.validation;


import java.lang.reflect.Field;

public class convertTextToCase {
    public convertTextToCase(Object object) {
        Class<?> clazz = object.getClass();

        Field[] fields =  clazz.getDeclaredFields();

        for (Field field : fields){
            if(field.isAnnotationPresent(ConvertCase.class)){
                ConvertCase convertCase = field.getAnnotation(ConvertCase.class);
                ConvertCase.CaseType caseType = convertCase.value();

                try{
                    field.setAccessible(true);
                    String text = (String)field.get(object);
                    switch (caseType){
                        case UPPERCASE -> {
                            text = text.toUpperCase();
                        }
                        case LOWERCASE -> {
                            text = text.toLowerCase();
                        }
                    }
                    field.set(object,text);
                } catch (Exception exception){
                    System.out.println("Something went wrong: " + exception);
                }
            }
        }

    }
}