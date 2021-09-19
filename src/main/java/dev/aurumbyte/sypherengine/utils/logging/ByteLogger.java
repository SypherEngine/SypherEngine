package dev.aurumbyte.sypherengine.utils.logging;

import dev.aurumbyte.sypherengine.utils.logging.logUtils.LoggerLevel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static dev.aurumbyte.sypherengine.utils.logging.logUtils.LogColors.*;

public class ByteLogger {
    private final boolean fileCreated;
    private final boolean overwriteFiles;
    private File logFile;
    private final boolean fileLog;
    private final String logFileName;
    private FileWriter fileWriter;
    private BufferedWriter writer;
    private final LoggerLevel level;

    public ByteLogger(String logFileName, boolean fileLog, LoggerLevel defaultLevel, boolean overwriteFiles){
        this.logFileName = logFileName;
        this.fileLog = fileLog;
        this.level = defaultLevel;

        if(logFileName.isEmpty()) logFileName = "bytelog.txt";
        this.overwriteFiles = overwriteFiles;

        if(fileLog) logFile = new File(logFileName);

        fileCreated = logFile != null;

        try {
           if(fileCreated) fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
         if(fileCreated) writer = new BufferedWriter(fileWriter);
    }

    public ByteLogger(boolean fileLog, LoggerLevel defaultLevel){
        this.logFileName = "bytelog.txt";
        this.fileLog = fileLog;
        this.level = defaultLevel;

        this.overwriteFiles = false;

        if(fileLog) logFile = new File(logFileName);

        fileCreated = logFile != null;

        try {
            if(fileCreated) fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(fileCreated) writer = new BufferedWriter(fileWriter);
    }

    public void info(Object msg) throws IOException {
        if(level.equals(LoggerLevel.OFF)){
            return;
        }

        if(fileLog && fileCreated){
            writer.write("<Info> => " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("<Info> => " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(BLUE + "<Info> => %s%n" + RESET, msg.toString());
    }

    public void warn(Object msg) throws IOException {
        if(level.equals(LoggerLevel.OFF)){
            return;
        }

        if(fileLog && fileCreated){
            writer.write("<Warning> => " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("<Warning> => " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(YELLOW + "<Warning> => %s%n" + RESET, msg.toString());
    }

    public void error(Object msg) throws IOException {
        if(level.equals(LoggerLevel.OFF)){
            return;
        }

        if(fileLog && fileCreated){
            writer.write("<Error> => " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("<Error> => " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(RED + "<Error> => %s%n" + RESET, msg.toString());
    }

    public void error(Exception e) throws IOException {
        if(level.equals(LoggerLevel.OFF)){
            return;
        }

        if(fileLog && fileCreated){
            writer.write("<Error> => " + e.toString());
            writer.newLine();
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("<Error> => " + e.toString());
            writer.newLine();
        }

        if((level.equals(LoggerLevel.DEBUG) || level.equals(LoggerLevel.TRACE))){
            writer.write("    <Stack Trace> =>\n" + Arrays.toString(e.getStackTrace()));
            writer.newLine();
            writer.write("    <Cause> => " + e.getCause().toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(RED + "<Error> => %s%n" + RESET, e.toString());
        System.out.printf(RED + "    <Stack Trace> =>%n %s%n" + RESET, Arrays.toString(e.getStackTrace()));
        System.out.printf(CYAN + "    <Cause> => %s%n" + RESET, e.getCause().toString());
    }

    public void fatal(Object msg) throws IOException {
        if(level.equals(LoggerLevel.OFF)){
            return;
        }

        if(fileLog && fileCreated){
            writer.write("<Fatal Error> => " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("<Fatal Error> => " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(RED + "<Error> => %s%n" + RESET, msg.toString());
    }

    public void fatal(Exception e) throws IOException {
        if(level.equals(LoggerLevel.OFF)){
            return;
        }

        if(fileLog && fileCreated){
            writer.write("<Fatal Error> => " + e.toString());
            writer.newLine();
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("<Fatal Error> => " + e.toString());
            writer.newLine();
        }

        if((level.equals(LoggerLevel.DEBUG) || level.equals(LoggerLevel.TRACE))){
            writer.write("    <Stack Trace> =>\n" + Arrays.toString(e.getStackTrace()));
            writer.newLine();
            writer.write("    <Cause> => " + e.getCause().toString());
            writer.newLine();
            writer.close();
            return;
        }


        System.out.printf(RED + "<Fatal Error> => %s%n" + RESET, e.toString());
        System.out.printf(RED + "    <Stack Trace> =>%n %s%n" + RESET, Arrays.toString(e.getStackTrace()));
        System.out.printf(CYAN + "    <Cause> => %s%n" + RESET, e.getCause().toString());
    }


    public void logPermissions(){
        System.out.println("Logger Permissions:");
        System.out.println("    Log File Name : " + logFileName);
        System.out.println("    New Log file created ? : " + (fileCreated ? "Yes" : "No"));
        System.out.println("    Log to file ? : " + (fileLog ? "Yes" : "No"));
        System.out.println("    Overwrite if file already exists ? : " + (overwriteFiles ? "Yes" : "No"));
    }

    public void runUsing(LoggerLevel level){
        level = level;
    }
}
