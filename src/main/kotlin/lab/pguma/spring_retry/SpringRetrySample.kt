package lab.pguma.spring_retry

import org.springframework.retry.RetryContext
import org.springframework.retry.support.RetryTemplate
import java.lang.IllegalArgumentException
import java.lang.RuntimeException


// https://github.com/spring-projects/spring-retry

private val template: RetryTemplate = RetryTemplate.builder()
    .maxAttempts(3)
    .retryOn(IllegalStateException::class.java)
    .build()

fun <T> retrySimpleImperativeStyleSample(retryCallBack: () -> T): T {
    return runCatching {
        template.execute<T, IllegalArgumentException> { retryCallBack.invoke() }
    }
        .onFailure { throw RuntimeException("リトライ回数上限を超えました。") }
        .getOrThrow()
}