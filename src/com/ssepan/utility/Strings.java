package com.ssepan.utility;

//import java.io.File;
import java.util.ArrayList;
import org.apache.commons.lang3.*;

/**
 *
 * @author ssepan
 */
public class Strings
{
    /**
     * 
     * @param list like new ArrayList(Of String)>() { { add(ROOT_PATH); add(IMAGE_PATH); } }
     * @param separator like "\"
     * @return
     */
    public static String join
    (
        ArrayList<String> list,
        String separator
    )
    {
        final String returnValue;

        returnValue = StringUtils.join(list, separator);
        
        return returnValue;
    }
    
}
