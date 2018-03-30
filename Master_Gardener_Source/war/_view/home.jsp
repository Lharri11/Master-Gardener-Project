<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Pollinator Preferences</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/additional-style.css" rel="stylesheet">

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home">Pollinator Preferences</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/signup">Sign Up</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/login">Log In</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<header class="masthead text-center text-master-gardener-home-page">
    <div class="masthead-content">
        <div class="container">
            <h1 class="masthead-heading mb-0">Master Gardener</h1>
            <h2 class="masthead-subheading mb-0"><br></h2>
            <a class="btn btn-primary btn-xl rounded-pill mt-5 js-scroll-trigger" href="#learn-more">Learn More</a>
        </div>
    </div>
</header>

<section id="learn-more">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 order-lg-0">
                <div class="p-5">
                    <img class="img-fluid rounded-circle" src="images/pages/home/animal-bee-bloom-460961.jpg" alt="">
                </div>
            </div>
            <div class="col-lg-6 order-lg-1">
                <div class="p-5">
                    <h2 class="display-4">Who we are...</h2>
                    <p>The Penn State Master Gardener volunteer program supports the outreach
                        mission of Penn State Extension by utilizing unbiased research-based information
                        to educate the public and our communities on best practices in sustainable
                        horticulture and environmental stewardship.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row">
            <div class="col-lg-12 order-lg-0">
                <div class="p-5">
                    <h2 class="display-4">What are we...</h2>
                    <p>Master Gardener volunteers support Penn State Extension&apos;s educational
                        programs in consumer horticulture. They develop their horticultural expertise
                        through participation in educational training classes conducted by Penn
                        State University faculty and Extension staff.
                        <br>
                        <br>A Penn State Master Gardener not only learns research-based and sustainable
                        horticultural practices, but also develops life-long friendships with others
                        who have similar gardening interests.
                        <br>
                        <br>After completing their training, Master Gardeners help Extension better
                        serve the home gardening public by answering questions, speaking to groups,
                        writing gardening articles, working with youth, gardening in the demonstration
                        gardens, participating in the Penn State pollinator research program, and
                        in many other ways.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 order-lg-0">
                <div class="p-5">
                    <img class="img-fluid rounded-circle" src="images/pages/home/dewdrops-flora-flower-56866.jpg"
                         alt="">
                </div>
            </div>
            <div class="col-lg-6 order-lg-1">
                <div class="p-5">
                    <h2 class="display-4">Join Us</h2>
                    <p>Contact our administration to get started on your journey as a master gardener!</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="py-5 bg-black">
    <div class="container">
        <p class="m-0 text-center text-white small">Create with care and precision; York College of PA, Senior Design
            2017-2018</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<%--<script src="js/contact.js"></script>--%>

<script>
    (function ($) {
        "use strict"; // Start of use strict

        // Smooth scrolling using jQuery easing
        $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function () {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
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


</body>

</html>