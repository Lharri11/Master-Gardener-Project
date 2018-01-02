<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator" content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
    <!--pageMeta-->

    <title>PSU Master Gardeners</title>

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
                        <a href="home" class="navbar-brand brand">
                            <img src="images/nittany_lions_logo.jpg" id="logo">
                        </a>
                        <button data-target="#navbar-collapse-02" data-toggle="collapse"
                                class="navbar-toggle" type="button">
                            <span class="sr-only">Toggle navigation</span>
                        </button>
                    </div>
                    <br>

                    <div id="navbar-collapse-02" class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="propClone">
                                <form action="${pageContext.servletContext.contextPath}/login" method="get">
                                    <button class="homeLinks-nav" type="submit">Login</button>
                                </form>
                            </li>

                            <li class="propClone">
                                <form action="${pageContext.servletContext.contextPath}/signup" method="get">
                                    <button class="homeLinks-nav" type="submit">Create Account</button>
                                </form>
                            </li>

                            <li class="propClone">
                                <form action="${pageContext.servletContext.contextPath}/user" method="get">
                                    <button class="homeLinks-nav" type="submit">My Account</button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>


                <!-- /.container -->
            </nav>


            <!-- // End Nav Bar --><!-- Start Content Block -->
            <div id="intro"></div>
            <div id="intro"></div>
            <div id="container" class="container">
                <div class="col-lg-7 col-md-6 col-sm-12">
                    <div id="editContent" class="editContent">
                        <h1>Our Mission</h1>
                    </div>
                    <div id="editContent" class="editContent">
                        <p class="lead">
                            The Penn State Master Gardener volunteer program supports the
                            outreach mission of Penn State Extension by utilizing unbiased
                            research-based information to educate the public and our communities
                            on best practices in sustainable horticulture and environmental
                            stewardship.
                        </p>
                    </div>
                </div>
                <div class="col-lg-5 col-md-6 col-sm-10">
                    <div id="divSpacer"></div>
                    <!-- /.spacer -->
                    <div id="frameRight">
                        <img class="img-responsive" src="images/black_gardener_logo.jpg">
                    </div>
                </div>

                <div class="col-lg-12 col-md-6 col-sm-12">
                    <div id="editContent" class="editContent">
                        <h1>About Us</h1>
                    </div>
                    <div id="editContent" class="editContent">
                        <p class="lead">
                            Master Gardener volunteers support Penn State Extension's educational programs in consumer
                            horticulture.
                            They develop their horticultural expertise through participation in educational training
                            classes
                            conducted by Penn State University faculty and Extension staff.
                            <br>
                            <br>
                            A Penn State Master Gardener not only learns research-based and sustainable horticultural
                            practices, but
                            also develops life-long friendships with others who have similar gardening interests.
                            <br>
                            <br>
                            After completing their training, Master Gardeners help Extension better serve the home
                            gardening
                            public
                            by answering questions, speaking to groups, writing gardening articles, working with youth,
                            gardening in
                            the demonstration gardens, participating in the Penn State pollinator research program, and
                            in
                            many
                            other ways.
                        </p>
                    </div>
                </div>

                <div id="centerDiv2">
                    <div id="divSpacer2"></div>
                    <!-- /.spacer -->
                    <p class="bold" align="center">
                        Penn State Extension | 112 Pleasant Acres Rd | York, PA 17403
                    </p>
                    <div id="centerDiv2">
                        <img src="images/YCP_Logo.jpg" style="width:7%; height:auto; float: left">
                        <p>Created by the chosen four of Computer Science at York College of PA</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container -->
        <!-- // End Content Block 1-5 v1 -->
    </header><!-- /.item -->
</div><!-- /#page -->


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