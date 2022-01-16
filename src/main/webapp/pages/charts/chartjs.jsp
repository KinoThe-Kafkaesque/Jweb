
<nav class="sidebar sidebar-offcanvas" id="sidebar">
  <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
    <a class="sidebar-brand brand-logo" href="index.jsp"><img src="assets/images/logo.svg" alt="logo" /></a>
    <a class="sidebar-brand brand-logo-mini" href="index.jsp"><img src="assets/images/logo-mini.svg" alt="logo" /></a>
  </div>
  <ul class="nav">
    <li class="nav-item profile">
      <div class="profile-desc">
        <div class="profile-pic">
          <div class="count-indicator">
            <i class="mdi mdi-account-multiple"></i>
            <span class="count bg-success"></span>
          </div>
          <div class="profile-name">
            <h5 class="mb-0 font-weight-normal"><%= request.getSession().getAttribute("fname")%> <%= request.getSession().getAttribute("lname")%></h5>
            <span><%= request.getSession().getAttribute("role")%> </span>
          </div>
        </div>
    <li class="nav-item nav-category">
      <span class="nav-link">Navigation</span>
    </li>
    
    <li class="nav-item menu-items">
      <a class="nav-link" href="Occupation.jsp">
        <span class="menu-icon">
          <i class="mdi mdi-playlist-play"></i>
        </span>
        <span class="menu-title">Occupation</span>
      </a>
    </li>
      <li class="nav-item menu-items">
      <a class="nav-link" href="planning.jsp">
        <span class="menu-icon">
          <i class=" mdi mdi-table-large"></i>
        </span>
        <span class="menu-title">Planning</span>
      </a>
    </li>
    <li class="nav-item menu-items">
            <a class="nav-link" href="charts.jsp">
              <span class="menu-icon">
                <i class="mdi mdi-chart-bar"></i>
              </span>
              <span class="menu-title">Charts</span>
            </a>
          </li>
      	<%
	if ("admin".equals(session.getAttribute("role"))) {
	%>
       <li class="nav-item menu-items">
      <a class="nav-link" href="v_occupation.jsp">
        <span class="menu-icon">
          <i class="mdi mdi-playlist-play"></i>
        </span>
        <span class="menu-title">valider Occupation</span>
      </a>
    </li>
   
	
       <li class="nav-item menu-items">
      <a class="nav-link" href="crenau.jsp">
        <span class="menu-icon">
          <i class="mdi mdi-laptop"></i>
        </span>
        <span class="menu-title">Creneau</span>
      </a>  </li>
  <li class="nav-item menu-items">
      <a class="nav-link" href="salle.jsp">
        <span class="menu-icon">
          <i class="mdi mdi-label-outline "></i>
        </span>
        <span class="menu-title">Salle</span>
      </a> </li>

  <li class="nav-item menu-items">
      <a class="nav-link" href="users.jsp">
        <span class="menu-icon">
          <i class=" mdi mdi-account-multiple  "></i>
        </span>
        <span class="menu-title">utilisateurs</span>
      </a> </li>

      <%} %>
      
      	<%
	if ("client".equals(session.getAttribute("role"))) {
	%>
	
   
      
      <%} %>
      
   
    </li>
  </ul>
</nav>