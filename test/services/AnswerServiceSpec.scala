package services

import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.PlaySpec

class AnswerServiceSpec extends PlaySpec with ScalaFutures {

  "AnswerService" should {

    val answerService = new AnswerService()

    "return answer to given question" in {
      val answer = answerService.getAnswer("where are you")
      whenReady(answer) { ans =>
        ans mustBe "I am in Pune"
      }
    }

    "return answer as 'Jaydip' for question 'What is your name'" in {
      val answer = answerService.getAnswer("what is your name")
      whenReady(answer) { ans =>
        ans mustBe "Jaydip"
      }
    }

    "return default answer for unknown question" in {
      val answer = answerService.getAnswer("how are your")
      whenReady(answer) { ans =>
        ans mustBe "Sorry, I don’t understand"
      }
    }
  }
}
