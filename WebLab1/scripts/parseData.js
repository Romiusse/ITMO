
let button = document.getElementById("send-button");

button.onclick = function() {

    if(document.getElementById("get_y").value != y_var ||
        document.getElementById("get_r").value != r_var)
        alert("Данные не в валидном диапазоне");
    else
	    getData();
}

initData();

const getTimeZone = () => {
    const timezoneOffset = new Date().getTimezoneOffset()
    const offset = Math.abs(timezoneOffset)
    const offsetOperator = timezoneOffset < 0 ? '+' : '-'
    var offsetHours = Math.floor(offset / 60) * 3600000
    if(offsetOperator == '-') offsetHours = -offsetHours
    return offsetHours
  }

function initData(){
    const xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            let arr = JSON.parse(xhr.responseText);
            for (let i = 0; i < arr['size']; i++) {
                php_response(arr['points'][i]);
            }
        }
    }
    xhr.open('GET', 'server_get.php');
    xhr.send();
}

function getData(){
    const xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            php_response(xhr.responseText);
        }
    }
    xhr.open('GET', 'server_checking.php?'+'x='+x_var+'&y='+y_var+'&r='+r_var);
    xhr.send();
}

function php_response(json){
    let obj = JSON.parse(json);

    var div = document.createElement("div");
    div.className = "result-row";

    var x = document.createElement("div");
    x.className = "result-cell"
    x.innerHTML = obj.x

    var y = document.createElement("div");
    y.className = "result-cell"
    y.innerHTML = obj.y

    var r = document.createElement("div");
    r.className = "result-cell"
    r.innerHTML = obj.r

    var time = document.createElement("div");
    time.className = "result-cell"
    var cur_time = obj.time + getTimeZone()
    var date = new Date(obj.time)
    time.innerHTML = date.toString()

    var res = document.createElement("div");
    res.className = "result-cell"
    if(obj.res) res.innerHTML = "Попадание";
    else res.innerHTML = "Не попадание";

    var speed = document.createElement("div");
    speed.className = "result-cell"
    speed.innerHTML = obj.speed

    div.appendChild(x);
    div.appendChild(y);
    div.appendChild(r);
    div.appendChild(res);
    div.appendChild(time);
    div.appendChild(speed);
    if(obj.ok)
        document.getElementById("list").appendChild(div);
    else
        window.alert("Ошибка ввода данных");
}