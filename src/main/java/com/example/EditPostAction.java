package com.example;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;

public class EditPostAction extends ActionSupport implements ServletRequestAware {
    private int postId;
    private String content;
    private HttpServletRequest request;

    public String post() {
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Retrieve the post from the database
            postId = Integer.parseInt(request.getParameter("postId"));
            Posts postToUpdate = session.get(Posts.class, postId);


            System.out.println(postId);

            content = request.getParameter("postContent");
            // Update the post
            postToUpdate.setPost(content);

            // Save the updated post back to the database
            session.update(postToUpdate);

            tx.commit();
            return SUCCESS;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            addActionError("Error updating post: " + e.getMessage());
            return ERROR;
        } finally {
            session.close();
        }
    }

//    public void setPostId(int postId) {
//        this.postId = postId;
//    }
@Override
public void setServletRequest(HttpServletRequest request) {
    this.request = request;
}

}