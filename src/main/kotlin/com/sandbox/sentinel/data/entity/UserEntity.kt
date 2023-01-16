package com.sandbox.sentinel.data.entity

import com.sandbox.sentinel.data.domain.UserStatus
import com.sandbox.sentinel.data.validation.annotation.Unique
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "users",
       uniqueConstraints = [ UniqueConstraint(name = "uk_user_nickname", columnNames = [ "nickname" ]) ],
       indexes = [ Index(name = "idx_user_email_status", columnList = "email,status") ])
@NamedEntityGraph(name = "nodes_addresses_phones",
                  attributeNodes = [ NamedAttributeNode("addresses"), NamedAttributeNode("phones") ])
@Unique
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "seqUserId")
    @SequenceGenerator(name = "seqUserId",
                       sequenceName = "seq_user_id",
                       initialValue = 1000000,
                       allocationSize = 10)
    val id: Long? = null,
    @Column(name = "first_name",
            length = 50,
            nullable = false)
    val firstName: String,
    @Column(name = "last_name",
            length = 50,
            nullable = false)
    val lastName: String,
    @Column(name = "email",
            length = 50,
            nullable = false)
    val email: String,
    @Column(name = "nickname",
            length = 30,
            nullable = false,
            updatable = false)
    val nickname: String,
    @Column(name = "password",
            length = 60,
            nullable = false)
    val password: String,
    @Column(name = "status",
            length = 30,
            nullable = false)
    @Enumerated(EnumType.STRING)
    val status: UserStatus,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id",
                nullable = false,
                foreignKey = ForeignKey(name = "fk_address_user"))
    val addresses: Set<AddressEntity>,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id",
                nullable = false,
                foreignKey = ForeignKey(name = "fk_phone_user"))
    val phones: Set<PhoneEntity>,

    @Column(name = "created_date",
            nullable = false,
            updatable = false)
    @CreatedDate
    var createdDate: Instant? = null,
    @Column(name = "modified_date",
            nullable = false)
    @LastModifiedDate
    var modifiedDate: Instant? = null
)