function comAjax(url, param, successFnc, errorFnc) {
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

const language = {
		sProcessing: '処理中...',
		sLengthMenu: '_MENU_ 件表示',
		sZeroRecords: 'データはありません。',
		sInfo: ' _TOTAL_ 件中 _START_ から _END_ まで表示',
		sInfoEmpty: ' 0 件中 0 から 0 まで表示',
		sInfoFiltered: '（全 _MAX_ 件より抽出）',
		sInfoPostFix: '',
		sUrl: '',
		sSearch: '絞り込み：',
		oPaginate: {
			sPrevious: '<',
			sNext: '>',
			sSearch: '検索'
		},
}