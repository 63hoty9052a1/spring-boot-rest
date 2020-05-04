$(function(){
	var param = {"first_name":"一郎", "last_name":"鈴木"};
	testAjax('list_rest', param, successFnc, errorFnc);
});

//通信成功時処理
function successFnc(data) {
	var keyMap = {
		userName: '名前',
		userAddressInfo: '住所情報',
		address: '住所',
		zipCodeX: '郵便番号',
	};

	createListTable(data, keyMap, 'userInfo');
}

// 通信失敗時処理
function errorFnc(XMLHttpRequest, textStatus, errorThrown) {
 	alert("error:" + XMLHttpRequest);
 	alert("status:" + textStatus);
 	alert("errorThrown:" + errorThrown);
}