<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<meta http-equiv="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
    <title>Risk Management</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!--   <script src="http://code.jquery.com/jquery.js"></script> -->
	  <script src="bootstrap/js/jQuery.js"></script>
  	  <script src="bootstrap/js/bootstrap.min.js"></script>
  	  <script type="text/javascript">
  			$(document).ready(function()
  					{
  						var modelAttributeValue1 = '${state}';
  						if('' == modelAttributeValue1)	{
							$("#failDiv").hide();$("#successDiv").hide();$("#typeFailDiv").hide();$("#typesuccessDiv").hide();$("#sysFail").hide();}
  						if('fail' == modelAttributeValue1){
  							$("#failDiv").show();$("#successDiv").hide();$("#typeFailDiv").hide();$("#typesuccessDiv").hide();$("#sysFail").hide();}
  						if('success' == modelAttributeValue1){
							$("#failDiv").hide();	$("#successDiv").show();$("#typeFailDiv").hide();$("#typesuccessDiv").hide();$("#sysFail").hide();}
  						if('typeFail' == modelAttributeValue1){
							$("#failDiv").hide();$("#successDiv").hide();$("#typeFailDiv").show();$("#typesuccessDiv").hide();$("#sysFail").hide();}
  						if('typeSuccess' == modelAttributeValue1)	{
							$("#failDiv").hide();$("#successDiv").hide();$("#typeFailDiv").hide();$("#typesuccessDiv").show();$("#sysFail").hide();
						}
  						if('systemFail' == modelAttributeValue1)	{
							$("#failDiv").hide();$("#successDiv").hide();$("#typeFailDiv").hide();$("#typesuccessDiv").hide();$("#sysFail").show();
						}
  					});
	</script>
</head>
<body>
	
	<div class="container">
		<div class="row">
				<div class="span12 pagination-centered" >
					<h2>Risk Management Tool</h2>
				</div>
		</div>
		
		<form:form name="riskForm" method="POST" action="add" modelAttribute="risk" >
			
				<div class="alert alert-error" id ="failDiv">  
 					 <a class="close" data-dismiss="alert">×</a>  
 					 <strong>Error!</strong> <br />
 					  <form:errors path="selectedCategory" class="text-error"  /><br />
					  <form:errors path="riskPoint" class="text-error" /><br />
					  <form:errors path="riskName" class="text-error"  /><br />
				</div>  
				<div class="alert alert-error" id ="typeFailDiv">  
 					 <a class="close" data-dismiss="alert">×</a>  
 					 <strong>Error!!</strong>EIther risk type already exists or no value has been provided.  <br />
 					
				</div> 
					<div class="alert alert-error" id ="sysFail">  
 					 <a class="close" data-dismiss="alert">×</a>  
 					 <strong>Error!!</strong>System experiencing trouble at this time.  <br />
 					
				</div>  
				<div class="alert alert-success" id ="successDiv">  
				  <a class="close" data-dismiss="alert">×</a>  
				  <strong>Success!</strong>You have successfully added a risk . Details can be viewed in the risk summary page.
				</div>  
				<div class="alert alert-success" id ="typesuccessDiv">  
				  <a class="close" data-dismiss="alert">×</a>  
				  <strong>Success!</strong>You have successfully added a risk type.Risk type is available in the drop down.  
				</div> 
			<div class="row">
				<div class="span6">
					<input class="span6" type="text" name="category" placeholder="Type new risk type here" maxlength="10"/> 
				</div>
				<div class="span6">
					 <form:select class="span6" path="selectedCategory">
					 	<form:option value="NONE" label="Select risk type" />
        					<form:options items="${categories}"/>
      				  </form:select>
				</div>
			</div>
			<div class="row">
				<div class="span6">
					<input type = "submit" class="btn span6" name = "addCategory" value = "Add risk type" />
				</div>
				<div class="span6">
					  <form:input class="span6" type="text" path="riskPoint" placeholder="Type risk points" maxlength="2"/>
					 
				</div>
			</div>
			<!-- <div class="row">
				<div class="span6">
				
					
				</div>
				<div class="span6">
					  <form:input class="span6" type="text" path="riskFactor" placeholder="Type risk factor" maxlength="6"/>
				</div>
			</div> -->
			<div class="row">
				<div class="span6">
					
				</div>
				<div class="span6">
					   <form:textarea class="span6" rows="4" path="riskName" placeholder="Type risk description" maxlength="500"/>
				</div>
			</div>
			<div class="row">
				<div class="span6">
					
				</div>
				<div class="span6">
					  <input type = "submit" class="btn span6" name = "next" value = "Add Risk" />
				</div>
			</div>
		</form:form>
	</div>
	<div class="container">
	  <a href="<c:url value="/risks" />" >Risk Summary</a>
	</div>
</body>
</html>