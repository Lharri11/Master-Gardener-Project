<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="generator"
		  content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
	<title>Chart Directory</title>
	<!--pageMeta-->

	<!-- Loading Bootstrap -->
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

	<!-- Loading Flat UI -->
	<link href="css/flat-ui.css" rel="stylesheet">


	<!--  <link href="css/style.css" rel="stylesheet"> -->

	<link href="css/newstyle.css" rel="stylesheet">
	<link href="css/datepicker.css" rel="stylesheet">


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
			<!--navContent-->

			<div>
				<div id="intro"></div>
				<div id="intro"></div>
				<div id="container" class="container">
					<div id="editContent" class="editContent">
						<h2>Data Chart Directory</h2>
					</div>
					<br>
					<div>
						<form action="${pageContext.servletContext.contextPath}/dataForm" method="post">
							<div>
								<table class="table">
									<thead>
										<tr>
											<th class="col-lg-2">Please select the desired data chart:</th>
										</tr>
									</thead>
									<tbody>
									<!-- Chart Selection -->
										<tr>
											<td>
												<form name="chartSelect">
													<select class="form-control" name="chartSelect" onChange="window.document.location.href=this.options[this.selectedIndex].value;" value="GO">
														<option style="display:none">Select</option>
														<option value="http://localhost:8081/Master-Gardener/pollVisits1">Pollinator Visit Counts By County</option>
														<option value="http://localhost:8081/Master-Gardener/pollVisits2">Pollinator Visit Counts For Coreopsis Plant</option>
														<option value="http://localhost:8081/Master-Gardener/pollVisits3">Pollinator Visit Counts For Monarda Plant</option>
														<option value="http://localhost:8081/Master-Gardener/pollVisits4">Pollinator Visit Counts By Pollinator Species</option>
													</select>
												</form>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</form>
						</div>
					</div>
					<!--/pageContent-->

					<div class="sidebar">
						<button onclick="toggleSideBar()" id="toggle" style="right: 0px;color: white">Options
						</button>
						<div id="sideBar" style="right: -300px;">
							<form id="GroupGet" method="post">
								<ul class="list-unstyled">
									<li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/editUser"
															   style="color: white">Account Settings</a></li>
									<li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/admin"
															   style="color: white">Administration</a></li>
									<c:forEach items="${gardens}" var="garden">
										<tr>
											<td class="groupListItem"><input class="sidebarItem" type="Submit"
																			 value="${garden.name}" id="Submit"
																			 name="Submit"></td>
										</tr>
									</c:forEach>
								</ul>

							</form>
						</div>
					</div>
					<!--/sidebar-->
				</div>
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
<script src="js/form.js"></script>
<script src="js/datepicker.js"></script>
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


<%--suppress JSUndefinedPropertyAssignment --%>
<script language='JavaScript'>
    function getSubmitTime() {
        var date = new Date();
        var d = date.getUTCDate();
        var day = (d < 10) ? '0' + d : d;
        var m = date.getUTCMonth() + 1;
        var month = (m < 10) ? '0' + m : m;
        var year = date.getUTCFullYear();
        var time = month + day + year;
        document.getElementById('dataFromSubmit').value = time;
    }
</script>


</body>
</html>