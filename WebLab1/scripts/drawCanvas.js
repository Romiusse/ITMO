canvas = document.getElementById("canvas");
ctx = canvas.getContext("2d");
let value_R = 2;
draw();

function draw(){
    ctx.clearRect(0, 0, 1000, 1000);

    ctx.fillStyle = "rgba(91,95,201,0.58)";

    //triangle
    ctx.beginPath();
    ctx.moveTo(150,70);
    ctx.lineTo(200,70);
    ctx.lineTo(150,20);
    ctx.fill();

    //arc
    ctx.beginPath();
    ctx.moveTo(150,70);
    ctx.arc(150, 70, 25, Math.PI, 3 * Math.PI / 2);
    //ctx.lineTo(150,20);
    ctx.fill();

    //rectangle
    ctx.beginPath();
    ctx.moveTo(150,70);
    ctx.lineTo(150,120);
    ctx.lineTo(125,120);
    ctx.lineTo(125,70);
    ctx.fill();


    //axis
    ctx.beginPath();
    ctx.fillStyle = "black"

    //X
    ctx.moveTo(150,70);
    ctx.lineTo(220,70);

    // arrow
    ctx.lineTo(215,75);
    ctx.moveTo(220,70);
    ctx.lineTo(215,65);

    ctx.fillText('X',220,65);
    ctx.moveTo(150,70);
    ctx.lineTo(80,70);

    //значения X
    ctx.moveTo(175,75);
    ctx.lineTo(175,65);
    ctx.fillText(value_R/2 ,170,63);
    ctx.moveTo(200,75);
    ctx.lineTo(200,65);
    ctx.fillText(value_R,197,63);

    ctx.moveTo(125,75);
    ctx.lineTo(125,65);
    ctx.fillText(-1*value_R/2 ,114,63);
    ctx.moveTo(100,75);
    ctx.lineTo(100,65);
    ctx.fillText(-1*value_R,96,63);

    //Y
    ctx.moveTo(150,70);
    ctx.lineTo(150,140);
    ctx.moveTo(150,70);
    ctx.lineTo(150,5);
    //arrow
    ctx.lineTo(155,10);
    ctx.moveTo(150,5);
    ctx.lineTo(145,10);
    ctx.fillText('Y',160,15);

    //значения X
    ctx.moveTo(145,95);
    ctx.lineTo(155,95);
    ctx.fillText(-1*value_R/2 ,160,100);
    ctx.moveTo(145,120);
    ctx.lineTo(155,120);
    ctx.fillText(-1*value_R ,160,123);

    ctx.moveTo(145,45);
    ctx.lineTo(155,45);
    ctx.fillText(value_R/2,160,48);
    ctx.moveTo(145,20);
    ctx.lineTo(155,20);
    ctx.fillText(value_R,160,25);

    ctx.closePath();
    ctx.stroke();


}