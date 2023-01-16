package com.sandbox.sentinel.data.validation.unique

import com.sandbox.sentinel.data.entity.UserEntity
import com.sandbox.sentinel.exception.SentinelException
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class UniqueValidatorProvider(private val uniqueUserEntityValidator: UniqueValidator) {

    fun get(entityKClass: KClass<*>): UniqueValidator {
        return when (entityKClass) {
            UserEntity::class -> uniqueUserEntityValidator
            else -> throw SentinelException("UniqueEntityValidator not found for entity class [${entityKClass.simpleName}]")
        }
    }
}