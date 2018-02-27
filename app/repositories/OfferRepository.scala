package repositories

import models.{Offer, OfferOpen}

import scala.collection.mutable

class OfferRepository {
  private val offers = mutable.HashMap.empty[String, Offer]

  def listOpenOffers: Seq[Offer] = {
    offers.map( _._2 ).filter( _.status == OfferOpen ).toSeq
  }

  def putOffer(id: String, offer: Offer): Unit = {
    offers(id) = offer
  }

  def getOffer(id:String): Option[Offer] = {
    offers.get(id)
  }
  def clearOffers: Unit = {
    offers.clear()
  }
}
