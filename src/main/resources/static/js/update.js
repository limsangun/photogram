// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault(); //폼태크 액션 막기!!	
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data: data,
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res=>{	// HttpStatue 상태코드 200번대
		console.log("성공 : " + JSON.stringify(res));
		location.href=`/user/${userId}`;
	}).fail(error=>{ // HttpStatue 상태코드 200번대 아닐 때
		if(error.data == null) {
			alert(error.responseJSON.message);
		} else {
			alert(JSON.stringify(error.responseJSON.data));			
		}
	});
}