package com.codecool.repvault.domain.exceptions

import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
class SessionException(message: String?, val status: HttpStatus?) : RuntimeException(message)
