<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload.jsp</title>
</head>
<body>
	upload.jsp<br>
	파일 업로드 완료<br>
	<svg>
		<a href="download?fileName=${fileName}">
			<rect id="download" width="200" height="100" rx="20" ry="20" style="fill:#E1F5A9;"/>
		</a>
	</svg>
</body>
</html>