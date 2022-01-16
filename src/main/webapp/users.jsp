<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@include file="index.jsp" %>


        <div class="main-panel">
            <div class="content-wrapper">
                <div class="page-header">
                    <h3 class="page-title">Clients</h3>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Jweb</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Gestion des clients</li>
                        </ol>
                    </nav>
                </div>
                <div class="row">
                    <div class="col-md-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <p class="card-description"></p>
                                <form class="forms-sample">
                                    <div class="form-group">
                                        <label for="salle">Username</label>
                                        <input type="text" class="form-control" id="user"
                                            placeholder="Veuiller Selectionner un clients depuis la list au dessous" style="color: #111010"; disabled>
                                    </div>

                                    <div class="form-group">
                                        <label for="pass">Nouveau mot de passe</label>
                                        <input type="text" class="form-control" id="pass"
                                            placeholder="Nouveau mot de passe">
                                    </div>



                                    <button id="btn" type="button" onclick="passu()"
                                        class="btn btn-primary mr-2">Modifier le
                                        mot de
                                        passe</button>
                                    <button id="btn" type="button" onclick="banu()"
                                        class="btn btn-danger mr-2">Ban</button>
                                    <button id="btn" type="button" onclick="unbanu()"
                                        class="btn btn-success mr-2">Unban</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <input type="text" class="form-control" placeholder="Chercher par nom /prenom /username "
                        aria-label="Recipient's username" id="search" onkeyup="search()" aria-describedby="basic-addon2">
                    <div class="table-responsive">
                        <table class="table table-dark">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>nom</th>
                                    <th>prenom</th>
                                    <th>Username</th>
                                    <th>status</th>
                                    <th>Supprimer</th>
                                    <th>Selectionner</th>


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
        <script src="js/user.js"></script>
        </body>

        </html>