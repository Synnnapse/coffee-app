package services

import models.User
import org.scalatest.WordSpec
import repositories.UserRepository
import org.scalatest.Matchers._
import org.mockito.Matchers.{eq => meq, _}
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar

class UserServiceTest extends WordSpec {

  "Calling UserService.putUser" should {

    "complete successfully" in {
      val userService = new UserService(MockitoSugar.mock[UserRepository])
      userService.putUser("user@example.com", User("user@example.com", "passw0rd", "Test User"))
    }
  }

  "Calling UserService.findUser" should {

    "return None when there is no user found" in {
      val userRepository = MockitoSugar.mock[UserRepository]
      val userService = new UserService(userRepository)
      when(userRepository.findUser("user@example.com")) thenReturn None
      userService.findUser("user@example.com") shouldBe None
    }

    "return Some user when there is a user found" in {
      val userRepository = MockitoSugar.mock[UserRepository]
      val user = User("user@example.com", "passw0rd", "Test User")
      val userService = new UserService(userRepository)
      when(userRepository.findUser("user@example.com")) thenReturn Some(user)
      userService.findUser("user@example.com") shouldBe Some(user)
    }
  }

  "Calling UserService.findAndCheckPassword" should {

    "return None when the password doesn't match" in {
      val userRepository = MockitoSugar.mock[UserRepository]
      val user = User("user@example.com", "passw0rd", "Test User")
      val userService = new UserService(userRepository)
      when(userRepository.findUser("user@example.com")) thenReturn Some(user)
      userService.findAndCheckPassword("user@example.com", "wrongpassw0rd") shouldBe None
    }

    "return Some user when the passwords match" in {
      val userRepository = MockitoSugar.mock[UserRepository]
      val user = User("user@example.com", "passw0rd", "Test User")
      val userService = new UserService(userRepository)
      when(userRepository.findUser("user@example.com")) thenReturn Some(user)
      userService.findAndCheckPassword("user@example.com", "passw0rd") shouldBe Some(user)
    }
  }
}
