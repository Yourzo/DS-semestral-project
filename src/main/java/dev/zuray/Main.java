package dev.zuray;

import dev.zuray.generators.DiscEmpiricalGen;
import dev.zuray.generators.SeedManager;

public class Main {
    public static void main(String[] args) {
        SeedManager sm = new SeedManager();
        DiscEmpiricalGen gen = new DiscEmpiricalGen(sm.getNextSeed(), 456, 8564, 2135684);
        for (int i = 0; i < 400; i++) {
            System.out.println(gen.getNext());
        }
    }
}