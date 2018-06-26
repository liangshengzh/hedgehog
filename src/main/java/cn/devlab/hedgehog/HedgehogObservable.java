package cn.devlab.hedgehog;

import rx.Observable;

public interface HedgehogObservable<R> extends HedgehogInvokable<R> {

  Observable<R> observe();


  Observable<R> toObservable();


}
