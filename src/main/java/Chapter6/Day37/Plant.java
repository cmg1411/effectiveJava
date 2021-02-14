package Chapter6.Day37;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plant {
    enum LifeCycle { ANNUAL, PERENNIAR, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        // 문제 1. 제네릭과 배열은 호환되지 않아 형변환이 필요.
        Set<Plant>[] plantByLifeCycle = (Set<Plant>[]) new Set[LifeCycle.values().length];
        for (int i = 0; i < plantByLifeCycle.length; i++) {
            plantByLifeCycle[i] = new HashSet<>();
        }

        List<Plant> garden = new ArrayList<>(List.of(new Plant("san", LifeCycle.ANNUAL), new Plant("se", LifeCycle.BIENNIAL)));
        // 문제 3. 가장 심각한 문제. ordinal() 정수값은 안전하지 않음.
        for (Plant plant : garden) {
            plantByLifeCycle[plant.lifeCycle.ordinal()].add(plant);
        }

        // 문제 2. plantByLifeCycle 배열은 자신의 인덱스가 뭘 뜻하는지 몰라서 직접 레이블을 달아야함.
        for (int i = 0; i < plantByLifeCycle.length; i++) {
            System.out.printf("%s : %s%n", Plant.LifeCycle.values()[i], plantByLifeCycle[i]);
        }
    }
}
