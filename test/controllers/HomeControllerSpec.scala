package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._


class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "Loading the index page" should {

    "redirect to login when no user is logged in" in {
      val request = FakeRequest(GET, "/")
      val r = route(app, request).get

      status(r) mustBe SEE_OTHER
      redirectLocation(r) mustBe Some("/login")
    }
  }
}
