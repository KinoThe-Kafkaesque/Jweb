
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="index.jsp"%>


<div class="main-panel">
	<div class="content-wrapper">
		<div class="page-header">
			<h3 class="page-title">Salle</h3>
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Jweb</a></li>
					<li class="breadcrumb-item active" aria-current="page">Salle</li>
				</ol>
			</nav>
		</div>
		<div class="row">
			<div class="col-md-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<p class="card-description">Veuillez remplir tous les champs</p>
						<form class="forms-sample">
							<div class="form-group">


								<div class="form-group">
									<label for="code">Code</label> <input type="text"
										class="form-control" id="code" placeholder="code">
								</div>

								<div class="form-group">
									<label for="capacity">Capacité</label> <input type="number"
										min="1" max="100000000" class="form-control" id="capacity"
										placeholder="1">
								</div>
								<div class="form-group">
									<label for="type">Type</label> <input type="text"
										class="form-control" id="type" placeholder="type">
								</div>


								<button id="btn" type="button" onclick="adds()"
									class="btn btn-primary mr-2">Ajouter</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table table-dark">
				<thead>
					<tr>
						<th>#</th>
						<th>Code</th>
						<th>Capacité</th>
						<th>Type</th>
						<th>Supprimer</th>
						<th>Modifier</th>
					</tr>
				</thead>
				<tbody id="content">

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
<script src="js/salle.js"></script>
</body>
</html>