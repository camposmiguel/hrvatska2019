
package com.miguelcr.a01_loginscreen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("newPassword")
    @Expose
    private String newPassword;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("activationCode")
    @Expose
    private Integer activationCode;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("created")
    @Expose
    private String created;

    /**
     * No args constructor for use in serialization
     * 
     */
    public User() {
    }

    /**
     * 
     * @param id
     * @param username
     * @param created
     * @param website
     * @param email
     * @param newPassword
     * @param activationCode
     * @param descripcion
     * @param active
     * @param role
     * @param password
     */
    public User(Integer id, String username, String email, String descripcion, String website, String password, String newPassword, String role, Integer activationCode, Boolean active, String created) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.descripcion = descripcion;
        this.website = website;
        this.password = password;
        this.newPassword = newPassword;
        this.role = role;
        this.activationCode = activationCode;
        this.active = active;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(Integer activationCode) {
        this.activationCode = activationCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
