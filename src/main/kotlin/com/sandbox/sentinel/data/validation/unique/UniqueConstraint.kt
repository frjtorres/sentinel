package com.sandbox.sentinel.data.validation.unique

import com.sandbox.sentinel.data.validation.annotation.Unique
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class UniqueConstraint(private val uniqueValidatorProvider: UniqueValidatorProvider) : ConstraintValidator<Unique, Any> {

    override fun isValid(entity: Any, context: ConstraintValidatorContext) =
        uniqueValidatorProvider.get(entity::class).isUnique(entity, context)
}