package controllers

import javax.inject.Inject

import models.SelectOutletForm
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.{OfferService, UserService}

import scala.concurrent.Future

class OfferController @Inject() (
  val cc: ControllerComponents,
  override val messagesApi: MessagesApi,
  val userService: UserService,
  val offerService: OfferService
) extends AbstractController(cc) with ControllerHelper with I18nSupport {

  val selectOutletForm = Form(
    mapping(
      "outletName" -> nonEmptyText
    )(SelectOutletForm.apply)(SelectOutletForm.unapply)
  )

  def selectOutlet() = AuthenticatedAction { implicit user => implicit request =>
    Future.successful(
      Ok(views.html.selectOutlet(selectOutletForm))
    )
  }

  def processSelectOutlet() = AuthenticatedAction { implicit user => implicit request =>
    Future.successful(Ok(""))
  }

//  def processSelectOutlet() = AuthenticatedAction { implicit user => request =>
//    selectOutletForm.bindFromRequest.fold(
//      formWithErrors => {
//        BadRequest(views.html.selectOutlet(formWithErrors))
//      },
//      selectOutletForm => {
//        Future.successful(Ok(""))
//      }
//    )
//  }

  def selectQuantity() = ???
  def processSelectQuantity() = ???

  def selectTimes() = ???
  def processSelectTimes() = ???
  
  def reviewOffer() = ???

  def listOpenOffers() = AuthenticatedAction { implicit user => request =>
    Future.successful {
      val openOffers = offerService.listOpenOffers
      Ok(views.html.openOffers(openOffers))
    }
  }

  def offerDetail() = ???
}
