package edu.bsuir.totema.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Basic DAO entity
 */
public abstract class Entity implements Cloneable, Serializable {

    @Expose
    private long id;

    Entity() {
    }

    Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}