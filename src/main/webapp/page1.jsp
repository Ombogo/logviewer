<%-- 
    Document   : page1
    Created on : Aug 7, 2014, 12:30:18 PM
    Author     : velma
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%ResultSet resultset =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
   
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting</title>   

<!--//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css.-->
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<!--<link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">-->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
<script type="text/javascript">
$(document).ready(function() {
     
    $("#example").dataTable( {
        "bProcessing": false,
        "bServerSide": false,
        "sort": "position",
        "sAjaxSource": "./UserDataServlet",
        "aoColumns": [
            { "mData": "msisdn" },
            { "mData": "short_code" },
            { "mData": "text" },
            { "mData": "created" }
           
        ]
    } );

} );

</script>
 <jsp:include page="menu.html" /> 
</head>
<body>
    
<form action="">
    <br>
    <h2 >VOTING<br><br></h2>
<table width="70%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
    <table id="example" class="display" cellspacing="0" width="100%">
       
           <%
   
  try{
//Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection connection = 
         DriverManager.getConnection
            ("jdbc:mysql://192.168.1.172/roamtech_dev?user=dev&password=root123");

       Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("select short_code from short_codes") ;
%>

<center>
   Select short code:
        <select>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getInt("short_code")%></option>
        <% } %>
        </select>
</center>

<%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>


        <thead>
            <tr>
                <th>MSISDN</th>
                <th>SHORTCODE</th>
                <th>KEYWORD</th>
                <th>CREATED</th>
               
            </tr>
        </thead>       
    </table>
    </td></tr></table>
</form>
</body>
</html>