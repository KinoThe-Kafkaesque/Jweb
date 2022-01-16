<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@include file="index.jsp" %>
		<div class="main-panel">
			<div class="content-wrapper">
				<div class="page-header">
					<h3 class="page-title">Ocuppation</h3>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Jweb</a></li>
							<li class="breadcrumb-item active" aria-current="page">Planning</li>
						</ol>
					</nav>
				</div>
				<div class="row">
					<div class="col-md-12 grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<p class="card-description">Veuillez selectionner une date et une salle</p>
								<form class="forms-sample">
									<div class="form-group">
										<label for="salle">Salle</label>

										<div class="dropdown">
											<select class="btn btn-primary dropdown-toggle" id="salle"
												data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="date">Date</label> <input type="date" class="form-control"
											id="date">
									</div>
								


									<button id="btn" type="button" onclick="search()"
										class="btn btn-primary mr-2">Afficher les planing</button>
								</form>
							</div>
						</div>
					</div>

					<div class="table-responsive">
						<table class="table table-dark">
							<thead>
								<tr>
									<th>#</th>
									<th>Date</th>
									<th>Debut du creneau</th>
									<th>fin du creneau</th>
									<th>Salle</th>
									<th>Valider par</th>
								

								</tr>
							</thead>
							<tbody id="content">
								<tr>
							</tbody>
						</table>
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
		<script src="js/planning.js"></script>
		</body>

		</html>