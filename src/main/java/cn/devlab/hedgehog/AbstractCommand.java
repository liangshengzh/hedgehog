package cn.devlab.hedgehog;

import cn.devlab.hedgehog.exception.HedgehogRuntimeException;
import cn.devlab.hedgehog.exception.HedgehogRuntimeException.FailureType;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func0;
import rx.subjects.ReplaySubject;

@Slf4j
public abstract class AbstractCommand<R> implements HedgehogInvokableInfo<R>,
    HedgehogObservable<R> {

  protected enum CommandState {
    NOT_STARTED, OBSERVABLE_CHAIN_CREATED, USER_CODE_EXECUTED, UNSUBSCRIBED, TERMINAL;
  }

  protected AtomicReference<CommandState> commandState = new AtomicReference<>(CommandState.NOT_STARTED);

  @Override

  public Observable<R> observe() {

    ReplaySubject<R> subject = ReplaySubject.create();

    final Subscription sourceSubscription = toObservable().subscribe(subject);
    return subject.doOnSubscribe(new Action0() {
      @Override
      public void call() {
        sourceSubscription.unsubscribe();
      }
    });
  }

  @Override
  public Observable<R> toObservable() {

    final AbstractCommand<R> _cmd = this;

    final Action0 terminationCommandCleanup = new Action0() {
      @Override
      public void call() {
        if(commandState.compareAndSet(CommandState.OBSERVABLE_CHAIN_CREATED, CommandState.TERMINAL)){
          handleCommandEnd(false);
        }else if (commandState.compareAndSet(CommandState.USER_CODE_EXECUTED, CommandState.TERMINAL)){
          handleCommandEnd(true);
        }
      }
    };

    return Observable.defer(new Func0<Observable<R>>() {
      @Override
      public Observable<R> call() {
        if(!commandState.compareAndSet(CommandState.NOT_STARTED, CommandState.OBSERVABLE_CHAIN_CREATED)){
          IllegalStateException exception = new IllegalStateException("This instance can only be executed once, please instantiate a new instance.");
          throw new HedgehogRuntimeException(FailureType.BAD_REQUEST_EXCEPTION, _cmd.getClass())
        }
        return;
      }
    });
  }


  private void handleCommandEnd(boolean commandExecutionStarted){

  }
}
