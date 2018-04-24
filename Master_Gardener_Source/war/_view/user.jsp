<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator"
          content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
    <title></title>
    <!--pageMeta-->

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

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
        <div class="col-lg-12 order-lg-3">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="" data-target="#profile" data-toggle="tab" class="nav-link active">Profile</a>
                </li>
                <li class="nav-item">
                    <a href="" data-target="#edit" data-toggle="tab" class="nav-link">Edit Account</a>
                </li>
            </ul>
            <div class="tab-content py-4">
                <div class="tab-pane active" id="profile">
                    <h5 class="mb-3">User Profile</h5>
                    <div class="row">
                        <div class="col-md-6">
                            <h6>About</h6>
                            <p>
                                ${user.firstName} ${user.lastName} <br>${user.email}
                            </p>
                            <h6>Bio</h6>
                            <p>
                                ${user.description}
                            </p>
                        </div>
                        <div class="col-md-6 align-content-center">
                            <h5>Wish to add a new garden?</h5>
                            <h6>Click below to search for one</h6>
                            <a class="btn btn-primary btn-lg rounded-pill mt-5 js-scroll-trigger"
                               href="${pageContext.servletContext.contextPath}/search">Search</a>

                        </div>
                        <div class="col-md-12">
                            <h5 class="mt-2"><span class="fa fa-clock-o ion-clock float-right"></span> Gardens you
                                belong to:
                            </h5>
                            <h6>Click on the garden name to go to its page</h6>

                            <form id="garden-nav" method="post">
                                <table class="table table-sm table-hover table-striped">


                                    <%--<a class="nav-link" href="${pageContext.servletContext.contextPath}/login">--%>


                                    <tbody>
                                    <c:forEach items="${gardens}" var="garden">
                                        <tr>
                                            <td>
                                                <strong><input type="submit" value="${garden.garden_name}"
                                                               id="groupDisplay"
                                                               name="Submit">
                                                </strong>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </form>
                        </div>


                    </div>
                    <!--/row-->
                </div>

            <!--pageContent-->
            <div>
                <div id="coverPhotoContainer">
                    <img src="portraits/${user.username}.jpg" id="userPhoto">
                </div>
                <div class="pageInfo" style="height: 100px;">
                    <p id="userName">${user.username}</p>
                    <div id="bio">${user.description}</div>

                    <h1 style="position:relative;left:10px;">Gardens you belong to:</h1>

                    <form id="GroupGet" method="post">
                        <table id="userGroups">
                            <c:forEach items="${gardens}" var="garden">
                                <tr class="groupList">
                                    <td style="color:white"><input type="submit" value="${garden.garden_name}" id="groupDisplay"
                                                                   name="Submit" class="buttonAsLink"></td>
                                    <td class="descriptionCol" style="color:white">"${garden.description}"</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- Load JS here for greater good =============================-->
<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="vendor/scrollreveal/scrollreveal.js"></script>
<script src="vendor/Popper/Popper.js"></script>
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
    // sandbox disable popups
    if (window.self !== window.top && window.name != "view1") {
        ;
        window.alert = function () {/*disable alert*/
        };
        window.confirm = function () {/*disable confirm*/
        };
        window.prompt = function () {/*disable prompt*/
        };
        window.open = function () {/*disable open*/
        };
    }

    // prevent href=# click jump
    document.addEventListener("DOMContentLoaded", function () {
        var links = document.getElementsByTagName("A");
        for (var i = 0; i < links.length; i++) {
            if (links[i].href.indexOf('#') != -1) {
                links[i].addEventListener("click", function (e) {
                    console.debug("prevent href=# click");
                    if (this.hash) {
                        if (this.hash == "#") {
                            e.preventDefault();
                            return false;
                        }
                        else {
                            /*
                            var el = document.getElementById(this.hash.replace(/#/, ""));
                            if (el) {
                              el.scrollIntoView(true);
                            }
                            */
                        }
                    }
                    return false;
                })
            }
        }
    }, false);
</script>

<script>
    $(document).ready(function () {
        $("a.submit-garden[form='garden-nav']").click(function () {
            document.getElementById("garden-nav").submit();
        });
    });
</script>

</body>
</html>
