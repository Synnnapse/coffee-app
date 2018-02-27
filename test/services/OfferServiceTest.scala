package services

import models._
import org.joda.time.LocalDate
import org.mockito.Matchers.{eq => meq}
import org.mockito.Mockito._
import org.scalatest.Matchers._
import org.scalatest.WordSpec
import org.scalatest.mockito.MockitoSugar
import repositories.OfferRepository

class OfferServiceTest extends WordSpec {

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

      offerService.putOffer("1234", openOffer)
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

}
