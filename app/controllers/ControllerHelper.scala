package controllers

import models.User
import play.api.mvc.{Action, AnyContent, Request, Result}
import services.UserService

import scala.concurrent.Future
import play.api.mvc.Results._

trait ControllerHelper {

  def userService: UserService

  def AuthenticatedAction(block: User => Request[AnyContent] => Future[Result]) = {
    Action.async { implicit request =>

      request.session.get("email") match {

        case None => Future.successful(Redirect(routes.HomeController.login()))
        case Some(emailAddress) =>

          val user = userService.findUser(emailAddress)
          user match {
            case None => Future.successful(Redirect(routes.HomeController.login()))
            case Some(user) => block(user)(request)
          }
      }
    }
  }

}
