package com.sandbox.sentinel.service

import com.sandbox.sentinel.data.conversion.ConversionServiceWrapper
import com.sandbox.sentinel.data.domain.UserStatus
import com.sandbox.sentinel.data.entity.UserEntity
import com.sandbox.sentinel.data.model.OnCreateUserModel
import com.sandbox.sentinel.data.model.OnSearchUserModel
import com.sandbox.sentinel.exception.EntityNotFoundException
import com.sandbox.sentinel.repository.UserRepository
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val conversionServiceWrapper: ConversionServiceWrapper,
                  private val passwordEncoder: PasswordEncoder,
                  private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun searchBy(nickname: String): OnSearchUserModel {
        val foundUser = userRepository.findByNicknameIgnoringCaseAndStatusIsNot(nickname)
            ?: run { throw EntityNotFoundException("sentinel.service.user.search", arrayOf(nickname)) }
        return conversionServiceWrapper.convert(foundUser, OnSearchUserModel::class.java)
    }

    fun createWith(onCreateUserModel: OnCreateUserModel): OnSearchUserModel {
        onCreateUserModel.password = passwordEncoder.encode(onCreateUserModel.password)
        val userEntity = conversionServiceWrapper.convert(onCreateUserModel, UserEntity::class.java)
        val createdUser = userRepository.save(userEntity)
        return conversionServiceWrapper.convert(createdUser, OnSearchUserModel::class.java)
    }

    fun deleteBy(nickname: String) {
        userRepository.updateStatusByNicknameIgnoringCase(nickname, UserStatus.DELETED)
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    fun isNicknameAvailable(nickname: String) = userRepository.existsByNicknameIgnoringCase(nickname).not()
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun isEmailAvailable(email: String) = userRepository.existsByEmailIgnoringCaseAndStatusIsNot(email).not()
}