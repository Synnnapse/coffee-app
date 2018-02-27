import javax.inject._
import models.User
import play.api.inject.ApplicationLifecycle
import repositories.UserRepository

import scala.concurrent.Future

trait BaseStartup

@Singleton
class Startup @Inject() (userRepository: UserRepository, appLifecycle: ApplicationLifecycle) extends BaseStartup {

  appLifecycle.addStopHook { () =>
    println("Goodbye!")
    Future.successful(())
  }

  userRepository.putUser("user1@example.com", User("user1@example.com", "passw0rd", "User One"))
  userRepository.putUser("user2@example.com", User("user2@example.com", "passw0rd", "User Two"))
  userRepository.putUser("user3@example.com", User("user3@example.com", "passw0rd", "User Three"))

}