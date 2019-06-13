package rover.app

import rover.api.CardinalPoint
import rover.api.Movement
import rover.api.Point
import rover.api.Position
import rover.app.map.Locator
import rover.app.map.Map
import rover.app.marsrover.MarsRover
import spock.lang.Specification

class MarsRoverServiceImplIntegrationSpec extends Specification{

    private STARTING_POSITION = new Position(new Point(2,3), CardinalPoint.SOUTH)
    private ENDING_POSITION = new Position(new Point(2,3), CardinalPoint.EAST)

    Map map = new Map(5,5 )

    Locator locator = new Locator(map)
    MarsRover mars = new MarsRover(STARTING_POSITION)

    private MarsRoverServiceImpl marsRoverService = new MarsRoverServiceImpl(locator, mars)

    def "getRoverPosition returns initial position"() {
        when:
            def result = marsRoverService.getMarsRoverPosition()
        then:
            result == STARTING_POSITION

    }

    def "after move is executed, rover position changes"() {
        when:
            marsRoverService.move(Movement.LEFT)
        then:
            marsRoverService.getMarsRoverPosition() == ENDING_POSITION
    }

}
