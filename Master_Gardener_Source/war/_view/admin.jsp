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

    <!-- Datatables style -->
    <link href="vendor/DataTables/DataTables-1.10.16/css/jquery.dataTables.css" rel="stylesheet">


</head>
<body>


<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home">Pollinator Preferences</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.servletContext.contextPath}/user">My Account</a>
            </li>
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


<div class="container admin-page-top-padding">
    <div class="row">
        <div class="col-lg-4">

            <form id="register-form" action="${pageContext.servletContext.contextPath}/signup"
                  method="post"
                  role="form">

                <%--<div class="tab">--%>
                <div class="form-group">
                    <input type="text" name="firstname" tabindex="1"
                           class="form-control" placeholder="First Name" required>
                </div>
                <div class="form-group">
                    <input type="text" name="lastname" tabindex="2"
                           class="form-control" placeholder="Last Name" required>
                </div>
                <%--</div>--%>

                <%--<div class="tab">--%>
                <div class="form-group">
                    <input type="text" name="usernameReg" tabindex="3" class="form-control"
                           placeholder="Username" required>
                </div>
                <div class="form-group">
                    <input type="email" name="email" id="email" tabindex="4" class="form-control"
                           placeholder="Email Address" required>
                </div>


                <div class="form-group">
                    <input type="password" name="pass1" id="password1" tabindex="5"
                           class="form-control" placeholder="Password" required>
                </div>
                <div class="row">
                    <div class="col-lg-6 text-lg-center text-center">
                        <p id="8char" class="fas fa-times"
                           style="color:#FF0004;"></p> 8 Characters
                        Long<br>
                        <p id="ucase" class="fas fa-times"
                           style="color:#FF0004;"></p> One Uppercase
                        Letter
                    </div>
                    <div class="col-lg-6 text-lg-center text-center">
                        <p id="lcase" class="fas fa-times"
                           style="color:#FF0004;"></p> One Lowercase
                        Letter<br>
                        <p id="num" class="fas fa-times" style="color:#FF0004;"></p>
                        One Number
                    </div>
                </div>


                <div class="form-group">
                    <input type="password" name="pass2" id="confirm-password" tabindex="6"
                           class="form-control" placeholder="Confirm Password" required>
                </div>
                <div class="row">
                    <div class="col-lg-12 text-lg-center text-center">
                        <p id="pwmatch" class="fas fa-times" style="color:#FF0004;"></p> Passwords
                        Match
                    </div>
                </div>

                <br>
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-6 offset-sm-3">
                            <input type="submit" name="register-submit" id="register-submit"
                                   tabindex="7" class="form-control btn btn-register"
                                   value="Create Account">
                        </div>
                    </div>
                </div>
                <%--</div>--%>
                <%--<div style="overflow:auto;">
                    <div style="float:right;">
                        <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
                        <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
                    </div>
                </div>
                <!-- Circles which indicates the steps of the form: -->
                <div style="text-align:center;margin-top:40px;">
                    <span class="step"></span>
                    <span class="step"></span>
                </div>--%>

            </form>


        </div>

        <div class="col-lg-8">
            <div class="row">
                <%--
                            <form id="delete" action="${pageContext.servletContext.contextPath}/admin" method="post">




                            </form>--%>

                <table id="activeAccountTable">
                    <thead>
                    <tr>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>E-mail</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${activeAccounts}" var="activeAccounts">
                        <tr>
                            <td>${activeAccounts.username}</td>
                            <td>${activeAccounts.firstName}</td>
                            <td>${activeAccounts.lastName}</td>
                            <td>${activeAccounts.email}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>

        </div>

        <%--   <h2>Delete Existing User</h2>
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
                   <th class="col-xs-3">First Name</th>
                   <th class="col-xs-3">Last Name</th>
                   <th class="col-xs-6">E-mail</th>
               </tr>
               </thead>
               <tbody>

               <c:forEach items="${activeAccounts}" var="activeAccounts">
                   <tr>
                       <td class="col-xs-3">${activeAccounts.username}</td>
                       <td class="col-xs-3">${activeAccounts.firstName}</td>
                       <td class="col-xs-3">${activeAccounts.lastName}</td>
                       <td class="col-xs-3">${activeAccounts.email}</td>

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
       </div>--%>
    </div>


</div>


<!-- Load JS here for greater good =============================-->
<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="vendor/scrollreveal/scrollreveal.js"></script>
<script src="vendor/Popper/Popper.js"></script>
<script src="vendor/DataTables/datatables.js"></script>

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

<script>
    (function($) {
        "use strict"; // Start of use strict
        // Configure tooltips for collapsed side navigation
        $('.navbar-sidenav [data-toggle="tooltip"]').tooltip({
            template: '<div class="tooltip navbar-sidenav-tooltip" role="tooltip" style="pointer-events: none;"><div class="arrow"></div><div class="tooltip-inner"></div></div>'
        })
        // Toggle the side navigation
        $("#sidenavToggler").click(function(e) {
            e.preventDefault();
            $("body").toggleClass("sidenav-toggled");
            $(".navbar-sidenav .nav-link-collapse").addClass("collapsed");
            $(".navbar-sidenav .sidenav-second-level, .navbar-sidenav .sidenav-third-level").removeClass("show");
        });
        // Force the toggled class to be removed when a collapsible nav link is clicked
        $(".navbar-sidenav .nav-link-collapse").click(function(e) {
            e.preventDefault();
            $("body").removeClass("sidenav-toggled");
        });
        // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
        $('body.fixed-nav .navbar-sidenav, body.fixed-nav .sidenav-toggler, body.fixed-nav .navbar-collapse').on('mousewheel DOMMouseScroll', function(e) {
            var e0 = e.originalEvent,
                delta = e0.wheelDelta || -e0.detail;
            this.scrollTop += (delta < 0 ? 1 : -1) * 30;
            e.preventDefault();
        });
        // Scroll to top button appear
        $(document).scroll(function() {
            var scrollDistance = $(this).scrollTop();
            if (scrollDistance > 100) {
                $('.scroll-to-top').fadeIn();
            } else {
                $('.scroll-to-top').fadeOut();
            }
        });
        // Configure tooltips globally
        $('[data-toggle="tooltip"]').tooltip()
        // Smooth scrolling using jQuery easing
        $(document).on('click', 'a.scroll-to-top', function(event) {
            var $anchor = $(this);
            $('html, body').stop().animate({
                scrollTop: ($($anchor.attr('href')).offset().top)
            }, 1000, 'easeInOutExpo');
            event.preventDefault();
        });
    })(jQuery); // End of use strict


</script>



</body>
</html>


<%--
<%@ page import="javax.swing.text.html.HTMLDocument" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: Nick
  Date: 11/10/2017
  Time: 12:35 AM
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
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
                    <form action="${pageContext.servletContext.contextPath}/dataForm" method="get">
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
                            <td> First Name:</td>

                            <td><input type="text" class="text-control" firstname="firstname" placeholder="First Name"
                                       value="${name}" required autocomplete="off"></td>

                        </tr>
                        <tr>
                            <td> Last Name:</td>

                            <td><input type="text" class="text-control" lastname="lastname" placeholder="Last Name"
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
                        <th class="col-xs-3">First Name</th>
                        <th class="col-xs-3">Last Name</th>
                        <th class="col-xs-6">E-mail</th>
                    </tr>
                    </thead>
                    <tbody>

                    &lt;%&ndash; <%Iterator itr;%>
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
                     <%}%> &ndash;%&gt;


                    <c:forEach items="${activeAccounts}" var="activeAccounts">
                        <tr>
                            <td class="col-xs-3">${activeAccounts.username}</td>
                            <td class="col-xs-3">${activeAccounts.firstName}</td>
                            <td class="col-xs-3">${activeAccounts.lastName}</td>
                            <td class="col-xs-3">${activeAccounts.email}</td>

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

    &lt;%&ndash;<div class="sidebar">
        <button onclick="toggleSideBar()" id="toggle" style="right: 0px;color: white">Options
        </button>
        <div id="sideBar" style="right: -300px;">
            <form id="GroupGet" method="post">
                <ul class="list-unstyled">
                    <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/createGarden"
                                               style="color: white">Add Garden</a></li>
                    <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/createGarden"
                                               style="color: white">Add Plant</a></li>
                    <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/createGarden"
                                               style="color: white">Add Plant Species</a></li>
                    <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/createGarden"
                                               style="color: white">Add Pollinator</a></li>
                    <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/editUser"
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
    </div>&ndash;%&gt;

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
</html>--%>
