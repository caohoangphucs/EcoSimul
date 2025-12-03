package org.example.ecosimul.core.server;

@FunctionalInterface
public interface Action {
    void run(long dt);
}
