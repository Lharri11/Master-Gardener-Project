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


<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home">Pollinator Preferences</a>
    </div>
</nav>


<div class="container card-login-top-padding">
    <div class="row">
        <div class="col-lg-6 offset-lg-3 align-content-lg-center">
            <div class="card card-login">
                <div class="card-header">
                    <div class="row">
                        <div class="col-lg-6 col-sm-6">
                            <a href="/login/login" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <a href="/login/register" class="login-link" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="card-block">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="${pageContext.servletContext.contextPath}/login" method="post"
                                  role="form"
                                  style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="username" tabindex="1" class="form-control"
                                           placeholder="Username" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password"  tabindex="2"
                                           class="form-control" placeholder="Password" required>
                                </div>
                                <div class="form-group text-lg-center text-center">
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
                                            <div class="text-lg-center text-center">
                                                <a href="#" tabindex="5"
                                                   class="forgot-password">Forgot Password?</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>


                            <form id="register-form" action="${pageContext.servletContext.contextPath}/signup"
                                  method="post"
                                  role="form" style="display: none;">

                                <%--<div class="tab">--%>
                                <div class="form-group">
                                    <input type="text" name="firstname" id = "firstname" tabindex="1"
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
                                    <input type="password" name="pass1" id="password1" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" tabindex="5"
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
                                                   value="Create Account" disabled="disabled">
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
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Load JS here for greater good =============================-->
<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="vendor/scrollreveal/scrollreveal.js"></script>

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

<script>

    $("input[type=password]").keyup(function () {
        var ucase = new RegExp("[A-Z]+");
        var lcase = new RegExp("[a-z]+");
        var num = new RegExp("[0-9]+");

        if ($("#password1").val().length >= 8) {
            $("#8char").removeClass("fa-times");
            $("#8char").addClass("fa-check");
            $("#8char").css("color", "#00A41E");
        } else {
            $("#8char").removeClass("fa-check");
            $("#8char").addClass("fa-times");
            $("#8char").css("color", "#FF0004");
        }

        if (ucase.test($("#password1").val())) {
            $("#ucase").removeClass("fa-times");
            $("#ucase").addClass("fa-check");
            $("#ucase").css("color", "#00A41E");
        } else {
            $("#ucase").removeClass("fa-check");
            $("#ucase").addClass("fa-times");
            $("#ucase").css("color", "#FF0004");
        }

        if (lcase.test($("#password1").val())) {
            $("#lcase").removeClass("fa-times");
            $("#lcase").addClass("fa-check");
            $("#lcase").css("color", "#00A41E");
        } else {
            $("#lcase").removeClass("fa-check");
            $("#lcase").addClass("fa-times");
            $("#lcase").css("color", "#FF0004");
        }

        if (num.test($("#password1").val())) {
            $("#num").removeClass("fa-times");
            $("#num").addClass("fa-check");
            $("#num").css("color", "#00A41E");
        } else {
            $("#num").removeClass("fa-check");
            $("#num").addClass("fa-times");
            $("#num").css("color", "#FF0004");
        }

        if ($("#password1").val() !== null && $("#password1").val() !== "" && $("#confirm-password").val() != null && $("#password1").val() === $("#confirm-password").val()) {
            $("#register-submit").prop("disabled", false);
            pcheck = true;
            $("#pwmatch").removeClass("fa-times");
            $("#pwmatch").addClass("fa-check");
            $("#pwmatch").css("color", "#00A41E");
        }
        if($("#password1").val() !== $("#confirm-password").val()) {
            $("#register-submit").prop("disabled", true);

            $("#pwmatch").removeClass("fa-check");
            $("#pwmatch").addClass("fa-times");
            $("#pwmatch").css("color", "#FF0004");
        }

    });

</script>

<%--<script>
        var currentTab = 0; // Current tab is set to be the first tab (0)
        showTab(currentTab); // Display the current tab
        function showTab(n) {
            // This function will display the specified tab of the form ...
            var x = document.getElementsByClassName("tab");
            x[n].style.display = "block";
            // ... and fix the Previous/Next buttons:
            if (n == 0) {
                document.getElementById("prevBtn").style.display = "none";
            } else {
                document.getElementById("prevBtn").style.display = "inline";
            }
            if (n == (x.length - 1)) {
                document.getElementById("nextBtn").innerHTML = "Submit";
            } else {
                document.getElementById("nextBtn").innerHTML = "Next";
            }
            // ... and run a function that displays the correct step indicator:
            fixStepIndicator(n)
        }
        function nextPrev(n) {
            // This function will figure out which tab to display
            var x = document.getElementsByClassName("tab");
            // Exit the function if any field in the current tab is invalid:

            // Hide the current tab:
            x[currentTab].style.display = "none";
            // Increase or decrease the current tab by 1:
            currentTab = currentTab + n;
            // if you have reached the end of the form... :
            if (currentTab >= x.length) {
                //...the form gets submitted:
                document.getElementById("register-form").submit();
                return false;
            }
            // Otherwise, display the correct tab:
            showTab(currentTab);
        }
        function fixStepIndicator(n) {
            // This function removes the "active" class of all steps...
            var i, x = document.getElementsByClassName("step");
            for (i = 0; i < x.length; i++) {
                x[i].className = x[i].className.replace(" active", "");
            }
            //... and adds the "active" class to the current step:
            x[n].className += " active";
        }
</script>--%>


</body>
</html>
