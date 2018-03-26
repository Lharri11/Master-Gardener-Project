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

<header></header>

<div class="container">
    <%-- Aligns center vertically --%>
    <div class="row align-items-center">
        <%-- Aligns center horizontally --%>
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

                        <div <%--class="form-group"--%>>
                            <input type="text" name="username" tabindex="1" class="form-control log-reg-text-fields"
                                   placeholder="Username" value="${username}" required>
                        </div>
                        <div <%--class="form-group"--%>>
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
