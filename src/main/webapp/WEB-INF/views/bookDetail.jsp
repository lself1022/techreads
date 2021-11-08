<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
    <a href="/books/"><--- Go To Book List</a><br><br><br>
    <img src="<c:out value="${book.coverURL}" />" align="middle" /><h1><c:out value="${book.title}" /></h1>
    <b>Author: </b><c:out value="${book.author}" /><br>
    <b>Rating: </b><c:out value="${book.rating}" />

</body>

</html>