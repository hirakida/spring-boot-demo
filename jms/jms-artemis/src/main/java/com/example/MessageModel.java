package com.example;

import java.io.Serializable;
import java.time.LocalTime;

public record MessageModel(String message, LocalTime time) implements Serializable {}
