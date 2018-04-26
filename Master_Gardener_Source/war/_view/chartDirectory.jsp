<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${user.firstName} - Pollinator Preferences</title>
    <!--pageMeta-->

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom fonts -->
    <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
          rel="stylesheet">

    <!-- Custom styles -->
    <link href="css/additional-style.css" rel="stylesheet">

    <!-- Font-Awesome javascript library -->
    <script defer src="vendor/font-awesome/fontawesome-all.js"></script>

</head>
<body>


<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home">Pollinator Preferences</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <%--TODO: Fix logout function, does not function as inteneded...add seperate servlet--%>

        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <form id="logout" action="${pageContext.servletContext.contextPath}/login" method="post">
                    <li class="nav-item">


                        <button class="nav-link" name="loginSubmit" value="Logout" type="submit"
                                alt="submit" title="Log Out">Logout
                        </button>
                        <%--<a class="nav-link" name="loginSubmit"
                           href="javascript:;" onclick="document.getElementById('logout_form').submit()">Logout</a>
                        <input type="hidden" name="logout" value="Logout"/>--%>
                    </li>
                </form>
            </ul>
        </div>
    </div>
</nav>


<div class="container global-top-padding">
        <div class="row">
            <h2>Data Chart Directory</h2>
        </div>
        <br>
        <div>
            <form action="${pageContext.servletContext.contextPath}/dataForm" method="post">
                <div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col-lg-2">Please select the desired data chart:</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Chart Selection -->
                        <tr>
                            <td>
                                <form name="chartSelect">
                                    <select class="form-control" name="chartSelect"
                                            onChange="window.document.location.href=this.options[this.selectedIndex].value;"
                                            value="GO">
                                        <option style="display:none">Select</option>
                                        <option value="${pageContext.servletContext.contextPath}/pvCounty">Pollinator
                                            Visit Counts By County
                                        </option>
                                        <option value="${pageContext.servletContext.contextPath}/pvCoreopsis">Pollinator
                                            Visit Counts For Coreopsis Plant
                                        </option>
                                        <option value="${pageContext.servletContext.contextPath}/pvMonarda">Pollinator
                                            Visit Counts For Monarda Plant
                                        </option>
                                        <option value="${pageContext.servletContext.contextPath}/pvPollType">Pollinator
                                            Visit Counts By Pollinator Species
                                        </option>
                                    </select>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </div>
    <!--/pageContent-->

<!-- Load JS here for greater good =============================-->
<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="vendor/scrollreveal/scrollreveal.js"></script>
<script src="vendor/Popper/Popper.js"></script>


</body>
</html>