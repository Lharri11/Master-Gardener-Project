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
    <link href="css/pracForm.css" rel="stylesheet">

    <!-- Font-Awesome javascript library -->
    <script defer src="vendor/font-awesome/fontawesome-all.js"></script>
</head>

<body>

<!--

The plant vigor is first since this will force the user to enter in whether it's dead or not. If it's, dead, everything will
be greyed out except the plot size.

-->


<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home">Pollinator Preferences</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.servletContext.contextPath}/user">My Account</a>
            </li>
            <form id="logout_form" method="post" name="loginSubmit"
                  action="${pageContext.servletContext.contextPath}/login">
                <li class="nav-item">
                    <a class="nav-link" name="loginSubmit"
                       href="javascript:{}" onclick="document.getElementById('logout_form').submit()">Logout</a>
                </li>
            </form>
        </ul>
    </div>
    </div>
</nav>


<div class="container dataform-page-top-padding">
    <form id="regForm" action="${pageContext.servletContext.contextPath}/dataForm" method="post"
          class="form-horizontal" role="form" data-parsley-validate novalidate onsubmit=getSubmitTime()>
        <div class="container-fluid shadow">

            <div class="tab">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label class="control-label control-label-left col-sm-3"
                                           for="">County</label>
                                    <div class="col-lg-9">
                                        <input type="text" class="form-control" data-role="text"
                                               placeholder="County Auto-populated" name="countyName">
                                    </div>

                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label class="control-label control-label-left col-sm-3">Garden</label>
                                    <div class="controls col-lg-9">
                                        <select class="form-control" name="garden_name">
                                            <option value=""></option>
                                        </select></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="tab">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">First Name<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                <input type="text" class="form-control" data-role="text"
                                       placeholder="First Name" name="firstNameSurveyor" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">Last Name<span class="req"> </span></label>
                            <div class="controls">

                                <input type="text" class="form-control" data-role="text"
                                       placeholder="Last Name" name="lastNameSurveyor" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">Username<span class="req"> *</span></label>
                            <div class="controls">

                                <select class="form-control" data-role="select" required="required"
                                        selected="selected" name="userNameSurveyor">


                                    <option value=""></option>
                                    <option value=""></option>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">First Name<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                <input type="text" class="form-control" data-role="text"
                                       placeholder="First Name" name="firstNameSurveyor" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">Last Name<span class="req"> </span></label>
                            <div class="controls">

                                <input type="text" class="form-control" data-role="text"
                                       placeholder="Last Name" name="lastNameSurveyor" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">Username<span class="req"> *</span></label>
                            <div class="controls">

                                <select class="form-control" data-role="select" required="required"
                                        selected="selected" name="userNameSurveyor">


                                    <option value=""></option>
                                    <option value=""></option>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">First Name<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                <input type="text" class="form-control" data-role="text"
                                       placeholder="First Name" name="firstNameSurveyor" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">Last Name<span class="req"> </span></label>
                            <div class="controls">

                                <input type="text" class="form-control" data-role="text"
                                       placeholder="Last Name" name="lastNameSurveyor" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">Username<span class="req"> *</span></label>
                            <div class="controls">

                                <select class="form-control" data-role="select" required="required"
                                        selected="selected" name="userNameSurveyor">


                                    <option value=""></option>
                                    <option value=""></option>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">First Name<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                <input type="text" class="form-control" data-role="text"
                                       placeholder="First Name" name="firstNameSurveyor" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label" for="">Last Name<span class="req"> </span></label>
                            <div class="controls">

                                <input type="text" class="form-control" data-role="text"
                                       placeholder="Last Name" name="lastNameSurveyor" required="required">
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label">Username<span class="req"> *</span></label>
                            <div class="controls">

                                <select class="form-control" data-role="select" required="required"
                                        selected="selected" name="userNameSurveyor">


                                    <option value=""></option>
                                    <option value=""></option>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>
            </div>


            <div class="tab">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label" for="">Collection Date
                                <span class="req"> *</span></label>
                            <div class="input-group date" id="collectionDate" data-target-input="nearest">
                                <input type="text" class="form-control datetimepicker-input"
                                       data-target="#collectionDate"/>
                                <div class="input-group-append" data-target="#collectionDate"
                                     data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">Temperature
                                <span class="req"> *</span></label>
                            <div class="controls">
                                <input type="text"
                                       class="form-control"
                                       placeholder="Temperature"
                                       name="temperture ">
                            </div>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label">Start Time<span
                                    class="req"> *</span></label>
                            <div class="input-group date" id="timePickerStart" data-target-input="nearest">
                                <input type="text" class="form-control datetimepicker-input" data-target="#timePickerStart"/>
                                <div class="input-group-append" data-target="#timePickerStart" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="fa fa-clock" aria-hidden="true"></i></div>
                                </div>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="control-label">Wind<span class="req"> *</span></label>
                            <div class="controls">

                                <select class="form-control" name="Wind"
                                        required="required">
                                    <option value=""></option>
                                    <option value="S">Still</option>
                                    <option value="L">Light Breeze</option>
                                    <option value="W">Windy</option>
                                    <option value="G">Gusty</option>
                                </select></div>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="control-label"> End Time <span class="req"> *</span></label>
                            <div class="input-group date" id="timePickerEnd" data-target-input="nearest">
                                <input type="text" class="form-control datetimepicker-input" data-target="#timePickerEnd"/>
                                <div class="input-group-append" data-target="#timePickerEnd" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="fa fa-clock" aria-hidden="true"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: block;">
                            <label class="control-label" for="">Cloud Cover<span
                                    class="req"> *</span></label>
                            <div class="controls">

                                <select id="" class="form-control" required="required"
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


</body>
</html>


