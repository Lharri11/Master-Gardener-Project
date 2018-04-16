<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator" content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
    <!--pageMeta-->
    <title>Sign Up - Master Gardener</title>
    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">

    <link href="css/newstyle.css" rel="stylesheet">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <!--headerIncludes-->


</head>
<body>


<nav role="navigation" class="navbar navbar-blue navbar-embossed navbar-lg navbar-fixed-top">

    <div class="container">
        <div class="navbar-header">
            <a href="home" class="navbar-brand brand"><img src="images/nittany_lions_logo.jpg"
                                                           id="logo"></a>
        </div>
    </div><!-- /.container -->
</nav>

<div class="container">
    <div class="row align-items-center">
        <form action="${pageContext.servletContext.contextPath}/signup" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                        <td><input type="text" id="username" name="username" placeholder="Username"
                                   value="${username}"></td>
                </tr>

                <tr>
                    <td> Password:</td>
                    <td><input type="password" id="password" name="pass1" placeholder="Password"
                               value="${password}">
                </tr>

                <tr>
                    <td> Re-Enter:</td>

                        <td><input type="password" id="password" name="pass2" value="${pass2}"></td>

                </tr>

                <tr>
                    <td> First Name:</td>
                        <!-- THE ID FOR THIS SHOULD PROBABLY NOT BE USERNAME BUT IT WORKS SO WHATEVER YEAH? -->
                        <td><input type="text" id="username" name="firstname" placeholder="First Name" value="${firstname}"></td>
                </tr>

                <tr>
                    <!-- THE ID FOR THIS SHOULD PROBABLY NOT BE USERNAME BUT IT WORKS SO WHATEVER YEAH? -->
                    <td> Last Name:</td>
                        <td><input type="text" id="username" name="lastname" placeholder="Last Name" value="${lastname}"></td>
                </tr>

                <tr>
                    <td> Email:</td>
                        <td><input type="text" id="email" name="email" placeholder="Email" value="${email}">
                        </td>
                </tr>

                <tr>
                    <td> Brief Bio:</td>
                        <td><input type="text" id="name" name="bio" value="${bio}"></td>
                </tr>

                <tr>
                    <td><input type="submit" id="loginSubmit" name="createUserSubmitOld" value="Create"></td>
                </tr>
            </table>
            <c:if test="${! empty errorMessage}">
                <tr>${errorMessage}</tr>
            </c:if>
        </form><!-- /.login -->
    </div><!-- /.signup -->
</div>


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