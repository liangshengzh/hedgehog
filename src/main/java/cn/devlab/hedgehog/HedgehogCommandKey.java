package cn.devlab.hedgehog;

import cn.devlab.hedgehog.util.InternMap;
import cn.devlab.hedgehog.util.InternMap.ValueConstructor;

public interface HedgehogCommandKey extends HedgehogKey {

  class Factory {

    private Factory() {
    }

    private static final InternMap<String, HedgehogCommandKeyDefault> intern = new InternMap<>(
        new ValueConstructor<String, HedgehogCommandKeyDefault>() {
          @Override
          public HedgehogCommandKeyDefault create(String key) {
            return new HedgehogCommandKeyDefault(key);
          }
        });

    private static class HedgehogCommandKeyDefault extends HedgehogKeyDefault implements
        HedgehogCommandKey {

      public HedgehogCommandKeyDefault(String name) {
        super(name);
      }
    }

    static int getCommandCount() {
      return intern.size();
    }
  }


}
