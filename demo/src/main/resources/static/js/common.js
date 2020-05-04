function testAjax(url, param, successFnc, errorFnc) {
	var validUrl = window.location.protocol + '//' + window.location.host + '/' + url;
 	$.ajax({
 	 	type		: 'POST',
 	 	contentType: "application/json",
 	 	url			: validUrl,
 	 	dataType 	: 'json',
 	 	data 		: JSON.stringify(param),
 	 	success		: function(data) {
 	 					successFnc(data);
 	 				},
 	 	error		: function(request, status) {
 	 					errorFnc(request, status);
 	 	 	 	 	}
 	});
}

function showErrorTable(request, status){
	var data = [{
 		"error"			: request.responseText,
 		"status"		: status
 	}]

 	const schema =  [
        {"header":"エラー内容", 			"key":"error"},
        {"header":"エラーステータス", 		"key":"status"},
 	]

	$('#errorInfo').columns({
		data: data,
		schema: schema,
		search: false
	});

 	$('.ui-table-footer').hide();
}
