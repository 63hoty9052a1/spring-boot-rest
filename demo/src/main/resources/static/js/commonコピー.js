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
 	 	error		: function(XMLHttpRequest, textStatus, errorThrown) {
 	 					errorFnc(XMLHttpRequest, textStatus, errorThrown);
 	 	 	 	 	}
 	});
}

function createListTable(data, keyMap, attId){
	var table = getTableElement(data, keyMap);

	$('#' + attId).append(table);
}

function getTableElement(data, keyMap){
	 var table = document.createElement('table');
	 table.border = 1;

	 // ヘッダー
	 var tr = document.createElement('tr');
	 createRowElement(data[0], tr, true, keyMap);
	 table.appendChild(tr);

	 // テーブル本体
	 data.forEach(rowData => {
		 var tr = document.createElement('tr');

		 createRowElement(rowData, tr, false, keyMap);
		 table.appendChild(tr);
	 });

	 return table;
}

function createRowElement(data, tr, isHeader, keyMap){
	 for (key in data) {
 		 var headerName = getAliasByKey(key, keyMap);

 		 if(headerName != null){

 			var td = document.createElement('td');

 			if(data[key] != null && typeof data[key] ==  "object"){
 				// 入れ子のオブジェクトがある場合、再帰
 				var childArray = [];
 				childArray.push(data[key]);
 				var table = getTableElement(childArray, keyMap);
 				td.appendChild(table);
 				tr.appendChild(td);
 				continue;
 			}

 			var textValue = "";
 			if(isHeader){
 				td.style.backgroundColor = "floralwhite";
 				textValue = headerName;
 			} else {
 				textValue = data[key] == null ? '-' : data[key];
 			}

 			td.textContent = textValue;
 			tr.appendChild(td);
 		 }
 	 }
}

function getAliasByKey(columnName, keyMap){
	for (var key in keyMap) {
		if(key == columnName){
			return keyMap[key];
		}
	}

	return null;
}
