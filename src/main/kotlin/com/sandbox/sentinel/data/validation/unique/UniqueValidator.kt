package com.sandbox.sentinel.data.validation.unique

import jakarta.validation.ConstraintValidatorContext

interface UniqueValidator {
    
    fun isUnique(entity: Any, context: ConstraintValidatorContext): Boolean
}