import java.util.Stack;
/**
 * This is the starting point for the game and its
 * functions will be called by the engine every frame
 * update.
 */
public class ApplicationEntryPoint implements IEngineInterface {
    /**
     * Called by the engine at startup - initializes the game
     */
    private GameManager _gameManager;
    private GameManagerWrapper _gameManagerWrapper;
    public void init()
    {
        _gameManager = new GameManager();
        _gameManagerWrapper = new GameManagerWrapper(_gameManager);
    }

    /**
     * Perform a single game update. This should never result in an infinite
     * loop or else the engine will stop updating.
     *
     * @param deltaSeconds how many seconds have passed since the previous frame
     * @return true if the game should keep playing and false
     *              if the engine should shut down
     */
    @Override
    public boolean update(double deltaSeconds)
    {
        KeyInput keyManager = Singleton.engine.getInputManager();
        CharacterEntity ce = _gameManager.getInFocus();
        if(ce != null){
        	String lastKey = keyManager.getLastKey();
        	if(!lastKey.isEmpty()){
        		ce.setCharacter(lastKey.charAt(0));
        		ce.setBlurRadius(0);
        	}
        	
        }
        if(keyManager.isClicked())
        {

        }
        /*String lastKey = keyManager.getLastKey();
        if(!lastKey.isEmpty())
        {
            // Perform action.
        }*/

        return true;
    }

    /**
     * Called by the engine when the game is shutting down
     */
    public void shutdown()
    {
    }
}
