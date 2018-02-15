<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="generator"
		  content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
	<title>Data Input</title>
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

<!--

The plant vigor is first since this will force the user to enter in whether it's dead or not. If it's, dead, everything will
be greyed out except the plot size.

-->


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


			<!--pageContent-->
			<div>
				<div id="intro"></div>
				<div id="intro"></div>
				<div id="container" class="container">

					<%--
					<!-- This is for dummy reasons and test purposes -->
					<div>
						<table align="center">
							<tr>
								<td>For testing purposes the users collected, garden, county, plant and strain will be entered by the user.</td>
							</tr>
						</table>
					</div> --%>
					<div id="editContent" class="editContent">
						<h2>Data Collection Input</h2>
					</div>
					<br>


					<div>
						<div>

							<form action="${pageContext.servletContext.contextPath}/dataForm" method="post"
								  onsubmit=getSubmitTime()>


								<table align="center">

									<%--
									<tr>
										<td>User Collected:</td>
										<td><input type="text" class="text-control" name="genUsers"
												   placeholder="User Collected"
												   value="${genUsers}"></td>


										<td>Garden:</td>
										<td><input type="text" class="text-control" name="garden"
												   placeholder="Garden"
												   value="${garden}"></td>


										<td>County:</td>
										<td><input type="text" class="text-control" name="county"
												   placeholder="County"
												   value="${county}"></td>

										<td>Week:</td>
										<td><input type="text" class="text-control" name="Week"
												   placeholder="Week"
												   value="${Week}"></td>

									</tr>
									<tr>
										<td>
											<br/>

										</td>
									</tr> --%>

									<tr>
										<td>Collection Date:</td>
										<td><input data-toggle="datepicker" type="text" class="text-control" name="dateCollected"
												   placeholder="Collection Date"
												   value="${dateCollected}" required autocomplete="off"></td>
										<div data-toggle="datepicker"></div>


										<td>Start Time:</td>
										<td><input type="time" class="text-control" name="startTime"
												   placeholder="Start Time"
												   value="${startTime}" required autocomplete="off"></td>


										<td>End Time:</td>
										<td><input type="time" class="text-control" name="endTime"
												   placeholder="End Time"
												   value="${endTime}" required autocomplete="off"></td>

									</tr>
									<tr>
										<td>
											<br/>

										</td>
									</tr>


									<tr>
										<td>Temperature:</td>
										<td><input type="text" class="text-control"
												   name="temperature"
												   placeholder="Temperature"
												   value="${temperature}" required autocomplete="off"></td>


										<td>Wind:</td>
										<td><input type="text" class="text-control" name="wind"
												   placeholder="Wind"
												   value="${wind}" required autocomplete="off">
										</td>


										<td>Cloud Cover:</td>
										<td><input type="text" class="text-control" name="cloudCover"
												   placeholder="Cloud Cover"
												   value="${cloudCover}" required autocomplete="off"></td>
									</tr>

								</table>


								<div id="intro"></div>
								<div id="intro"></div>
								<div align="center">
									<!--
                                    <h3>Plant (Link to Plant Main)</h3>
                                    -->
									<table align="center">
										<tr>
											<td>Plant Genus:</td>
											<td><input type="text" class="text-control" name="plantGenus"
													   placeholder="Plant Genus"
													   value="${plantGenus}" required autocomplete="off"></td>
										</tr>
									</table>
									<hr>
								</div>


								<div>

									<!-- The id here needs to increment for each strain -->

									<div class="panel panel-default" id="div-Strain1">

										<div class="panel-body">

											<div class="col-lg-5 nopadding">
												<div class="form-group">
													<div class="input-group">
														<div>
															<h4>Plot Survey</h4>
														</div>
														<table class="table">
															<thead>
															<tr>
																<th class="col-lg-2">&nbsp</th>
																<th class="col-lg-2">&nbsp</th>
															</tr>
															</thead>
															<tbody>
															<tr>
																<td>
																	Plant Vigor
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Vigor" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="T">Thriving</option>
																		<option value="P">Poor</option>
																		<option value="D">Dead</option>
																	</select>
																</td>
															</tr>

															<tr>
																<td>
																	Average Height of Plot
																</td>
																<td>
																	<input type="text" class="form-control"
																		   name="Strain1Height"
																		   placeholder="Inches" required autocomplete="off">
																</td>

															</tr>

															<tr>
																<td>
																	Size of Plot
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1PlotSize" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="4.0">2' X 2'</option>
																		<option value="9.0">3' X 3'</option>
																		<option value="16.0">4' x 4' or greater</option>
																	</select>
																</td>
															</tr>

															<tr>
																<td>
																	Blooms Open
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Blooms" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="e">Early</option>
																		<option value="o">Peak</option>
																		<option value="f">Faded</option>
																	</select>
																</td>
															</tr>

															<tr>
																<td>
																	Flower Coverage
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Coverage" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="1.0">1% - 25%</option>
																		<option value="2.0">26% - 50%</option>
																		<option value="3.0">51% - 75%</option>
																		<option value="4.0">76% - 100%</option>
																	</select>
																</td>
															</tr>


															</tbody>
															</tr>
														</table>


													</div>

												</div>


											</div>


											<div class="col-lg-7 nopadding">
												<div class="form-group">
													<div class="input-group">
														<div>
															<!--   <h4>Pollinators</h4> -->
															<table>
																<tr>
																	<td>Plant Strain:</td>
																	<td><input type="text" class="text-control"
																			   name="Strain1Name"
																			   placeholder="Plant Strain"
																			   value="${Strain1Name}" required autocomplete="off"></td>
																</tr>
															</table>
														</div>
														<table class="table">
															<thead>
															<tr>
																<th class="col-lg-2">Genus</th>
																<th class="col-lg-2">Species</th>
																<th class="col-lg-1">Count</th>
															</tr>
															</thead>
															<tbody>


															<!-- Pollinator 1-->
															<tr>
																<td>
																	<select class="form-control"
																			name="Strain1Genus1" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Bee">Bee</option>

																	</select>
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Species1" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Honey Bee">Honey Bee</option>
																		<option value="Carpenter Bee">Carpenter Bee</option>
																		<option value="Bumble Bee">Bumble Bee</option>
																		<option value="Green Metallic Bee">Green Metallic Bee</option>
																	</select>
																</td>
																<td>
																	<input type="text" class="form-control"
																		   name="Strain1Count1"
																		   placeholder="Count">
																</td>
															</tr>


															<!-- Pollinator 2-->
															<tr>
																<td>
																	<select class="form-control"
																			name="Strain1Genus2" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Bee">Bee</option>
																	</select>
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Species2" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Honey Bee">Honey Bee</option>
																		<option value="Carpenter Bee">Carpenter Bee</option>
																		<option value="Bumble Bee">Bumble Bee</option>
																		<option value="Green Metallic Bee">Green Metallic Bee</option>
																	</select>
																</td>
																<td>
																	<input type="text" class="form-control"
																		   name="Strain1Count2"
																		   placeholder="Count">
																</td>
															</tr>


															<!-- Pollinator 3-->
															<tr>
																<td>
																	<select class="form-control"
																			name="Strain1Genus3" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Bee">Bee</option>
																	</select>
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Species3" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Honey Bee">Honey Bee</option>
																		<option value="Carpenter Bee">Carpenter Bee</option>
																		<option value="Bumble Bee">Bumble Bee</option>
																		<option value="Green Metallic Bee">Green Metallic Bee</option>
																	</select>
																</td>
																<td>
																	<input type="text" class="form-control"
																		   name="Strain1Count3"
																		   placeholder="Count" required autocomplete="off">
																</td>
															</tr>


															<!-- Pollinator 4-->
															<tr>
																<td>
																	<select class="form-control"
																			name="Strain1Genus4" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Bee">Bee</option>
																	</select>
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Species4" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Honey Bee">Honey Bee</option>
																		<option value="Carpenter Bee">Carpenter Bee</option>
																		<option value="Bumble Bee">Bumble Bee</option>
																		<option value="Green Metallic Bee">Green Metallic Bee</option>
																	</select>
																</td>
																<td>
																	<input type="text" class="form-control"
																		   name="Strain1Count4"
																		   placeholder="Count" required autocomplete="off">
																</td>
															</tr>


															<!-- Pollinator 5-->
															<tr>
																<td>
																	<select class="form-control"
																			name="Strain1Genus5" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Butterflies & Moths">Butterflies & Moths</option>

																	</select>
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Species5" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Butterfly & Moths">Butterflies & Moths</option>
																	</select>
																</td>
																<td>
																	<input type="text" class="form-control"
																		   name="Strain1Count5"
																		   placeholder="Count" required autocomplete="off">
																</td>
															</tr>


															<!-- Pollinator 6-->
															<tr>
																<td>
																	<select class="form-control"
																			name="Strain1Genus6" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Other">Other</option>
																	</select>
																</td>
																<td>
																	<select class="form-control"
																			name="Strain1Species6" required autocomplete="off">
																		<option style="display: none" value="">Select</option>
																		<option value="Other">Wasps, Flies, Beetles, etc</option>
																	</select>
																</td>
																<td>
																	<input type="text" class="form-control"
																		   name="Strain1Count6"
																		   placeholder="Count" required autocomplete="off">
																</td>
															</tr>


															</tbody>
														</table>


													</div>
												</div>
											</div>


											<div class="clear"></div>

										</div>
									</div>


								</div>
								<div id="new_poll_fields">

								</div>

								<div class="input-group-btn">
									Enter Another Strain
									<button class="btn btn-success" type="button"
											onclick="new_poll_fields();"><span
											class="glyphicon glyphicon-plus"
											aria-hidden="true"></span></button>
								</div>
								<%--<textarea rows="4" cols="50" id="newPost" name="newPost"
										  placeholder="Any comments?"
										  value="${postText}"></textarea>--%>

								<div id="intro"></div>

								<table>
									<tr>
										<td><input type="submit" class="text-control" name="dataFormSubmit"
												   value="Submit Data"></td>
									</tr>
								</table>

								<div id="intro"></div>

								<c:if test="${! empty errorMessage}">
									<tr>${errorMessage}</tr>
								</c:if>
							</form>
						</div>
					</div>

				</div>

				<!--/pageContent-->

			</div>


			<div class="sidebar2">
				<button onclick="toggleSideBar()" id="toggle" style="right: 0px;color: white">Options
				</button>
				<div id="sideBar2" style="right: -300px;">
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


			<!--/pageContent-->
		</div>
		<!-- /.wrapper -->

	</header>
</div>
<!-- /.item -->

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
        var x = document.getElementById('sideBar2');
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
    function getSubmitTime() {
        var date = new Date();
        var d = date.getUTCDate();
        var day = (d < 10) ? '0' + d : d;
        var m = date.getUTCMonth() + 1;
        var month = (m < 10) ? '0' + m : m;
        var year = date.getUTCFullYear();
        var time = month + day + year;
        document.getElementById('dataFormSubmit').value = time;
    }
</script>


</body>
</html>