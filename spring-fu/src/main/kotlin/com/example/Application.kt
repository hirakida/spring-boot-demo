package com.example

import org.springframework.fu.kofu.web.server
import org.springframework.fu.kofu.webApplication
import org.springframework.web.reactive.function.BodyInserters


val app = webApplication {
    server {
        port = if (profiles.contains("test")) 8181 else 8080
        codecs {
            string()
            jackson()
        }
        router {
            GET("/") {
                ok().syncBody("Hello, Spring Fu!")
            }
            GET("/json") {
                ok().body(BodyInserters.fromObject(mapOf("message" to "Hello!")))
            }
        }
    }
}

fun main() {
    app.run()
}
