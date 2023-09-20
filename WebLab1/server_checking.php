<?php

    session_start();
    date_default_timezone_set('UTC');

    class Point{ 
        public $x, $y, $r, $in_range, $valid;

        function __construct($x, $y, $r){
            $this->x = $x;
            $this->y = $y;
            $this->r = $r;
            $this->valid = $this->is_correct();

            if($this->valid) $this->in_range = $this->is_in_range();
            else $this->in_range = false;
        }

        function is_correct(){

            $is_short = false;
            $is_num = false;
            $is_in_range = true;

            if(strlen((string)$this->x) <= 20 && strlen((string)$this->y) <= 20
                && strlen((string)$this->r) <= 20) $is_short = true;

            if(is_numeric($this->x) && is_numeric($this->y) 
                && is_numeric($this->r)) $is_num = true;

            if ($this->x < -3 || $this->x > 5)
                $is_in_range = false;
            if ($this->y < -5 || $this->y > 5)
                $is_in_range = false;
            if ($this->r < 1 || $this->r > 4)
                $is_in_range = false;

            return $is_num && $is_in_range && $is_short;
        }

        function is_in_range(){
            if ( ($this->x >= 0 && $this->y >= 0 && $this->y <= -$this->x + $this->r) ||
            ($this->x <= 0 && $this->y >= 0 && $this->x * $this->x + $this->y + $this->y <= ($this->r * $this->r) / 4) ||
            ($this->x <= 0 && $this->y <= 0 && $this->x >= -$this->r / 2 && $this->y >= -$this->r) ) return true;
            return false;
        }

        function get_gson($speed){

            $time = number_format(microtime() - $speed, 5);
            $time.= "mcs";

            return json_encode(array(
                'res' => $this->in_range,
                'x' => $this->x,
                'y' => $this->y,
                'r' => $this->r,
                'speed' => $time,
                'time' => floor(microtime(true) * 1000),
                'ok' => $this->valid
            ), JSON_FORCE_OBJECT);

        }


    }

    $start_time = microtime();

    if ($_SERVER['REQUEST_METHOD'] == 'GET'){

        $point = new Point($_GET['x'], $_GET['y'], $_GET['r']);
        $valid = $point->valid;
        if($valid){
            if(!isset($_SESSION["points"])) $_SESSION["points"] = array();
            array_push($_SESSION["points"], $point->get_gson($start_time));
        }
        echo $point->get_gson($start_time);

    }


?>
