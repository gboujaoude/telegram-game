import java.util.HashMap;

/**
 * Allows the engine to create string->string variable mappings
 */
public class ConsoleVariables {
    private HashMap<String, String> _cvars = new HashMap<>();

    public void addCVar(String variable, String value)
    {
        _cvars.put(variable, value);
    }

    public String getVariable(String variable)
    {
        if (!contains(variable)) return "";
        return _cvars.get(variable);
    }

    public boolean contains(String variable)
    {
        return _cvars.containsKey(variable);
    }
}
