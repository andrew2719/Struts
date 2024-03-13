package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class LoginAction extends ActionSupport implements ServletRequestAware {
    private String email;
    private String password;
    private HttpServletRequest request;

    public String post() {
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Users WHERE email = :email AND password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            Users user = (Users) query.uniqueResult();
            tx.commit();

            if (user != null) {
                request.getSession().setAttribute("userId", user.getId());
                addActionMessage("User logged in successfully!");
                return SUCCESS;
            } else {
                addActionError("Invalid email or password");
                return ERROR;
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            addActionError("Error logging in: " + e.getMessage());
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

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}