package com.taks.circuitbreaker;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class CircuitBreaker {
    protected BlackBox blackBox;
    protected int errorsLimit;
    protected int errorsCounter;
    protected int secondsWaiting;
    protected LocalDateTime lockTime;
    protected Path logFile;

    CircuitBreaker(int errorsLimit, int secondsWaiting) {
        this.errorsLimit = errorsLimit;
        this.secondsWaiting = secondsWaiting;
        this.blackBox = new BlackBox();
        this.logFile = Paths.get("log.txt");
    }

    public void execute() {
        if(this.isLocked()) {
            this.writeLog();
            return;
        }

        if(this.isErrorsLimitExceeded()) {
            this.setLockTime();
            return;
        }

        if(this.lockTime != null) {
            this.resetErrorsCounter();
            this.resetLockTime();
        }

        try {
            blackBox.execute();
        } catch (RuntimeException e) {
            this.increaseErrorsCounter();
        }
    }

    protected boolean isErrorsLimitExceeded() {
        return this.errorsCounter == this.errorsLimit;
    }

    protected void increaseErrorsCounter() {
        this.errorsCounter++;
    }

    protected void resetErrorsCounter() {
        this.errorsCounter = 0;
    }

    protected void setLockTime() {
        this.lockTime = LocalDateTime.now().plusSeconds(this.secondsWaiting);
    }

    protected void resetLockTime() {
        this.lockTime = null;
    }

    protected boolean isLocked() {
        return LocalDateTime.now().isBefore(this.lockTime);
    }

    protected void writeLog() {
        try {
            Files.writeString(this.logFile, "BlackBox is temporarily unavailable!", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
