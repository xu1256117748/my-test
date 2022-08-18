package com.xyk.util;

import com.xyk.pojo.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 通用的实体类对象链式赋值API
 * @author 徐亚奎
 * @date 2021-08-24 08:22
 */
public class Builder<T> {
    private final Supplier<T> instantiator;
    private List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }
    public static <T> Builder<T> newInstanceOf(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }
    public <Value> Builder<T> setValue(MyConsumer<T, Value> consumer, Value value) {
        Consumer<T> c = instance -> consumer.setValue(instance, value);
        modifiers.add(c);
        return this;
    }
    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }
    /**
     * 1 参数 Consumer
     */
    @FunctionalInterface
    public interface MyConsumer<T, Value> {
        void setValue(T t, Value value);
    }

    public static void main(String[] args) {
        Book book = Builder.newInstanceOf(Book::new)
                .setValue(Book::setId, "01")
                .setValue(Book::setIsbn, "xxxx")
                .setValue(Book::setName, "java从入门到入坟")
                .setValue(Book::setNote, "xx")
                .build();
        System.out.println(book);
    }

}
