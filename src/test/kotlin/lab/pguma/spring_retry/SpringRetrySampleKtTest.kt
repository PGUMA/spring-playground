package lab.pguma.spring_retry

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.RuntimeException

class SpringRetrySampleKtTest : FunSpec({
    context("retrySimpleImperativeStyleSample") {

        test("リトライ回数を超えた場合にRuntimeExceptionが早出される") {
            val exception = shouldThrow<RuntimeException>{
                retrySimpleImperativeStyleSample { throw IllegalStateException() }
            }
            exception.message shouldBe "リトライ回数上限を超えました。"
        }

        test("リトライ回数を超えない場合に戻り値が返る") {
            val actual = retrySimpleImperativeStyleSample { 1 + 1 }
            actual shouldBe 2
        }
    }
})
