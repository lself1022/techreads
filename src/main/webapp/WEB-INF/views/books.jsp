<!DOCTYPE html>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <body>
        <h1>Books</h1>
        <a href="<c:url value="/books/add" />">Add Books</a>

        <c:if test="${not empty books}">
            <table>

                <c:forEach var="book" items="${books}">
                    <tr>
                        <td><img src="<c:out value="${book.coverURL}" />" align="middle" height="50" width="50" /></td>
                        <td><c:out value="${book.title}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>

</html>