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
                <form id="logout_form" method="post" name="loginSubmit"
                      action="${pageContext.servletContext.contextPath}/login">
                    <li class="nav-item">
                        <a class="nav-link" name="loginSubmit"
                           href="javascript:{}" onclick="document.getElementById('logout_form').submit()">Logout</a>
                    </li>
                </form>
            </ul>
        </div>
    </div>
</nav>


<div class="container user-page-top-padding">
    <div class="row">
        <div class="col-md-5">
            <div class="row">
                <div class="col-lg-5">
                    <img src="images/pages/user/blank-profile-picture.png" class="img-thumbnail img-fluid"
                         >
                </div>
                <div class="col-lg-7">
                    <h5>${user.firstName} ${user.lastName}</h5>

                </div>
            </div>
        </div>
        <div class="col-md-7">
            <h4>User banner to go here. Most likely just random JS to generate fractals or random desgin</h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-5">

            <button type="button" class="btn btn-block btn-lg btn-info">
                Messages
            </button>
            <button type="button" class="btn btn-block btn-lg btn-info">
                Notifications
            </button>
            <button type="button" class="btn btn-lg btn-block btn-info">
                Edit User Settings
            </button>
        </div>
        <div class="col-md-7">
            <div class="page-header">
                <h1>
                    Garden's ${user.firstName} belongs too
                </h1>
            </div>
        </div>
    </div>
</div>


<%--<nav role="navigation"
     class="navbar navbar-blue navbar-embossed navbar-lg navbar-fixed-top">

    <div class="container">

        <div class="navbar-header">
            <a href="home" class="navbar-brand brand">
                <img src="images/nittany_lions_logo.jpg" id="logo">
            </a>
            <button data-target="#navbar-collapse-02" data-toggle="collapse"
                    class="navbar-toggle" type="button">
                <span class="sr-only">Toggle navigation</span>
            </button>
        </div>
        <!--/.navbar-header -->
        <form class="search-collapse"
              action="${pageContext.servletContext.contextPath}/user" method="post">
            <input type="text" id="searchText" name="keyword" placeholder="Search..." required>
            <input type="image" src="images/search_black.jpg" value="Search" id="searchButton">
        </form>
        <br>

        <div id="navbar-collapse-02" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="propClone">
                    <form action="${pageContext.servletContext.contextPath}/login" method="post">
                        <button name="loginSubmit" value="Logout" class="homeLinks-nav" type="submit"
                                alt="submit" title="Log Out">Logout
                        </button>
                    </form>
                </li>
            </ul>
        </div>
        <!--/.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>--%>

<!--pageContent-->
<%--<div>
    <div id="coverPhotoContainer">
        <img src="portraits/${user.username}.jpg" id="userPhoto">
    </div>
    <div class="pageInfo" style="height: 100px;">
        <p id="userName">${user.firstName}</p>
        <div id="bio">${user.description}</div>

        <h1 style="position:relative;left:10px;">Gardens you belong to:</h1>

        <form id="GroupGet" method="post">
            <table id="userGroups">
                <c:forEach items="${gardens}" var="garden">
                    <tr class="groupList">
                        <td style="color:white"><input type="submit" value="${garden.garden_name}"
                                                       id="groupDisplay"
                                                       name="Submit" class="buttonAsLink"></td>
                        <td class="descriptionCol" style="color:white">"${garden.description}"</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>

    <div class="sidebar">
        <button onclick="toggleSideBar()" id="toggle" style="right: 0px;color: white">Options
        </button>
        <div id="sideBar" style="right: -300px;">
            <form id="userSidebar" method="post">
                <ul class="list-unstyled">
                    <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/admin"
                                               style="color: white">Administration</a></li>
                    <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/editUser"
                                               style="color: white">Edit User Credentials</a></li>
                    <li class="sidebarItem"><a
                            href="${pageContext.servletContext.contextPath}/editUserPortrait"
                            style="color: white">Edit User Portrait</a></li>
                </ul>
            </form>
        </div>
    </div>
</div>
<!--/pageContent-->
</div>
<!-- /.wrapper -->
</header>
<!-- /.item -->
</div>--%>
<!-- /#page -->
<!-- Load JS here for greater good =============================-->
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-switch.js"></script>
<script src="js/flatui-checkbox.js"></script>
<script src="js/flatui-radio.js"></script>
<script src="js/jquery.tagsinput.js"></script>
<script src="js/jquery.placeholder.js"></script>
<script src="js/jquery.nivo.slider.pack.js"></script>
<script src="js/application.js"></script>
<script src="js/over.js"></script>
<script>

    function toggleSideBar() {
        var x = document.getElementById('sideBar');
        var y = document.getElementById('toggle');
        if (x.style.right === '-300px') {
            x.style.right = '0%';
            y.style.right = '300px';
        } else {
            x.style.right = '-300px';
            y.style.right = '0px';
        }
    }

    $(function () {

        if ($('#nivoSlider').size() > 0) {

            $('#nivoSlider').nivoSlider({
                effect: 'random',
                pauseTime: 5000
            });

        }

    })
</script>


</body>
</html>