<!DOCTYPE html>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <%@include file="../resources/head.html"%>
</head>

    <body class="container">
    <%@include file="../resources/navbar.html"%>
    <main>

        <div class="row align-items-center justify-content-between">
            <div class="col-sm-2">
                <h1>Books</h1>
            </div>
            <div class="col-sm-4">
                <a class="btn btn-primary" href="<c:url value="/books/add" />"><i class="fas fa-plus"> Add Book</i></a>
            </div>
        </div>

        <li class="row">

            <c:if test="${not empty books}">
            <ul class="list-group list-group-flush">
                <c:forEach var="book" items="${books}">

                    <li class="list-group-item">
                        <a href="/books/<c:out value="${book.id}" />">
                            <img class="img-thumbnail" src="<c:out value="${book.coverURL}" />" align="middle" height="50" width="50" />
                            <c:out value="${book.title}" />
                        </a>
                    </li>

                </c:forEach>
            </ul>

            </c:if>

            </div>
    </main>
    </body>

</html>