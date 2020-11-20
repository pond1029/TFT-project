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
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css"	rel="stylesheet">
<link href="/resources/css/shop-homepage.css" rel="stylesheet">
<script type="text/javascript" src="/resources/js/Chart.js" ></script> 
<script type="text/javascript" src="/resources/js/Chart.min.js" ></script> 
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
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
					<li class="nav-item active"><a class="nav-link" href="main.do">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="simulation.do">Simulaion</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container" style="width: 90vw; height: 90vh;">
		<div>
		<label id="summonerName" style="cursor: pointer; font-size: xx-large;">야 방금 무빙봤냐</label>
		<button id="update" onclick="update()">갱신</button>
		
		</div>
		<p id="summery"></p>
		<div id="date">
		<button onclick="getDate(-1)"> < </button>
		<label id="year">2020</label> - <label id="month">09</label>
		
		<button onclick="getDate(1)"> > </button>
		</div>
		<div>
		</div>
		
		<div id="chartContainer" class="chart-container" style="position:relative; height:70vh; width:70vw;">
			<canvas id="chart"></canvas>	
		</div>
		
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
initDate();
send();
	
function update(){

	var summonerName = document.getElementById("summonerName").innerHTML;	
	
	fetch('http://localhost:8080/tft/update?summonerName=' + summonerName,{
			method: 'GET',
			headers:{
				'Content-Type':'application/json'
			}
		});	
}

function initDate(){
	let date = new Date();

	var year = document.getElementById('year');
	year.innerHTML = "";
	var pickedYear = document.createTextNode(date.getFullYear());
	year.appendChild(pickedYear);


	var month = document.getElementById('month');
	month.innerHTML = "";
	var pickedMonth = document.createTextNode(date.getMonth() + 1);
	month.appendChild(pickedMonth);
}

function getDate(move){

	var year = document.getElementById('year');
	var month = document.getElementById('month');

	let date = new Date(year.innerHTML, month.innerHTML - 1);
	
	if(move > 0){
		date.setMonth( date.getMonth() + 1);
	}else{
		date.setMonth( date.getMonth() - 1);
	}

	year.innerHTML = "";
	var pickedYear = document.createTextNode(date.getFullYear());
	year.appendChild(pickedYear);


	month.innerHTML = "";
	var pickedMonth = document.createTextNode(date.getMonth() + 1);
	month.appendChild(pickedMonth);

	send();
}

function send(){
	var summonerName = document.getElementById("summonerName").innerHTML;	
	var year = document.getElementById("year").innerHTML;	
	var month = document.getElementById("month").innerHTML;	

	var params = 'year=' + year + '&month=' + month + '&summonerName=' + summonerName;
	
	fetch('http://localhost:8080/tft/playInfo?' + params,{
			method: 'GET',
			headers:{
				'Content-Type':'application/json'
			}
		})
		.then(res => res.json())
		.then(json => draw(json));	
}

function draw(playInfo){
	var playTimeLists = playInfo.playTimeLists;
	var totalSeconds = playInfo.totalPlayTime;
	var data = [];
	var labels = [];
	var backgroundColor = [];
	var summery = document.getElementById('summery');
	summery.innerHTML = "";
	var totalPlayTime = document.createTextNode('총 플레이 시간 : '+Math.round(totalSeconds/60) + '분');
	summery.appendChild(totalPlayTime);
	summery.appendChild(document.createElement("br"));
	
	for(var i = 0; i < playTimeLists.length; i++){
		data.push(Math.round(playTimeLists[i].seconds/60));
		if(playTimeLists[i].seconds/60 > 300){
			backgroundColor.push('#fe4365');
		}else{
			backgroundColor.push('#30A9DE');
		}
		labels.push(playTimeLists[i].date);
	}
	
	var chart = document.createElement("canvas");
	chart.setAttribute("id","chart");
	var ctx = chart.getContext('2d');
	
	var infoChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: labels,
	        datasets: [{
	            label: 'minute',
	            data: data,
	            backgroundColor : backgroundColor
	        }]
	    },
	    options: {
	    	maintainAspectRatio:false,
	    	scales:{ 
		    	yAxes: [{
		                display: true,
		                ticks: {
		                    beginAtZero: true
		                }
			    }]
			}
	    }
	});

	var chartContainer = document.getElementById('chartContainer');
	chartContainer.innerHTML = "";
	chartContainer.appendChild(chart);
}

</script>
</html>
