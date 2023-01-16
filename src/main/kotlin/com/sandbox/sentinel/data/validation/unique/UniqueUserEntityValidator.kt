package com.sandbox.sentinel.data.validation.unique

import com.sandbox.sentinel.data.entity.UserEntity
import com.sandbox.sentinel.exception.SentinelException
import com.sandbox.sentinel.service.UserService
import jakarta.validation.ConstraintValidatorContext
import org.springframework.stereotype.Component

@Component
class UniqueUserEntityValidator(private val userService: UserService) : UniqueValidator {

    override fun isUnique(entity: Any, context: ConstraintValidatorContext): Boolean {
        if (entity !is UserEntity) {
            throw SentinelException("UniqueUserEntityValidator only support entity [UserEntity]")
        }

        var isUnique = true
        context.disableDefaultConstraintViolation()

        if (userService.isNicknameAvailable(entity.nickname).not()) {
            context.buildConstraintViolationWithTemplate("{sentinel.validation.nickname.unique.message}")
                .addPropertyNode("nickname")
                .addConstraintViolation()
            isUnique = false
        }

        if (userService.isEmailAvailable(entity.email).not()) {
            context.buildConstraintViolationWithTemplate("{sentinel.validation.email.unique.message}")
                .addPropertyNode("email")
                .addConstraintViolation()
            isUnique = false
        }

        return isUnique
    }
}