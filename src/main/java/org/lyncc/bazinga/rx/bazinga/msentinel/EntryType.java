package org.lyncc.bazinga.rx.bazinga.msentinel;

public enum EntryType {

    IN("IN"),OUT("OUT");

    private String name;

    EntryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
