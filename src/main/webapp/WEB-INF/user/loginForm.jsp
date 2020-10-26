<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../include/nav.jsp" %> <!--  상대경로.. -->
<% 
	/*
	String remember = (String) request.getAttribute("remember");
	if(remember ==null){
		remember = "";
	}
	*/
%>
<div class = "container">
	
<form action="/loginProc" method="POST" onsubmit="return validate()" class="was-validated" >
  <div class="form-group">
    <label for="username">Username:</label>
    <input type="text" value = "${cookie.remember.value}" class="form-control" id="username" placeholder="Enter username" name="username" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  
  
 

  <div class="form-group form-check">
    <label class="form-check-label">
    <c:choose>
    	<c:when test="${empty cookie.remember}">
    		  <input class="form-check-input" type="checkbox" name="remember"> 아이디 기억하기
		</c:when>
		<c:otherwise>
			<input class ="form-check-input" type="checkbox" name="remember" checked> 아이디 기억하기
		</c:otherwise>	
	</c:choose>
    </label>
  </div>
  <button type="submit" class="btn btn-primary">로그인</button>
</form>
</div>


<%-- 	
 <c:if test="${empty sessionScope.principal }">
 <c:redirect url = "/index.jsp"></c:redirect>
 </c:if>		
--%>
	
<!--  체크박스에 체크를 누르면 쿠키가 저장되고  -->
<!--   -->


<script>
	function validate() {
		// 값을 어떻게 들고 올까? 제이쿼리로해서 id 선택자를 써서 가져온다
		let username = $("#username").val();
		let password = $("#password").val();
		
		if(username == "" || username == null) {
			alert("아이디를 입력하세요");
			return false;
			
		} else if (password == "" || password == null) {	
			alert("비밀번호를 입력하세요");
			return false;
		}
			return true;
		
	}

</script>

<%@ include file = "../include/footer.jsp" %>