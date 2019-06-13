package rover.app

import rover.api.Position
import rover.app.map.Locator
import rover.app.map.Map
import rover.app.marsrover.MarsRover

open class MarsRoverFactory {

    open fun createMarsRover(position: Position, map: Map): MarsRoverServiceImpl {
        return MarsRoverServiceImpl(
                locator = Locator(map),
                marsRover = MarsRover(position)
            )
    }
}