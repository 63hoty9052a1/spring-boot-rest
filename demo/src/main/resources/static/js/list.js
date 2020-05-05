$(function(){
	// 初期表示_一覧情報取得
	comAjax('movie_list', [], success_list, error_list);

	// 削除ボタンイベント
	$('#delBtn').click(function() {
		var checkValues = $('input[name=delCheck]:checked').map(function(){
		      return $(this).val();
	    }).get();

		deleteMovie(checkValues);
	});

	// 登録ボタンイベント
	$('#registBtn').click(function() {
		registMovieLink();
	});

	//コメント登録イベント
	$('#inputComment').keypress(function(e){
		var comment = $('#inputComment').val();

		if ( e.which == 13 && comment != "") {
			addComment(comment);
			registComment(comment);

			$('#inputComment').val("")
			return false;
		}
	} );

	// カテゴリソート時のリスナー（draw時に呼び出される）
	$('#movieInfo').DataTable.ext.search.push(
		function(settings, data, dataIndex) {
			if(0 == $('#selectMovieCategory').val()){
				return true;
			}

			if(data[9] == $('#selectMovieCategory').val()){
				return true;
			}

			return false;
		}
	);
});

const columns =  [
    {"data":"id",   "width": '1%', "visible": false },
    {"data":"name", "width": '20%', "orderable": false,
    	render:function(data, type, row, meta){
    		return `<div align="center" style="margin-top: 1em;"><textarea  style="border:solid 0px; background-color:rgba(0,0,0,0); font-size:13pt; overflow:hidden; resize: none;" size="15" id="mName_${meta.row}" >${data}</textarea></div>`;
    	}
    },
    {"data":"url", "width": '1%', "visible": false },
    {"data":"thumbnailUrl", "width": '20%', "orderable": false,
    	render:function(data, type, row, meta){
    		var params = meta.settings.aoData[meta.row]._aData;
    		return `<a href="javascript:void(0);" onclick="showMovieArea(\'${params.id}\', \'${params.url}\')"><img border="0" src="${data}" width="120" height="90" alt=""></a>`;
    	}
    },

    {"data":"categoryId", "width": '15%', "orderable": false,
    	render:function(data, type, row, meta){

    		return createCategoryTag(categoryData, data, 'movieCategory_' + meta.row, false);
    	}
    },

    {"data":"commentCount", "width": '15%'},

    {"data":"registDate", "width": '10%'},

    {"data":"", "defaultContent": "", "width": '10%', "orderable": false,
    	render:function(data, type, row, meta){
    		return `<div align="center"><input type="checkbox" name="delCheck" value="${meta.row}"></div>`;
    	}
    },

    {"data":"", "defaultContent": "", "width": '10%', "orderable": false,
    	render:function(data, type, row, meta){
    		var newAddress = "";
    		var params = meta.settings.aoData[meta.row]._aData;

    		return `<div align="center"><button onclick="updateMovie(\'${params.id}\', \'${meta.row}\')">更新</button></div>`;
    	}
    },

    {"data":"categoryId", "width": '1%', "visible": false},
];

var categoryData;
//通信成功時処理（一覧情報）
function success_list(data) {
	// カテゴリ情報
	categoryData = data[1];

	$('#categoryDiv').empty();
	$('#categoryDiv').append(createCategoryTag(categoryData, 0, 'registMovieCategory', false));
	$('#list_categoryDiv').empty();
	$('#list_categoryDiv').append(createCategoryTag(categoryData, 0, 'selectMovieCategory', true));

	// 一覧テーブル
	var sss = $('#movieInfo').DataTable({
		destroy: true,
		data: data[0],
		autowidth: false,
		stateSave: true,
		stateDuration: -1,
		order: [ 6, "desc" ],
		columns: columns,
		language: language,
		lengthMenu: [ 5, 10, 15, 20, 50, 100 ],
		displayLength: 5,
		scrollX: false,
	    scrollY: 500,
	});

	$('#movieInfo_length').css('text-align', 'left');
	$('#movieInfo_filter').css('text-align', 'left');
	$('#movieInfo_info').css('text-align', 'left');
	$('#movieInfo_paginate').css('text-align', 'left');
}

// 通信失敗時処理（一覧情報）
function error_list(request, status) {}

function createCategoryTag(data, selectedNo, tagId, isSort) {
	var onChangeAttr = isSort ? 'onchange="sortCategory()"' : '';
	var selectTagStart = '<select ' + onChangeAttr +  ' id="' + tagId + '" style="height:25px;">';
	var selectTagEnd = '</select>';

	var selectTacOptions = '<option value="0">-</option>';

	for(var category of data){
		var optionTagStart = '<option ';
		if(category.categoryId == selectedNo){
			optionTagStart += 'selected ';
		}
		selectTacOptions += optionTagStart + 'value="' + category.categoryId + '">' + category.categoryName + '</option>';
	}

	return selectTagStart + selectTacOptions + selectTagEnd;
}

function sortCategory() {
	$('#movieInfo').DataTable().draw();
}

function deleteMovie(selectRowsNo) {
	var tableData = $('#movieInfo').DataTable();
	var params = [];

	for(var no of selectRowsNo){
		// チェックされた行のデータを取得
		var id = tableData.context[0].oInit.data[no].id;
		params.push({"id":id});
	}

	if(params.length > 0){
		bugControl();
		comAjax('delete', params, success_delete, error_delete);
	}
}

function updateMovie(id, rowNo) {
	var tableData = $('#movieInfo').DataTable();
	var rowData = tableData.context[0].oInit.data[rowNo];

	var inputName = $('#mName_' + rowNo).val();
	var inputCategory = $('#movieCategory_'  + rowNo).val();

	if(!(inputName == rowData.name && inputCategory == rowData.categoryId)){
		var param = {"id": rowData.id, "name": inputName, "categoryId": inputCategory};
		comAjax('update_movie', param, success_update, error_update);
	}
}

function success_update(updCnt) {
	if(updCnt > 0){
		$('#movieInfo').DataTable().destroy();
		comAjax('movie_list', [], success_list, error_list);
	}
}

function error_update(updCnt) {}

function success_delete(delCnt) {
	$('#movieInfo').DataTable().destroy();
	comAjax('movie_list', [], success_list, error_list);
}

function error_delete(request, status) {}

function showMovieArea(id, url){
	$('#movieFrame').empty();
	$('#commentRow').empty();
	$('#movieArea').show();

	$('#movieFrame').append('<iframe style="position: absolute; top: 0; right: 0; width: 100%; height: 100%;" src="' + url + '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>');
	$('#mId').val(id);

	var param = {"mid": id};
	comAjax('comment', param, success_comment, error_comment);
}

function registComment(comment){
	var param = {"mid": $('#mId').val(), "comment": comment};
	comAjax('regist_comment', param, success_registComment, error_registComment);
}

function success_registComment(data) {
	$('#commentRow').empty();

	var param = {"mid": $('#mId').val()};
	comAjax('comment', param, success_comment, error_comment);
}

function error_registComment() {}

function registMovieLink(){
	var movieName =  $('#movieName').val();
	var movieLink = $('#movieLink').val();
	var movieCategory = $('#registMovieCategory option:selected').val();

	var param = {"name": movieName, "url": movieLink, "categoryId": movieCategory};
	comAjax('regist_movie_link', param, success_registMovieLink, error_registMovieLink);
}

function success_registMovieLink() {
	bugControl();

	$('#movieInfo').DataTable().destroy();
	comAjax('movie_list', [], success_list, error_list);
}

function bugControl() {
	$('#selectMovieCategory').val(999);
	$('#movieInfo').DataTable().draw();
}

function error_registMovieLink() {}


function success_comment(data) {
	for(var movieObj of data){
		addComment(movieObj.comment);
	}
}

function error_comment() {}

function addComment(comment){
	$('#commentRow').append('<tr><td style="text-align:left; border:inset 0px"><font size="2" color="white">' + comment + '</font></td></tr>');
	$('#commentRow').css("top", $('#movieArea').height());
	obj = $('#divScrollBox');
	obj.scrollTop(obj[0].scrollHeight);
}

function closeMovieArea(){
	$('#movieFrame').empty();
	$('#commentRow').empty();
	$('#movieArea').hide();
}
