package dev.zuray;

import dev.zuray.generators.ContinuousDistGen;
import dev.zuray.generators.SeedManager;
import dev.zuray.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.info("Starting the program!");
        var sm = new SeedManager();
        var cont = new ContinuousDistGen(sm.getNextSeed(), 138, 879, 123415);
        for (int i = 0; i < 40; i++) {
            System.out.println(cont.getNextDouble());
        }
    }
}