<!DOCTYPE html>

    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <body>
        <h1>Add Book</h1>

        <form:form method="post" modelAttribute="bookForm" action="${pageContext.request.contextPath}/books">
            Title: <form:input path="title" type="text" /><br>
            Cover URL: <form:input path="coverURL" type="text" /><br>
            Author: <form:input path="author" type="text" /><br>
            Rating: <form:input path="rating" type="text" /><br>
            <button type="submit">Add</button>
        </form:form>
    </body>

</html>