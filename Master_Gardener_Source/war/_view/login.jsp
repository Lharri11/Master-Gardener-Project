<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login - Pollinator Preferences</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/additional-style.css" rel="stylesheet">

</head>


<%--   <link href="css/login.css" rel="stylesheet">

   <!-- Loading Bootstrap -->
   <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

   <!-- Loading Flat UI -->
   <link href="css/flat-ui.css" rel="stylesheet">

   <link href="css/newstyle.css" rel="stylesheet">

   <link href="css/newLogin.css" rel="stylesheet">--%>


<body>


<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home">Pollinator Preferences</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

<header></header>


<div class="container card-login-top-padding">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card card-login">
                <div class="card-header">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="/login/login" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="/login/register" class="login-link" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="card-block">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="${pageContext.servletContext.contextPath}/login" method="post" role="form"
                                  style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="username" tabindex="1" class="form-control"
                                           placeholder="Username" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" tabindex="2"
                                           class="form-control" placeholder="Password" required>
                                </div>
                                <div class="form-group text-xs-center">
                                    <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                    <label for="remember"> Remember Me</label>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 offset-sm-3">
                                            <input type="submit" name="loginSubmit" id="login-submit" tabindex="4"
                                                   class="form-control btn btn-login" value="Login">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-xs-center">
                                                <a href="#" tabindex="5"
                                                   class="forgot-password">Forgot Password?</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form id="register-form" action="${pageContext.servletContext.contextPath}/signup" method="post"
                                  role="form" style="display: none;">
                                <div class="form-group">
                                    <input type="text" name="firstname"  tabindex="1"
                                           class="form-control" placeholder="First Name" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="lastname" tabindex="2"
                                           class="form-control" placeholder="Last Name" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="usernameReg" tabindex="3" class="form-control"
                                           placeholder="Username" required>
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" id="email" tabindex="4" class="form-control"
                                           placeholder="Email Address" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="pass1" id="password" tabindex="5"
                                           class="form-control" placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="pass2" id="confirm-password" tabindex="6"
                                           class="form-control" placeholder="Confirm Password" required>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 offset-sm-3">
                                            <input type="submit" name="register-submit" id="register-submit"
                                                   tabindex="4" class="form-control btn btn-register"
                                                   value="Create Account">
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


<%--<div class="container">
    &lt;%&ndash; Aligns center vertically &ndash;%&gt;
    <div class="row align-items-center">
        &lt;%&ndash; Aligns center horizontally &ndash;%&gt;
        <div class="container-fluid">
            <div class="card panel-login">
                <div class="card-header">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="/login/login" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="/login/register" class="login-link" id="register-form-link">Register</a>
                        </div>
                    </div>

                </div>
                <div class="card-block">
                    <form id="login-form" action="${pageContext.servletContext.contextPath}/login" method="post"
                          role="form" style="display: block;">

                        <div &lt;%&ndash;class="form-group"&ndash;%&gt;>
                            <input type="text" name="username" tabindex="1" class="form-control log-reg-text-fields"
                                   placeholder="Username" value="${username}" required>
                        </div>
                        <div &lt;%&ndash;class="form-group"&ndash;%&gt;>
                            <input type="password" name="password" tabindex="2" class="form-control log-reg-text-fields"
                                   placeholder="Password" value="${password}" required>
                        </div>
                        <div class="form-group text-xs-center">
                            <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                            <label for="remember"> Remember Me</label>
                        </div>
                        <a href="#">Forgot your password?</a>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6 offset-sm-3">
                                    <input type="submit" name="loginSubmit" id="loginSubmit" tabindex="4"
                                           class="form-control btn btn-login" value="Login">
                                </div>
                            </div>
                        </div>

                        <div id="errorMessage">
                            <c:if test="${! empty errorMessage}">
                                <tr>${errorMessage}</tr>
                            </c:if>
                        </div>

                    </form>
                    <form id="register-form" action="${pageContext.servletContext.contextPath}/signup" method="post"
                          role="form" style="display: none;">

                        <div class="form-group">
                            <input type="text" name="usernameReg"  tabindex="1" class="form-control log-reg-text-fields"
                                   placeholder="Username" value="${usernameReg}">
                        </div>

                        <div class="form-group">
                            <input type="email" name="email" tabindex="1" class="form-control log-reg-text-fields"
                                   placeholder="Email Address" value="${email}">
                        </div>

                        <div class="form-group">
                            <input type="password" name="pass1"  tabindex="2" class="form-control log-reg-text-fields"
                                   placeholder="Password" value="${pass1}">
                        </div>

                        <div class="form-group">
                            <input type="password" name="pass2"  tabindex="3"
                                   class="form-control log-reg-text-fields" placeholder="Confirm Password" value="${pass2}">
                        </div>

                        <div class="form-group">
                            <input type="text" name="firstname" tabindex="4"
                                   class="form-control log-reg-text-fields" placeholder="First Name" value="${firstname}">
                        </div>

                        <div class="form-group">
                            <input type="text" name="lastname" tabindex="5"
                                   class="form-control log-reg-text-fields" placeholder="Last Name" value="${lastname}">
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6 offset-sm-3">
                                    <input type="submit" name="register-submit" id="register-submit" tabindex="6"
                                           class="form-control btn btn-register" value="Create">
                                </div>
                            </div>
                        </div>
                        <div id="errorMessageReg">
                            <c:if test="${! empty errorMessageReg}">
                                <tr>${errorMessageReg}</tr>
                            </c:if>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>--%>


<!-- Load JS here for greater good =============================-->
<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="vendor/scrollreveal/scrollreveal.js"></script>
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
    $(function () {
        $('#login-form-link').click(function () {
            $(this).addClass('active');
            $('#register-form-link').removeClass('active');
            $("#login-form").show();
            $("#register-form").hide();
            return false;
            // e.preventDefault();
        });
        $('#register-form-link').click(function () {
            $(this).addClass('active');
            $('#login-form-link').removeClass('active');
            $("#register-form").show();
            $("#login-form").hide();
            return false;
            //e.preventDefault();
        });
        switch (true) {
            case 'register':
                $('#register-form-link').click();
                break;
            case 'login':
            default:
                $('#login-form-link').click();
                break;
        }
    });
</script>


</body>
</html>
