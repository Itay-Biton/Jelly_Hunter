package com.example.jellyhunter.interfaces;

public interface SensorCallback {
    void Move(String dir);
    void ChangeSpeed(int delta);
}
