package dev.zuray.generators;

public interface Distribution<T extends Number> {
    T next();
}
