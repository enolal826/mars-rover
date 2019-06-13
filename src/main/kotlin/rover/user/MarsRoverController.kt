package rover.user

import rover.app.MarsRoverFactory
import rover.app.exception.CellNotFreeException

class MarsRoverController(private val marsRoverDataReader: MarsRoverDataReader,
                          private val marsRoverFactory: MarsRoverFactory) {

    open fun execute() {
        val setup = marsRoverDataReader.askForSetup()
        val movements = marsRoverDataReader.askForMovements()

        val service = marsRoverFactory.createMarsRover(setup.position, setup.map)

        try {
            movements.forEach {
                service.move(it)
            }
        } catch (e: CellNotFreeException) {
            println(message = "An obstacle has been found. The rover is now at " + service.getRoverPosition())
        }
    }

}