package services

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AnswerService {
  def getAnswer(question: String): Future[String] = Future {
    question.toLowerCase.trim match {
      case "what is your name" => "Jaydip"
      case "where are you" => "I am in Pune"
      case _ => "Sorry, I don’t understand"
    }
  }
}
