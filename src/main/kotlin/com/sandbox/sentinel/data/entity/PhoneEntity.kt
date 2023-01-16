package com.sandbox.sentinel.data.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "phones")
class PhoneEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "seqPhoneId")
    @SequenceGenerator(name = "seqPhoneId",
                       sequenceName = "seq_phone_id",
                       initialValue = 1000000,
                       allocationSize = 10)
    val id: Long? = null,
    @Column(name = "country_calling_code",
            length = 6,
            nullable = false)
    val countryCallingCode: String,
    @Column(name = "phone_number",
            length = 30,
            nullable = false)
    val phoneNumber: String,

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