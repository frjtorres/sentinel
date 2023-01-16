package com.sandbox.sentinel.data.validation.annotation

import com.sandbox.sentinel.data.validation.Constants
import jakarta.validation.Constraint
import jakarta.validation.OverridesAttribute
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import kotlin.reflect.KClass

@MustBeDocumented
@Size
@Pattern(regexp = Constants.PASSWORD_REGEX)
@Constraint(validatedBy = [])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Password(
        @get:OverridesAttribute(constraint = Pattern::class, name = "message")
        val message: String = "{sentinel.validation.password.pattern.message}",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [],
        @get:OverridesAttribute(constraint = Size::class, name = "min")
        val min: Int = 0,
        @get:OverridesAttribute(constraint = Size::class, name = "max")
        val max: Int = Integer.MAX_VALUE
)
