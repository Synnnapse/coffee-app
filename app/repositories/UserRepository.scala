package repositories

import javax.inject.Singleton
import models.User

import scala.collection.mutable

@Singleton
class UserRepository {

  val records = mutable.HashMap.empty[String, User]

  def putUser(email:String, user: User): Unit = {
    records(user.email) = user
  }

  def findUser(email: String): Option[User] = {
    records.get(email)
  }
}
