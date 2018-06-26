package cn.devlab.hedgehog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class HedgehogCommand<R> extends AbstractCommand<R> {

  protected abstract R run() throws Exception;

}
