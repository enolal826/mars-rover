package rover.api

interface MarsRoverService {

    fun move(movement: Movement)
    fun getRoverPosition(): Position

}