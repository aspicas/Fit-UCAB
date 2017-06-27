package edu.ucab.desarrollo.fitucab.common.entities;

/**
 * Clase que maneja la informacion de los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class Challenge extends Entity {
    private int _id;
    private String _name;
    private String _description;
    private int _score;
    private long _achieve;
    private long _unachieve;
    private int _level;

    /**
     * Contructor vacio de challenge
     */
    public Challenge() {
    }

    /**
     * Constructor que inicializa el id, nombre, descripcion y record de challenge
     * @param id Identificador del reto.
     * @param name Nombre del reto
     * @param description Descripcion del reto
     * @param score Record del reto
     */
    public Challenge(int id, String name, String description, int score) {
        _id = id;
        _name = name;
        _description = description;
        _score = score;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public int getScore() {
        return _score;
    }

    public void setScore(int score) {
        _score = score;
    }

    public long getAchieve() {
        return _achieve;
    }

    public void setAchieve(long achieve) {
        _achieve = achieve;
    }

    public long getUnachieve() {
        return _unachieve;
    }

    public void setUnachieve(long unachieve) {
        _unachieve = unachieve;
    }

    public int getLevel() {
        return _level;
    }

    public void setLevel(int level) {
        _level = level;
    }
}
