package repositories

import models.Offer

import scala.collection.mutable

class OfferRepository {
  private val offers = mutable.HashMap.empty[String, Offer]

  def listOffers = ???
  def putOffer(id: String, offer: Offer) = ???
  def getOffer(id:String) = ???
  def clearOffers = ???



}
