package com.tasks.immutablemap;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ImmutableMap<K, V> implements Map<K, V> {

    protected Map<K, V> mapInner;

    protected ImmutableMap(Map<K, V> map) {
        this.mapInner = map;
    }
    
    public int size() {
        return this.mapInner.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public V get(Object key) {
        return this.mapInner.get(key);
    }

    public boolean containsKey(Object key) {
        return this.mapInner.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.mapInner.containsValue(value);
    }

    public Set<K> keySet() {
        return this.mapInner.keySet();
    }

    public Set<Entry<K, V>> entrySet() {
        return this.mapInner.entrySet();
    }

    public Collection<V> values() {
        return this.mapInner.values();
    }

    public V getOrDefault(Object key, V defaultValue) {
        return this.mapInner.getOrDefault(key, defaultValue);
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.mapInner.forEach(action);
    }

    public V put(K key, V value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be changed.",
                        this.getClass().getName()
                )
        );
    }

    public void putAll(Map<? extends K, ? extends V> m) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be changed.",
                        this.getClass().getName()
                )
        );
    }

    public V putIfAbsent(K key, V value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be changed.",
                        this.getClass().getName()
                )
        );
    }

    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be cleared.",
                        this.getClass().getName()
                )
        );
    }

    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be cleared.",
                        this.getClass().getName()
                )
        );
    }

    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be cleared.",
                        this.getClass().getName()
                )
        );
    }

    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be cleared.",
                        this.getClass().getName()
                )
        );
    }

    public void clear() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be cleared.",
                        this.getClass().getName()
                )
        );
    }

    public V remove(Object key) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the element cannot be removed.",
                        this.getClass().getName()
                )
        );
    }

    public boolean remove(Object key, Object value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the element cannot be removed.",
                        this.getClass().getName()
                )
        );
    }

    public boolean replace(K key, V oldValue, V newValue) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be changed.",
                        this.getClass().getName()
                )
        );
    }

    public V replace(K key, V value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be changed.",
                        this.getClass().getName()
                )
        );
    }

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                String.format("%s is immutable, so the collection cannot be changed.",
                        this.getClass().getName()
                )
        );
    }

    public static class Builder<K, V> {
        protected Map<K, V> innerMap;

        public Builder<K, V> fromHashMap() {
            this.innerMap = new HashMap<>();

            return this;
        }

        public Builder<K, V> fromTreeMap() {
            this.innerMap = new TreeMap<>();

            return this;
        }

        public Builder<K, V> fromLinkedHashMap() {
            this.innerMap = new LinkedHashMap<>();

            return this;
        }

        public Builder<K, V> put(K key, V value) {
            this.innerMap.put(key, value);

            return this;
        }

        public Builder<K, V> putAll(Map<K, V> elements) {
            this.innerMap.putAll(elements);

            return this;
        }

        public ImmutableMap<K, V> build() {
            return new ImmutableMap<>(this.innerMap);
        }
    }
}
