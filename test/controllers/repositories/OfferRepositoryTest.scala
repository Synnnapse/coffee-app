package controllers.repositories

import org.scalatest.WordSpec
import repositories.OfferRepository

class OfferRepositoryTest extends WordSpec {

  "Calling listOffers" should {
    "return all offers" in {
      val offerRepository = new OfferRepository()


    }
  }

  "Calling putOffer" should {
    "create an offer if it does not exist" in {

    }

    "update offer if it does already exist" in {

    }
  }

  "Calling getOffer" should {
    "return a single offer with the respective id" in {

    }
  }

  "Calling clearOffers" should {
    "remove all offers from the list" in {

    }
  }
}
