<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator"
          content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
    <title>Data Charts</title>


    <!--pageMeta-->

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">

    <link href="css/style.css" rel="stylesheet">
    <link href="css/newstyle.css" rel="stylesheet">


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
                        <button data-target="#navbar-collapse-02" data-toggle="collapse"
                                class="navbar-toggle" type="button">
                            <span class="sr-only">Toggle navigation</span>
                        </button>
                        <a href="home" class="navbar-brand brand"><img
                                src="images/nittany_lions_logo.jpg" id="logo"></a>
                    </div>
                    <!--/.navbar-header -->
                    <!-- HERE IS YOUR SEARCH JSP THING -->
                    <form class="search-collapse"
                          action="${pageContext.servletContext.contextPath}/user"
                          method="post">
                        <input type="text" id="searchText" name="keyword"
                               placeholder="Search..." required> <input type="image"
                                                                        src="images/search_black.jpg" value="Search" id="searchButton">
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
                                <form action="${pageContext.servletContext.contextPath}/upload" method="get">
                                    <button class="homeLinks-nav" type="submit">Document Upload</button>
                                </form>
                            </li>

                            <li class="propClone">
                                <form action="${pageContext.servletContext.contextPath}/user" method="get">
                                    <button class="homeLinks-nav" type="submit">My Account</button>
                                </form>
                            </li>
                        </ul>
                    </div>
                    <!--/.navbar-collapse -->
                </div>
                <!-- /.container -->
            </nav>

            <div id="intro"></div>
            <div id="intro"></div>
            <div id="container" class="container">
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
                                        <option value="http://localhost:8081/Master-Gardener/pvCounty">Pollinator Visit Counts By County</option>
                                        <option value="http://localhost:8081/Master-Gardener/pvCoreopsis">Pollinator Visit Counts For Coreopsis Plant</option>
                                        <option value="http://localhost:8081/Master-Gardener/pvMonarda">Pollinator Visit Counts For Monarda Plant</option>
                                        <option value="http://localhost:8081/Master-Gardener/pvPollType">Pollinator Visit Counts By Pollinator Species</option>
                                    </select>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="pageContent">
                <div>
                    <!-- Adams County -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Adams County</h2>
                        </div>
                        <canvas id="adamsCountyChart" width="50em" height="20em"></canvas>
                    </div>
                    <!-- Allegheny County -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Allegheny County</h2>
                        </div>
                        <canvas id="alleghenyCountyChart" width="50em" height="20em"></canvas>
                    </div>
                    <!-- Beaver County -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Beaver County</h2>
                        </div>
                        <canvas id="beaverCountyChart" width="50em" height="20em"></canvas>
                    </div>
                    <!-- Berks County -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Berks County</h2>
                        </div>
                        <canvas id="berksCountyChart" width="50em" height="20em"></canvas>
                    </div>
                    <!-- Bradford County -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Bradford County</h2>
                        </div>
                        <canvas id="bradfordCountyChart" width="50em" height="20em"></canvas>
                        <div id="intro"></div>
                    </div>
                </div>

                <div class="sidebar">
                    <button onclick="toggleSideBar()" id="toggle" style="right: 0px;color: white">Garden Options</button>
                    <div id="sideBar" style="right: -300px;">
                        <form id="GardenGet" method="post">
                            <ul class="list-unstyled">
                                <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/documents" style="color: white">Documents</a></li>
                                <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/chartGen" style="color: white">Generate Chart</a></li>
                                <li class="sidebarItem"><a href="${pageContext.servletContext.contextPath}/createGarden" style="color: white">Create New Garden</a></li>
                                <li class="sidebarItem"><span>--------------- Your Gardens: ---------------</span></li>
                                <c:forEach items="${user_gardens}" var="user_garden">
                                    <tr>
                                        <td class="groupListItem"><input class="sidebarItem" type="Submit" value="${user_garden.garden_name}" id="Submit" name="Submit"></td>
                                    </tr>
                                </c:forEach>
                            </ul>
                        </form>
                    </div>
                </div>
                <!--/sidebar-->
            </div>
            <!--/pageContent-->
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
<script src="js/Chart.js"></script>
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

    function on() {
        document.getElementById("overlay").style.display = "block";
    }

    function off() {
        document.getElementById("overlay").style.display = "none";
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

<!--============================= Begin Chart Content =============================-->
<input type="hidden" id="pollinatorString" value='${pollinatorJSON}' >

<script>
    <%-- Adams County --%>
    $(document).ready(
        function() {
            var json;
            json = ${pollinatorJSON};

            var pollinatorNames = [];

            for (var i = 0; i < json.length; i++) {
                pollinatorNames.push(json[i].pollinatorName);
            }

            var ctx = document.getElementById("adamsCountyChart").getContext('2d');
            var adamsCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: [75, 109, 69, 0, 79, 9, 25, 20],
                        backgroundColor: [
                            'rgba(255, 0, 0, 0.75)',
                            'rgba(255, 125, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(66, 134, 244, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        label: 'Dataset 1'
                    }],
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bees", "Other Pollinators"],
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>

<script>
    <%-- Allegheny County --%>
    $(document).ready(
        function() {
            var json;
            json = ${pollinatorJSON};

            var pollinatorNames = [];

            for (var i = 0; i < json.length; i++) {
                pollinatorNames.push(json[i].pollinatorName);
            }

            var ctx = document.getElementById("alleghenyCountyChart").getContext('2d');
            var alleghenyCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: [34, 39, 6, 21, 3, 4, 22, 15],
                        backgroundColor: [
                            'rgba(255, 0, 0, 0.75)',
                            'rgba(255, 125, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(66, 134, 244, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        label: 'Dataset 1'
                    }],
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bees", "Other Pollinators"],
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>

<script>
    <%-- Beaver County --%>
    $(document).ready(
        function() {
            var json;
            json = ${pollinatorJSON};

            var pollinatorNames = [];

            for (var i = 0; i < json.length; i++) {
                pollinatorNames.push(json[i].pollinatorName);
            }

            var ctx = document.getElementById("beaverCountyChart").getContext('2d');
            var beaverCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: [8, 1, 2, 12, 26, 6, 12, 9],
                        backgroundColor: [
                            'rgba(255, 0, 0, 0.75)',
                            'rgba(255, 125, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(66, 134, 244, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        label: 'Dataset 1'
                    }],
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bees", "Other Pollinators"],
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>

<script>
    <%-- Berks County --%>
    $(document).ready(
        function() {
            var json;
            json = ${pollinatorJSON};

            var pollinatorNames = [];

            for (var i = 0; i < json.length; i++) {
                pollinatorNames.push(json[i].pollinatorName);
            }

            var ctx = document.getElementById("berksCountyChart").getContext('2d');
            var berksCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: [69, 71, 57, 139, 88, 45, 56, 70],
                        backgroundColor: [
                            'rgba(255, 0, 0, 0.75)',
                            'rgba(255, 125, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(66, 134, 244, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        label: 'Dataset 1'
                    }],
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bees", "Other Pollinators"],
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>

<script>
    <%-- Bradford County --%>
    $(document).ready(
        function() {
            var json;
            json = ${pollinatorJSON};

            var pollinatorNames = [];

            for (var i = 0; i < json.length; i++) {
                pollinatorNames.push(json[i].pollinatorName);
            }

            var ctx = document.getElementById("bradfordCountyChart").getContext('2d');
            var bradfordCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: [124, 84, 0, 63, 131, 10, 20, 53],
                        backgroundColor: [
                            'rgba(255, 0, 0, 0.75)',
                            'rgba(255, 125, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(66, 134, 244, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        label: 'Dataset 1'
                    }],
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bees", "Other Pollinators"],
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>
<!--============================== End Chart Content ==============================-->

</body>
</html>