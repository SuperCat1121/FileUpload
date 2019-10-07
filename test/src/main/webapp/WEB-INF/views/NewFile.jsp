<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	svg {
		border: 1px solid black;
	}
	.bar {
		fill: orange;
	}
</style>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
window.onload = function() {
	var monthList = new Array();
	<c:forEach items="${monthList}" var="item">
		monthList.push("${item}");
	</c:forEach>
	var numList = ${numList};
	console.log(typeof numList);
	
	var svg = d3.select("#svg");
	svg.selectAll()
	   .data(numList)
	   .enter().append("rect")
	   .attr("height", function(d) {
		   return d;
	   })
	   .attr("width", function(d) {
		   return "30px";
	   })
	   .attr("x", function(d, i) {
		   return i*35;
	   })
	   .attr("y", function(d, i) {
		   return 100-d;
	   })
	   .attr("class", "bar");
	}
</script>
</head>
<body>
	<svg id="svg" style="width:500px; height:500px;">
	</svg>
	<form name="uploadForm" action="upload" enctype="multipart/form-data" method="post">
		<input type="text" name="title">
		<input type="file" name="upfile">
		<input type="submit">
	</form>
</body>
</html>