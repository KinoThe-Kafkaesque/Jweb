<%@page import="service.CrenonService"%>
<%@page import="beans.Crenon"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="index.jsp"%>


<div class="main-panel">
	<div class="content-wrapper">
		<div class="page-header">
			<h3 class="page-title">Gestion des Créneaux</h3>
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Jweb</a></li>
					<li class="breadcrumb-item active" aria-current="page">Créneau
					</li>
				</ol>
			</nav>
		</div>
		<div class="row">
			<div class="col-md-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">Ajouter un Crénaux</h4>
						<p class="card-description"></p>
						<form class="forms-sample">

							<div class="form-group">
								<label for=debc>Début de crénau</label> <input type="time"
									class="form-control timepicker" id="debc">
							</div>
							<div class="form-group">
								<label for="finc">Fin de crénau </label> <input type="time"
									class="form-control timepicker" id="finc">
							</div>
							<button type="button" onclick="addc()" class="btn  btn-primary  mr-2">Submit</button>
						</form>
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-dark">
					<thead>
						<tr>
							<th>#</th>
							<th>Début</th>
							<th>Fin</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody id ="content">

					
					</tbody>
				</table>
			</div>
			<!-- content-wrapper ends -->
			<!-- partial:../../partials/_footer.html -->

			<!-- partial -->
		</div>
		<!-- main-panel ends -->
	</div>
	<!-- page-body-wrapper ends -->
</div>
    <script src="js/crenau.js"></script>

</body>
</html>