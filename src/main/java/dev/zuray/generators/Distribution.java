package dev.zuray.generators;

public interface Distribution<T> {
    T getNext();
    long getM();
}
