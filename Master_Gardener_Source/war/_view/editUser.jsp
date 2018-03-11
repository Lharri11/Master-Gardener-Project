<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html><head>
	<link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator" content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">  
    <title>Edit ${account.name} - Master Gardener</title>
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
    	
    			<nav role="navigation" class="navbar navbar-blue navbar-embossed navbar-lg navbar-fixed-top">
    					
    				<div class="container">
    				
    					<div class="navbar-header">
    						<button data-target="#navbar-collapse-02" data-toggle="collapse" class="navbar-toggle" type="button">
    							<span class="sr-only">Toggle navigation</span>
    						</button>
    						<a href="home" class="navbar-brand brand"><img src="images/nittany_lions_logo.jpg" id="logo"></a>
   						</div>
    					<!--/.navbar-header -->
    					<!-- HERE IS YOUR SEARCH JSP THING -->
    					<form class="search-collapse"
							action="${pageContext.servletContext.contextPath}/user" method="post">
							<input type="text" id="searchText" name="keyword" placeholder="Search..." required> 
							<input type="image" src="images/search.png" value="Search" id="searchButton">
						</form><br>
	                    
    					<div id="navbar-collapse-02" class="collapse navbar-collapse" >
    						<ul class="nav navbar-nav navbar-right">
    													<li class="propClone">
									<form action="${pageContext.servletContext.contextPath}/data" method="get">
  										<input name="buttonPress" type="image" value="Data" class="homeLinks" src="images/Data-Board.png" title="Data"/>
									</form>
								</li>
								<li class="propClone">
									<form action="${pageContext.servletContext.contextPath}/user" method="get">
  										<input name="buttonPress" type="image" value="Account" class="homeLinks" src="images/user.png" title="My Account"/>
									</form>
								</li>
								<li class="propClone">
									<form action="${pageContext.servletContext.contextPath}/editUser" method="get">
  										<input name="submit" type="image" value="submit" class="homeLinks" src="images/gear.png" title="Account Settings"/>
									</form>
								</li>
								<li class="propClone">
									<input type="image" value="Logout" id="logoutSubmit" name="logoutSubmit" class="homeLinks" src="images/logout.png" title="Log Out">
								</li>
							</ul>
    					</div> 
    					<!--/.navbar-collapse -->
    				</div><!-- /.container -->
    			</nav>
    			
    		<!--pageContent-->
    			<div class="pageContent">
    			<!--FORM FOR EDITS STARTS HERE-->
				<form id="editUser" action="${pageContext.servletContext.contextPath}/editUser" method="post">
					<div class="pageInfo" style="height:100px;">
    					<input type="text" id="editName" placeholder="Change Your First Name" name="editName"/>
    					<input type="text" id="lastname" placeholder="Change Your Last Name" name="lastname"/>
    					<input type="text" id="editBio" placeholder="Change Bio" name="editBio"/>
    				</div>
    			<button type="Submit" color="white" value="Save Changes" id="saveChanges" name="EditSubmit">
					<span>Save Changes</span>
				</button>
    			</form>
					<br>
					<p><a href="${pageContext.servletContext.contextPath}/updatePassword">Update Password</a></p>
				</div>
    			
    		<!--/pageContent-->
    		
    		</div><!-- /.wrapper -->
    	
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
	
	$(function(){
		
		if( $('#nivoSlider').size() > 0 ) {
		
	    	$('#nivoSlider').nivoSlider({
	    		effect: 'random',
				pauseTime: 5000
	    	});
		
		}
		
	})
	</script>


</body></html>
