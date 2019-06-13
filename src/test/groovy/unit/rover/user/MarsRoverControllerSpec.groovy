package rover.user

import rover.api.CardinalPoint
import rover.api.Movement
import rover.api.Point
import rover.api.Position
import rover.app.MarsRoverFactory
import rover.app.MarsRoverServiceImpl
import rover.app.exception.CellNotFreeException
import rover.app.map.Map
import spock.lang.Specification

class MarsRoverControllerSpec extends Specification {

    private def POSITION = new Position(new Point(1,1), CardinalPoint.EAST)
    private def MAP = new Map(5,5)
    private def MARS_ROVER_SETUP = new MarsRoverSetup(POSITION, MAP)
    private def MOVEMENT_LIST = [Movement.FORWARD, Movement.FORWARD, Movement.LEFT, Movement.BACKWARD]

    private MarsRoverDataReader marsRoverDataReader = Mock()
    private MarsRoverFactory marsRoverFactory = Mock()
    private MarsRoverServiceImpl marsRoverService = Mock()

    private MarsRoverController marsRoverController = new MarsRoverController(marsRoverDataReader, marsRoverFactory)

    def "execute - happy case"() {
        given:
            marsRoverDataReader.askForSetup() >> MARS_ROVER_SETUP
            marsRoverDataReader.askForMovements() >> MOVEMENT_LIST
            marsRoverFactory.createMarsRover(POSITION, MAP) >> marsRoverService
        when:
            marsRoverController.execute()
        then:
             MOVEMENT_LIST.size() * marsRoverService.move(_)
    }

    def "execute - obstacle found"() {
        given:
            marsRoverDataReader.askForSetup() >> MARS_ROVER_SETUP
            marsRoverDataReader.askForMovements() >> MOVEMENT_LIST
            marsRoverFactory.createMarsRover(POSITION, MAP) >> marsRoverService
            marsRoverService.move(_) >> { throw new CellNotFreeException() }
        when:
            marsRoverController.execute()
        then:
            1 * marsRoverService.getRoverPosition()
    }
}
