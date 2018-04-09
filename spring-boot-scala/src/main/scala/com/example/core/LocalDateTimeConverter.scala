package com.example.core

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.{AttributeConverter, Converter}

@Converter(autoApply = true)
class LocalDateTimeConverter extends AttributeConverter[LocalDateTime, Timestamp] {

  override def convertToDatabaseColumn(localDateTime: LocalDateTime): Timestamp = Timestamp.valueOf(localDateTime)

  override def convertToEntityAttribute(timestamp: Timestamp): LocalDateTime = timestamp.toLocalDateTime
}
