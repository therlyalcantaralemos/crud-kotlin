package br.com.crudkotlin.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*


@Component
class JWTUtil {

    private var secret: String = "secret"
    private val expiration: Long = 60000

    fun generateToken(username: String): String {
        val now = Instant.now()
        return JWT.create()//HS512
                .withSubject(username)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date(System.currentTimeMillis() + expiration))
                //.withArrayClaim("roles", roles.toTypedArray())
                .sign(Algorithm.HMAC512(secret))
    }

    fun isTokenValid(token: String): Boolean {
        val claims = getClaimsToken(token)
        if (claims != null) {
            val username = claims.subject
            val expirationDate = claims.expiresAt
            val now = Date(System.currentTimeMillis())
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true
            }
        }
        return false
    }

    private fun getClaimsToken(token: String): DecodedJWT? {
        return try {
            val algorithm = Algorithm.HMAC512(secret)

            val verifier: JWTVerifier = JWT.require(algorithm)
                    .build()

            verifier.verify(token.replace("Bearer ", ""))

        } catch (e: Exception) {
            null
        }
    }

    fun getUserName(token: String): String? {
        val claims = getClaimsToken(token)
        return claims?.subject
    }

}