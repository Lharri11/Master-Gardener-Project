<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator"
          content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
    <title>${user.name} - Master Gardener</title>
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
                <div id="coverPhotoContainer">
                    <img src="portraits/${user.username}.jpg" id="userPhoto">
                </div>
                <div class="pageInfo" style="height: 100px;">
                    <p id="userName">${user.name}</p>
                    <div id="bio">${user.description}</div>

                    <h1 style="position:relative;left:10px;">Gardens you belong to:</h1>

                    <form id="GroupGet" method="post">
                        <table id="userGroups">
                            <c:forEach items="${gardens}" var="garden">
                                <tr class="groupList">
                                    <td style="color:white"><input type="submit" value="${garden.garden_name}" id="groupDisplay"
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
                                <li class="sidebarItem"><a href="http://localhost:8081/Master-Gardener/admin"
                                                           style="color: white">Administration</a></li>
                                <li class="sidebarItem"><a href="http://localhost:8081/Master-Gardener/editUser"
                                                           style="color: white">Edit User Credentials</a></li>
                                <li class="sidebarItem"><a href="http://localhost:8081/Master-Gardener/editUserPortrait"
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