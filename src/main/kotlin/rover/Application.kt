package rover

import rover.app.MarsRoverFactory
import rover.user.MarsRoverController
import rover.user.MarsRoverDataReader

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        val marsController = MarsRoverController(MarsRoverDataReader(), MarsRoverFactory())
        marsController.execute()
    }
}