package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.FakeRequest
import play.api.test.Helpers.{GET, contentAsString, contentType, route, status, _}

class GreetingControllerSpec extends PlaySpec with GuiceOneAppPerTest {


  "GreetingController" should {

    "route to login url" in {
      routes.GreetingController.greet().toString mustEqual "/welcome"
    }

    "return welcome message json" in {
      val request = FakeRequest(GET, "/welcome")
      val greeting = route(app, request).get

      status(greeting) mustBe OK
      contentType(greeting) mustBe Some("application/json")
      contentAsString(greeting) mustBe """{"message":"Welcome"}"""
    }
  }

}
