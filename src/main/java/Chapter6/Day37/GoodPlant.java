package Chapter6.Day37;

import java.util.*;

public class GoodPlant {
    enum LifeCycle { ANNUAL, PERENNIAR, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    public GoodPlant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Map<LifeCycle, Set<GoodPlant>> plantByLifeCycle = new EnumMap<>(LifeCycle.class);
        for (LifeCycle lifeCycle : LifeCycle.values()) {
            plantByLifeCycle.put(lifeCycle, new HashSet<>());
        }

        List<GoodPlant> garden = List.of(new GoodPlant("san", LifeCycle.ANNUAL), new GoodPlant("se", LifeCycle.BIENNIAL));

        for (GoodPlant plant : garden) {
            plantByLifeCycle.get(plant.lifeCycle).add(plant);
        }

        System.out.println(plantByLifeCycle);
    }
}
