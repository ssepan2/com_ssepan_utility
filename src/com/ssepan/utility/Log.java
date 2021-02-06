package com.ssepan.utility;


import java.util.Date;
import java.util.logging.*;
import org.apache.commons.lang3.StringUtils;
import java.io.*;

/**
 *
 * @author ssepan
 */
public class Log
{
    private final static /*const*/ String LOG_FILENAME = "Application.log";
    

    private static Boolean _UseEventLog = true; //default(String); //System.Windows.Forms.Application.CommonAppDataPath;
    public static Boolean getUseEventLog() 
    {
        return _UseEventLog;
    }
    public static void setUseEventLog(Boolean useEventLog) 
    {
        _UseEventLog = useEventLog;
    }

    private static String _LogPath = ""; //default(String); //System.Windows.Forms.Application.CommonAppDataPath;
    public static String getLogPath() 
    {
        return _LogPath;
    }
    public static void setLogPath(String logPath) 
    {
        _LogPath = logPath;
    }


    /**
     *
     * @param e
     * @param level
     */
    public static void write
    (
        Exception e,
        Level level
    )
    {
        //Log
        StackTraceElement elements[];
        
        elements = e.getStackTrace();
        for (Integer i = 0; i < elements.length; i++)
        {
            write
            (
                String.format
                (
                    "%s, %s, %s, %s,'%s'",
                    elements[i].getFileName(),
                    elements[i].getClassName(),
                    elements[i].getMethodName(),
                    elements[i].getLineNumber(),
                    e.getMessage()
                ), 
                level
            );
        }
    }

    /**
     *
     * @param message
     * @param level
     */
    public static void write
    (
        String message, 
        Level level
    )
    {
        Logger logger;
        
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME/*"package.name"*/);
        logger.log
        (
            level,
            message
        );
    }

    /// <summary>
    /// Write log entry.
    /// General-purpose version that can be used for any situation.
    /// </summary>
    /// <param name="message"></param>
    /// <param name="level"></param>
    /// <param name="logFilename"></param>
    /// <param name="append"></param>
    public static void Write
    (
        String message,
        Level level,
        String logFilename,
        Boolean append
    )
        throws IOException
    {
        if (getUseEventLog())
        {
            write
            (
                message,
                level
            );
        }
        else
        {
            String LogFilename;
            if (StringUtils.isNotBlank(logFilename) && StringUtils.isNotEmpty(logFilename))
            {
                LogFilename = Files.PathCombine(getLogPath(), LOG_FILENAME);
            }
            else
            {
                LogFilename = Files.PathCombine(getLogPath(), logFilename);
            }
            
            try 
            (
                OutputStreamWriter out =
                    new OutputStreamWriter
                    (
                        new FileOutputStream(LogFilename),
                        "UTF-8"
                    ); 
                FileWriter fw = 
                    new FileWriter(LogFilename);
            ) 
            {
                fw.write(String.format("{0}\t{1}\t{2}", new Date(), level.toString(), message));
                fw.flush();
            }
            catch (IOException ex) 
            {
               ex.printStackTrace();
            }
        }
    }

    /// <summary>
    /// Writes a message to the Application event log.
    /// Special-purpose version that is designed for use with exceptions.
    /// exception: an exception that we want to write to log
    /// ex: an exception that may occur when we try to write exception to log
    /// </summary>
    /// <param name="exception"></param>
    ///// <param name="currentMethod"></param>
    /// <param name="level"></param>
    /// <param name="logFilename"></param>
    /// <param name="append"></param>
    public static void Write
    (
        Exception exception,
        //MethodBase currentMethod,
        Level level,
        String logFilename,
        Boolean append
    ) 
        throws Exception
    {
        try
        {
            Log.Write
            (//TODO: use Exception.printStackTrace()
                exception.getStackTrace().toString(), //Log.FormatEntry(Log.Build(exception, currentMethod), currentMethod.DeclaringType.FullName, currentMethod.Name),
                level,
                logFilename,
                append
            );
        }
        catch (IOException ex)
        {
            //this will appear in the UI
            throw new Exception(String.format("Unable to write to log. \n Reason: {0} \n Message: {1}", ex.getMessage(), exception.getMessage()), exception);
        }
        catch (Exception ex)
        {
            //this will appear in the UI
            throw new Exception(String.format("Unable to write to log. \n Reason: {0} \n Message: {1}", ex.getMessage(), exception.getMessage()), exception);
        }
    }

}
