package rover.app.map

import rover.api.Point
import rover.app.exception.PointNotValidException
import spock.lang.Specification

class MapIntegrationSpec extends Specification {

    private NUMBER_OF_ROWS = 5
    private NUMBER_OF_COLUMNS = 5

    private POINT_VALID = new Point(2,4)
    private POINT_NOT_VALID = new Point(7,10)

    private Map map = new Map(NUMBER_OF_ROWS,NUMBER_OF_COLUMNS)

    def "numberOfRows"() {
        when:
            def result = map.numberOfRows()
        then:
            result == NUMBER_OF_ROWS
    }

    def "numberOfColumns"() {
        when:
            def result = map.numberOfColumns()
        then:
            result == NUMBER_OF_COLUMNS
    }

    def "isCellFree"() {
        when:
            def result = map.isCellFree(POINT_VALID)
        then:
            result == true
    }

    def "setCellValue - happy case"() {
        when:
            map.setCellValue(POINT_VALID, MapCell.OBSTACLE)
        then:
            map.isCellFree(POINT_VALID) == false
    }

    def "setCellValue - invalid point"() {
        when:
            map.setCellValue(POINT_NOT_VALID, MapCell.OBSTACLE)
        then:
            thrown(PointNotValidException)
    }

}
