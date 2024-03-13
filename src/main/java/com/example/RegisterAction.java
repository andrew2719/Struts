package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
    private String email;
    private String password;

    public String post() {
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Users user = new Users(email, password);
            session.persist(user);
            tx.commit();
            addActionMessage("User registered successfully!");
            return SUCCESS;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            addActionError("Error registering user: " + e.getMessage());
            return ERROR;
        } finally {
            session.close();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}