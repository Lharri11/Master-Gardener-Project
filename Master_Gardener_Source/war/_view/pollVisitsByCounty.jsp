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
                    <!-- County 1 -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Adams County (Total Visits: ${visitCountsTotal1})</h2>
                        </div>
                        <canvas id="county1Chart" width="50em" height="20em"></canvas>
                    </div>
                    <!-- County 2 -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Allegheny County (Total Visits: ${visitCountsTotal2})</h2>
                        </div>
                        <canvas id="county2Chart" width="50em" height="20em"></canvas>
                    </div>
                    <!-- County 3 -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Beaver County (Total Visits: ${visitCountsTotal3})</h2>
                        </div>
                        <canvas id="county3Chart" width="50em" height="20em"></canvas>
                    </div>
                    <!-- County 4 -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Berks County (Total Visits: ${visitCountsTotal4})</h2>
                        </div>
                        <canvas id="county4Chart" width="50em" height="20em"></canvas>
                    </div>
                    <!-- County 5 -->
                    <div class="col-lg-10 col-md-6 col-sm-12">
                        <div id="editContent" class="editContent">
                            <div id="intro"></div>
                            <h2>Bradford County (Total Visits: ${visitCountsTotal5})</h2>
                        </div>
                        <canvas id="county5Chart" width="50em" height="20em"></canvas>
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
<input type="hidden" id="pollinatorString" value='${pollinatorsJSON}' >
<input type="hidden" id="visitCountString1" value='${visitCountsJSON1}' >
<input type="hidden" id="visitCountString2" value='${visitCountsJSON2}' >
<input type="hidden" id="visitCountString3" value='${visitCountsJSON3}' >
<input type="hidden" id="visitCountString4" value='${visitCountsJSON4}' >
<input type="hidden" id="visitCountString5" value='${visitCountsJSON5}' >
<input type="hidden" id="visitCountTotalString1" value='${visitCountsTotal1}' >
<input type="hidden" id="visitCountTotalString2" value='${visitCountsTotal2}' >
<input type="hidden" id="visitCountTotalString3" value='${visitCountsTotal3}' >
<input type="hidden" id="visitCountTotalString4" value='${visitCountsTotal4}' >
<input type="hidden" id="visitCountTotalString5" value='${visitCountsTotal5}' >

<script>
    <%-- County 1 --%>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts = ${visitCountsJSON1};
            var pollinatorNames = [];
            var visitCounts = [];

            for (var i = 0; i < pollinators_json.length; i++) {
                pollinatorNames.push(pollinators_json[i].pollinatorName);
                visitCounts.push(visit_counts[i]);
            }

            var ctx = document.getElementById("county1Chart").getContext('2d');
            var adamsCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: visitCounts,
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
                    labels: pollinatorNames,
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>

<script>
    <%-- County 2 --%>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts = ${visitCountsJSON2};
            var pollinatorNames = [];
            var visitCounts = [];

            for (var i = 0; i < pollinators_json.length; i++) {
                pollinatorNames.push(pollinators_json[i].pollinatorName);
                visitCounts.push(visit_counts[i]);
            }

            var ctx = document.getElementById("county2Chart").getContext('2d');
            var adamsCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: visitCounts,
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
                    labels: pollinatorNames,
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>

<script>
    <%-- County 3 --%>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts = ${visitCountsJSON3};
            var pollinatorNames = [];
            var visitCounts = [];

            for (var i = 0; i < pollinators_json.length; i++) {
                pollinatorNames.push(pollinators_json[i].pollinatorName);
                visitCounts.push(visit_counts[i]);
            }

            var ctx = document.getElementById("county3Chart").getContext('2d');
            var adamsCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: visitCounts,
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
                    labels: pollinatorNames,
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>

<script>
    <%-- County 4 --%>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts = ${visitCountsJSON4};
            var pollinatorNames = [];
            var visitCounts = [];

            for (var i = 0; i < pollinators_json.length; i++) {
                pollinatorNames.push(pollinators_json[i].pollinatorName);
                visitCounts.push(visit_counts[i]);
            }

            var ctx = document.getElementById("county4Chart").getContext('2d');
            var adamsCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: visitCounts,
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
                    labels: pollinatorNames,
                },
                options: {
                    responsive: true
                }
            });
        },
    );
</script>

<script>
    <%-- County 5 --%>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts = ${visitCountsJSON5};
            var pollinatorNames = [];
            var visitCounts = [];

            for (var i = 0; i < pollinators_json.length; i++) {
                pollinatorNames.push(pollinators_json[i].pollinatorName);
                visitCounts.push(visit_counts[i]);
            }

            var ctx = document.getElementById("county5Chart").getContext('2d');
            var adamsCountyChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    datasets: [{
                        data: visitCounts,
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
                    labels: pollinatorNames,
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