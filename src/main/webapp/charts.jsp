<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@include file="index.jsp" %>
		<div class="main-panel">
			<div class="content-wrapper">
				<div class="page-header">
					<h3 class="page-title">Charts</h3>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Jweb</a></li>
							<li class="breadcrumb-item active" aria-current="page">Charts</li>
						</ol>
					</nav>
				</div>
				<div class="col-lg-6 grid-margin stretch-card">
					<div class="card">
					  <div class="card-body">
						<h4 class="card-title"> les salle le plus reservees</h4>
						<canvas id="MyChart" style="height:230px"></canvas>
					  </div>
					</div>
					<div class="card">
						<div class="card-body">
						  <h4 class="card-title"> Reservation de l'annee derniere </h4>
						  <canvas id="Monthly" style="height:230px"></canvas>
						</div>
					  </div>
				</div>
			</div>
		</div>
		<!-- content-wrapper ends -->
		<!-- partial:../../partials/_footer.html -->

		<!-- partial -->
		</div>
		<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
		</div>
		<script src="js/charts.js"></script>
		<script src="node_modules/chart.js/dist/Chart.min.js"></script>
		</body>

		</html>