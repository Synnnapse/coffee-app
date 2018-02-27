package services

import javax.inject.{Inject, Singleton}
import models.User
import repositories.UserRepository

@Singleton
class UserService @Inject() (val userRepository: UserRepository) {

  def putUser(email:String, user: User): Unit = {
    userRepository.putUser(email, user)
  }

  def findUser(email: String): Option[User] = {
    userRepository.findUser(email)
  }

  def findAndCheckPassword(email: String, password: String): Option[User] = {
    userRepository.findUser(email).filter(_.password == password)
  }
}
