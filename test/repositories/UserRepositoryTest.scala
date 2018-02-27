package repositories

import models.User
import org.scalatest.WordSpec
import org.scalatest.Matchers._


class UserRepositoryTest extends WordSpec {

  "Calling UserRepository.putUser" should {

    "complete successfully" in {
      val userRepository = new UserRepository()
      userRepository.putUser("user@example.com", User("user@example.com", "passw0rd", "Test User"))
    }

  }

  "Calling UserRepository.findUser" should {

    "return None when there is no user found" in {
      val userRepository = new UserRepository()
      userRepository.findUser("user@example.com") shouldBe None
    }

    "return Some user when there is a user found" in {
      val user = User("user@example.com", "passw0rd", "Test User")
      val userRepository = new UserRepository()
      userRepository.putUser("user@example.com", user)
      userRepository.findUser("user@example.com") shouldBe Some(user)
    }

  }
}
