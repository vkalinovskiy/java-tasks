package com.tasks.circuitbreaker;
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
    protected LocalDateTime timeBeforeUnlock;
    protected Path logFile;

    CircuitBreaker(int errorsLimit, int secondsWaiting) {
        this.errorsLimit = errorsLimit;
        this.secondsWaiting = secondsWaiting;
        this.blackBox = new BlackBox();
        this.logFile = Paths.get("log.txt");
    }

    public void execute() throws RuntimeException {
        this.checkLock();
        this.checkErrorsLimitExceed();
        this.resetErrorsLimitAndTimeBeforeUnlockIfAlreadyUnlocked();
        this.executeInBlackBox();
    }

    protected void executeInBlackBox() {
        try {
            blackBox.execute();
            this.resetErrorsCounter();
        } catch (RuntimeException e) {
            this.increaseErrorsCounter();
        }
    }

    protected void resetErrorsLimitAndTimeBeforeUnlockIfAlreadyUnlocked() {
        if(this.timeBeforeUnlock != null) {
            this.resetErrorsCounter();
            this.resetLockTime();
        }
    }

    protected void checkLock() throws RuntimeException {
        if(this.isLocked()) {
            this.writeLog();

            throw new RuntimeException("BlackBox threw an exception!");
        }
    }

    protected void checkErrorsLimitExceed() {
        if(this.isErrorsLimitExceeded()) {
            this.setLockTime();

            throw new RuntimeException("BlackBox threw an exception!");
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
        this.timeBeforeUnlock = LocalDateTime.now().plusSeconds(this.secondsWaiting);
    }

    protected void resetLockTime() {
        this.timeBeforeUnlock = null;
    }

    protected boolean isLocked() {
        return LocalDateTime.now().isBefore(this.timeBeforeUnlock);
    }

    protected void writeLog() {
        try {
            Files.writeString(this.logFile, "BlackBox is temporarily unavailable!", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
