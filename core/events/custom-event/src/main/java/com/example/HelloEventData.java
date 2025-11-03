package com.example;

import java.time.LocalDateTime;

public record HelloEventData(String message, LocalDateTime dateTime) {
}
