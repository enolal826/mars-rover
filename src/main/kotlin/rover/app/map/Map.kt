package rover.app.map

import rover.api.Point
import rover.app.exception.PointNotValidException

open class Map {

    private val map: Array<Array<MapCell>>

    constructor(rows: Int, columns: Int) {
        this.map = Array(rows) {
            Array(columns, {
                MapCell.FREE
            })
        }
    }

    constructor(map: Array<Array<MapCell>>) {
        this.map = map
    }

    fun setCellValue(point: Point, cell: MapCell) {
        validatePoint(point)
        map[point.y][point.x] = cell
    }

    open fun isCellFree(point: Point): Boolean {
        validatePoint(point)
        return map[point.y][point.x] == MapCell.FREE
    }

    open fun numberOfRows(): Int {
        return map.size
    }

    open fun numberOfColumns(): Int {
        return map[0].size
    }

    private fun validatePoint(point: Point) {
        if (point.y < 0 || point.y >= numberOfRows() || point.x < 0 || point.x >= numberOfColumns()) {
            throw PointNotValidException()
        }
    }

}