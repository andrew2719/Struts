<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.example.Book" %>
<%@ page import="com.example.FactoryProvider" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Books</title>
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        form {
            width: 50%;
            margin: 20px auto;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
        }
        button {
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<form action="SearchBookAction" method="get">
    <label for="search">Search:</label>
    <input type="text" id="search" name="search" placeholder="Enter Book Title or Author" required />
    <button type="submit">Search</button>
</form>
<s:if test="books != null">
    <h1>Search Results</h1>
    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Genre</th>
            <th>Copies Available</th>
        </tr>
        <s:iterator value="books">
            <tr>
                <td><s:property value="id" /></td>
                <td><s:property value="title" /></td>
                <td><s:property value="author" /></td>
                <td><s:property value="isbn" /></td>
                <td><s:property value="genre" /></td>
                <td><s:property value="numCopies" /></td>
            </tr>
        </s:iterator>
    </table>
</s:if>
<h1>List of Books</h1>

<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>ISBN</th>
        <th>Genre</th>
        <th>Copies Available</th>
    </tr>

    <%

        Session session1 = FactoryProvider.getFactory().openSession();

        try {
            session1.beginTransaction();
            List<Book> books = session1.createQuery("from Book").getResultList();

            for (Book book : books) {
    %>
    <tr>
        <td><%= book.getId() %></td>
        <td><%= book.getTitle() %></td>
        <td><%= book.getAuthor() %></td>
        <td><%= book.getIsbn() %></td>
        <td><%= book.getGenre() %></td>
        <td><%= book.getNumCopies() %></td>
    </tr>
    <%
            }
            session1.getTransaction().commit();
        } finally {
            if (session1 != null && session1.isOpen()) {
                session1.close();
            }
        }
    %>
    <div class="container text-center mt-2">
        <a href="index.jsp" class="btn btn-primary">Home</a>
    </div>
</table>
</body>
</html>
