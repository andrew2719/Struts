package com.example;

import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class EditPostPageAction extends ActionSupport implements ServletRequestAware {
    private int postId;
    private Posts post_retrive;
    private HttpServletRequest request;

    public String execute() {
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Retrieve the post from the database
            post_retrive = session.get(Posts.class, postId);

            tx.commit();
            return SUCCESS;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            addActionError("Error retrieving post: " + e.getMessage());
            return ERROR;
        } finally {
            session.close();
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}