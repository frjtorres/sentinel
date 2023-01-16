package com.sandbox.sentinel.controller

import com.sandbox.sentinel.data.model.OnCreateUserModel
import com.sandbox.sentinel.data.model.OnSearchUserModel
import com.sandbox.sentinel.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{nickname}")
    fun search(@PathVariable nickname: String): ResponseEntity<OnSearchUserModel> {
        val foundUser = userService.searchBy(nickname)
        return ResponseEntity.status(HttpStatus.OK).body(foundUser)
    }

    @PostMapping
    fun create(@Valid @RequestBody onCreateUserModel: OnCreateUserModel): ResponseEntity<OnSearchUserModel> {
        val createdUser = userService.createWith(onCreateUserModel)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }

    @DeleteMapping("/{nickname}")
    fun delete(@PathVariable nickname: String): ResponseEntity<Void> {
        userService.deleteBy(nickname)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
