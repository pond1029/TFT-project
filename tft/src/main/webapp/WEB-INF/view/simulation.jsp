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

<title>이재연의 TFT</title>

<!-- Bootstrap core CSS -->
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/css/shop-homepage.css" rel="stylesheet">
<!-- Chart.js -->
<script type="text/javascript" src="/resources/js/Chart.js" ></script> 
<script type="text/javascript" src="/resources/js/Chart.min.js" ></script> 
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
.parent {
    display: flex;
}
.child {
    flex: 2;
}
.none {
    flex: 1;
}
</style>
</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">SIMULATION</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="main.do">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="simulation.do">Simulaion</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Contact</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container" style="width: 90vw; height: 90vh;">

		<div id="row1" class="parent" style="width: 750px; height: 100px;">
		    <div id="col11" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col12" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col13" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col14" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col15" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col16" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col17" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div class="none"></div>
		</div>
		
		<div id="row2" class="parent" style="width: 750px; height: 100px;">
		    <div class="none"></div>
		    <div id="col21" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col22" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col23" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col24" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col25" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col26" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col27" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		</div>
		
		<div id="row3" class="parent" style="width: 750px; height: 100px;">
		    <div id="col31" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col32" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col33" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col34" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col35" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div id="col36" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #0278AE;"></div>
		    <div id="col37" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #A5ECD7;"></div>
		    <div class="none"></div>
		</div>
		
		<div id="row4" class="parent" style="width: 750px; height: 100px;">
		    <div class="none"></div>
		    <div id="col41" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col42" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col43" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col44" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col45" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		    <div id="col46" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #E8FFC1;"></div>
		    <div id="col47" ondrop="drop(event)" ondragover="allowDrop(event)" class="child" style="background: #51ADCF;"></div>
		</div>
	<!-- <div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)" style = "border: solid; width: 100px; height: 100px"></div>
	<div id="div2" ondrop="drop(event)" ondragover="allowDrop(event)" style = "border: solid; width: 100px; height: 100px"></div>
	 -->
	<img id="drag1" src="/resources/img/champ/LeeSin.png" draggable="true"
	ondragstart="drag(event)" width="100px" height="100px">
	<img id="drag2" src="/resources/img/Enemy.png" draggable="true"
	ondragstart="drag(event)" width="100px" height="100px">
	<img id="drag3" src="/resources/img/Enemy.png" draggable="true"
	ondragstart="drag(event)" width="100px" height="100px">
		
	</div>
	<!-- /.container -->
	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; 이재연 2020</p>
		</div>
		<!-- /.container -->
	</footer>
	<!-- Bootstrap core JavaScript -->

</body>

<script type="text/javascript">
function allowDrop(ev) {
    ev.preventDefault();
}
 
function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}
 
function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
}
</script>
</html>
