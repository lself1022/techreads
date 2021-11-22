<!DOCTYPE html>

    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <%@include file="../resources/head.html"%>
</head>

<body class="container">
<%@include file="../resources/navbar.html"%>
<main>

    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#"><a href="/books/">Book List</a></a></li>
                <li class="breadcrumb-item active" aria-current="page">Add Book</li>
            </ol>
        </nav>
    </div>
    <div class="row">
        <h1><c:out value="${addOrEdit}" /> Book</h1>
    </div>
    <div class="row">
        <form:form method="post" modelAttribute="bookForm" action="${pageContext.request.contextPath}/books">
            <form:input path="id" type="hidden" />
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <form:input class="form-control" name="title" path="title" type="text" />
            </div>
            <div class="mb-3">
                <label for="coverURL" class="form-label">Cover Image URL</label>
                <form:input class="form-control" name="coverURL" path="coverURL" type="text" />
            </div>
            <div class="mb-3">
                <label for="author" class="form-label">Author</label>
                <form:input class="form-control" name="author" path="author" type="text" />
            </div>
            <div class="mb-3">
                <label for="rating" class="form-label">Rating</label>
                <form:input class="form-control" name="rating" path="rating" type="number" min="0" max="5" step="0.1" />
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <button type="submit" class="btn btn-primary form-control"><i class="fas fa-book"> <c:out value="${addOrEdit}" /></i></button>
                </div>
                <div class="col-sm-2">
                    <a class="btn btn-light form-control" href="/books/<c:out value="${book.id}" />"><i class="fas fa-trash"> Cancel</i></a>
                </div>
            </div>
        </form:form>
    </div>
</main>

</body>


</html>