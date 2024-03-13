package com.example;

import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.List;

public class DisplayPostsAction extends ActionSupport implements ServletRequestAware {
    private List<Posts> posts;
    private HttpServletRequest request;

    public String execute() {
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Get the user ID from the session
            int userId = (int) request.getSession().getAttribute("userId");

            // Retrieve the posts from the database
            Query query = session.createQuery("FROM Posts WHERE userId = :userId");
            query.setParameter("userId", userId);
            posts = query.list();

            tx.commit();
            return SUCCESS;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            addActionError("Error retrieving posts: " + e.getMessage());
            return ERROR;
        } finally {
            session.close();
        }
    }

    public List<Posts> getPosts() {
        return posts;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
