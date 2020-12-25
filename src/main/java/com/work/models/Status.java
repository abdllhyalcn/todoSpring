package com.work.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EStatus name;

    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL,
            mappedBy="status")
    private List<Todo> todoSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Todo> getTodoSet() {
        return todoSet;
    }

    public void setTodoSet(List<Todo> todoSet) {
        this.todoSet = todoSet;
    }

    public EStatus getName() {
        return name;
    }

    public void setName(EStatus name) {
        this.name = name;
    }
}
