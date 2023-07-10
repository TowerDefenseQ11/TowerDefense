package com.towerdefense.waves;

public interface WaveCallback<T> {
    T done();
    Throwable error();
}
