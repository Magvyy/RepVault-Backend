package com.codecool.repvault.controllers.utils

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ResponseUtil {
    fun <T : Any> wrapEntity(entity: T?): ResponseEntity<T> {
        val headers = HttpHeaders()
        headers.add("Content-Type", "application/json; charset=utf-8")
        return ResponseEntity<T>(entity, headers, HttpStatus.OK)
    }

    fun <T : Any> wrapEntity(entity: T?, status: HttpStatus): ResponseEntity<T> {
        val headers = HttpHeaders()
        headers.add("Content-Type", "application/json; charset=utf-8")
        return ResponseEntity<T>(entity, headers, status)
    }
}
