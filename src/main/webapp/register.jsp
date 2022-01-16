<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Corona Admin</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="assets/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<!-- End Plugin css for this page -->
<!-- inject:css -->
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet" href="assets/css/style.css">
<!-- End layout styles -->
<link rel="shortcut icon" href="../../assets/images/favicon.png" />
<!-- container-scroller -->
<!-- plugins:js -->
<script src="assets/vendors/js/vendor.bundle.base.js"></script>
<!-- endinject -->
<!-- Plugin js for this page -->
<script src="assets/vendors/chart.js/Chart.min.js"></script>
<script src="assets/vendors/progressbar.js/progressbar.min.js"></script>
<script src="assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
<script
	src="assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
<!-- End plugin js for this page -->
<!-- inject:js -->
<script src="assets/js/off-canvas.js"></script>
<script src="assets/js/hoverable-collapse.js"></script>
<script src="assets/js/misc.js"></script>
<script src="assets/js/settings.js"></script>
<script src="assets/js/todolist.js"></script>
<!-- endinject -->
<!-- Custom js for this page -->
<script src="assets/js/dashboard.js"></script>

<!-- End custom js for this page -->
</head>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="row w-100 m-0">
          <div class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
            <div class="card col-lg-4 mx-auto">
              <div class="card-body px-5 py-5">
                <h3 class="card-title text-left mb-3">Register</h3>
                <form id="form" accept-charset=utf-8>
                  <div class="form-group">
                    <label>First name</label>
                    <input type="text" class="form-control p_input"  id="fname" onkeyup="success()" required >
                  </div>
                  <div class="form-group">
                    <label>Last name</label>
                    <input type="text" class="form-control p_input" id="lname" onkeyup="success()" required >
                  </div>
                  <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control p_input" id="email" onkeyup="success()" required >
                  </div>
               
                  <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control p_input" id="password" onkeyup="success()" required >
                  </div>
                  <div class="form-group">
                    <label>Reponse secret</label>
                    <input type="text" class="form-control p_input" id="recuperation" onkeyup="success()" required >
                  </div>
                  <div class="form-group d-flex align-items-center justify-content-between">
                  </div>
                  <div class="text-center">
                    <button type = "button" onclick="register()"   class="btn btn-primary btn-block enter-btn">Register</button>
                  </div>
              
                  <p class="sign-up text-center">Already have an Account?<a href="./login.jsp"> Log in</a></p>
                </form>
              </div>
            </div>
          </div>
          <!-- content-wrapper ends -->
        </div>
        <!-- row ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <script src="js/register.js"></script>
    <!-- plugins:js -->
  
  </body>
  
</html>