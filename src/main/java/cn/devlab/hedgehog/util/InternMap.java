package cn.devlab.hedgehog.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InternMap<K, V> {

  private final ConcurrentMap<K, V> storage = new ConcurrentHashMap<>();
  private final ValueConstructor<K, V> valueConstructor;


  public interface ValueConstructor<K, V> {

    V create(K key);
  }

  public InternMap(ValueConstructor<K, V> valueConstructor) {
    this.valueConstructor = valueConstructor;
  }


  public V intern(K key) {
    V existingKey = storage.get(key);
    V newKey = null;

    if (existingKey == null) {
      newKey = valueConstructor.create(key);
      existingKey = storage.putIfAbsent(key, newKey)
    }
    return existingKey != null ? existingKey : newKey;
  }

  public int size() {
    return storage.size();
  }
}
