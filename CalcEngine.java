/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{

    // The current value (to be) shown in the display.
    private String displayValue;
    private Postfix pfx;
    

    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        pfx = new Postfix();
    	clear();
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public String getDisplayValue()
    {
        return displayValue;
    }
    public void setDisplayValue(String s)
    {
        displayValue = s;
    }


    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {
        displayValue = "0";
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "David J. Barnes and Michael Kolling";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }

    /**
     * Combine leftOperand, lastOperator, and the
     * current display value.
     * The result becomes both the leftOperand and
     * the new display value.
     */
    public void calculateResult()
    {
    	Integer value = new Integer(pfx.evaluate(pfx.infixToPostfix(displayValue)));
    	displayValue = value.toString();
    }
}
