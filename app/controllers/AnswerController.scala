package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.AnswerService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AnswerController @Inject()(cc: ControllerComponents, answerService: AnswerService) extends AbstractController(cc) {

  private implicit val ec: ExecutionContext = cc.executionContext

  def getAnswer: Action[AnyContent] = Action.async {
    request =>
      val mayBeBody = request.body.asText
      mayBeBody match {
        case Some(question) => answerService.getAnswer(question).map(ans => Ok(Json.parse(s"""{"answer": "$ans"}""")))
        case None => Future(BadRequest(Json.parse("""{ "error" :"request body is missing"}""")))
      }
  }
}
