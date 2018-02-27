package controllers.repositories

import models._
import org.joda.time.format.DateTimeFormat
import org.scalatest.Matchers._
import org.scalatest.WordSpec
import repositories.OfferRepository

class OfferRepositoryTest extends WordSpec {

  val timeFormatter = DateTimeFormat.forPattern("HH:mm")

  val cancelledOffer = Offer(User("email@email.com", "pwd", "name"),
    Some("coffeeshop"),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(4), Some(OfferCancelled)
  )

  val openOffer = Offer(User("email@email.com", "pwd", "name"),
    Some("coffeeshop"),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(4), Some(OfferOpen)
  )

  val inProgressOffer = Offer(User("email@email.com", "pwd", "name"),
    Some("coffeeshop"),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(4), Some(OfferInProgress)
  )

  val completeOffer = Offer(User("email@email.com", "pwd", "name"),
    Some("coffeeshop"),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(4), Some(OfferComplete)
  )

  val workingOffer = Offer(User("email@email.com", "pwd", "currentuser"),
    Some("coffeeshop"),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(timeFormatter.parseLocalTime("12:30")),
    Some(4), None
  )

  "Calling putOffer" should {
    "complete successfully" in {
      val offerRepository = new OfferRepository()
      offerRepository.putOffer(cancelledOffer)
    }
  }

  "Calling listOpenOffers" should {
    "return all open offers" in {
      val offerRepository = new OfferRepository()

      offerRepository.putOffer(openOffer)
      offerRepository.putOffer(cancelledOffer)
      offerRepository.putOffer(completeOffer)

      offerRepository.listOpenOffers.size shouldBe 1
    }
  }

  "Calling getUsersWorkingOffer" should {
    "return an offer with the correct status and owned by the given user" in {
      val offerRepository = new OfferRepository()
      offerRepository.putOffer(workingOffer)

      offerRepository.getUsersWorkingOffer(User("email@email.com", "pwd", "currentuser")) shouldBe Some(workingOffer)
    }

    "return no matching offer for the given user when it does not exist" in {
      val offerRepository = new OfferRepository()
      offerRepository.putOffer(workingOffer)

      offerRepository.getUsersWorkingOffer(User("incorrect", "pwd", "incorrect")) shouldBe None

    }
  }

  "Calling getOffer" should {
    "return a single offer with the respective id when it exists" in {
      val offerRepository = new OfferRepository()

      offerRepository.putOffer(completeOffer)
      offerRepository.putOffer(openOffer)

      offerRepository.getOffer(openOffer.id) shouldBe Some(openOffer)
    }

    "return None when it does not exist" in {
      val offerRepository = new OfferRepository()

      offerRepository.putOffer(completeOffer)
      offerRepository.putOffer(openOffer)

      offerRepository.getOffer("123") shouldBe None
    }
  }

  "Calling clearOffers" should {
    "remove all offers from the list" in {
      val offerRepository = new OfferRepository()

      offerRepository.putOffer(openOffer)

      offerRepository.clearOffers

      offerRepository.getOffer("6587").size shouldBe 0
    }
  }
}
