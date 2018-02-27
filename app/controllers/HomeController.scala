package controllers

import javax.inject._
import models.LoginForm
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.UserService

import scala.concurrent.Future

@Singleton
class HomeController @Inject() (
  val cc: ControllerComponents,
  override val messagesApi: MessagesApi,
  val userService: UserService
) extends AbstractController(cc) with I18nSupport with ControllerHelper {

  val loginForm = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText
    )(LoginForm.apply)(LoginForm.unapply)
  )

  def login() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login(loginForm))
  }

  def processLogin() = Action { implicit request: Request[AnyContent] =>

    loginForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.login(formWithErrors))
      },
      loginForm => {
        Redirect(routes.HomeController.index()).addingToSession("email" -> loginForm.email)
      }
    )
  }

  def signout() = AuthenticatedAction { implicit user => request =>
    Future.successful {
      Redirect(routes.HomeController.login()).withNewSession
    }
  }

  def index() = AuthenticatedAction { implicit user => request =>
    Future.successful {
      Ok(views.html.index())
    }
  }
}
