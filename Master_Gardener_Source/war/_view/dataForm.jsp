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

    <script href="vendor/Moment/Moment.js"></script>
    <%-- <script href="vendor/Tempus-Dominus/js/tempusdominus-bootstrap-4.min.js"></script>
     <link href="vendor/Tempus-Dominus/css/tempusdominus-bootstrap-4.min.css">--%>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.4/moment-with-locales.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.14/moment-timezone-with-data-2012-2022.min.js"></script>


    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css"/>


    <link href="css/additional-style.css" rel="stylesheet">

    <!-- Font-Awesome javascript library -->
    <script defer src="vendor/font-awesome/fontawesome-all.js"></script>
</head>
<body>

<!--

The plant vigor is first since this will force the user to enter in whether it's dead or not. If it's, dead, everything will
be greyed out except the plot size.

-->


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
                    </li>
                </form>
            </ul>
        </div>
    </div>
</nav>


<div class="container dataform-page-top-padding">
    <form id="regForm" action="${pageContext.servletContext.contextPath}/dataForm" method="post"
          class="form-horizontal" role="form" onsubmit=getSubmitTime()>
        <div class="container">

            <%-- Garden Name Tab --%>

            <div class="tab">


                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="control-label">This Dataform is for the garden</label>

                            <input class="form-control"
                                   type="text"
                                   name="garden_name">
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="control-label"
                            >Collection Week Number</label>
                            <input class="form-control"
                                   type="text"
                                   name="weekNumber">
                        </div>
                    </div>
                </div>

            </div>


            <%-- Collector Names' Tab --%>

            <div class="tab">

                <%-- Generator 1 --%>

                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label">First Name<span
                            > *</span></label>
                            <div class="controls">
                                <input type="text" class="form-control"
                                       placeholder="First Name" name="generatorFirstName1" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label">Last Name<span> *</span></label>
                            <div class="controls">
                                <input type="text" class="form-control"
                                       placeholder="Last Name" name="generatorLastName1" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label">Username<span> *</span></label>
                            <div class="controls">
                                <select class="form-control" required="required"
                                        name="userNameSurveyor">
                                    <option value=""></option>
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <%-- Generator 2 --%>

                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <input type="text" class="form-control"
                                       placeholder="First Name" name="generatorFirstName2" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <input type="text" class="form-control"
                                       placeholder="Last Name" name="generatorLastName2" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <select class="form-control" required="required"
                                        name="userNameSurveyor">
                                    <option value=""></option>
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <%-- Generator 3 --%>

                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <input type="text" class="form-control"
                                       placeholder="First Name" name="generatorFirstName3" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <input type="text" class="form-control"
                                       placeholder="Last Name" name="generatorLastName3" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <select class="form-control" required="required"
                                        name="userNameSurveyor">
                                    <option value=""></option>
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <%-- Generator 4 --%>

                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <input type="text" class="form-control"
                                       placeholder="First Name" name="generatorFirstName4" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <input type="text" class="form-control"
                                       placeholder="Last Name" name="generatorLastName4" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <div class="controls">
                                <select class="form-control" required="required"
                                        name="userNameSurveyor">
                                    <option value=""></option>
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <%-- Plot Data --%>

            <div class="tab">
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Collection Date<span
                        > *</span></label>
                        <div class="input-group date" id="collectionDate" data-target-input="nearest">
                            <input type="text" class="form-control datetimepicker-input"
                                   data-target="#collectionDate" name="dateCollected"/>
                            <div class="input-group-append" data-target="#collectionDate"
                                 data-toggle="datetimepicker">
                                <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                            </div>
                        </div>
                    </div>


                    <div class="form-group col-lg-4">
                        <label class="control-label">Start Time<span
                        > *</span></label>
                        <div class="input-group date" id="timePickerStart" data-target-input="nearest">
                            <input type="text" class="form-control datetimepicker-input"
                                   data-target="#timePickerStart" name="startTime"/>
                            <div class="input-group-append" data-target="#timePickerStart"
                                 data-toggle="datetimepicker">
                                <div class="input-group-text"><i class="fa fa-clock"></i></div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">End Time<span> *</span></label>
                        <div class="input-group date" id="timePickerEnd" data-target-input="nearest">
                            <input type="text" class="form-control datetimepicker-input"
                                   data-target="#timePickerEnd" name="stopTime"/>
                            <div class="input-group-append" data-target="#timePickerEnd"
                                 data-toggle="datetimepicker">
                                <div class="input-group-text"><i class="fa fa-clock"></i></div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Temperature<span
                        > *</span></label>
                        <div class="controls">

                            <input type="text"
                                   class="form-control"
                                   placeholder="Temperature"
                                   name="temperature">
                        </div>

                    </div>


                    <div class="form-group col-lg-4">
                        <label class="control-label">Wind<span> *</span></label>
                        <div class="controls">

                            <select class="form-control" name="windStatus"
                                    required="required">
                                <option value=""></option>
                                <option value="S">Still</option>
                                <option value="L">Light Breeze</option>
                                <option value="W">Windy</option>
                                <option value="G">Gusty</option>
                            </select>
                        </div>

                    </div>


                    <div class="form-group col-lg-4">
                        <label class="control-label">Cloud Cover<span
                        > *</span></label>
                        <div class="controls">
                            <select class="form-control" required="required"
                                    name="cloudCover">
                                <option value=""></option>
                                <option value="C">Clear</option>
                                <option value="P">Partly Cloudy</option>
                                <option value="M">Mostly Cloudy</option>
                                <option value="O">Overcast</option>
                            </select></div>

                    </div>
                </div>
            </div>


            <%------------------------------------------------------------ Begin DataForm Lists ------------------------------------------------------------%>
            <%-------------------------------------------------- Begin Form1 --------------------------------------------------%>
            <div class="tab" id="form-submission">

                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Genus</label>
                        <input type="text" class="form-control"
                               placeholder="Coreopsis Verticillata" name="plant[1]Name"
                               value="Coreopsis Verticillata">
                    </div>


                    <div class="form-group col-lg-6">
                        <label class="control-label">Species</label>
                        <input type="text" class="form-control"
                               placeholder="Straight" name="plant[1]Strain[1]Name" value="Straight">
                    </div>
                </div>

                <br>


                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>
                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>
                    </div>
                </div>


                <div class="row">

                    <div class="form-group col-lg-6">
                        <label class="control-label">Flower Coverage<span
                        >*</span></label>

                        <select class="form-control"
                                name="plant[1]Strain[1]Plot[1]PercentCoverage" required>
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>
                    </div>
                    <div class="form-group col-lg-6">
                        <label class="control-label">Plot Area Dimensions</label>
                        <select class="form-control"
                                name="plant[1]Strain[1]Plot[1]AreaDbl" required>
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Pollinator Visit Counts</h4>
                    </div>
                    <div class="form-group col-lg-6">
                        <div id="chronoExample">
                            <div class="values">00:00:00</div>
                            <div>
                                <button class="startButton">Start</button>
                                <button class="pauseButton">Pause</button>
                                <button class="stopButton">Stop</button>
                                <button class="resetButton">Reset</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[1]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[2]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[3]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[4]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[5]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[6]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[7]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[8]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>


                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[1]Plot[1]Pollinator[9]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <%-------------------------------------------------- End Form1 --------------------------------------------------%>
            <%-------------------------------------------------- Begin Form2 --------------------------------------------------%>
            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Genus</label>
                        <input type="text" class="form-control" placeholder="Coreopsis Verticillata" name="plant[1]Name"
                               value="Coreopsis Verticillata">
                    </div>

                    <div class="form-group col-lg-6">
                        <label class="control-label">Species</label>
                        <input type="text" class="form-control" placeholder="Moonbeam" name="plant[1]Strain[2]Name"
                               value="Moonbeam">
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>
                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Flower Coverage<span>*</span></label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[2]Plot[2]PercentCoverage">
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>
                    </div>


                    <div class="form-group col-lg-6">
                        <label class="control-label">Plot Area Dimensions</label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[2]Plot[2]AreaDbl">
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Pollinator Visit Counts</h4>
                    </div>

                    <div class="form-group col-lg-6">
                        <p>
                            Time will be placed here. Hard set to 5 minutes. At the end, it will lock
                            the
                            count fields, but leave the comment section open for butterflies &amp; moths
                            if
                            &gt;0
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[1]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[2]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[3]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[4]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[5]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[6]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[7]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[8]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>


                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[2]Plot[2]Pollinator[9]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>


            <%-------------------------------------------------- End Form2 --------------------------------------------------%>
            <%-------------------------------------------------- Begin Form3 --------------------------------------------------%>
            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Genus</label>
                        <input type="text" class="form-control" placeholder="Coreopsis Verticillata"
                               name="plant[1]Name"
                               value="Coreopsis Verticillata">
                    </div>

                    <div class="form-group col-lg-6">
                        <label class="control-label">Species</label>
                        <input type="text" class="form-control" placeholder="Zagreb" name="plant[1]Strain[3]Name"
                               value="Zagreb">
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>
                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Flower Coverage<span>*</span></label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[3]Plot[3]PercentCoverage">
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>
                    </div>


                    <div class="form-group col-lg-6">
                        <label class="control-label">Plot Area Dimensions</label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[3]Plot[3]AreaDbl">
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Pollinator Visit Counts</h4>
                    </div>
                    <div class="form-group col-lg-6">
                        <p>
                            Time will be placed here. Hard set to 5 minutes. At the end, it will lock
                            the
                            count fields, but leave the comment section open for butterflies &amp; moths
                            if
                            &gt;0
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[1]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[2]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                                <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[3]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[4]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[5]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[6]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[7]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[8]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[3]Plot[3]Pollinator[9]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <%-------------------------------------------------- End Form3 --------------------------------------------------%>
            <%-------------------------------------------------- Begin Form4 --------------------------------------------------%>
            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="col-lg-6 form-group">
                        <label class="control-label">Genus</label>
                        <input type="text" class="form-control"
                               placeholder="Coreopsis Verticillata" name="plant[1]Name"
                               value="Coreopsis Verticillata">
                    </div>

                    <div class="form-group col-lg-6">
                        <label class="control-label">Species</label>

                        <input type="text" class="form-control"
                               placeholder="Crème Brulee" name="plant[1]Strain[4]Name"
                               value="Crème Brulee">
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>
                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Flower Coverage<span>*</span></label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[4]Plot[4]PercentCoverage">
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>
                    </div>
                    <div class="form-group col-lg-6">
                        <label class="control-label">Plot Area Dimensions</label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[4]Plot[4]AreaDbl">
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Pollinator Visit Counts</h4>
                    </div>
                    <div class="form-group col-lg-6">

                        <p>
                            Time will be placed here. Hard set to 5 minutes. At the end, it will lock
                            the
                            count fields, but leave the comment section open for butterflies &amp; moths
                            if
                            &gt;0
                        </p>

                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[1]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[2]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>

                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[3]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[4]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[5]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[6]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[7]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[8]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[4]Plot[4]Pollinator[9]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                </div>
            </div>
            <%-------------------------------------------------- End Form4 --------------------------------------------------%>
            <%-------------------------------------------------- Begin Form5 --------------------------------------------------%>
            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Genus</label>
                        <input type="text" class="form-control"
                               placeholder="Coreopsis Verticillata" name="plant[1]Name"
                               value="Coreopsis Verticillata">
                    </div>
                    <div class="form-group col-lg-6">
                        <label class="control-label">Species</label>

                        <input type="text" class="form-control"
                               placeholder="Route 66" name="plant[1]Strain[5]Name" value="Route 66">
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>
                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>

                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Flower Coverage<span
                        >*</span></label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[5]Plot[5]PercentCoverage">
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>

                    </div>
                    <div class="form-group col-lg-6">

                        <label class="control-label">Plot Area Dimensions</label>

                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[5]Plot[5]AreaDbl">
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Pollinator Visit Counts</h4>
                    </div>

                    <div class="form-group col-lg-6">

                        <p>
                            Time will be placed here. Hard set to 5 minutes. At the end, it will lock
                            the
                            count fields, but leave the comment section open for butterflies &amp; moths
                            if
                            &gt;0
                        </p>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[5]Plot[5]Pollinator[1]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[5]Plot[5]Pollinator[2]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[5]Plot[5]Pollinator[3]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[5]Plot[5]Pollinator[4]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[5]Plot[5]Pollinator[5]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[5]Plot[5]Pollinator[6]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[5]Plot[5]Pollinator[7]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[5]Plot[5]Pollinator[8]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>


                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text" name="plant[1]Strain[5]Plot[5]Pollinator[9]"
                                   value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>


                    </div>
                </div>
            </div>


            <%-------------------------------------------------- End Form5 --------------------------------------------------%>
            <%-------------------------------------------------- Begin Form6 --------------------------------------------------%>
            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="form-group col-lg-6">

                        <label class="control-label">Genus</label>
                        <input type="text" class="form-control"
                               placeholder="Monarda" name="plant[1]Name"
                               value="Coreopsis Verticillata">
                    </div>
                    <div class="form-group col-lg-6">
                        <label class="control-label">Species</label>
                        <input type="text" class="form-control"
                               placeholder="Didyma Straight" name="plant[1]Strain[6]Name"
                               value="Didyma Straight">

                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>
                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Flower Coverage<span>*</span></label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[6]Plot[6]PercentCoverage">
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>
                    </div>


                    <div class="form-group col-lg-6">

                        <label class="control-label">Plot Area Dimensions</label>

                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[6]Plot[6]AreaDbl">
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group col-lg-6">

                        <h4>Pollinator Visit Counts</h4>

                    </div>
                    <div class="form-group col-lg-6">
                        <p>
                            Time will be placed here. Hard set to 5 minutes. At the end, it will lock
                            the
                            count fields, but leave the comment section open for butterflies &amp; moths
                            if
                            &gt;0
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[1]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[2]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>

                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[3]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>

                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[4]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[5]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[6]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[7]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[8]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>


                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>

                            <input class="form-control" type="text"
                                   name="plant[1]Strain[6]Plot[6]Pollinator[9]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <%-------------------------------------------------- End Form6 --------------------------------------------------%>
            <%-------------------------------------------------- Begin Form7 --------------------------------------------------%>
            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Genus</label>

                        <input type="text" class="form-control"
                               placeholder="Coreopsis Verticillata" name="plant[1]Name"
                               value="Coreopsis Verticillata">

                    </div>

                    <div class="form-group col-lg-6">

                        <label class="control-label"
                        >Species</label>

                        <input type="text" class="form-control"
                               placeholder="Didyma Petite Delight" name="plant[1]Strain[7]Name"
                               value="Didyma Petite Delight">
                    </div>

                </div>

                <br>

                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>

                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-6">

                        <label class="control-label">Flower Coverage<span
                        >*</span></label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[7]Plot[7]PercentCoverage">
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>
                    </div>


                    <div class="form-group col-lg-6">
                        <label class="control-label">Plot Area Dimensions</label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[7]Plot[7]AreaDbl">
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>

                    </div>
                </div>

                <br>


                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Pollinator Visit Counts</h4>

                    </div>
                    <div class="form-group col-lg-6">
                        <p>
                            Time will be placed here. Hard set to 5 minutes. At the end, it will lock
                            the
                            count fields, but leave the comment section open for butterflies &amp; moths
                            if
                            &gt;0
                        </p>

                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[1]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>

                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[2]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[3]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[4]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[5]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[6]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[7]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[8]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[7]Plot[7]Pollinator[9]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                </div>

            </div>

            <%-------------------------------------------------- End Form7 --------------------------------------------------%>
            <%-------------------------------------------------- Begin Form8 --------------------------------------------------%>
            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Genus</label>
                        <input type="text" class="form-control"
                               placeholder="Coreopsis Verticillata" name="plant[1]Name"
                               value="Coreopsis Verticillata">
                    </div>


                    <div class="form-group col-lg-6">
                        <label class="control-label">Species</label>
                        <input type="text" class="form-control"
                               placeholder="Didyma Marshalls Delight" name="plant[1]Strain[8]Name"
                               value="Didyma Marshalls Delight">
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>
                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Flower Coverage<span>*</span></label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[8]Plot[8]PercentCoverage">
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>
                    </div>


                    <div class="form-group col-lg-6">
                        <label class="control-label">Plot Area Dimensions</label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[8]Plot[8]AreaDbl">
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Pollinator Visit Counts</h4>
                    </div>

                    <div class="form-group col-lg-6">
                        <p>
                            Time will be placed here. Hard set to 5 minutes. At the end, it will lock
                            the
                            count fields, but leave the comment section open for butterflies &amp; moths
                            if
                            &gt;0
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[1]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[2]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[3]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>

                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[4]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[5]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[6]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[7]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[8]" value="0">
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>


                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[8]Plot[8]Pollinator[9]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <%-------------------------------------------------- End Form8 --------------------------------------------------%>
            <%-------------------------------------------------- Begin Form9 --------------------------------------------------%>
            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Genus</label>
                        <input type="text" class="form-control"
                               placeholder="Coreopsis Verticillata" name="plant[1]Name"
                               value="Coreopsis Verticillata">
                    </div>

                    <div class="form-group col-lg-6">
                        <label class="control-label">Species</label>
                        <input type="text" class="form-control"
                               placeholder="X Peters Fancy Fuschia" name="plant[1]Strain[9]Name"
                               value="X Peters Fancy Fuschia">
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group">
                        <h4>Plot Survey</h4>
                        <p>
                            If plant strain is not blooming, select '0' for Flower Coverage and continue to
                            the
                            next plant strain.
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <label class="control-label">Flower Coverage<span
                        >*</span></label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[9]Plot[9]PercentCoverage">
                            <option value=""></option>
                            <option value="0">0</option>
                            <option value="1">1%-25%</option>
                            <option value="2">26%-50%</option>
                            <option value="3">51%-75%</option>
                            <option value="4">76%-100%</option>
                        </select>
                    </div>

                    <div class="form-group col-lg-6">
                        <label class="control-label">Plot Area Dimensions</label>
                        <select class="form-control"
                                required="required"
                                name="plant[1]Strain[9]Plot[9]AreaDbl">
                            <option value=""></option>
                            <option value="1">2'x2'</option>
                            <option value="2">3'x3'</option>
                            <option value="3">4'x4'</option>
                            <option value="4">Greater</option>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Pollinator Visit Counts</h4>
                    </div>
                    <div class="form-group col-lg-6">
                        <p>
                            Time will be placed here. Hard set to 5 minutes. At the end, it will lock
                            the
                            count fields, but leave the comment section open for butterflies &amp; moths
                            if
                            &gt;0
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Honey Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[1]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Carpenter Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[2]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Bumble Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[3]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Metallic Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[4]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Green Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[5]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Dark Sweat Bee</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[6]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <label class="control-label">Butterflies &amp; Moths</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[7]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Bees</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[8]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>

                    <div class="form-group col-lg-4">
                        <label class="control-label">Other Pollinators</label>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button class="btn bg-danger btn-minus" type="button"><i
                                        class="fa fa-minus"></i></button>
                            </span>
                            <input class="form-control" type="text"
                                   name="plant[1]Strain[9]Plot[9]Pollinator[9]" value="0"
                            >
                            <span class="input-group-btn">
                                <button class="btn bg-success btn-plus" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </span>
                        </div>
                    </div>
                </div>

            </div>
            <%-------------------------------------------------- End Form9 --------------------------------------------------%>

            <%------------------------------------------------------------ End DataForm Lists ------------------------------------------------------------%>
            <div class="tab">
                <div class="row">
                    <div class="form-group col-lg-12">
                        <label class="control-label">Butterflies &amp; Moths
                            Comments</label>
                        <textarea rows="3" class="form-control" name="butterfliesMothsCommentStrain"></textarea>
                    </div>
                    <div class="form-group col-lg-12">
                        <label class="control-label">Additional Comments?</label>
                        <textarea rows="3" class="form-control" name="strainComments"></textarea>

                    </div>

                </div>
                <input type="submit" name="dataformSubmit" id="register-submit"
                       class="form-control btn btn-register"
                       value="Create DataForm">
            </div>

            <div style="overflow:auto;">
                <div style="float:right;">
                    <button type="button" class="dataform-button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
                    <button type="button" id="nextBtn" class="dataform-button"
                            onclick="nextPrev(1), window.scrollTo(0, 0);">Next
                    </button>
                </div>
            </div>
            <!-- Circles which indicates the steps of the form: -->
            <div style="text-align:center;margin-top:40px;">
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>
            </div>
        </div>
    </form>
</div>


<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="vendor/scrollreveal/scrollreveal.js"></script>
<script src="vendor/Popper/Popper.js"></script>
<script src="vendor/DataTables/datatables.js"></script>
<script src="js/form.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>


<script>
    $(function () {
        $('#collectionDate').datetimepicker({
            format: 'YYYY-MM-DD'
        });
    });
</script>

<script>
    $(function () {
        $('#timePickerStart').datetimepicker({
            format: 'HH:mm'
        });
    });
</script>

<script>
    $(function () {
        $('#timePickerEnd').datetimepicker({
            format: 'HH:mm'
        });
    });
</script>


<script>


</script>


<script>
    function getSubmitTime() {
        var date = new Date();
        var d = date.getUTCDate();
        var day = (d < 10) ? '0' + d : d;
        var m = date.getUTCMonth() + 1;
        var month = (m < 10) ? '0' + m : m;
        var year = date.getUTCFullYear();
        var time = month + day + year;
        document.getElementById('dataFormSubmit').value = time;
    }
</script>


<script>
    var currentTab = 0; // Current tab is set to be the first tab (0)
    showTab(currentTab); // Display the current tab

    function showTab(n) {
        // This function will display the specified tab of the form ...
        var x = document.getElementsByClassName("tab");
        x[n].style.display = "block";
        // ... and fix the Previous/Next buttons:
        if (n == 0) {
            document.getElementById("prevBtn").style.display = "none";
        } else {
            document.getElementById("prevBtn").style.display = "inline";
        }
        if (n == (x.length - 1)) {
            document.getElementById("nextBtn").innerHTML = "Submit";
        } else {
            document.getElementById("nextBtn").innerHTML = "Next";
        }
        // ... and run a function that displays the correct step indicator:
        fixStepIndicator(n)
    }

    function nextPrev(n) {
        // This function will figure out which tab to display
        var x = document.getElementsByClassName("tab");
        // Exit the function if any field in the current tab is invalid:
        if (n == 1 && !validateForm()) return false;
        {
            // Hide the current tab:
            x[currentTab].style.display = "none";
            // Increase or decrease the current tab by 1:
            currentTab = currentTab + n;
        }
        if (document.getElementById("form-submission"))


        // if you have reached the end of the form... :
            if (currentTab >= x.length) {
                //...the form gets submitted:
                document.getElementById("regForm").submit();
                return false;
            }
        // Otherwise, display the correct tab:
        showTab(currentTab);
    }


    function validateForm() {
        // This function deals with validation of the form fields
        var x, y, i, valid = true;
        x = document.getElementsByClassName("tab");
        y = x[currentTab].getElementsByTagName("input");
        // A loop that checks every input field in the current tab:
        for (i = 0; i < y.length; i++) {
            // If a field is empty...
            if (y[i].value == "") {
                // add an "invalid" class to the field:
                y[i].className += " invalid";
                // and set the current valid status to false:
                // TODO: This needs to be changed back to false
                valid = true;
            }
        }
        // If the valid status is true, mark the step as finished and valid:
        if (valid) {
            document.getElementsByClassName("step")[currentTab].className += " finish";
        }
        return valid; // return the valid status
    }

    function fixStepIndicator(n) {
        // This function removes the "active" class of all steps...
        var i, x = document.getElementsByClassName("step");
        for (i = 0; i < x.length; i++) {
            x[i].className = x[i].className.replace(" active", "");
        }
        //... and adds the "active" class to the current step:
        x[n].className += " active";
    }
</script>


<script>
    $('.btn-minus').on('click', function () {
        $(this).parent().siblings('input').val(parseInt($(this).parent().siblings('input').val()) - 1)
    })

    $('.btn-plus').on('click', function () {
        $(this).parent().siblings('input').val(parseInt($(this).parent().siblings('input').val()) + 1)
    })
</script>

<script>
    var timer = new Timer();
    $('#chronoExample .startButton').click(function () {
        timer.start();
    });
    $('#chronoExample .pauseButton').click(function () {
        timer.pause();
    });
    $('#chronoExample .stopButton').click(function () {
        timer.stop();
    });
    $('#chronoExample .resetButton').click(function () {
        timer.reset();
    });
    timer.addEventListener('secondsUpdated', function (e) {
        $('#chronoExample .values').html(timer.getTimeValues().toString());
    });
    timer.addEventListener('started', function (e) {
        $('#chronoExample .values').html(timer.getTimeValues().toString());
    });
    timer.addEventListener('reset', function (e) {
        $('#chronoExample .values').html(timer.getTimeValues().toString());
    });
</script>


</body>
</html>
