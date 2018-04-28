<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Pollinator Preferences</title>

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
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/user">My Account</a>
                </li>
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

<div class="container search-page-top-padding">

    <table id="allGardensTable" class="table table-striped table-borded" style="width:100%"></table>

</div>

<!-- Load JS here for greater good =============================-->
<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="vendor/scrollreveal/scrollreveal.js"></script>
<script src="vendor/DataTables/datatables.js"></script>


<script>
    var allGardens = ${allGardens};


    var table = $('#allGardensTable').resize().DataTable({

        data: allGardens,

        columns: [{
            data: 'garden_name',
            className: 'center',
            title: 'Garden Name'
        },
            {
                width: '50%',
                orderable: false,
                title: 'Join A Garden'

            },
        ],
        "columnDefs": [{
            "targets": -1,       // -1 = last column
            "data": null,        // no data for this column, instead we will show default content, described in 'defaultContent'
            "defaultContent": "<button id='submit-btn' class='btn btn-primary rounded-pill align-content-lg-center'>Submit</button>"
        }],
    });

    $('#allGardensTable').on('click', 'button', function () {
        // create an object from a row data
        var garden = table.row($(this).parents('tr')).data();
        // fire a function, based on the button id that was clicked
        if (this.id === 'submit-btn') {
            joinSomeGarden(garden);
        }
    });


    function joinSomeGarden(joining) {
        var gardenData = {garden: JSON.stringify(joining)};

        try {
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/search",
                data: gardenData
            });
        } catch (err) {
            alert(err.message);
        } finally {
           //alert("You added " + JSON.stringify(joining.garden_name) + " to your account");
            reloadTable();

        }
    }

    //TODO: This does not work right. It works once and that's it
    function reloadTable() {
       var thisreDraw = $("#allGardensTable").DataTable().draw();
       thisreDraw.draw();

    }

    

</script>


</body>
</html>