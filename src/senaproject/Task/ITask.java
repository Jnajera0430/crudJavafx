/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package senaproject.Task;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author auxsistemas3
 */
public class ITask {

    private IntegerProperty id;
    private StringProperty title;
    private StringProperty description;
    private IntegerProperty fk_user;
    private StringProperty fk_adminTask;
    private StringProperty state;
    private StringProperty name;

    public ITask(int id, String title, String description, int fk_user, String fk_adminTask, String state) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.fk_user = new SimpleIntegerProperty(fk_user);
        this.fk_adminTask = new SimpleStringProperty(fk_adminTask);
        this.state = new SimpleStringProperty(state);
    }
    public ITask(int id, String title, String description, int fk_user, String fk_adminTask, String state,String name) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.fk_user = new SimpleIntegerProperty(fk_user);
        this.fk_adminTask = new SimpleStringProperty(fk_adminTask);
        this.state = new SimpleStringProperty(state);
        this.name = new SimpleStringProperty(name);
    }

    public ITask(String title, String description, int fk_user, String fk_adminTask, StateEnum state) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.fk_user = new SimpleIntegerProperty(fk_user);
        this.fk_adminTask = new SimpleStringProperty(fk_adminTask);
        this.state = new SimpleStringProperty(state.getState());
    }
    public ITask() {
        this.title = null;
        this.description = null;
        this.fk_user = null;
        this.fk_adminTask = null;
        this.state = null;
    }

    public ITask(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(StringProperty title) {
        this.title = title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(StringProperty description) {
        this.description = description;
    }

    public IntegerProperty fk_userProperty() {
        return fk_user;
    }

    public int getFk_user() {
        return fk_user.get();
    }

    public void setFk_user(IntegerProperty fk_user) {
        this.fk_user = fk_user;
    }

    public StringProperty fk_adminTaskProperty() {
        return fk_adminTask;
    }

    public String getFk_adminTask() {
        return fk_adminTask.get();
    }

    public void setFk_adminTask(StringProperty fk_adminTask) {
        this.fk_adminTask = fk_adminTask;
    }

    public StringProperty stateProperty() {
        return state;
    }

    public String getState() {
        return state.get();
    }

    public void setState(StringProperty state) {
        this.state = state;
    }
    
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

}