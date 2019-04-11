package controllers

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{AnyContentAsText, Result}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.AnswerService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AnswerControllerSpec extends PlaySpec with MockitoSugar {

  "AnswerController" should {

    "route to query" in {
      routes.AnswerController.getAnswer().toString mustBe "/query"
    }

    "return bad request when request body is empty" in new Fixture {
      val response = controller.getAnswer.apply(FakeRequest(POST, "/query"))

      status(response) mustBe BAD_REQUEST
      contentType(response) mustBe Some("application/json")
      contentAsString(response) mustBe """{"error":"request body is missing"}"""
    }

    "return answer to given question" in new Fixture {
      val question = "How are you"
      val answer = "I am fine"
      when(mockAnswerService.getAnswer(question)) thenReturn Future(answer)
      val request: FakeRequest[AnyContentAsText] = FakeRequest(POST, "/query").withTextBody(question)

      val response: Future[Result] = controller.getAnswer()(request)

      status(response) mustBe OK
      contentType(response) mustBe Some("application/json")
      contentAsString(response) mustBe s"""{"answer":"$answer"}"""
    }
  }

  private trait Fixture {
    val mockAnswerService: AnswerService = mock[AnswerService]
    val controller: AnswerController = new AnswerController(stubControllerComponents(), mockAnswerService)
  }

}
