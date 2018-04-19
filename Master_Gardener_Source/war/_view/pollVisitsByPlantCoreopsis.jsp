<%--
  Created by IntelliJ IDEA.
  User: Nick
  Date: 11/26/2017
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <!-- <link href="css/style.css" rel="stylesheet"> -->

    <link href="css/newstyle.css" rel="stylesheet">

    <link href="css/datatables.css" rel="stylesheet">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <!--headerIncludes-->


</head>
<body>

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
                    <form action="${pageContext.servletContext.contextPath}/dataInput" method="get">
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

<div id="navAdmin" class="sidenavAdmin">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="${pageContext.servletContext.contextPath}/chartGen">Chart Generation</a>
    <a href="${pageContext.servletContext.contextPath}/editDb">Edit Database</a>
    <a href="${pageContext.servletContext.contextPath}/login">User Manipulation</a>
</div>

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
                        <select class="form-control" name="chartSelect"
                                onChange="window.document.location.href=this.options[this.selectedIndex].value;" value="GO">
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

<div>
    <span style="font-size:1.875em;cursor:pointer" onclick="openNav()">&#9776; Admin Controls</span>
    <div></div>
    <div><h2>Plant: Coreopsis Verticillata (Total Visits: 5109)</h2></div>
    <div>
        <div class="col-lg-10 col-md-6 col-sm-12">
            <div id="editContent" class="editContent">
                <h2>Strain: Straight</h2>
            </div>
            <canvas id="myChart" width="50em" height="20em"></canvas>
        </div>
    </div>


    <div>
        <div class="col-lg-10 col-md-6 col-sm-12">
            <div id="editContent" class="editContent">
                <h2>Strain: Moonbeam</h2>
            </div>
            <canvas id="myChart2" width="50em" height="20em"></canvas>
        </div>
    </div>
    <div>
        <div class="col-lg-10 col-md-6 col-sm-12">
            <div id="editContent" class="editContent">
                <h2>Strain: Zagreb</h2>
            </div>
            <canvas id="myChart3" width="50em" height="20em"></canvas>
        </div>
    </div>
    <div>
        <div class="col-lg-10 col-md-6 col-sm-12">
            <div id="editContent" class="editContent">
                <h2>Strain: Creme Brulee</h2>
            </div>
            <canvas id="myChart4" width="50em" height="20em"></canvas>
        </div>
    </div>
    <div>
        <div class="col-lg-10 col-md-6 col-sm-12">
            <div id="editContent" class="editContent">
                <h2>Strain: Route 66</h2>
            </div>
            <canvas id="myChart5" width="50em" height="20em"></canvas>
        </div>
    </div>
    <!--/pageContent-->

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
<script src="js/datatables.js"></script>
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
    function openNav() {
        document.getElementById("navAdmin").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("navAdmin").style.width = "0";
    }
</script>

<input type="hidden" id="pollinatorsString" value='${pollinatorsJSON}' >
<input type="hidden" id="visitCountsString1" value='${visitCountsStraightJSON}' >
<input type="hidden" id="visitCountsString2" value='${visitCountsMoonbeamJSON}' >
<input type="hidden" id="visitCountsString3" value='${visitCountsDidymaMarshallsJSON}' >
<input type="hidden" id="visitCountsString4" value='${visitCountsXPetersJSON}' >

<!-- First graph -->
<script>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts_straight_json = ${visitCountsStraightJSON};
            var pollinatorNames = [];
            var visitCountsStraight = [];

            for (var i = 0; i < pollinators_json.length; i++) {
                pollinatorNames.push(pollinators_json[i].pollinatorName);
                visitCounts.push(visit_counts_json[i]);
            }

            var ctx = document.getElementById("myChart").getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
                    datasets: [{
                        display: false,
                        label: '# of Pollinators',
                        data: [97, 4, 21, 95, 263, 37, 71, 105],
                        backgroundColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'

                        ],
                        borderColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Pollinator'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Visit Count'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }]
                    }
                }
            })
        },
    );
</script>
<!-- Second graph -->
<script>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts_moonbeam_json = ${visitCountsMoonbeamJSON};
            var pollinatorNames = [];
            var visitCountsMoonbeam = [];

            var ctx = document.getElementById("myChart2").getContext('2d');
            var myChart2 = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
                    datasets: [{
                        label: '# of Pollinators',
                        data: [227, 6, 16, 77, 416, 59, 83, 95],
                        backgroundColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'

                        ],
                        borderColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Plant Strain'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Visit Count'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }]
                    }
                }
            });
        },
    );
</script>
<!-- third graph -->
<script>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts_zagreb_json = ${visitCountsZagrebJSON};
            var pollinatorNames = [];
            var visitCountsZagreb = [];

            var ctx = document.getElementById("myChart3").getContext('2d');
            var myChart3 = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
                    datasets: [{
                        display: false,
                        label: '# of Pollinators',
                        data: [202, 7, 32, 99, 576, 51, 74, 91],
                        backgroundColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'

                        ],
                        borderColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Pollinator'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Visit Count'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }]
                    }
                }
            });
        },
    );
</script>

<!-- fourth graph -->
<script>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts_creme_json = ${visitCountsCremeJSON};
            var pollinatorNames = [];
            var visitCountsCreme = [];

            for (var i = 0; i < pollinators_json.length; i++) {
                pollinatorNames.push(pollinators_json[i].pollinatorName);
                visitCountsCreme.push(visit_counts_creme_json[i]);
            }

            var ctx = document.getElementById("myChart4").getContext('2d');
            var myChart4 = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
                    datasets: [{
                        display: false,
                        label: '# of Pollinators',
                        data: [132, 7, 38, 70, 386, 68, 92, 150],
                        backgroundColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'

                        ],
                        borderColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Pollinator'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Visit Count'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }]
                    }
                }
            });
        },
    );
</script>

<!-- Fifth graph -->
<script>
    $(document).ready(
        function() {
            var pollinators_json = ${pollinatorsJSON};
            var visit_counts_route_json = ${visitCountsCremeJSON};
            var pollinatorNames = [];
            var visitCountsRoute = [];

            for (var i = 0; i < pollinators_json.length; i++) {
                pollinatorNames.push(pollinators_json[i].pollinatorName);
                visitCountsRoute.push(visit_counts_route_json[i]);
            }

            var ctx = document.getElementById("myChart5").getContext('2d');
            var myChart5 = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
                    datasets: [{
                        display: false,
                        label: '# of Pollinators',
                        data: [365, 4, 13, 195, 519, 36, 102, 128],
                        backgroundColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'

                        ],
                        borderColor: [
                            'rgba(255, 0 , 0, 0.75)',
                            'rgba(255, 127, 0, 0.75)',
                            'rgba(255, 255, 0, 0.75)',
                            'rgba(0, 255, 0, 0.75)',
                            'rgba(0, 0, 255, 0.75)',
                            'rgba(75, 0, 130, 0.75)',
                            'rgba(148, 0, 211, 0.75)',
                            'rgba(225, 0, 255, 0.75)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Pollinator'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                fontSize: 17,
                                labelString: 'Visit Count'
                            },
                            ticks: {
                                beginAtZero: true,
                            }
                        }]
                    }
                }
            });
        },
    );
</script>

</body>
</html>
