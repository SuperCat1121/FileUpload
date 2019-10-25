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
	
	$(function() {
		$("#uploadForm").submit(function() {
			var existFile = $("input[type=file]").val();
			if(!existFile) {
				alert("파일을 첨부해주세요!!");
				return false;
			}
		});
	});
</script>
<style type="text/css">
	form label {
		padding: 6px 12px;
	    color: black;
	    background: #fff;
	    cursor: pointer;
	    border: 1px solid #A4A4A4;
	    border-radius: 1px;
	    margin-left: 2px;
	    position: absolute;
	    font-size: 13px;
	}
	
	form label:hover {
		border: 1px solid rgba(108,161,254, .6);
	}

	form input[type="file"] {
		width: 240px;
	    height: 27px;
	    margin-left: 50px;
	    margin-top: 2px;
	}
</style>
</head>
<body>
	<svg id="svg" style="width:500px; height:500px;">
	</svg>
	<form id="uploadForm" action="upload" enctype="multipart/form-data" method="post">
		<label for="inputFile">업로드파일 찾기</label>
		<input type="file" id="inputFile" name="file">
		<input type="submit" value="업로드하기">
	</form>
</body>
</html>