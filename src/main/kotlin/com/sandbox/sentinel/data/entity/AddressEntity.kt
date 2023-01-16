package com.sandbox.sentinel.data.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "addresses")
class AddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "seqAddressId")
    @SequenceGenerator(name = "seqAddressId",
                       sequenceName = "seq_Address_id",
                       initialValue = 1000000,
                       allocationSize = 10)
    val id: Long? = null,
    @Column(name = "alpha_country_code",
            length = 3,
            nullable = false)
    val alphaCountryCode: String,
    @Column(name = "city",
            length = 50,
            nullable = false)
    val city: String,
    @Column(name = "street",
            length = 100,
            nullable = false)
    val street: String,

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