let x_var = -2
let y_var = 0
let r_var = 2

set_x_value();
set_y_value();
set_r_value();

function check_y(value){
    value = value.substr(0, 6);
    if(value == '') return 0;
    if(value < -5) return -5;
    if(value > 5) return 5;
    return value;
}

function check_r(value){
    value = value.substr(0, 6);
    if(value == '') return 1;
    if(value < 1) return 1;
    if(value > 4) return 4;
    return value;
}

function set_x_value(){
    x_var = document.getElementById("selector").options[document.getElementById("selector").
    options.selectedIndex].value;
	document.getElementById('x').innerText = "Значение x = " + x_var;
}

function set_y_value(){
    let change = document.getElementById("get_y").value;
    y_var = check_y(change);
    document.getElementById('y').innerText = "Значение y = " + y_var;

}

function set_r_value(){
    let change = document.getElementById("get_r").value;
    r_var = check_r(change);
    value_R = r_var;
    draw();
    document.getElementById('r').innerText = "Значение r = " + r_var;
}

