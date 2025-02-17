import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: TpgDispatchers)

enum class TpgDispatchers {
    IO,
    DEFAULT
}