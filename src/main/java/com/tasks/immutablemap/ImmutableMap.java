package com.tasks.immutablemap;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ImmutableMap<K, V> implements Map<K, V> {

    protected Map<K, V> innerMap;

    protected ImmutableMap(Map<K, V> map) {
        this.innerMap = map;
    }

    public int size() {
        return this.innerMap.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public V get(Object key) {
        return this.innerMap.get(key);
    }

    public boolean containsKey(Object key) {
        return this.innerMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.innerMap.containsValue(value);
    }

    public Set<K> keySet() {
        return this.innerMap.keySet();
    }

    public Set<Entry<K, V>> entrySet() {
        return this.innerMap.entrySet();
    }

    public Collection<V> values() {
        return this.innerMap.values();
    }

    public V getOrDefault(Object key, V defaultValue) {
        return this.innerMap.getOrDefault(key, defaultValue);
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.innerMap.forEach(action);
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

    public static class Builder<K, V, T extends Builder<K, V, T>> {
        protected Map<K, V> innerMap;

        protected Builder(Map<K, V> map) {
            this.innerMap = map;
        }

        public static <K, V> Builder<K, V, ? extends Builder<K, V, ?>> fromHashMap() {
            return new Builder<>(new HashMap<>());
        }

        public static <K, V> Builder<K, V, ? extends Builder<K, V, ?>> fromLinkedHashMap() {
            return new Builder<>(new LinkedHashMap<>());
        }

        public T put(K key, V value) {
            this.innerMap.put(key, value);

            return (T) this;
        }

        public T putAll(Map<K, V> elements) {
            this.innerMap.putAll(elements);

            return (T) this;
        }

        public ImmutableMap<K, V> build() {
            return new ImmutableMap<>(this.innerMap);
        }
    }

    public static class TreeMapBuilder<K, V> extends Builder<K, V, TreeMapBuilder<K, V>> {
        protected Comparator<K> comparator;

        protected TreeMapBuilder(HashMap<K, V> map) {
            super(map);
        }

        public static <K, V> TreeMapBuilder<K, V> fromTreeMap() {
            return new TreeMapBuilder<>(new HashMap<>());
        }

        public TreeMapBuilder<K, V> withComparator(Comparator<K> comparator) {
            this.comparator = comparator;

            return this;
        }

        public ImmutableMap<K, V> build() {
            var map = new TreeMap<K, V>(this.comparator);
            map.putAll(this.innerMap);

            return new ImmutableMap<>(map);
        }
    }
}
