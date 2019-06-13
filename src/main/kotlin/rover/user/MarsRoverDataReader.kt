package rover.user

import rover.api.CardinalPoint
import rover.api.Movement
import rover.api.Point
import rover.api.Position
import rover.app.map.Map
import rover.app.map.MapCell

/*
    This class asks the user for data. In a real use case, that might done through reading from standard input with
    a fixed format
*/
open class MarsRoverDataReader {

    open fun askForSetup(): MarsRoverSetup {
        val position = Position(Point(1,1), CardinalPoint.EAST)
        val map = Map(10, 10)

        map.setCellValue(Point(2, 1), MapCell.OBSTACLE)

        return MarsRoverSetup(position, map)
    }

    open fun askForMovements(): List<Movement> {
        return listOf(Movement.FORWARD, Movement.FORWARD)
    }

}