package cn.devlab.hedgehog.exception;

import cn.devlab.hedgehog.HedgehogInvokable;

public class HedgehogRuntimeException extends  RuntimeException {

  public enum FailureType{
    BAD_REQUEST_EXCEPTION, COMMAND_EXCEPTION, TIMEOUT, SHORTCIRCUIT, REJECTED_THREAD_EXECUTION, REJECTED_SEMAPHORE_EXECUTION, REJECTED_SEMAPHORE_FALLBACK
  }

  private Class<? extends HedgehogInvokable> commandClass;
  private Throwable fallbackException;
  private FailureType failureType;


  public HedgehogRuntimeException(FailureType failureType,Class<? extends HedgehogInvokable> commandClass, String message, Exception cause, Throwable fallbackException) {
    super(message, cause);
    this.commandClass = commandClass;
    this.fallbackException = fallbackException;
    this.failureType = failureType;
  }


  public HedgehogRuntimeException(FailureType failureType,Class<? extends HedgehogInvokable> commandClass, String message, Throwable cause, Throwable fallbackException) {
    super(message, cause);
    this.commandClass = commandClass;
    this.fallbackException = fallbackException;
    this.failureType = failureType;
  }
}
