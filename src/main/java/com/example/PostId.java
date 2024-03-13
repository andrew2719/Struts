package com.example;

import java.io.Serializable;
import java.util.Objects;

public class PostId implements Serializable {
    private int id;
    private String hash;

    public PostId() {
    }

    public PostId(int id, String hash) {
        this.id = id;
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return id == postId.id && Objects.equals(hash, postId.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hash);
    }
}