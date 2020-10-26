// ajax update 로직 정리할 것.
function update(id) {

	let data = $("#updateFrm").serialize();
	console.log("frm serialize하면 값이 원하는데로 나오나? : " + data);
	
	
	let title = $("#title").val();
	let content = $("#content").val();
	console.log("title", title)
	console.log("content", content)
	
	if(title == null || title == "") {
		alert("제목을 입력하세요.");
		return;
	} else if(content == null || content == "") {
		alert("내용을 입력하세요.");	
		return;
	}

	$.ajax({
		type : "PUT",
		url : "/updateProc",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		// contentType : json으로 잘못 넣으면 바로 글 수정실패뜸
		dataType : "text"

	}).done(function(resp) {
		console.log(resp);
		if (resp == 1) {
			alert("글 수정 성공");
			location.href = "/detail/" + id;
		} else {
			alert("글 수정 실패");
		}

	}).fail(function(error) {
		alert("서버 문제");
	});
	
	
	function validate() {
		
//		return console.log("validate 이상무");
	
	}
	
	
	
	
	
	

}