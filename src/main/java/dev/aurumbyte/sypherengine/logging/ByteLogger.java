/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.logging;

import static dev.aurumbyte.sypherengine.logging.logUtils.LogColors.*;

import dev.aurumbyte.sypherengine.logging.logUtils.LoggerLevel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ByteLogger {

    private final boolean fileCreated;
    private final boolean overwriteFiles;
    private File logFile;
    private final boolean fileLog;
    private final String logFileName;
    private FileWriter fileWriter;
    private BufferedWriter writer;
    private final LoggerLevel level;

    public ByteLogger(String logFileName, boolean fileLog, LoggerLevel defaultLevel, boolean overwriteFiles) {
        this.logFileName = logFileName;
        this.fileLog = fileLog;
        this.level = defaultLevel;

        if (logFileName.isEmpty()) logFileName = "bytelog.txt";
        this.overwriteFiles = overwriteFiles;

        if (fileLog) logFile = new File(logFileName);

        fileCreated = logFile != null;

        try {
            if (fileCreated) fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileCreated) writer = new BufferedWriter(fileWriter);
    }

    public ByteLogger(boolean fileLog, LoggerLevel defaultLevel) {
        this.logFileName = "bytelog.txt";
        this.fileLog = fileLog;
        this.level = defaultLevel;

        this.overwriteFiles = false;

        if (fileLog) logFile = new File(logFileName);

        fileCreated = logFile != null;

        try {
            if (fileCreated) fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileCreated) writer = new BufferedWriter(fileWriter);
    }

    public void info(Object msg) throws IOException {
        if (level.equals(LoggerLevel.OFF)) {
            return;
        }

        if (fileLog && fileCreated) {
            writer.write("[=== INFO ===] " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("[=== INFO ===] " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(BLUE + "[=== INFO ===] %s%n" + RESET, msg.toString());
    }

    public void warn(Object msg) throws IOException {
        if (level.equals(LoggerLevel.OFF)) {
            return;
        }

        if (fileLog && fileCreated) {
            writer.write("[=== WARN ===] " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("[=== WARN ===] " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(YELLOW + "[=== WARN ===] %s%n" + RESET, msg.toString());
    }

    public void error(Object msg) throws IOException {
        if (level.equals(LoggerLevel.OFF)) {
            return;
        }

        if (fileLog && fileCreated) {
            writer.write("[=== ERR ===] " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("[=== ERR ===] " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(RED + "[=== ERR ===] %s%n" + RESET, msg.toString());
    }

    public void error(Exception e) throws IOException {
        if (level.equals(LoggerLevel.OFF)) {
            return;
        }

        if (fileLog && fileCreated) {
            writer.write("[=== ERR ===] " + e.toString());
            writer.newLine();
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("[=== ERR ===] " + e.toString());
            writer.newLine();
        }

        if ((level.equals(LoggerLevel.DEBUG) || level.equals(LoggerLevel.TRACE))) {
            writer.write("    [=== STACK TRACE ===]\n" + Arrays.toString(e.getStackTrace()));
            writer.newLine();
            writer.write("    [=== CAUSE ===] " + e.getCause().toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(RED + "[=== ERR ===] %s%n" + RESET, e.toString());
        System.out.printf(RED + "    [=== STACK TRACE ===]%n %s%n" + RESET, Arrays.toString(e.getStackTrace()));
        System.out.printf(
                CYAN + "    [=== CAUSE ===] %s%n" + RESET, e.getCause().toString());
    }

    public void fatal(Object msg) throws IOException {
        if (level.equals(LoggerLevel.OFF)) {
            return;
        }

        if (fileLog && fileCreated) {
            writer.write("[=== FATAL ===] " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("[=== FATAL ===] " + msg.toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(RED + "[=== ERR ===] %s%n" + RESET, msg.toString());
    }

    public void fatal(Exception e) throws IOException {
        if (level.equals(LoggerLevel.OFF)) {
            return;
        }

        if (fileLog && fileCreated) {
            writer.write("[=== FATAL ===] " + e.toString());
            writer.newLine();
        } else if (fileLog && !fileCreated && overwriteFiles) {
            writer.write("[=== FATAL ===] " + e.toString());
            writer.newLine();
        }

        if ((level.equals(LoggerLevel.DEBUG) || level.equals(LoggerLevel.TRACE))) {
            writer.write("    [=== STACK TRACE ===]\n" + Arrays.toString(e.getStackTrace()));
            writer.newLine();
            writer.write("    [=== CAUSE ===] " + e.getCause().toString());
            writer.newLine();
            writer.close();
            return;
        }

        System.out.printf(RED + "[=== FATAL ===] %s%n" + RESET, e.toString());
        System.out.printf(RED + "    [=== STACK TRACE ===]%n %s%n" + RESET, Arrays.toString(e.getStackTrace()));
        System.out.printf(
                CYAN + "    [=== CAUSE ===] %s%n" + RESET, e.getCause().toString());
    }

    public void logPermissions() {
        System.out.println("Logger Permissions:");
        System.out.println("    Log File Name : " + logFileName);
        System.out.println("    New Log file created ? : " + (fileCreated ? "Yes" : "No"));
        System.out.println("    Log to file ? : " + (fileLog ? "Yes" : "No"));
        System.out.println("    Overwrite if file already exists ? : " + (overwriteFiles ? "Yes" : "No"));
    }

    public void runUsing(LoggerLevel level) {
        level = level;
    }
}
