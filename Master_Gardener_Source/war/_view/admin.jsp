<%--
  Created by IntelliJ IDEA.
  User: Nick
  Date: 11/10/2017
  Time: 12:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator"
          content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
    <title>${account.name} - Master Gardener Admin</title>
    <!--pageMeta-->

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">

    <link href="css/style.css" rel="stylesheet">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <!--headerIncludes-->


</head>
<body>

<div id="page" class="page">

    <header class="item header margin-top-0 header10" id="header10">

        <div class="wrapper">

            <nav role="navigation"
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
                                <form action="${pageContext.servletContext.contextPath}/dataInput" method="get">
                                    <button class="homeLinks-nav" type="submit">Data Input</button>
                                </form>
                            </li>

                            <li class="propClone">
                                <form action="${pageContext.servletContext.contextPath}/user" method="get">
                                    <button class="homeLinks-nav" type="submit">My Account</button>
                                </form>
                            </li>


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
            </nav>

            <!--pageContent-->
            <div>
                <div id="intro"></div>
                <div id="intro"></div>
                <div id="container" class="container">
                    <div class="col-lg-7 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <h2>Create New User</h2>
                        </div>
                        <div id="editContent" class="editContent">
                            <form action="${pageContext.servletContext.contextPath}/admin" method="post">
                                <table>
                                    <tr>
                                        <td>Username:</td>
                                        <c:if test="${! empty username}">
                                            <td><input type="text" id="username" name="username" placeholder="Username"
                                                       value="${username}"></td>
                                        </c:if>

                                        <c:if test="${empty username}">
                                            <td><input type="text" id="username" name="username" placeholder="Username">
                                            </td>
                                        </c:if>
                                    </tr>

                                    <tr>
                                        <td> Password:</td>
                                        <c:if test="${! empty password}">
                                        <td><input type="password" id="password" name="pass1" placeholder="Password"
                                                   value="${password}">
                                            </c:if>

                                            <c:if test="${empty password}">
                                        <td><input type="password" id="password" name="pass1" placeholder="Password">
                                        </td>
                                        </c:if>
                                    </tr>

                                    <tr>
                                        <td> Re-Enter:</td>
                                        <c:if test="${! empty pass2}">
                                            <td><input type="password" id="password" name="pass2" value="${pass2}"></td>
                                        </c:if>

                                        <c:if test="${empty pass2}">
                                            <td><input type="password" id="password" name="pass2"
                                                       placeholder="Re-enter Password"></td>
                                        </c:if>
                                    </tr>

                                    <tr>
                                        <td> Name:</td>
                                        <c:if test="${! empty name}">
                                            <td><input type="text" id="name" name="name" placeholder="Name"
                                                       value="${name}"></td>
                                        </c:if>

                                        <c:if test="${empty name}">
                                            <td><input type="text" id="name" name="name" placeholder="Name"></td>
                                        </c:if>
                                    </tr>

                                    <tr>
                                        <td> Email:</td>
                                        <c:if test="${! empty email}">
                                            <td><input type="text" id="email" name="email" placeholder="Email"
                                                       value="${email}"></td>
                                        </c:if>

                                        <c:if test="${empty email}">
                                            <td><input type="text" id="email" name="email" placeholder="Email Address">
                                            </td>
                                        </c:if>
                                    </tr>

                                    <tr>
                                        <td><input type="submit" id="loginSubmit" name="userCreate" value="Create">
                                        </td>
                                    </tr>
                                </table>
                                <c:if test="${! empty errorMessage}">
                                    <tr>${errorMessage}</tr>
                                </c:if>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-5 col-md-6 col-sm-10">
                        <div id="divSpacer"></div>
                        <!-- /.spacer -->
                        <div id="editContent" class="editContent">
                            <h2>Delete Existing User</h2>
                        </div>
                        <div id="frameRight">
                            <form action="${pageContext.servletContext.contextPath}/admin" method="post">
                                <table>
                                    <tr>
                                        <td>User to delete:</td>
                                        <c:if test="${! empty usernameDelete}">
                                            <td><input type="text" id="usernameDelete" name="username2"
                                                       placeholder="User to Delete"
                                                       value="${usernameDelete}"></td>
                                        </c:if>

                                        <c:if test="${empty usernameDelete}">
                                            <td><input type="text" id="usernameDelete" name="username2"
                                                       placeholder="User to Delete">
                                            </td>
                                        </c:if>
                                    </tr>

                                    <tr>
                                        <td><input type="submit" id="userDeleteSubmit" name="userDelete"
                                                   value="Delete">
                                        </td>
                                    </tr>
                                </table>
                                <c:if test="${! empty errorMessage2}">
                                    <tr>${errorMessage2}</tr>
                                </c:if>
                            </form>
                        </div>
                    </div>


                    <div id="container" class="container">
                        <div class="col-lg-7 col-md-6 col-sm-12">
                            <div id="divSpacer"></div>
                            <div id="divSpacer"></div>
                            <!-- /.spacer -->
                            <div id="editContent" class="editContent">
                                <h2>Add New Specimen</h2>
                            </div>
                        </div>
                    </div>


                </div>

                <!--/pageContent-->

            </div>

            <div class="sidebar">
                <button onclick="toggleSideBar()" id="toggle" style="right: 0px;color: white">Options
                </button>
                <div id="sideBar" style="right: -300px;">
                    <form id="GroupGet" method="post">
                        <ul class="list-unstyled">
                            <li class="sidebarItem"><a href="http://localhost:8081/Master-Gardener/createGarden"
                                                       style="color: white">Add Garden</a></li>
                            <li class="sidebarItem"><a href="http://localhost:8081/Master-Gardener/createGarden"
                                                       style="color: white">Add Plant</a></li>
                            <li class="sidebarItem"><a href="http://localhost:8081/Master-Gardener/createGarden"
                                                       style="color: white">Add Plant Species</a></li>
                            <li class="sidebarItem"><a href="http://localhost:8081/Master-Gardener/createGarden"
                                                       style="color: white">Add Pollinator</a></li>
                            <li class="sidebarItem"><a href="http://localhost:8081/Master-Gardener/editUser"
                                                       style="color: white">Account Settings</a></li>
                            <c:forEach items="${groups}" var="group">
                                <tr>
                                    <td class="groupListItem"><input class="sidebarItem" type="Submit"
                                                                     value="${group.name}" id="Submit"
                                                                     name="Submit"></td>
                                </tr>
                            </c:forEach>
                        </ul>

                    </form>
                    <!--<input type="Submit" value=${group.name } id="Submit" name="Submit">-->
                </div>
            </div>


        </div>    <!-- /.wrapper -->

    </header>
    <!-- /.item -->
</div>
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