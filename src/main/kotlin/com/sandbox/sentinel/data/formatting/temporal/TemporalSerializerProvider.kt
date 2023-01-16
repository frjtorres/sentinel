package com.sandbox.sentinel.data.formatting.temporal

import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.sandbox.sentinel.exception.SentinelException
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.temporal.Temporal
import java.util.*

object TemporalSerializerProvider {

    private val temporalSerializers: Map<Class<out Temporal>, StdSerializer<out Temporal>> = mapOf(
        OffsetDateTime::class.java to OffsetDateTimeSerializer(),
        LocalDateTime::class.java to LocalDateTimeSerializer(),
        Instant::class.java to InstantSerializer()
    )

    fun get(type: Class<out Temporal>): StdSerializer<out Temporal> =
        Optional.ofNullable(temporalSerializers[type])
            .orElseThrow { SentinelException("temporal serializer for type [${type.name}] not found") }
}
