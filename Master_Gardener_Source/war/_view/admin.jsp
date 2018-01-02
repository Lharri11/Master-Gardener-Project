<%@ page import="javax.swing.text.html.HTMLDocument" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
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
    <title>Master Gardener Admin</title>
    <!--pageMeta-->

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">

    <link href="css/newstyle.css" rel="stylesheet">
    <link href="css/datatables.css" rel="stylesheet">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <!--headerIncludes-->


</head>
<body>

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

<div id="navAdmin" class="sidenavAdmin">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="${pageContext.servletContext.contextPath}/chartDirectory">Data Charts</a>
    <a href="${pageContext.servletContext.contextPath}/user">Account Settings</a>
</div>


<!--pageContent-->
<div>
    <div id="intro"></div>
    <div id="intro"></div>
    <span style="font-size:1.875em;cursor:pointer" onclick="openNav()">&#9776; Admin Controls</span>
    <br>
    <br>
    <div class="container">
        <div class="col-lg-7 col-md-6 col-sm-12">
            <div class="editContent">
                <h2>Create New User</h2>
            </div>
            <div class="editContent">
                <form action="${pageContext.servletContext.contextPath}/admin" method="post">
                    <table>
                        <tr>
                            <td>Username:</td>
                            <td><input type="text" class="text-control" name="userName"
                                       placeholder="Username"
                                       value="${userName}" required autocomplete="off"></td>

                        </tr>

                        <tr>
                            <td> Password:</td>

                            <td><input type="password" class="text-control" name="pass1" placeholder="Password"
                                       value="${password}" required autocomplete="off">

                        </tr>

                        <tr>
                            <td> Re-Enter:</td>
                            <td><input type="password" class="text-control" name="pass2" placeholder="Re-Enter Password"
                                       value="${pass2}" required autocomplete="off">
                            </td>

                        </tr>

                        <tr>
                            <td> Name:</td>

                            <td><input type="text" class="text-control" name="name" placeholder="Name"
                                       value="${name}" required autocomplete="off"></td>

                        </tr>

                        <tr>
                            <td> Email:</td>

                            <td><input type="text" class="text-control" name="email" placeholder="Email"
                                       value="${email}" required autocomplete="off"></td>

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


        <!--/pageContent-->

    </div>


    <div class="container">
        <div class="col-lg-12 col-md-6 col-sm-6">
            <div id="divSpacer"></div>
            <!-- /.spacer -->
            <div class="editContent">
                <h2>Delete Existing User</h2>
            </div>
            <div>
                <form id="delete" action="${pageContext.servletContext.contextPath}/admin" method="post">
                    <table>
                        <tr>
                            <td>User to delete:</td>

                                <td><input type="text" class="text-control" id="usernameDelete" name="username2"
                                           placeholder="User to Delete"
                                           value="${usernameDelete}" required autocomplete="off"></td>
                            <td><input type="submit" class="text-control" name="userDelete"
                                       value="Delete">
                            </td>
                        </tr>
                    </table>
                    <c:if test="${! empty errorMessage2}">
                        <tr>${errorMessage2}</tr>
                    </c:if>
                </form>
            </div>

            <div>
                <div id="intro"></div>
            </div>


            <div class="col-lg-8 col-md-6 col-sm-12">
                <table id="activeAccountTable">
                    <thead>
                    <tr>
                        <th class="col-xs-3">Username</th>
                        <th class="col-xs-3">Name</th>
                        <th class="col-xs-6">E-mail</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%-- <%Iterator itr;%>

                     <% List activeAccounts = (List) request.getAttribute("activeAccounts");
                         for (itr = activeAccounts.iterator(); itr.hasNext(); ) {
                     %>
                     <tr>
                         <td class="col-xs-3"><%=itr.next()%>
                         </td>
                         <td class="col-xs-3"><%=itr.next()%>
                         </td>
                         <td class="col-xs-3"><%=itr.next()%>
                         </td>
                     </tr>

                     <%}%> --%>


                    <c:forEach items="${activeAccounts}" var="activeAccounts">
                        <tr>
                            <td class="col-xs-3">${activeAccounts.username}</td>
                            <td class="col-xs-3">${activeAccounts.name}</td>
                            <td class="col-xs-3"> ${activeAccounts.email}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


                <!--
                <table id="dataTable" class="table table-bordered">
                    <thead>
                    <tr>
                        <th class="col-xs-3">Username</th>
                        <th class="col-xs-3">Name</th>
                        <th class="col-xs-6">E-mail</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="col-xs-3">gfhgf</td>
                        <td class="col-xs-3">gfhfgh</td>
                        <td class="col-xs-6">gfhfgh</td>
                    </tr>
                    </tbody>
                </table> -->
            </div>

        </div>
    </div>

    <%--<div class="sidebar">
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
    </div>--%>

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
<script src="js/datatables.js"></script>
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

<script>
    function openNav() {
        document.getElementById("navAdmin").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("navAdmin").style.width = "0";
    }
</script>


<script>
    $(document).ready(function () {
        $('#activeAccountTable').DataTable().draw();
        ;
    });
</script>

<script>
    document.getElementById("delete").onsubmit = function () {
        location.reload(true);
    }
</script>


</body>
</html>