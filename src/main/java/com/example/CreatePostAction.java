package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class CreatePostAction extends ActionSupport implements ServletRequestAware {
    private String title;
    private String content;
    private HttpServletRequest request;

    public String post() {
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Get the user ID from the session
            int userId = (int) request.getSession().getAttribute("userId");

            // Hash the content using SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(content.getBytes(StandardCharsets.UTF_8));
            String hash = String.format("%064x", new BigInteger(1, hashBytes));

            // Create a new Posts object and save it to the database
            Posts post = new Posts(userId, hash, content,title);
            session.save(post);

            tx.commit();
            addActionMessage("Post created successfully!");
            return SUCCESS;
        } catch (NoSuchAlgorithmException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            addActionError("Error creating post: " + e.getMessage());
            return ERROR;
        } finally {
            session.close();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}