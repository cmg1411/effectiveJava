package Chapter6.Day39.customtest;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import java.util.Set;


@SupportedAnnotationTypes("com.meetcoder.testannotation.CustomTest")

public class CustomTestProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(com.meetcoder.testannotation.CustomTest.class);
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Chapter6.Day39.customtest.CustomTest.class);
        for(Element element : elementsAnnotatedWith){
            makeErrorIfNotStaticMethod(element);
        }
        return true;
    }

    private void makeErrorIfNotStaticMethod(Element element) {
        if (isNotStaticMethod(element)) {
            processingEnv.getMessager().printMessage(Kind.ERROR, "static 메서드가 아닙니다.");
        }
    }

    private boolean isNotStaticMethod(Element element) {
        return !element.getModifiers().contains(Modifier.STATIC);
    }
}
