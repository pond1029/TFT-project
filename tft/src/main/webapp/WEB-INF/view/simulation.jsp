<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://www.typeleaf.org">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Project TFT</title>

<!-- Bootstrap core CSS -->
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/css/shop-homepage.css" rel="stylesheet">
<!-- Chart.js -->
<script type="text/javascript" src="/resources/js/Chart.js" ></script> 
<script type="text/javascript" src="/resources/js/Chart.min.js" ></script> 
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<style type="text/css">
.container{
	display: flex;
}
.parent {
    display: flex;
}
.child {
    flex: 2;
}
.none {
    flex: 1;
}
.shop {
    display: flex;
    height:75px;
}
.slot {
	background-color:black;
    flex: 1;
}
</style>
</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="main.do">Project TFT</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<a class="nav-link" href="main.do">
							Home
						</a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="simulation.do">
							Simulaion<span class="sr-only">(current)</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container" style="width: 90vw; height: 80vh;">
	<div class="map" style="width: 60%; height: 80vh; float: left;" >

		<div id="row1" class="parent" style=" height: 70px;">
		    <div id="col11" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col12" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col13" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col14" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col15" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col16" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col17" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div class="none"></div>
		</div>
		
		<div id="row2" class="parent" style=" height: 70px;">
		    <div class="none"></div>
		    <div id="col21" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col22" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col23" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col24" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col25" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col26" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col27" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		</div>
		
		<div id="row3" class="parent" style=" height: 70px;">
		    <div id="col31" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col32" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col33" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col34" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col35" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col36" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col37" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div class="none"></div>
		</div>
		
		<div id="row4" class="parent" style=" height: 70px;">
		    <div class="none"></div>
		    <div id="col41" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col42" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col43" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col44" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col45" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col46" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col47" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		</div>
		
		<div id="row5" class="parent" style=" height: 70px;">
		    <div id="col51" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col52" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col53" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col54" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col55" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col56" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col57" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div class="none"></div>
		</div>
		
		<div id="row6" class="parent" style=" height: 70px;">
		    <div class="none"></div>
		    <div id="col61" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col62" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col63" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col64" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col65" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col66" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col67" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		</div>
		
		<div id="row7" class="parent" style=" height: 70px;">
		    <div id="col71" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col72" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col73" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col74" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col75" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col76" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col77" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div class="none"></div>
		</div>
		
		<div id="row8" class="parent" style=" height: 70px;">
		    <div class="none"></div>
		    <div id="col81" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col82" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col83" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col84" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col85" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col86" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col87" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		</div>
		
	</div>
		
	<div class="side" style="width: 40%; height: 80vh; float: 40%;">
		<div style="background-color: red; height: 30%;">
			<button onclick="levelup()">레벨업</button>
			<button onclick="reroll()">리롤</button>
			<span id="wallet">10</span>
			<span id="exp">0/10</span>
			<span id="level">1</span>
			<div id="shop" class="shop">
				<div id="item1" class="slot">
					<img src="/resources/img/champ/Garen.png" width="100%">
				</div>
				<div id="item2" class="slot">
					<img src="/resources/img/champ/Garen.png" width="100%">
				</div>
				<div id="item3" class="slot">
					<img src="/resources/img/champ/Garen.png" width="100%">
				</div>
				<div id="item4" class="slot">
					<img src="/resources/img/champ/Garen.png" width="100%">
				</div>
				<div id="item5" class="slot">
					<img src="/resources/img/champ/Garen.png" width="100%">
				</div>
			</div>
		</div>
		<div style="background-color: blue; height: 70%">
			
		</div>
		
	</div>
	
	<!-- <img id="drag1" src="/resources/img/champ/LeeSin.png" draggable="true"
	ondragstart="drag(event)" width="50px" height="50px">
	
	<img id="drag2" src="/resources/img/Enemy.png" draggable="true"
	ondragstart="drag(event)" width="50px" height="50px">
	
	<img id="drag3" src="/resources/img/Enemy.png" draggable="true"
	ondragstart="drag(event)" width="50px" height="50px"> -->
	
		
	</div>
	<!-- /.container -->
	<!-- Footer -->
	<footer class="py-2 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; 이재연 2020</p>
		</div>
		<!-- /.container -->
	</footer>
	<!-- Bootstrap core JavaScript -->

</body>

<script type="text/javascript">

function levelup(){

	var wallet = document.getElementById('wallet');
	if(wallet.innerHTML > 4){
		wallet.innerHTML = wallet.innerHTML - 4;
		expUp();
	}	
}

function expUp(){

	var exp = document.getElementById('exp');
	exp.innerHTML += 4;
}

function reroll(){
	fetch('http://localhost:8080/simulation/reroll',{
		method: 'GET',
		headers:{
			'Content-Type':'application/json'
		}
	})
	.then(res => res.json())
	.then();		
}

function allowDrop(ev) {
    ev.preventDefault();
}
 
function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}
 
function drop(ev) {
    ev.preventDefault();        
    var data = ev.dataTransfer.getData("text");
    var element = document.getElementById(data);
    element.setAttribute("style","width:"+ev.target.clientWidth+"px; height:"+ev.target.clientHeight+"px;");
    ev.target.appendChild(element);
}
</script>
</html>
