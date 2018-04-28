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
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/login">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%--<div class="container-fluid search-page-top-padding">
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
            <div class="align-content-center">
                <div class="input-group stylish-input-group">
                    <input type="text" class="form-control" placeholder="Search...">
                    <span class="input-group-addon">
                        <button type="submit">
                            <span class="fas fa-search"></span>
                        </button>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>--%>

<%--<form class="search-collapse"
      action="${pageContext.servletContext.contextPath}/user" method="post">
    <input type="text" id="searchText" name="keyword" placeholder="Search..." required>
    <input type="image" src="images/search_black.jpg" value="Search" id="searchButton">
</form>--%>


<div class="container search-page-top-padding">
    <%-- <form action="${pageContext.servletContext.contextPath}/user" method="post">
     <div class="row">

         <div class="input-group mb-3">

                 <input type="text" name="keyword" class="form-control" placeholder="Enter County Name"
                        aria-label="Search term"
                        aria-describedby="basic-addon">
                 <div class="input-group-append">
                     <button class="btn btn-secondary" type="button" value="Search">Search!</button>
                 </div>
             </form>
         </div>
     </div>--%>

    <table id="allGardensTable" class="table table-striped table-borded" style="width:100%"></table>
    <%--<table id="example" class="display"></table>--%>


    <%-- <div class="row">
         <table id="allGardensTable">
             <thead>
             <tr>
                 <th>Garden Name</th>
             </tr>
             </thead>

             <tbody>
             <c:forEach items="${gardens}" var="gardens">
                 <tr>
                     <td>${gardens.garden_name}</td>
                     &lt;%&ndash;<td>
                         <button>Join Garden</button>
                     </td>&ndash;%&gt;
                 </tr>
             </c:forEach>
             </tbody>

         </table>
     </div>--%>


</div>

<%--<div class="container">
    <div class="results">

        <h1>Results:</h1>


        <form id="GroupGet" method="post">
            <table id="userGroups">
                <c:forEach items="${gardens}" var="garden">
                    <tr class="groupList">
                        <td style="color:white"><input type="submit" value="${garden.garden_name}" id="groupDisplay"
                                                       name="Submit" class="buttonAsLink"></td>
                    </tr>
                </c:forEach>
            </table>
        </form>

    </div>
</div>--%>

<%--
   <div id="page" class="page">

   <header class="item header margin-top-0 header10" id="header10">

           <div class="wrapper">

               <nav role="navigation" class="navbar navbar-blue navbar-embossed navbar-lg navbar-fixed-top">

                   <div class="container">

                       <div class="navbar-header">
                           <button data-target="#navbar-collapse-02" data-toggle="collapse" class="navbar-toggle" type="button">
                               <span class="sr-only">Toggle navigation</span>
                           </button>
                           <a href="home" class="navbar-brand brand"><img src="images/nittany_lions_logo.jpg" id="logo"></a>
                          </div>
                       <!--/.navbar-header -->
                       <!-- HERE IS YOUR SEARCH JSP THING -->
                       <form class="search-collapse"
                           action="${pageContext.servletContext.contextPath}/user" method="post">
                           <input type="text" id="searchText" name="keyword" placeholder="Search..." required>
                           <input type="image" src="images/search_black.jpg" value="Search" id="searchButton">
                       </form><br>

                       <div id="navbar-collapse-02" class="collapse navbar-collapse">
                           <ul class="nav navbar-nav navbar-right">
                               <li class="propClone">
                                   <form action="${pageContext.servletContext.contextPath}/user" method="get">
                                         <input name="buttonPress" type="image" value="Account" class="homeLinks" src="images/user.png" title="My Account"/>
                                   </form>
                               </li>
                               <li class="propClone">
                                   <form action="${pageContext.servletContext.contextPath}/editUser" method="get">
                                         <input name="submit" type="image" value="submit" class="homeLinks" src="images/gear.png" title="Account Settings"/>
                                   </form>
                               </li>
                               <li class="propClone">
                                   <input type="image" value="Logout" id="loginSubmit" name="logoutSubmit" class="homeLinks" src="images/logout.png" title="Log Out">
                               </li>
                           </ul>
                       </div>
                       <!--/.navbar-collapse -->
                   </div><!-- /.container -->
               </nav>

           <!--pageContent-->
               <div class="pageContent">
                   <div class="results">

                       <h1>Results:</h1>


                       <form id="GroupGet" method="post">
                               <table id="userGroups">
                                   <c:forEach items="${gardens}" var="garden">
                                       <tr class="groupList">
                                       <td style="color:white"><input type="submit" value="${garden.garden_name}" id="groupDisplay" name="Submit" class="buttonAsLink"></td>
                                       <td class="descriptionCol" style="color:white">"${garden.description}"</td>
                                       </tr>
                                   </c:forEach>
                               </table>
                       </form>

                   </div>
               </div>

           <!--/pageContent-->

           </div><!-- /.wrapper -->

       </header><!-- /.item --></div><!-- /#page -->--%>
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

        }
    }

</script>


</body>
</html>