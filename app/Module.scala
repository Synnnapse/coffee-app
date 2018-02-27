import com.google.inject.AbstractModule
import services._


class Module extends AbstractModule {

  override def configure() = {
    // We bind the implementation to the interface (trait) as an eager singleton,
    // which means it is bound immediately when the application starts.
    bind(classOf[BaseStartup]).to(classOf[Startup]).asEagerSingleton()
  }
}