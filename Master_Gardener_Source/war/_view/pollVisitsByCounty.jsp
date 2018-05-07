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

        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <form id="logout" action="${pageContext.servletContext.contextPath}/login" method="post">
                    <li class="nav-item">
                        <button class="nav-link" name="loginSubmit" value="Logout" type="submit"
                                alt="submit" title="Log Out">Logout
                        </button>
                    </li>
                </form>
            </ul>
        </div>
    </div>
</nav>

<div class="container global-top-padding">
    <div class="col-lg-12">
        <div>

            <h6>Please select the desired data chart:</h6>

            <select class="form-control" name="chartSelect"
                    onChange="window.document.location.href=this.options[this.selectedIndex].value;"
                    value="GO">
                <option style="display:none">Select</option>
                <option value="${pageContext.servletContext.contextPath}/pvCounty">Pollinator
                    Visit Counts By County
                </option>
                <option value="${pageContext.servletContext.contextPath}/pvCoreopsis">Pollinator
                    Visit Counts For Coreopsis Plant
                </option>
                <option value="${pageContext.servletContext.contextPath}/pvMonarda">Pollinator
                    Visit Counts For Monarda Plant
                </option>
                <option value="${pageContext.servletContext.contextPath}/pvPollType">Pollinator
                    Visit Counts By Pollinator Species
                </option>
            </select>

        </div>

    </div>
</div>

<div class="container">
    <%--TODO: Add goto buttons for each garden and export to jpg.--%>
</div>

<div class="container global-top-padding">
    <div class="row">

        <!-- Adams County -->
        <div class="col-lg-12">
            <h2>Adams County</h2>
            <canvas id="adamsCountyChart" class="col-lg-12"></canvas>
        </div>
    </div>

    <br><br><br>
    <div class="row">
        <!-- Allegheny County -->
        <div class="col-lg-12">
            <h2>Allegheny County</h2>
            <canvas id="alleghenyCountyChart" class="col-lg-12"></canvas>
        </div>
    </div>

    <br><br><br>
    <div class="row">
        <!-- Beaver County -->
        <div class="col-lg-12">
            <h2>Beaver County</h2>
            <canvas id="beaverCountyChart" class="col-lg-12"></canvas>
        </div>
    </div>

    <br><br><br>
    <div class="row">
        <!-- Berks County -->
        <div class="col-lg-12">
            <h2>Berks County</h2>
            <canvas id="berksCountyChart" class="col-lg-12"></canvas>
        </div>
    </div>

    <br><br><br>
    <div class="row">
        <!-- Bradford County -->
        <div class="col-lg-12">
            <h2>Bradford County</h2>
            <canvas id="bradfordCountyChart" class="col-lg-12"></canvas>
        </div>
    </div>

</div>


<!-- Load JS here for greater good =============================-->
<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="vendor/scrollreveal/scrollreveal.js"></script>
<script src="vendor/Popper/Popper.js"></script>
<script src="vendor/Charts/js/Chart.js"></script>

<script>
    (function ($) {
        "use strict"; // Start of use strict

        // Smooth scrolling using jQuery easing
        $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function () {
            if (location.pathname.replace(/^\//, '') === this.pathname.replace(/^\//, '') && location.hostname === this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html, body').animate({
                        scrollTop: (target.offset().top - 57)
                    }, 1000, "easeInOutExpo");
                    return false;
                }
            }
        });
    })(jQuery);
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