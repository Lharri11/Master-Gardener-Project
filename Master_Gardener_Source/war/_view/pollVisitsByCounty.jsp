<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${user.firstName} - Pollinator Preferences</title>
    <!--pageMeta-->

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


<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home">Pollinator Preferences</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <%--TODO: Fix logout function, does not function as inteneded...add seperate servlet--%>

        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <form id="logout" action="${pageContext.servletContext.contextPath}/login" method="post">
                    <li class="nav-item">


                        <button class="nav-link" name="loginSubmit" value="Logout" type="submit"
                                alt="submit" title="Log Out">Logout
                        </button>
                        <%--<a class="nav-link" name="loginSubmit"
                           href="javascript:;" onclick="document.getElementById('logout_form').submit()">Logout</a>
                        <input type="hidden" name="logout" value="Logout"/>--%>
                    </li>
                </form>
            </ul>
        </div>
    </div>
</nav>

<div class="container user-page-top-padding">
    <div class="row">
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
<script src="vendor/Charts/js/Chart.js"></script>
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