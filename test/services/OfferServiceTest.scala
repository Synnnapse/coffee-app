package services

import models._
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import org.mockito.Matchers.{eq => meq}
import org.mockito.Mockito._
import org.scalatest.Matchers._
import org.scalatest.WordSpec
import org.scalatest.mockito.MockitoSugar
import repositories.OfferRepository

class OfferServiceTest extends WordSpec {

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

 "Calling listOpenOffers" should {
   "return a list of open offers" in {
     val offerRepository = MockitoSugar.mock[OfferRepository]
     val offerService = new OfferService(offerRepository)
     when(offerRepository.listOpenOffers) thenReturn List(openOffer)

     offerService.listOpenOffers.size shouldBe 1
   }
 }

  "Calling putOffer" should {
    "successfully put offer" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)

      offerService.putOffer(openOffer)
    }
  }

  "Calling getOffer" should {
    "return an offer if offer with id exists" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)
      when(offerRepository.getOffer("1234")) thenReturn Some(openOffer)

      offerService.getOffer("1234") shouldBe Some(openOffer)
    }

    "return None if no offer with id exists" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)
      when(offerRepository.getOffer("1234")) thenReturn None

      offerService.getOffer("1234") shouldBe None
    }
  }

  "Calling clearOffers" should {
    "clear all offers" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)

      offerService.clearOffers
    }
  }

  "Calling updateOutlet" should {
    "update the offer with the selected outlet" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)
      when(offerRepository.getOffer(openOffer.id)) thenReturn Some(openOffer)

      offerService.updateOutlet(openOffer.id, "costa")
    }
  }

  "Calling updateQuantity" should {
    "update the offer with the selected quantity" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)
      when(offerRepository.getOffer(openOffer.id)) thenReturn Some(openOffer)

      offerService.updateQuantity(openOffer.id, 4)
    }
  }

  "Calling updateTimes" should {
    "update the times for an offer" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)
      when(offerRepository.getOffer(openOffer.id)) thenReturn Some(openOffer)

      offerService.updateTimes(openOffer.id, new LocalTime(timeFormatter.parseLocalTime("12:30")), new LocalTime(timeFormatter.parseLocalTime("12:30")))
    }
  }

  "calling confirmOffer" should {
    "confirm an offer by changing the status in progress" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)
      when(offerRepository.getOffer(openOffer.id)) thenReturn Some(openOffer)


      offerService.markOfferInProgress(openOffer.id)
    }
  }

  "Calling getUsersWorkingOffer" should {

    val currentUser = User("email@email.com", "pwd", "currentuser")

    "return the offer with no status or status open for a given user" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)
      when(offerRepository.getUsersWorkingOffer(currentUser)) thenReturn Some(workingOffer)

      offerService.getOrCreateUsersWorkingOffer(currentUser) shouldBe workingOffer
    }

    "return a new offer when no matching users offer and create and insert a new offer" in {
      val offerRepository = MockitoSugar.mock[OfferRepository]
      val offerService = new OfferService(offerRepository)
      when(offerRepository.getUsersWorkingOffer(currentUser)) thenReturn None

      offerService.getOrCreateUsersWorkingOffer(currentUser).status shouldBe None   // TODO - is this correct or needed?

    }
  }

}
