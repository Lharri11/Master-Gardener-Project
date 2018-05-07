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

    <%-- DataTables css --%>
    <link href="vendor/DataTables/datatables.css" rel="stylesheet">

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
                        </div>
                        <div class="col-md-6 align-content-center">
                            <h5>Wish to add a new garden?</h5>
                            <h6>Click below to search for one</h6>
                            <a class="btn btn-primary btn-lg rounded-pill mt-5 js-scroll-trigger"
                               href="${pageContext.servletContext.contextPath}/search">Search</a>

                        </div>
                        <div class="col-md-12">
                            <h5 class="mt-2">Your Gardens:</h5>
                            <br>
                            <table id="userGardensTable" class="table table-striped table-borded"
                                   style="width:100%"></table>

                        </div>


                    </div>
                    <!--/row-->
                </div>

                <div class="tab-pane" id="edit">
                    <form id="change-user-settings" action="${pageContext.servletContext.contextPath}/editUser"
                          method="post" role="form">
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">First name</label>
                            <div class="col-lg-9">
                                <input class="form-control" name="editName" type="text" value="${user.firstName}"
                                       required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Last name</label>
                            <div class="col-lg-9">
                                <input class="form-control" name="lastname" type="text" value="${user.lastName}"
                                       required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Email</label>
                            <div class="col-lg-9">
                                <input class="form-control" name="email" type="email" value="${user.email}" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Add or change bio</label>
                            <div class="col-lg-9">
                                <input class="form-control" name="editBio" type="text" value="${user.description}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label"></label>
                            <div class="col-lg-9">
                                <p>Please note: Successfully saving changes will log you out</p>
                                <input type="reset" class="btn btn-secondary" value="Revert Changes">
                                <input type="submit" class="btn btn-primary" value="Save Changes" name="EditSubmit">
                            </div>
                        </div>
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
<script src="vendor/DataTables/datatables.js"></script>
<script>
    var userGardens = ${userGardens};

    var table = $('#userGardensTable').resize().DataTable({

        data: userGardens,
        columns: [{
            data: 'garden_name',
            title: 'Garden Name'
        },
            {
                orderable: false,
                title: 'Create Dataform'
            },

            {
                orderable: false,
                title: 'Leave Garden'
            },
        ],
        "columnDefs": [{
            "targets": -1,       // -1 = last column
            "data": null,        // no data for this column, instead we will show default content, described in 'defaultContent'
            "defaultContent": "<button id='Remove' class='btn btn-primary rounded-pill align-content-lg-center'>Remove</button>"
        },
            {
                "targets": -2,       // -1 = last column
                "data": null,        // no data for this column, instead we will show default content, described in 'defaultContent'
                "defaultContent": "<button id='Dataform' class='btn btn-primary rounded-pill align-content-lg-center'>Dataform</button>"
            }
        ],
    });

    $('#userGardensTable').on('click', 'button', function () {
        // create an object from a row data
        var garden = table.row($(this).parents('tr')).data();
        // fire a function, based on the button id that was clicked
        if (this.id === 'Dataform') {
            submitGardenDataform(garden)
        }
    });

    function submitGardenDataform(submit) {
        var gardenData = {garden: JSON.stringify(submit)};

        try {
            $.ajax({
                type: "GET",
                url: "${pageContext.servletContext.contextPath}/dataForm",
                data: gardenData
            });
        } catch (err) {
            alert(err.message);
        } finally {
            window.location.href = "${pageContext.servletContext.contextPath}/dataForm";
        }
    }
</script>

</body>
</html>