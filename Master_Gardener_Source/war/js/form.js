var room = 1;

function new_poll_fields() {

    room++;
    var objTo = document.getElementById('new_poll_fields')
    var divtest = document.createElement("div");
    divtest.setAttribute("class", "form-group removeclass" + room);
    var rdiv = 'removeclass' + room;
    divtest.innerHTML = ' ' +
        '<div> ' +
        '<div class="panel panel-default"> <div class="panel-body"> <div class="col-lg-7 nopadding"> <div class="form-group"> <div class="input-group"> <div> <h4>Pollinators</h4> </div> <table class="table"> <thead> <tr> <th class="col-lg-2">Genus</th> <th class="col-lg-2">Species</th> <th class="col-lg-1">Count</th> </tr> </thead> <tbody> <tr> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Bee">Bee</option> <option value="Butterflies & Moths">Butterflies & Moths</option> <option value="Other">Other</option> </select> </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Honey Bee">Honey Bee</option> <option value="Carpenter Bee">Carpenter Bee</option> <option value="Bumble Bee">Bumble Bee</option> <option value="Green Metallic Bee">Green Metallic Bee</option> <option value="Butterfly & Moths">Butterflies & Moths</option> <option value="Other">Wasps, Flies, Beetles, etc</option> </select> </td> <td> <input type="text" class="form-control" id="dataInput" name="count" placeholder="Count"> </td> </tr> <tr> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Bee">Bee</option> <option value="Butterflies & Moths">Butterflies & Moths</option> <option value="Other">Other</option> </select> </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Honey Bee">Honey Bee</option> <option value="Carpenter Bee">Carpenter Bee</option> <option value="Bumble Bee">Bumble Bee</option> <option value="Green Metallic Bee">Green Metallic Bee</option> <option value="Butterfly & Moths">Butterflies & Moths</option> <option value="Other">Wasps, Flies, Beetles, etc</option> </select> </td> <td> <input type="text" class="form-control" id="dataInput" name="count" placeholder="Count"> </td> </tr> <tr> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Bee">Bee</option> <option value="Butterflies & Moths">Butterflies & Moths</option> <option value="Other">Other</option> </select> </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Honey Bee">Honey Bee</option> <option value="Carpenter Bee">Carpenter Bee</option> <option value="Bumble Bee">Bumble Bee</option> <option value="Green Metallic Bee">Green Metallic Bee</option> <option value="Butterfly & Moths">Butterflies & Moths</option> <option value="Other">Wasps, Flies, Beetles, etc</option> </select> </td> <td> <input type="text" class="form-control" id="dataInput" name="count" placeholder="Count"> </td> </tr> <tr> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Bee">Bee</option> <option value="Butterflies & Moths">Butterflies & Moths</option> <option value="Other">Other</option> </select> </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Honey Bee">Honey Bee</option> <option value="Carpenter Bee">Carpenter Bee</option> <option value="Bumble Bee">Bumble Bee</option> <option value="Green Metallic Bee">Green Metallic Bee</option> <option value="Butterfly & Moths">Butterflies & Moths</option> <option value="Other">Wasps, Flies, Beetles, etc</option> </select> </td> <td> <input type="text" class="form-control" id="dataInput" name="count" placeholder="Count"> </td> </tr> <tr> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Bee">Bee</option> <option value="Butterflies & Moths">Butterflies & Moths</option> <option value="Other">Other</option> </select> </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Honey Bee">Honey Bee</option> <option value="Carpenter Bee">Carpenter Bee</option> <option value="Bumble Bee">Bumble Bee</option> <option value="Green Metallic Bee">Green Metallic Bee</option> <option value="Butterfly & Moths">Butterflies & Moths</option> <option value="Other">Wasps, Flies, Beetles, etc</option> </select> </td> <td> <input type="text" class="form-control" id="dataInput" name="count" placeholder="Count"> </td> </tr> <tr> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Bee">Bee</option> <option value="Butterflies & Moths">Butterflies & Moths</option> <option value="Other">Other</option> </select> </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="Honey Bee">Honey Bee</option> <option value="Carpenter Bee">Carpenter Bee</option> <option value="Bumble Bee">Bumble Bee</option> <option value="Green Metallic Bee">Green Metallic Bee</option> <option value="Butterfly & Moths">Butterflies & Moths</option> <option value="Other">Wasps, Flies, Beetles, etc</option> </select> </td> <td> <input type="text" class="form-control" id="dataInput" name="count" placeholder="Count"> </td> </tr> </tbody> </tr> </table> </div> </div> </div> <div class="col-lg-5 nopadding"> <div class="form-group"> <div class="input-group"> <div> <h4>Plot Survey</h4> </div> <table class="table"> <thead> <tr> <th class="col-lg-2">&nbsp</th> <th class="col-lg-2">&nbsp</th> </tr> </thead> <tbody> <tr> <td> Average Height of Plot </td> <td> <input type="text" class="form-control" id="dataInput" name="inches" placeholder="Inches"> </td> </tr> <tr> <td> Size of Plot </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="2’ X 2’">2’ X 2’</option> <option value="3’ X 3\'">3’ X 3’</option> <option value="4’ x 4’ or greater">4’ x 4’ or greater</option> </select> </td> </tr> <tr> <td> Blooms Open </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="e">Early</option> <option value="o">Peak</option> <option value="f">Faded</option> </select> </td> </tr> <tr> <td> Flower Coverage </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="1">1% - 25%</option> <option value="2">26% - 50%</option> <option value="3">51% - 75%</option> <option value="4">76% - 100%</option> </select> </td> </tr> <tr> <td> Plant Vigor </td> <td> <select class="form-control" id="educationDate" name="educationDate[]"> <option value="T">Thriving</option> <option value="P">Poop</option> <option value="D">Dead</option> </td> </tr> </tbody> </tr> </table> </div> </div> </div> <div class="clear"></div> </div> </div> </div><div class="input-group-btn"> \n' +
        '<button class="btn btn-danger" type="button" onclick="remove_education_fields(\' + room + \');"> \n' +
        '<span class="glyphicon glyphicon-minus" aria-hidden="true"></span> \n' +
        '</button>\n' +
        '</div>' +
        '<div class="clear"></div>';

    objTo.appendChild(divtest)
}

function remove_education_fields(rid) {
    $('.removeclass' + rid).remove();
}

$(function () {

    'use strict';

    var $date = $('.docs-date');
    var $container = $('.docs-datepicker-container');
    var $trigger = $('.docs-datepicker-trigger');
    var options = {
        show: function (e) {
            console.log(e.type, e.namespace);
        },
        hide: function (e) {
            console.log(e.type, e.namespace);
        },
        pick: function (e) {
            console.log(e.type, e.namespace, e.view);
        }
    };

    $date.on({
        'show.datepicker': function (e) {
            console.log(e.type, e.namespace);
        },
        'hide.datepicker': function (e) {
            console.log(e.type, e.namespace);
        },
        'pick.datepicker': function (e) {
            console.log(e.type, e.namespace, e.view);
        }
    }).datepicker(options);

    $('.docs-options, .docs-toggles').on('change', function (e) {
        var target = e.target;
        var $target = $(target);
        var name = $target.attr('name');
        var value = target.type === 'checkbox' ? target.checked : $target.val();
        var $optionContainer;

        switch (name) {
            case 'container':
                if (value) {
                    value = $container;
                    $container.show();
                } else {
                    $container.hide();
                }

                break;

            case 'trigger':
                if (value) {
                    value = $trigger;
                    $trigger.prop('disabled', false);
                } else {
                    $trigger.prop('disabled', true);
                }

                break;

            case 'inline':
                $optionContainer = $('input[name="container"]');

                if (!$optionContainer.prop('checked')) {
                    $optionContainer.click();
                }

                break;

            case 'language':
                $('input[name="format"]').val($.fn.datepicker.languages[value].format);
                break;
        }

        options[name] = value;
        $date.datepicker('reset').datepicker('destroy').datepicker(options);
    });

    $('.docs-actions').on('click', 'button', function (e) {
        var data = $(this).data();
        var args = data.arguments || [];
        var result;

        e.stopPropagation();

        if (data.method) {
            if (data.source) {
                $date.datepicker(data.method, $(data.source).val());
            } else {
                result = $date.datepicker(data.method, args[0], args[1], args[2]);

                if (result && data.target) {
                    $(data.target).val(result);
                }
            }
        }
    });

    $('[data-toggle="datepicker"]').datepicker();
    $('[data-toggle="tooltip"]').tooltip();
});













/*
<div class="col-sm-3 nopadding">
<div class="form-group">
<div class="input-group">
select class="form-control" id="educationDate" name="educationDate[]">' +
'<option value="">Pollinator</option>\n' +
'                                                            <option value="Bee1">Bee1</option>\n' +
'                                                            <option value="Bee2">Bee2</option>\n' +
'                                                  <option value="Bee3">Bee3</option>\n' +
'                                                            <option value="Butterfly">Butterfly</option> </select>
<div class="input-group-btn">
<button class="btn btn-danger" type="button" onclick="remove_education_fields(' + room + ');">
<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
</button>
</div>
</div>
</div>
</div>
<div class="clear"></div>
*/