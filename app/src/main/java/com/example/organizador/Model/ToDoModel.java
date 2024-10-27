package com.example.organizador.Model;

public class ToDoModel {
    private int id;
    private boolean status;
    private String task;

    public ToDoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status; 
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setStatus(int anInt) {
    }
}
