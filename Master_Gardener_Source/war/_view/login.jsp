<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html >
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

    <link href="css/style.css" rel="stylesheet">

    <link href="css/login.css" rel="stylesheet">




</head>

<body>
<div id="page" class="page">

    <header class="item header margin-top-0 header10" id="header10">

    		<div class="wrapper">

    			<nav role="navigation" class="navbar navbar-blue navbar-embossed navbar-lg navbar-fixed-top">

    				<div class="container">

    					<div class="navbar-header">
    						<a href="home" class="navbar-brand brand"><img src="images/nittany_lions_logo.jpg" id="logo"></a>
   						</div>


    				</div><!-- /.container -->
    			</nav>

  <div class="wrapper">
  <form class="login" action="${pageContext.servletContext.contextPath}/login" method="post">
    <p class="title">Log in</p>
    <input type="text" id="username" name="username" placeholder="Username" value="${username}" required>
    <i class="fa fa-user"></i>
    <input type="password"  id="username" name="password" placeholder="Password" value="${password}" required>
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
    	</header><!-- /.item --></div><!-- /#page -->
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
	$(function(){

		if( $('#nivoSlider').size() > 0 ) {

	    	$('#nivoSlider').nivoSlider({
	    		effect: 'random',
				pauseTime: 5000
	    	});

		}

	})
	</script>







</body>
</html>
