<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="Payment.Paynew"  %>   
    
    
    <!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/PayNew.js"></script>
	<meta charset="ISO-8859-1">


</head>
<body>

	<div class="container">
	<div class="row">
	<div class="col-6">

	<h1>Payment form</h1>
	<form id="formPayment" name="formPayment" method="post" action="Paynew.jsp">
	
	ID:
	<input id="ID" name="ID" type="text"
	class="form-control form-control-sm">
	<br>
	<br> Patient name:
	<input id="PName" name="PName" type="text"
	class="form-control form-control-sm">
	<br>
	<br> Doctor name:
	<input id="DName" name="DName" type="text"
	class="form-control form-control-sm">
	<br>
	<br> Fee:
	<input id="Fee" name="Fee" type="text"
	class="form-control form-control-sm">
	<br>
	<br>
	<input id="btnSave" name="btnSave" type="button" value="Save"
	class="btn btn-primary">
	<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
	</form>


<div id="alertSuccess" class="alert alert-success"></div>

<div id="alertError" class="alert alert-danger"></div>
  
   <br>
   
   <div id="divGrid">
   <%
  		Paynew Payobj = new Paynew();
     	out.print(Payobj.readPayment());
      %>
      </div>
   
   
   </div>
   </div>
   </div>


</body>
</html>