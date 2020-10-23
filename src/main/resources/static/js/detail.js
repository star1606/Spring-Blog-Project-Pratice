
function deleteById(id) {
	
	let data = {
			id : id
	}
	
	$.ajax({
		
		type : "DELETE",
		data : data,
		url : "/delete",	
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		// 버튼클릭했을 때 사용하는 contentType은? ->
		// data가 key:value로 보내진 것을 주목
		dataType : "text",
		
		
		
	}).done(function(resp){
		console.log(1, resp);
	
		
		// 이거 작동이 안되는데
		//location.href("/");
		if(resp == 1){
			alert("삭제성공")
			location.href="/";
		} else {
			alert("삭제 실패")
		}
		
		
	
		
	}).fail(function(error){
		console.log(error.responseText);
		alert("서버 오류")
	});
	
}