package com.arobs.generics;

public class GenericBox<T> {

    private T content;

    //Constructor
    public GenericBox(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "GenericBox{" +
                "content=" + content +
                '}';
    }
}
