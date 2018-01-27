/**
 * Inherited by all modules managed by the engine
 */
public interface IEngineInterface {
    /**
     * Perform a single game update. This should never result in an infinite
     * loop or else the engine will stop updating.
     *
     * @param deltaSeconds how many seconds have passed since the previous frame
     * @return true if the game should keep playing and false
     *              if the engine should shut down
     */
    boolean update(double deltaSeconds);
}
