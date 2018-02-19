<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>

    <title>Login - Master Gardener</title>
    <link rel="icon" type="image/jpg" href="images/nittany_lions_logo.jpg">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator" content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
    <!--pageMeta-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>


    <link href="css/login.css" rel="stylesheet">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">

    <link href="css/newstyle.css" rel="stylesheet">

    <link href="css/newLogin.css" rel="stylesheet">



</head>

<body>


<nav role="navigation"
     class="navbar navbar-blue navbar-embossed navbar-lg navbar-fixed-top">

    <div class="container">

        <div class="navbar-header">
            <a href="home" class="navbar-brand brand"><img src="images/nittany_lions_logo.jpg"
                                                           id="logo"></a>
        </div>


    </div><!-- /.container -->
</nav>


<div class="container">
    <div class="row align-items-center">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="/login/login" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="/login/register" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="${pageContext.servletContext.contextPath}/login" method="post" role="form"
                                  style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="username" placeholder="Username" value="${username}"
                                           required>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" placeholder="Password" value="${password}"
                                           required>
                                </div>
                                <div class="form-group text-center">
                                    <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                    <label for="remember"> Remember Me</label>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="loginSubmit" id="login-submit" tabindex="4"
                                                   class="form-control btn btn-login" value="Login">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a href="#" tabindex="5"
                                                   class="forgot-password">Forgot Password?</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form id="register-form" action="https://phpoll.com/register/process" method="post"
                                  role="form" style="display: none;">
                                <div class="form-group">
                                    <input type="text" name="username" id="username" tabindex="1" class="form-control"
                                           placeholder="Username" value="">
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" id="email" tabindex="1" class="form-control"
                                           placeholder="Email Address" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="confirm-password" id="confirm-password" tabindex="2"
                                           class="form-control" placeholder="Confirm Password">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit"
                                                   tabindex="4" class="form-control btn btn-register"
                                                   value="Register Now">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<%--
<div id="page" class="page">
    <div class="wrapper">
        <form class="login" action="${pageContext.servletContext.contextPath}/login" method="post">
            <p class="title">Log in</p>
            <input type="text" id="username" name="username" placeholder="Username" value="${username}"
                   required>

            <input type="password" id="username" name="password" placeholder="Password" value="${password}"
                   required>
            <i class="fa fa-key"></i>
            <a href="#">Forgot your password?</a>
            <button type="Submit" value="Login" id="loginSubmit" name="loginSubmit">
                <span>Log in</span>

            </button>
            <div id="errorMessage">
                <c:if test="${! empty errorMessage}">
                    <tr>${errorMessage}</tr>
                </c:if>
            </div>

        </form>
    </div>
</div>
<!-- /.item -->
--%>


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

<script>
    $(function() {

        $('#login-form-link').click(function(e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#register-form-link').click(function(e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });

    });

</script>


</body>
</html>
