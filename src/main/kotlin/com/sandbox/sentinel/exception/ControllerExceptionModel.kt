package com.sandbox.sentinel.exception

import com.sandbox.sentinel.data.formatting.annotation.TemporalFormat
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.ServletWebRequest
import java.time.Instant

data class ControllerExceptionModel(
    val status: Int,
    val error: String,
    val path: String,
    val causes: List<String>,
    @field:TemporalFormat
    val date: Instant
) {

    class Builder(private val httpStatus: HttpStatus,
                  private val webRequest: ServletWebRequest,
                  private val causes: List<String>) {

        fun build() = ControllerExceptionModel(
            status = this.httpStatus.value(),
            error = this.httpStatus.reasonPhrase,
            path = this.webRequest.request.requestURI,
            causes = this.causes,
            date = Instant.now()
        )
    }
}
