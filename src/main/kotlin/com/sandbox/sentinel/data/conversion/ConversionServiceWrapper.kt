package com.sandbox.sentinel.data.conversion

import com.sandbox.sentinel.exception.SentinelException
import org.springframework.core.convert.ConverterNotFoundException
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.support.GenericConversionService

class ConversionServiceWrapper(private val genericConversionService: GenericConversionService) {

    fun addLocalConverters() {
        genericConversionService.addConverter(OnCreateUserModelToEntityConverter())
        genericConversionService.addConverter(EntityToOnSearchUserModelConverter())
    }

    fun <T : Any> convert(source: Any, targetType: Class<T>) =
        genericConversionService.convert(source, targetType)
            ?: throw SentinelException(
                ConverterNotFoundException(TypeDescriptor.forObject(source), TypeDescriptor.valueOf(targetType))
            )

    fun convert(source: Any, sourceType: TypeDescriptor, targetType: TypeDescriptor) =
        genericConversionService.convert(source, sourceType, targetType)
            ?: throw SentinelException(ConverterNotFoundException(sourceType, targetType))
}