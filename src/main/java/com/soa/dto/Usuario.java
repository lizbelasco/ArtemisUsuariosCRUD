/**
 * 
 */
package com.soa.dto;

import com.google.gson.Gson;

/**
 * Clase que modela la informacion resumida de una persona
 */
public class Usuario {
    /* Id del usuario */
    private long id;
    /* Login del usuario */
    private String login;
    /* Contraseña del usuario */
    private String password;
    
    
    
    @Override /*sobreescritura*/
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }



    /**
     * @return the id
     */
    public long getId() {
        return id;
    }



    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }



    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }



    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }



    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }



    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
