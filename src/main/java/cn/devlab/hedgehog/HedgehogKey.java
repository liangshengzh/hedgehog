package cn.devlab.hedgehog;

public interface HedgehogKey {

  String name();

  abstract class HedgehogKeyDefault implements HedgehogKey{
    private final String name;

    public HedgehogKeyDefault(String name) {
      this.name = name;
    }

    @Override
    public String name() {
      return name;
    }

    @Override
    public String toString() {
      return name;
    }
  }
}
