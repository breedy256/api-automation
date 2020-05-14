package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object key
     
    /**
     * <p></p>
     */
    public static Object token
     
    /**
     * <p></p>
     */
    public static Object userName
     
    /**
     * <p></p>
     */
    public static Object boardID
     
    /**
     * <p></p>
     */
    public static Object doneListID
     
    /**
     * <p></p>
     */
    public static Object todoListID
     
    /**
     * <p></p>
     */
    public static Object cardID
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            key = selectedVariables['key']
            token = selectedVariables['token']
            userName = selectedVariables['userName']
            boardID = selectedVariables['boardID']
            doneListID = selectedVariables['doneListID']
            todoListID = selectedVariables['todoListID']
            cardID = selectedVariables['cardID']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
