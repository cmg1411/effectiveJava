package Chapter6.Day34;

import jdk.jfr.AnnotationElement;

import java.lang.annotation.Annotation;

public class AsSubClass {
    static Annotation getAnnotation(AnnotationElement annotationElement, String annotationTypeName) {
        Class<?> annotationType = null;

        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException();
        }

        return annotationElement.getAnnotation(annotationType.asSubclass(Annotation.class));
    }
}
