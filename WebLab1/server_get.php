<?php

session_start();
if(isset($_SESSION["points"])){
    echo json_encode(array(
        'points' => $_SESSION["points"],
        'size'  => count($_SESSION["points"])
    ), JSON_FORCE_OBJECT);
}

?>