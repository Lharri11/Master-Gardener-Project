//Code stolen from: https://codepen.io/ishanbakshi/pen/pgzNMv
//Code modified to work properly for our intents

//document.getElementById("start").onclick = startFunction();
//document.getElementById("reset").onclick = resetFunction();
//document.getElementById("pause").onclick = pauseFunction();

var paused = 0;
var num_instances = 0;
function startFunction(){
    // num_instances to prevent the user from breaking the function
    // (don't worry, it still breaks very easily)
    if(num_instances == 0 && paused == 0) {
        num_instances = 1;
        document.getElementById('timer').innerHTML =
            "5:00"
        startTimer();
    }
    if(num_instances == 1 && paused == 1)
    {
        paused = 0;
        setTimeout(startTimer, 200);
    }

}

function startTimer() {
    if(paused == 1){
        this.pause();
    }

    var presentTime = document.getElementById('timer').innerHTML;
    var timeArray = presentTime.split(/[:]+/);
    var m = timeArray[0];
    var s = checkSecond((timeArray[1] - 1));
    if (s == 59) {
        m = m - 1
    }
    if (m < 0) {
        endTimer();
    } else {
        document.getElementById('timer').innerHTML =
            m + ":" + s;
        setTimeout(startTimer, 1000);
    }
}
function checkSecond(sec) {
    if (sec < 10 && sec >= 0) {sec = "0" + sec}; // add zero in front of numbers < 10
    if (sec < 0) {sec = "59"};
    return sec;
}


function stopFunction()
{
    paused = 1;
    setTimeout(pauseFunction, 100);
}

function pauseFunction()
{

    num_instances = 1;
}

function resetFunction()
{
    document.getElementById('timer').innerHTML =
        '5:00';
    num_instances = 0;
    paused = 0;
}

function endTimer()
{
    document.getElementById('timer').innerHTML =
        00+":"+00;
}