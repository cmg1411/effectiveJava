package Chapter7.Day43;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InstanceMethodReference {

    private final String name = "mehtod";

    public static void main(String[] args) {
        List<InstanceMethodReference> list = new ArrayList<>(Arrays.asList(new InstanceMethodReference(), new InstanceMethodReference()));
        NewInstance nee = new NewInstance();


        List<NewInstance> l = list.stream()
            .map(nee.now()::getNewInstance)
            .collect(Collectors.toList());

        System.out.println(l);
    }
}

class NewInstance {

    private InstanceMethodReference ins;

    public NewInstance() {
    }

    public NewInstance(InstanceMethodReference ins) {
        this.ins = ins;
    }

    public NewInstance now() {
        return this;
    }

    public NewInstance getNewInstance(InstanceMethodReference ins) {
        return new NewInstance(ins);
    }
}