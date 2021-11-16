<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <%@include file="../resources/head.html"%>
</head>

<body class="container">
    <%@include file="../resources/navbar.html"%>
    <main>

    </main>
    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#"><a href="/books/">Book List</a></a></li>
                <li class="breadcrumb-item active" aria-current="page"><c:out value="${book.title}" /></li>
            </ol>
        </nav>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="container">
                <div class="row">
                    <img class="image-fluid w-100 h-auto" src="<c:out value="${book.coverURL}" />" />
                </div>
                <div class="row">
                    <div class="btn-group">
                        <a class="btn btn-primary" href="/books/edit/<c:out value="${book.id}" />"><i class="fas fa-pencil-alt"> Edit</i></a>
                        <a class="btn btn-danger" href="/books/delete/<c:out value="${book.id}" />"><i class="fas fa-trash"> Delete</i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-8 d-flex align-items-center">
            <div class="container">
                <div class="row text-wrap">
                    <h2><c:out value="${book.title}" /></h2>
                </div>
                <div class="row">
                    <h5><c:out value="${book.author}" /></h5>
                </div>
                <div class="row">
                    <h5><b>Rating:</b> <c:out value="${book.rating}" /></h5>
                </div>
            </div>

        </div>
    </div>

</body>

</html>