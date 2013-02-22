<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
    <title>Risk Management</title>
    <!-- Bootstrap -->
    <link type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<!--   <script src="http://code.jquery.com/jquery.js"></script> -->
	  <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"> </script>
  	  <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"> </script>
  	   <script type="text/javascript">
  			$(document).ready(function()
  					{var modelAttributeValue1 = '${details}';
  						if('' == modelAttributeValue1){
							$("#detailDiv").hide();}
  						else{
  							$("#detailDiv").show();}
  					});
	</script>
</head>
<body>
	<div class="container">
		<div class="alert alert-info">  
				  <a class="close" data-dismiss="alert">×</a>  
				  <strong>Info!</strong>  Click on a specific date to see all risks reported for that date.  
		</div> 
	</div>  
	
	<div class="container">
		<table class="table table-condensed table-hover table-bordered table-striped">
	  		<caption><strong>Risk Summary</strong></caption>
	  		<thead>
			    <tr>
			      <th>Risk Type</th>
			      <th>Added Date</th>
			      <th>Points Total</th>
			      <th>Risks Reported</th>
			      <th>Risk factor total</th>
			    </tr>
	  		</thead>
			<tbody>
				<c:forEach items="${summary}" var="element"> 
				    <tr>
				      <td>${element.category}</td>
				      <td>
				      <a id ="detailLink" href="<c:url value="/risks/risks/${element.date}/${element.category}" />" >${element.date}</a>
				      </td>
				      <td>${element.totalPoints}</td>
				      <td>${element.total_risks}</td>
				      <td>${element.totalFactors}</td>
				    </tr>
			    </c:forEach>
	  		</tbody>
		</table>
	</div>
	
	<div class="container" id="detailDiv">
		<table class="table table-condensed table-hover table-bordered table-striped">
	  		<caption><strong>${riskType} risk(s) for ${riskDate}</strong></caption>
	  		<thead>
			    <tr>
			      <th>Risk Description</th>
			      <th>Risk Points</th>
			      <th>Risk Factor</th>
			    </tr>
	  		</thead>
			<tbody>
				<c:forEach items="${details}" var="element"> 
			    <tr>
			      <td>${element.riskName}</td>
			      <td>${element.riskPoint}</td>
			      <td>${element.riskFactor}</td>
			    </tr>
			    </c:forEach>
	  		</tbody>
		</table>
	</div>
	
	<div class="container" id="goBackDiv">
	 <a href="<c:url value="/add" />" >Add more risks</a>
	</div>

</body>
</html>