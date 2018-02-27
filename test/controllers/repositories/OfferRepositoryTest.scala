package controllers.repositories

import models._
import org.joda.time.LocalDate
import org.scalatest.WordSpec
import repositories.OfferRepository
import org.scalatest.Matchers._

class OfferRepositoryTest extends WordSpec {

  val cancelledOffer = Offer(User("email@email.com", "pwd", "name"),
      "coffeeshop",
      new LocalDate(2017, 10, 1),
      new LocalDate(2017,10,1),
      4, OfferCancelled
    )

  val openOffer = Offer(User("email@email.com", "pwd", "name"),
    "coffeeshop",
    new LocalDate(2017, 10, 1),
    new LocalDate(2017,10,1),
    4, OfferOpen
  )

  val inProgressOffer = Offer(User("email@email.com", "pwd", "name"),
    "coffeeshop",
    new LocalDate(2017, 10, 1),
    new LocalDate(2017,10,1),
    4, OfferInProgress
  )

  val completeOffer = Offer(User("email@email.com", "pwd", "name"),
    "coffeeshop",
    new LocalDate(2017, 10, 1),
    new LocalDate(2017,10,1),
    4, OfferComplete
  )

  "Calling putOffer" should {
    "complete successfully" in {
      val offerRepository = new OfferRepository()
      offerRepository.putOffer("12345678",
        cancelledOffer)
    }
  }

  "Calling listOpenOffers" should {
    "return all open offers" in {
      val offerRepository = new OfferRepository()

      offerRepository.putOffer("3456", openOffer)
      offerRepository.putOffer("523675237", openOffer)
      offerRepository.putOffer("7897", cancelledOffer)
      offerRepository.putOffer("7897", completeOffer)

      offerRepository.listOpenOffers.size shouldBe 2
    }
  }



  "Calling getOffer" should {
    "return a single offer with the respective id when it exists" in {
      val offerRepository = new OfferRepository()

      offerRepository.putOffer("12", completeOffer)
      offerRepository.putOffer("123", openOffer)

      offerRepository.getOffer("123") shouldBe Some(openOffer)
    }

    "return None when it does not exist" in {
      val offerRepository = new OfferRepository()

      offerRepository.putOffer("12", completeOffer)
      offerRepository.putOffer("1234", openOffer)

      offerRepository.getOffer("123") shouldBe None
    }
  }

  "Calling clearOffers" should {
    "remove all offers from the list" in {
      val offerRepository = new OfferRepository()

      offerRepository.putOffer("6587", openOffer)

      offerRepository.clearOffers

      offerRepository.getOffer("6587").size shouldBe 0


    }
  }
}
