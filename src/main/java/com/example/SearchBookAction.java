package com.example;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

public class SearchBookAction extends ActionSupport {
    private String search;
    private List<Book> books;

    public String execute() {
        Session session = FactoryProvider.getFactory().openSession();

        try {
//            String hql = "FROM Book B WHERE B.title LIKE :search OR B.author LIKE :search or B.isbn LIKE :search";
            String hql = "FROM Book B WHERE B.title LIKE :search OR B.author LIKE :search OR cast( B.isbn as string ) LIKE :search OR cast(B.id as string) LIKE :search";
            Query query = session.createQuery(hql);
            query.setParameter("search", "%" + search + "%");
            books = query.list();
        } finally {
            session.close();
        }

        return SUCCESS;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}