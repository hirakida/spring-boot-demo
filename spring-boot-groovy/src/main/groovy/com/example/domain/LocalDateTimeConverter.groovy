package com.example.domain

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.sql.Timestamp
import java.time.LocalDateTime

@Converter(autoApply = true)
class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
        return dateTime == null ? null : Timestamp.valueOf(dateTime)
    }

    @Override
    LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime()
    }
}
