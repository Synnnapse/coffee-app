package services

import javax.inject.{Inject, Singleton}

import models.{Offer, OfferInProgress, User}
import org.joda.time.LocalTime
import repositories.OfferRepository

@Singleton
class OfferService @Inject() (val offerRepository: OfferRepository) {

  def listOpenOffers: Seq[Offer] = {
    offerRepository.listOpenOffers
  }

  def putOffer(offer: Offer): Unit = {
    offerRepository.putOffer(offer)
  }

  def getOffer(id: String): Option[Offer] = {
    offerRepository.getOffer(id)
  }

  def clearOffers: Unit = {
    offerRepository.clearOffers
  }

  def getOrCreateUsersWorkingOffer(user: User): Offer = {
    offerRepository.getUsersWorkingOffer(user) match {
      case Some(offer) => offer
      case None => {
        val newOfferToInsert = Offer(user, None, None, None,None, None)
        offerRepository.putOffer(newOfferToInsert)
        newOfferToInsert
      }
    }
  }

  def updateOutlet(id: String, outlet: String): Unit = {

    offerRepository.getOffer(id) map {
      offer =>
        offerRepository.putOffer(offer.copy(outlet = Some(outlet)))
    }

  }

  def updateQuantity(id: String, quantity: Int): Unit = {

    offerRepository.getOffer(id) map {
      offer =>
        offerRepository.putOffer(offer.copy(slotsAvailable = Some(quantity)))
    }
  }

  def updateTimes(id: String, timeIn: LocalTime, timeOut: LocalTime): Unit = {

    offerRepository.getOffer(id) map {
      offer =>
        offerRepository.putOffer(offer.copy(timeIn = Some(timeIn), timeOut = Some(timeOut)))
    }
  }

  def markOfferAsOpen(id:String): Unit = ??? // end of create offer journey, now people can go and add their orders

  def markOfferInProgress(id: String): Unit = {
    offerRepository.getOffer(id) map {
      offer =>
        offerRepository.putOffer(offer.copy(status = Some(OfferInProgress)))
    }
  }
}
