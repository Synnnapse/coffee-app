package services

import javax.inject.{Inject, Singleton}

import models.Offer
import repositories.OfferRepository

@Singleton
class OfferService @Inject() (val offerRepository: OfferRepository) {

  def listOpenOffers: Seq[Offer] = {
    offerRepository.listOpenOffers
  }

  def putOffer(id: String, offer: Offer): Unit = {
    offerRepository.putOffer(id, offer)
  }

  def getOffer(id: String): Option[Offer] = {
    offerRepository.getOffer(id)
  }

  def clearOffers: Unit = {
    offerRepository.clearOffers
  }
}
