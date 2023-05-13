/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package senaproject.Task;

/**
 *
 * @author auxsistemas3
 */
public enum StateEnum {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    IN_PROGRESS("IN PROGRESS");
    
    private String state;

    private StateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
    
    /*public Tarea(String titulo, String descripcion, EstadoTarea estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }*/
    /*Tarea tarea1 = new Tarea("Título de tarea 1", "Descripción de tarea 1", EstadoTarea.PENDING);*/
}
