package com.test.demo.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="role")
public class Role implements Serializable {
    private Long id;
    private String name;
    private Set<DBUsers> users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<DBUsers> getUsers() {
        return users;
    }

    public void setUsers(Set<DBUsers> users) {
        this.users = users;
    }
}
