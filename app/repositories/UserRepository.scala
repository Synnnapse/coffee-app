package repositories

import models.User
import scala.collection.mutable


class UserRepository {

  private val records = mutable.HashMap.empty[String, User]

  def createUser(user: User) = ???
  def findUser(email: String) = ???
}
