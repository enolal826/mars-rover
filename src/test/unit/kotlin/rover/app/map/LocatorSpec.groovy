package rover.app.map

import rover.api.CardinalPoint
import rover.api.Movement
import rover.api.Point
import rover.api.Position
import rover.app.exception.CellNotFreeException
import spock.lang.Specification
import spock.lang.Unroll

class LocatorSpec extends Specification {

    private Map map = Mock(Map)
    private Locator locator = new Locator(map)

    private static MOVEMENT_1 = Movement.LEFT
    private static STARTING_POSITION_1 = new Position(new Point(2,3), CardinalPoint.SOUTH)
    private static ENDING_POSITION_1 = new Position(new Point(2,3), CardinalPoint.EAST)

    private static MOVEMENT_2 = Movement.RIGHT
    private static STARTING_POSITION_2 = new Position(new Point(1,4), CardinalPoint.WEST)
    private static ENDING_POSITION_2 = new Position(new Point(1,4), CardinalPoint.NORTH)

    private static MOVEMENT_3 = Movement.FORWARD
    private static STARTING_POSITION_3 = new Position(new Point(1,0), CardinalPoint.NORTH)
    private static ENDING_POSITION_3 = new Position(new Point(1,4), CardinalPoint.NORTH)

    private static MOVEMENT_4 = Movement.BACKWARD
    private static STARTING_POSITION_4 = new Position(new Point(0,3), CardinalPoint.NORTH)
    private static ENDING_POSITION_4 = new Position(new Point(0,4), CardinalPoint.NORTH)

    @Unroll
    def "move - happy case with #desc movement"() {
        given:
            map.isCellFree(endingPosition.point) >> true
            map.numberOfColumns() >> 5
            map.numberOfRows() >> 5
        when:
            locator.move(movement, startingPosition)
        then:
            startingPosition == endingPosition
        where:
            desc       | movement    | startingPosition     | endingPosition
            "left"     | MOVEMENT_1  | STARTING_POSITION_1  | ENDING_POSITION_1
            "right"    | MOVEMENT_2  | STARTING_POSITION_2  | ENDING_POSITION_2
            "foward"   | MOVEMENT_3  | STARTING_POSITION_3  | ENDING_POSITION_3
            "backward" | MOVEMENT_4  | STARTING_POSITION_4  | ENDING_POSITION_4
    }

    def "move - obstacle found"() {
        given:
            map.isCellFree(ENDING_POSITION_4.point) >> false
            map.numberOfColumns() >> 5
            map.numberOfRows() >> 5
        when:
            locator.move(MOVEMENT_4, STARTING_POSITION_4)
        then:
            thrown(CellNotFreeException)
    }

}
