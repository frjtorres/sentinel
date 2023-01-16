package com.sandbox.sentinel.repository

import com.sandbox.sentinel.data.domain.UserStatus
import com.sandbox.sentinel.data.entity.UserEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    @EntityGraph(value = "nodes_addresses_phones")
    fun findByNicknameIgnoringCaseAndStatusIsNot(nickname: String, status: UserStatus = UserStatus.DELETED): UserEntity?
    fun existsByNicknameIgnoringCase(nickname: String): Boolean
    fun existsByEmailIgnoringCaseAndStatusIsNot(email: String, status: UserStatus = UserStatus.DELETED): Boolean
    @Transactional
    @Modifying
    @Query("update UserEntity u set u.status = :status where upper(u.nickname) = upper(:nickname)")
    fun updateStatusByNicknameIgnoringCase(@Param("nickname") nickname: String, @Param("status") status: UserStatus)
}
