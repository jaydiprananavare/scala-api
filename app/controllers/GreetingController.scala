package controllers

import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}


class GreetingController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private implicit val ec: ExecutionContext = cc.executionContext

  def greet: Action[AnyContent] = Action.async {
    Future(Ok(Json.parse("""{"message": "Welcome" }""")))
  }

}
