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
                        <%--<a class="nav-link" name="loginSubmit"
                           href="javascript:;" onclick="document.getElementById('logout_form').submit()">Logout</a>
                        <input type="hidden" name="logout" value="Logout"/>--%>
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

    <h2>Plant: Monarda (Total Visits: 4049)</h2>
    <br><br>

    <div class="row">
        <div class="col-lg-12">
            <h2>Strain: Didyma Straight</h2>
            <canvas id="myChart" class="col-lg-12"></canvas>

        </div>
    </div>

    <br><br><br>

    <div class="row">
        <div class="col-lg-12">
            <h2>Strain: Didyma Petite Delight</h2>
            <canvas id="myChart2" class="col-lg-12"></canvas>
        </div>
    </div>

    <br><br><br>

    <div class="row">
        <div class="col-lg-12">
            <h2>Strain: Didyma Marshalls Delight</h2>

            <canvas id="myChart3" class="col-lg-12"></canvas>
        </div>
    </div>

    <br><br><br>

    <div class="row">
        <div class="col-lg-12">
            <h2>Strain: X Peters Fancy Fuschia</h2>

            <canvas id="myChart4" class="col-lg-12"></canvas>
        </div>
    </div>
</div>

<!-- /#page -->
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

<!-- First graph -->
<script>
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
            datasets: [{
                display: false,
                label: '# of Pollinators',
                data: [95, 105, 112, 60, 145, 27, 46, 93],
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
</script>
<!-- Second graph -->
<script>
    var ctx = document.getElementById("myChart2").getContext('2d');
    var myChart2 = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
            datasets: [{
                label: '# of Pollinators',
                data: [39, 50, 104, 26, 81, 30, 37, 58],
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
</script>
<!-- third graph -->
<script>
    var ctx = document.getElementById("myChart3").getContext('2d');
    var myChart3 = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
            datasets: [{
                display: false,
                label: '# of Pollinators',
                data: [112, 102, 208, 59, 212, 71, 61, 89],
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
</script>

<!-- fourth graph -->
<script>
    var ctx = document.getElementById("myChart4").getContext('2d');
    var myChart4 = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Honey Bee", "Carpenter Bee", "Bumble Bee", "Green Sweat Bee", "Dark Sweat Bee", "Butterfly/Moth", "Other Bee", "Other Pollinator"],
            datasets: [{
                display: false,
                label: '# of Pollinators',
                data: [641, 475, 318, 128, 242, 49, 74, 100],
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
</script>


</body>
</html>
