
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" href="images/nittany_lions_logo.jpg">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator"
          content="Script Eden ( http://scripteden.net/ ) Template Builder v2.0.0">
    <title>Data Input</title>
    <!--pageMeta-->

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">


    <!--  <link href="css/style.css" rel="stylesheet"> -->

    <link href="css/newstyle.css" rel="stylesheet">
    <link href="css/datepicker.css" rel="stylesheet">
    <link href="css/pracForm.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="http://wenzhixin.net.cn/p/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>

    <link href="http://cdn.kendostatic.com/2014.1.318/styles/kendo.common.min.css" rel="stylesheet"/>
    <link href="http://cdn.kendostatic.com/2014.1.318/styles/kendo.bootstrap.min.css" rel="stylesheet"/>
    <link href="http://protostrap.com/Assets/gv/css/gv.bootstrap-form.css" rel="stylesheet" type="text/css"/>


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <!--headerIncludes-->


</head>
<body>

<!--

The plant vigor is first since this will force the user to enter in whether it's dead or not. If it's, dead, everything will
be greyed out except the plot size.

-->


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
                    <form action="${pageContext.servletContext.contextPath}/dataForm" method="get">
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
<header></header>


<div id="container">
    <form id="regForm" action="${pageContext.servletContext.contextPath}/dataForm" method="post"
          class="form-horizontal" role="form" data-parsley-validate novalidate onsubmit=getSubmitTime()>
        <div class="container-fluid shadow">

            <div class="tab">
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group" rel="popover" data-trigger="hover"
                                     data-content="County name of data collection" data-original-title="">
                                    <label class="control-label control-label-left col-sm-3"
                                           for="">County</label>
                                    <div class="controls col-sm-9">

                                        <input id="" type="text" class="form-control k-textbox"
                                               data-role="text"
                                               placeholder="County Auto-populated" name="countyName"
                                               data-parsley-errors-container="#errId1"><span id="errId1"
                                                                                             class="error"></span>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group" rel="popover" data-trigger="hover"
                                     data-content="Garden name for collection" data-original-title="">
                                    <label class="control-label control-label-left col-sm-3"
                                           for="">Garden</label>
                                    <div class="controls col-sm-9">

                                        <select id="" class="form-control" data-role="select"
                                                selected="selected"
                                                name="gardenName" data-parsley-errors-container="#errId2">


                                            <option value=""></option>
                                        </select><span id="errId2" class="error"></span></div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label" for="">First Name<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                <input id="" type="text" class="form-control k-textbox" data-role="text"
                                       placeholder="First Name" name="firstNameSurveyor" required="required"
                                       data-parsley-errors-container="#errId3"><span id="errId3"
                                                                                     class="error"></span>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label" for="">Last Name<span class="req"> *</span></label>
                            <div class="controls">

                                <input id="" type="text" class="form-control k-textbox" data-role="text"
                                       placeholder="Last Name" name="lastNameSurveyor" required="required"
                                       data-parsley-errors-container="#errId4"><span id="errId4"
                                                                                     class="error"></span>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label" for="">Username<span class="req"> *</span></label>
                            <div class="controls">

                                <select id="" class="form-control" data-role="select" required="required"
                                        selected="selected" name="userNameSurveyor"
                                        data-parsley-errors-container="#errId5">


                                    <option value=""></option>
                                    <option value=""></option>
                                </select><span id="errId5" class="error"></span></div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="tab">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label" for="">Collection Date<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                    <span class="k-widget k-datepicker k-header form-control" style=""><span
                                            class="k-picker-wrap k-state-default"><input id="" type="text"
                                                                                         class="form-control k-input"
                                                                                         data-role="date" role="textbox"
                                                                                         aria-haspopup="true"
                                                                                         aria-expanded="false"
                                                                                         aria-owns="field24_dateview"
                                                                                         aria-disabled="false"
                                                                                         aria-readonly="false"
                                                                                         aria-label="Current focused date is null"
                                                                                         data-error-container="#errfield24"
                                                                                         required="required"
                                                                                         name="collectionDate"
                                                                                         style="width: 100%;"
                                                                                         data-parsley-errors-container="#errId6"><span
                                            unselectable="on" class="k-select" role="button"
                                            aria-controls="field24_dateview"><span unselectable="on"
                                                                                   class="k-icon k-i-calendar">select</span></span></span></span><span
                                    id="errId6" class="error"></span></div>

                        </div>
                        <div class="form-group" style="display: block;">
                            <label class="control-label" for="">Tempeture <span
                                    class="req"> *</span></label>
                            <div class="controls">

                                    <span class="k-widget k-numerictextbox form-control" style=""><span
                                            class="k-numeric-wrap k-state-default"><input type="text"
                                                                                          class="k-formatted-value form-control k-input"
                                                                                          tabindex="0"
                                                                                          aria-disabled="false"
                                                                                          aria-readonly="false"
                                                                                          placeholder="Temperature"
                                                                                          id="" name="temperture "
                                                                                          style="display: inline-block;"
                                                                                          data-parsley-errors-container="#errId7"><input
                                            id="" value="" type="text" class="form-control k-input" data-role="numeric"
                                            data-format="integer" role="spinbutton" aria-valuenow=""
                                            aria-disabled="false" aria-readonly="false"
                                            data-error-container="#errfield27" required="required"
                                            data-parsley-max="130" name="temperture " style="display: none;"
                                            data-parsley-errors-container="#errId7"><span class="k-select"><span
                                            unselectable="on" class="k-link"><span unselectable="on"
                                                                                   class="k-icon k-i-arrow-n"
                                                                                   title="Increase value">Increase value</span></span><span
                                            unselectable="on" class="k-link"><span unselectable="on"
                                                                                   class="k-icon k-i-arrow-s"
                                                                                   title="Decrease value">Decrease value</span></span></span></span></span><span
                                    id="errId7" class="error"></span></div>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label" for="">Start Time<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                    <span class="k-widget k-timepicker k-header form-control" style=""><span
                                            class="k-picker-wrap k-state-default"><input id="" type="text"
                                                                                         class="form-control k-input"
                                                                                         data-role="time" role="textbox"
                                                                                         aria-haspopup="true"
                                                                                         aria-expanded="false"
                                                                                         aria-owns="field25_timeview"
                                                                                         aria-disabled="false"
                                                                                         aria-readonly="false"
                                                                                         data-error-container="#errfield25"
                                                                                         required="required"
                                                                                         name="startTime"
                                                                                         style="width: 100%;"
                                                                                         data-parsley-errors-container="#errId8"><span
                                            unselectable="on" class="k-select" role="button"
                                            aria-controls="field25_timeview"><span unselectable="on"
                                                                                   class="k-icon k-i-clock">select</span></span></span></span><span
                                    id="errId8" class="error"></span></div>

                        </div>
                        <div class="form-group" style="display: block;">
                            <label class="control-label" for="">Wind<span class="req"> *</span></label>
                            <div class="controls">

                                <select id="" class="form-control" data-role="select" name="Wind"
                                        required="required" data-parsley-errors-container="#errId9">
                                    <option value=""></option>
                                    <option value="S">Still</option>
                                    <option value="L">Light Breeze</option>
                                    <option value="W">Windy</option>
                                    <option value="G">Gusty</option>
                                </select><span id="errId9" class="error"></span></div>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label" for="">End Time<span class="req"> *</span></label>
                            <div class="controls">

                                    <span class="k-widget k-timepicker k-header form-control" style=""><span
                                            class="k-picker-wrap k-state-default"><input id="" type="text"
                                                                                         class="form-control k-input"
                                                                                         data-role="time" role="textbox"
                                                                                         aria-haspopup="true"
                                                                                         aria-expanded="false"
                                                                                         aria-owns="field26_timeview"
                                                                                         aria-disabled="false"
                                                                                         aria-readonly="false"
                                                                                         data-error-container="#errfield26"
                                                                                         name="endTime"
                                                                                         required="required"
                                                                                         style="width: 100%;"
                                                                                         data-parsley-errors-container="#errId10"><span
                                            unselectable="on" class="k-select" role="button"
                                            aria-controls="field26_timeview"><span unselectable="on"
                                                                                   class="k-icon k-i-clock">select</span></span></span></span><span
                                    id="errId10" class="error"></span></div>

                        </div>
                        <div class="form-group" style="display: block;">
                            <label class="control-label" for="">Cloud Cover<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                <select id="" class="form-control" data-role="select" required="required"
                                        name="cloudCover" data-parsley-errors-container="#errId11">
                                    <option value=""></option>
                                    <option value="C">Clear</option>
                                    <option value="P">Partly Cloudy</option>
                                    <option value="M">Mostly Cloudy</option>
                                    <option value="O">Overcast</option>
                                </select><span id="errId11" class="error"></span></div>

                        </div>
                    </div>
                </div>
            </div>



            <%-- Form Starts here --%>

            <div class="tab" id="form-submission">
                <div class="row">
                    <div class="col-md-12">
                        <div class="row" style="display: block;">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label control-label-left col-sm-3"
                                           for="">Genus</label>
                                    <div class="controls col-sm-9">

                                        <input id="" type="text" class="form-control k-textbox"
                                               data-role="text"
                                               placeholder="Genus Auto-populateed " name="genusName"
                                               data-parsley-errors-container="#errId12"><span id="errId12"
                                                                                              class="error"></span>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label control-label-left col-sm-3"
                                           for="">Strain</label>
                                    <div class="controls col-sm-9">

                                        <input id="" type="text" class="form-control k-textbox"
                                               data-role="text"
                                               placeholder="Strain Auto-populated" name="strainName"
                                               data-parsley-errors-container="#errId13"><span id="errId13"
                                                                                              class="error"></span>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="form-group brdbot" style="display: block;">
                            <h4>Plot Survey</h4>
                            <div class="controls col-sm-9">

                                <p id="field123" data-default-label="Header" data-default-is-header="true"
                                   data-control-type="header">If plant strain is not blooming, select '0'
                                    for
                                    Flower
                                    Coverage and continue to the next plant strain.</p><span id="errId14"
                                                                                             class="error"></span>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group" style="display: block;">
                                            <label class="control-label" for="">Flower Coverage<span
                                                    class="req"> *</span></label>
                                            <div class="controls">

                                                <select id="" class="form-control" data-role="select"
                                                        required="required" selected="selected"
                                                        name="flowerCoverageStrain"
                                                        data-parsley-errors-container="#errId15">


                                                    <option value=""></option>
                                                    <option value="0">0</option>
                                                    <option value="Option 2">1%-25%</option>
                                                    <option value="2">26%-50%</option>
                                                    <option value="3">51%-75%</option>
                                                    <option value="4">76%-100%</option>
                                                </select><span id="errId15" class="error"></span></div>

                                        </div>
                                        <div class="form-group" style="display: block;">
                                            <label class="control-label" for="">Average Height of
                                                Plot</label>
                                            <div class="controls">

                                                    <span class="k-widget k-numerictextbox form-control" style=""><span
                                                            class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                          class="k-formatted-value form-control k-input"
                                                                                                          tabindex="0"
                                                                                                          aria-disabled="false"
                                                                                                          aria-readonly="false"
                                                                                                          placeholder="Average Height of Plot"
                                                                                                          id=""
                                                                                                          name="avgPlotHeightStrain"
                                                                                                          style="display: inline-block;"
                                                                                                          data-parsley-errors-container="#errId16"><input
                                                            id="" value="" type="text" class="form-control k-input"
                                                            data-role="numeric" data-format="integer" role="spinbutton"
                                                            aria-valuenow="0" aria-disabled="false"
                                                            aria-readonly="false" data-error-container="#errfield193"
                                                            data-parsley-min="0" name="avgPlotHeightStrain"
                                                            style="display: none;"
                                                            data-parsley-errors-container="#errId16"><span
                                                            class="k-select"><span unselectable="on"
                                                                                   class="k-link"><span
                                                            unselectable="on" class="k-icon k-i-arrow-n"
                                                            title="Increase value">Increase value</span></span><span
                                                            unselectable="on" class="k-link"><span unselectable="on"
                                                                                                   class="k-icon k-i-arrow-s"
                                                                                                   title="Decrease value">Decrease value</span></span></span></span></span><span
                                                    id="errId16" class="error"></span></div>

                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group" style="display: block;">
                                            <label class="control-label" for="">Size of Plot</label>
                                            <div class="controls">

                                                    <span class="k-widget k-numerictextbox form-control" style=""><span
                                                            class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                          class="k-formatted-value form-control k-input"
                                                                                                          tabindex="0"
                                                                                                          aria-disabled="false"
                                                                                                          aria-readonly="false"
                                                                                                          placeholder="Size of Plot"
                                                                                                          id=""
                                                                                                          name="plotSizeStrain"
                                                                                                          style="display: inline-block;"
                                                                                                          data-parsley-errors-container="#errId17"><input
                                                            id="" value="" type="text" class="form-control k-input"
                                                            data-role="numeric" data-format="integer" role="spinbutton"
                                                            aria-valuenow="" aria-disabled="false" aria-readonly="false"
                                                            data-error-container="#errfield192" data-parsley-min="0"
                                                            data-parsley-max="9" name="plotSizeStrain"
                                                            style="display: none;"
                                                            data-parsley-errors-container="#errId17"><span
                                                            class="k-select"><span unselectable="on"
                                                                                   class="k-link"><span
                                                            unselectable="on" class="k-icon k-i-arrow-n"
                                                            title="Increase value">Increase value</span></span><span
                                                            unselectable="on" class="k-link"><span unselectable="on"
                                                                                                   class="k-icon k-i-arrow-s"
                                                                                                   title="Decrease value">Decrease value</span></span></span></span></span><span
                                                    id="errId17" class="error"></span></div>

                                        </div>
                                        <div class="form-group" style="display: block;">
                                            <label class="control-label" for="">Blooms Open</label>
                                            <div class="controls">

                                                <select id="" class="form-control" data-role="select"
                                                        selected="selected" name="bloomsOpenStrain"
                                                        data-parsley-errors-container="#errId18">


                                                    <option value=""></option>
                                                    <option value="E">Early</option>
                                                    <option value="P">Peak</option>
                                                    <option value="F">Faded</option>
                                                </select><span id="errId18" class="error"></span></div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="display: block;">
                            <div class="col-md-6">
                                <div class="form-group brdbot">
                                    <h4>Pollinator Count</h4>
                                    <div class="controls col-sm-9">

                                        <p id="field231" data-default-label="Header"
                                           data-default-is-header="true"
                                           data-control-type="header"></p><span id="errId19"
                                                                                class="error"></span>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <p>Time will be placed here. Hard set to 5 minutes. At the end, it will
                                        lock
                                        the
                                        count fields, but leave the comment section open for butterflies
                                        &amp;
                                        moths
                                        if &gt;0</p>


                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="control-label" for="">Honey Bee</label>
                                    <div class="controls">

                                            <span class="k-widget k-numerictextbox form-control" style=""><span
                                                    class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                  class="k-formatted-value form-control k-input"
                                                                                                  tabindex="0"
                                                                                                  aria-disabled="false"
                                                                                                  aria-readonly="false"
                                                                                                  placeholder=""
                                                                                                  name="honeyBeeStrain"
                                                                                                  id=""
                                                                                                  style="display: inline-block;"
                                                                                                  data-parsley-errors-container="#errId20"><input
                                                    id="" value="" type="text" class="form-control k-input"
                                                    data-role="numeric" data-format="integer" role="spinbutton"
                                                    aria-valuenow="0" aria-disabled="false" aria-readonly="false"
                                                    data-error-container="#errfield237" data-default="0"
                                                    data-parsley-min="0" name="honeyBeeStrain" style="display: none;"
                                                    data-parsley-errors-container="#errId20"><span
                                                    class="k-select"><span unselectable="on" class="k-link"><span
                                                    unselectable="on" class="k-icon k-i-arrow-n" title="Increase value">Increase value</span></span><span
                                                    unselectable="on" class="k-link"><span unselectable="on"
                                                                                           class="k-icon k-i-arrow-s"
                                                                                           title="Decrease value">Decrease value</span></span></span></span></span><span
                                            id="errId20" class="error"></span></div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="">Carpenter Bee</label>
                                    <div class="controls">

                                            <span class="k-widget k-numerictextbox form-control" style=""><span
                                                    class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                  class="k-formatted-value form-control k-input"
                                                                                                  tabindex="0"
                                                                                                  aria-disabled="false"
                                                                                                  aria-readonly="false"
                                                                                                  style="display: inline-block;"
                                                                                                  placeholder=""
                                                                                                  name="carpenterBeeStrain"
                                                                                                  id=""
                                                                                                  data-parsley-errors-container="#errId21"><input
                                                    id="" value="" type="text" class="form-control k-input"
                                                    data-role="numeric" data-format="integer" role="spinbutton"
                                                    aria-valuenow="" aria-disabled="false" aria-readonly="false"
                                                    data-error-container="#errfield233" style="display: none;"
                                                    data-default="0" data-parsley-min="0" name="carpenterBeeStrain"
                                                    data-parsley-errors-container="#errId21"><span
                                                    class="k-select"><span unselectable="on" class="k-link"><span
                                                    unselectable="on" class="k-icon k-i-arrow-n" title="Increase value">Increase value</span></span><span
                                                    unselectable="on" class="k-link"><span unselectable="on"
                                                                                           class="k-icon k-i-arrow-s"
                                                                                           title="Decrease value">Decrease value</span></span></span></span></span><span
                                            id="errId21" class="error"></span></div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="">Bumble Bee</label>
                                    <div class="controls">

                                            <span class="k-widget k-numerictextbox form-control" style=""><span
                                                    class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                  class="k-formatted-value form-control k-input"
                                                                                                  tabindex="0"
                                                                                                  aria-disabled="false"
                                                                                                  aria-readonly="false"
                                                                                                  style="display: inline-block;"
                                                                                                  placeholder=""
                                                                                                  name="bumbleBeeStrain"
                                                                                                  id=""
                                                                                                  data-parsley-errors-container="#errId22"><input
                                                    id="" value="" type="text" class="form-control k-input"
                                                    data-role="numeric" data-format="integer" role="spinbutton"
                                                    aria-valuenow="" aria-disabled="false" aria-readonly="false"
                                                    data-error-container="#errfield234" style="display: none;"
                                                    data-default="0" data-parsley-min="0" name="bumbleBeeStrain"
                                                    data-parsley-errors-container="#errId22"><span
                                                    class="k-select"><span unselectable="on" class="k-link"><span
                                                    unselectable="on" class="k-icon k-i-arrow-n" title="Increase value">Increase value</span></span><span
                                                    unselectable="on" class="k-link"><span unselectable="on"
                                                                                           class="k-icon k-i-arrow-s"
                                                                                           title="Decrease value">Decrease value</span></span></span></span></span><span
                                            id="errId22" class="error"></span></div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="">Green Sweat Bee</label>
                                    <div class="controls">

                                            <span class="k-widget k-numerictextbox form-control" style=""><span
                                                    class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                  class="k-formatted-value form-control k-input"
                                                                                                  tabindex="0"
                                                                                                  aria-disabled="false"
                                                                                                  aria-readonly="false"
                                                                                                  placeholder=""
                                                                                                  name="greenSweatBeeStrain"
                                                                                                  id=""
                                                                                                  style="display: inline-block;"
                                                                                                  data-parsley-errors-container="#errId23"><input
                                                    id="" value="" type="text" class="form-control k-input"
                                                    data-role="numeric" data-format="integer" role="spinbutton"
                                                    aria-valuenow="" aria-disabled="false" aria-readonly="false"
                                                    data-error-container="#errfield236" data-default="0"
                                                    data-parsley-min="0" name="greenSweatBeeStrain"
                                                    style="display: none;"
                                                    data-parsley-errors-container="#errId23"><span
                                                    class="k-select"><span unselectable="on" class="k-link"><span
                                                    unselectable="on" class="k-icon k-i-arrow-n" title="Increase value">Increase value</span></span><span
                                                    unselectable="on" class="k-link"><span unselectable="on"
                                                                                           class="k-icon k-i-arrow-s"
                                                                                           title="Decrease value">Decrease value</span></span></span></span></span><span
                                            id="errId23" class="error"></span></div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="">Small Dark Bee</label>
                                    <div class="controls">

                                            <span class="k-widget k-numerictextbox form-control" style=""><span
                                                    class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                  class="k-formatted-value form-control k-input"
                                                                                                  tabindex="0"
                                                                                                  aria-disabled="false"
                                                                                                  aria-readonly="false"
                                                                                                  style="display: inline-block;"
                                                                                                  placeholder=""
                                                                                                  name="smallDarkBeeStrain"
                                                                                                  id=""
                                                                                                  data-parsley-errors-container="#errId24"><input
                                                    id="" value="" type="text" class="form-control k-input"
                                                    data-role="numeric" data-format="integer" role="spinbutton"
                                                    aria-valuenow="" aria-disabled="false" aria-readonly="false"
                                                    data-error-container="#errfield235" style="display: none;"
                                                    data-default="0" data-parsley-min="0" name="smallDarkBeeStrain"
                                                    data-parsley-errors-container="#errId24"><span
                                                    class="k-select"><span unselectable="on" class="k-link"><span
                                                    unselectable="on" class="k-icon k-i-arrow-n" title="Increase value">Increase value</span></span><span
                                                    unselectable="on" class="k-link"><span unselectable="on"
                                                                                           class="k-icon k-i-arrow-s"
                                                                                           title="Decrease value">Decrease value</span></span></span></span></span><span
                                            id="errId24" class="error"></span></div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="">Butterflies &amp; Moths</label>
                                    <div class="controls">

                                            <span class="k-widget k-numerictextbox form-control" style=""><span
                                                    class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                  class="k-formatted-value form-control k-input"
                                                                                                  tabindex="0"
                                                                                                  aria-disabled="false"
                                                                                                  aria-readonly="false"
                                                                                                  placeholder=""
                                                                                                  name="butterflierMothsStrain"
                                                                                                  id=""
                                                                                                  style="display: inline-block;"
                                                                                                  data-parsley-errors-container="#errId25"><input
                                                    id="" value="" type="text" class="form-control k-input"
                                                    data-role="numeric" data-format="integer" role="spinbutton"
                                                    aria-valuenow="" aria-disabled="false" aria-readonly="false"
                                                    data-error-container="#errfield238" data-default="0"
                                                    data-parsley-min="0" name="butterflierMothsStrain"
                                                    style="display: none;"
                                                    data-parsley-errors-container="#errId25"><span
                                                    class="k-select"><span unselectable="on" class="k-link"><span
                                                    unselectable="on" class="k-icon k-i-arrow-n" title="Increase value">Increase value</span></span><span
                                                    unselectable="on" class="k-link"><span unselectable="on"
                                                                                           class="k-icon k-i-arrow-s"
                                                                                           title="Decrease value">Decrease value</span></span></span></span></span><span
                                            id="errId25" class="error"></span></div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="">Hummingbirds</label>
                                    <div class="controls">

                                            <span class="k-widget k-numerictextbox form-control" style=""><span
                                                    class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                  class="k-formatted-value form-control k-input"
                                                                                                  tabindex="0"
                                                                                                  aria-disabled="false"
                                                                                                  aria-readonly="false"
                                                                                                  placeholder=""
                                                                                                  name="hummingbirdStrain"
                                                                                                  id=""
                                                                                                  style="display: inline-block;"
                                                                                                  data-parsley-errors-container="#errId26"><input
                                                    id="" value="" type="text" class="form-control k-input"
                                                    data-role="numeric" data-format="integer" role="spinbutton"
                                                    aria-valuenow="" aria-disabled="false" aria-readonly="false"
                                                    data-error-container="#errfield240" data-default="0"
                                                    data-parsley-min="0" name="hummingbirdStrain" style="display: none;"
                                                    data-parsley-errors-container="#errId26"><span
                                                    class="k-select"><span unselectable="on" class="k-link"><span
                                                    unselectable="on" class="k-icon k-i-arrow-n" title="Increase value">Increase value</span></span><span
                                                    unselectable="on" class="k-link"><span unselectable="on"
                                                                                           class="k-icon k-i-arrow-s"
                                                                                           title="Decrease value">Decrease value</span></span></span></span></span><span
                                            id="errId26" class="error"></span></div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="">Other Bees</label>
                                    <div class="controls">

                                            <span class="k-widget k-numerictextbox form-control" style=""><span
                                                    class="k-numeric-wrap k-state-default"><input type="text"
                                                                                                  class="k-formatted-value form-control k-input"
                                                                                                  tabindex="0"
                                                                                                  aria-disabled="false"
                                                                                                  aria-readonly="false"
                                                                                                  style="display: inline-block;"
                                                                                                  placeholder=""
                                                                                                  name="otherBeesStrains"
                                                                                                  id=""
                                                                                                  data-parsley-errors-container="#errId27"><input
                                                    id="" value="" type="text" class="form-control k-input"
                                                    data-role="numeric" data-format="integer" role="spinbutton"
                                                    aria-valuenow="" aria-disabled="false" aria-readonly="false"
                                                    data-error-container="#errfield239" style="display: none;"
                                                    data-default="0" data-parsley-min="0" name="otherBeesStrains"
                                                    data-parsley-errors-container="#errId27"><span
                                                    class="k-select"><span unselectable="on" class="k-link"><span
                                                    unselectable="on" class="k-icon k-i-arrow-n" title="Increase value">Increase value</span></span><span
                                                    unselectable="on" class="k-link"><span unselectable="on"
                                                                                           class="k-icon k-i-arrow-s"
                                                                                           title="Decrease value">Decrease value</span></span></span></span></span><span
                                            id="errId27" class="error"></span></div>

                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="">Other (Wasps, Flies, Beetles, etc.)</label>
                            <div class="controls">

                                    <span class="k-widget k-numerictextbox form-control" style=""><span
                                            class="k-numeric-wrap k-state-default"><input type="text"
                                                                                          class="k-formatted-value form-control k-input"
                                                                                          tabindex="0"
                                                                                          aria-disabled="false"
                                                                                          aria-readonly="false"
                                                                                          placeholder=""
                                                                                          name="otherWFBEStrain" id=""
                                                                                          style="display: inline-block;"
                                                                                          data-parsley-errors-container="#errId28"><input
                                            id="" value="" type="text" class="form-control k-input" data-role="numeric"
                                            data-format="integer" role="spinbutton" aria-valuenow=""
                                            aria-disabled="false" aria-readonly="false"
                                            data-error-container="#errfield241" data-default="0" data-parsley-min="0"
                                            name="otherWFBEStrain" style="display: none;"
                                            data-parsley-errors-container="#errId28"><span class="k-select"><span
                                            unselectable="on" class="k-link"><span unselectable="on"
                                                                                   class="k-icon k-i-arrow-n"
                                                                                   title="Increase value">Increase value</span></span><span
                                            unselectable="on" class="k-link"><span unselectable="on"
                                                                                   class="k-icon k-i-arrow-s"
                                                                                   title="Decrease value">Decrease value</span></span></span></span></span><span
                                    id="errId28" class="error"></span></div>

                        </div>
                        <div class="form-group">
                            <label class="control-label control-label-left col-sm-3" for="">Butterflies
                                &amp;
                                Moths
                                Comments</label>
                            <div class="controls col-sm-9">

                                    <textarea id="" rows="3" class="form-control k-textbox" data-role="textarea"
                                              data-maxwords="250" name="butterfliesMothsCommentStrain"
                                              data-parsley-errors-container="#errId29"></textarea><span id="errId29"
                                                                                                        class="error"></span>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="tab">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label control-label-left col-sm-3" for="">Additional
                                Comments?</label>
                            <div class="controls col-sm-9">

                                    <textarea id="" rows="3" class="form-control k-textbox" data-role="textarea"
                                              data-maxwords="1000" name="strainComments"
                                              data-parsley-errors-container="#errId30"></textarea><span id="errId30"
                                                                                                        class="error"></span>
                            </div>

                        </div>
                    </div>
                </div>
            </div>


        </div>
        <div style="overflow:auto;">
            <div style="float:right;">
                <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
                <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
            </div>
        </div>
        <!-- Circles which indicates the steps of the form: -->
        <div style="text-align:center;margin-top:40px;">
            <span class="step"></span>
            <span class="step"></span>
            <span class="step"></span>
            <span class="step"></span>
            <span class="step"></span>
        </div>
    </form>
</div>


<!--/pageContent-->

<!-- /.item -->

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
<script src="js/form.js"></script>
<script src="js/datepicker.js"></script>
<script src="http://cdn.kendostatic.com/2014.1.318/js/jquery.min.js"></script>
<script src="http://protostrap.com/Assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="http://wenzhixin.net.cn/p/bootstrap-table/src/bootstrap-table.js" type="text/javascript"></script>

<script src="http://protostrap.com/Assets/inputmask/js/jquery.inputmask.js" type="text/javascript"></script>
<script src="http://cdn.kendostatic.com/2014.1.318/js/kendo.all.min.js"></script>
<script src="http://protostrap.com/Assets/parsely/parsley.extend.js" type="text/javascript"></script>
<script src="http://protostrap.com/Assets/parsely/2.0/parsley.js" type="text/javascript"></script>
<script src="http://protostrap.com/Assets/download.js" type="text/javascript"></script>
<script src="http://protostrap.com/Assets/protostrap.js" type="text/javascript"></script>
<script>

    function toggleSideBar() {
        var x = document.getElementById('sideBar2');
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
        if (n == 1 && !validateForm()) return false; {
        // Hide the current tab:
        x[currentTab].style.display = "none";
        // Increase or decrease the current tab by 1:
        currentTab = currentTab + n;
        }
        if (document.getElementById("form-submission")  )


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


</body>
</html>
