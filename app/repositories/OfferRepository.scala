package repositories

import models._

import scala.collection.mutable

class OfferRepository {
  private val offers = mutable.HashMap.empty[String, Offer]

  def listOpenOffers: Seq[Offer] = {
    offers.map( _._2 ).filter( _.status == Some(OfferOpen) ).toSeq
  }

  def putOffer(offer: Offer): Unit = {
    offers(offer.id) = offer
  }

  def getOffer(id:String): Option[Offer] = {
    offers.get(id)
  }
  def clearOffers: Unit = {
    offers.clear()
  }

  def getUsersWorkingOffer(user: User): Option[Offer] = {
    offers.find(x =>
      x._2.owner == user && (x._2.status.contains(OfferOpen) || x._2.status.isEmpty)) map {_._2}
  }
}
