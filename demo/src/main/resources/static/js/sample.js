$(function(){
	// 初期表示_一覧情報取得
	testAjax('list_rest', [], success_list, error_list);

	// 削除ボタンイベント
	$('#delBtn').click(function() {
		var checkValues = $('input[name=delCheck]:checked').map(function(){
		      return $(this).val();
	    }).get();

		// 削除処理
		deleteMovie(checkValues);
	});

	// 登録ボタンイベント
	$('#registBtn').click(function() {
		// 登録処理
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

			if(data[8] == $('#selectMovieCategory').val()){
				return true;
			}

			return false;
		}
	);
});

const columns =  [
    {"data":"id",   "width": '1%', "visible": false },
    {"data":"name", "width": '27%'},
    {"data":"url", "width": '1%', "visible": false },
    {"data":"thumbnailUrl", "width": '20%',
    	render:function(data, type, row, meta){
    		var params = meta.settings.aoData[meta.row]._aData;
    		return `<a href="javascript:void(0);" onclick="showMovieArea(\'${params.id}\', \'${params.url}\')"><img border="0" src="${data}" width="120" height="90" alt=""></a>`;
    	}
    },

    {"data":"categoryId", "width": '20%',
    	render:function(data){

    		return createCategoryTag(categoryData, data, 'movieCategory', false);
    	}
    },

    {"data":"commentCount", "width": '10%'},

    {"data":"", "defaultContent": "", "width": '10%',
    	render:function(data, type, row, meta){
    		var params = meta.settings.aoData[meta.row]._aData;

    		return `<div align="center"><input type="checkbox" name="delCheck" value="${meta.row}"></div>`;
    	}
    },

    {"data":"", "defaultContent": "", "width": '10%',
    	render:function(data, type, row, meta){
    		var newAddress = "";
    		var params = meta.settings.aoData[meta.row]._aData;
    		if(params.userAddressInfo != null){
    			newAddress = params.userAddressInfo.address;
    		}
    		return `<div align="center"><button onclick="updateAddress(\'${params.userId}\', \'${newAddress}\')">更新</button></div>`;
    	}
    },

    {"data":"categoryId", "width": '1%', "visible": false},
];

const columnDefs = [
	{ "targets": 0, "width": '1%', "visible": false },
	{ "targets": 1, "width": '27%' },
	{ "targets": 2, "width": '1%', "visible": false },
	{ "targets": 3, "width": '20%' },
	{ "targets": 4, "width": '20%' },
	{ "targets": 5, "width": '10%' },
	{ "targets": 6, "width": '10%', "orderable": false },
	{ "targets": 7, "width": '10%', "orderable": false },
	{ "targets": 8, "width": '1%', "visible": false },
];

const language = {
	sProcessing: '処理中...',
	sLengthMenu: '_MENU_ 件表示',
	sZeroRecords: 'データはありません。',
	sInfo: ' _TOTAL_ 件中 _START_ から _END_ まで表示',
	sInfoEmpty: ' 0 件中 0 から 0 まで表示',
	sInfoFiltered: '（全 _MAX_ 件より抽出）',
	sInfoPostFix: '',
	sUrl: '',
	sSearch: '検索 ',
	oPaginate: {
		sPrevious: '<',
		sNext: '>',
		sSearch: '検索'
	}
};

var categoryData;
//通信成功時処理（一覧情報）
function success_list(data) {
	// カテゴリ情報
	categoryData = data[1];
	// 登録エリアのカテゴリ生成
	$('#categoryDiv').empty();
	$('#categoryDiv').append(createCategoryTag(categoryData, 0, 'registMovieCategory', false));

	// テーブルヘッダのカテゴリ生成
	$('#list_categoryDiv').empty();
	$('#list_categoryDiv').append(createCategoryTag(categoryData, 0, 'selectMovieCategory', true));

	// 一覧テーブル
	var sss = $('#movieInfo').DataTable({
		destroy: true,
		data: data[0],
		autowidth: false,
		columns: columns,
		//columnDefs: columnDefs,
		language: language,
		//pagingType: "full_numbers",
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
function error_list(request, status) {
	//showErrorTable(request, status);
}

function createCategoryTag(data, selectedNo, tagId, isSort) {
	var onChangeAttr = isSort ? 'onchange="sortCategory()"' : '';
	var selectTagStart = '<select ' + onChangeAttr +  ' id="' + tagId + '" style="height:25px;">';
	var selectTagEnd = '</select>';

	var selectTacOptions = '<option value="0"></option>';

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
		testAjax('delete', params, success_delete, error_delete);
	}

}

function updateAddress(userId) {
//	var param = {"userId":userId};
//	testAjax('delete', param, success_delete, error_delete);
}

function success_update(updCnt) {

}

function error_update(updCnt) {

}

function success_delete(delCnt) {
	//alert('削除件数：' + delCnt);
	$('#movieInfo').DataTable().destroy();
	testAjax('list_rest', [], success_list, error_list);
}

function error_delete(request, status) {
	//showErrorTable(request, status);
}

function showMovieArea(id, url){
	$('#movieFrame').empty();
	$('#commentRow').empty();
	$('#movieArea').show();

	$('#movieFrame').append('<iframe style="position: absolute; top: 0; right: 0; width: 100%; height: 100%;" src="' + url + '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>');
	$('#mId').val(id);

	// コメント取得
	var param = {"mid": id};
	testAjax('comment', param, success_comment, error_comment);
}

function registComment(comment){
	var param = {"mid": $('#mId').val(), "comment": comment};
	testAjax('regist_comment', param, success_registComment, error_registComment);
}

function success_registComment(data) {
	$('#commentRow').empty();
	// コメント取得
	var param = {"mid": $('#mId').val()};
	testAjax('comment', param, success_comment, error_comment);
}

function error_registComment() {

}

function registMovieLink(){
	var movieName =  $('#movieName').val();
	var movieLink = $('#movieLink').val();
	var movieCategory = $('#registMovieCategory option:selected').val();

	var param = {"name": movieName, "url": movieLink, "categoryId": movieCategory};
	testAjax('regist_movie_link', param, success_registMovieLink, error_registMovieLink);
}

function success_registMovieLink() {

	bugControl();

	$('#movieInfo').DataTable().destroy();
	testAjax('list_rest', [], success_list, error_list);


}

function bugControl() {
	// scrollY適用時、テーブルを再ロード後、drawからの「ext.search.push」イベントで
	// ヘッダ項目の読込エラーが起きてテーブルズレるのため、再ロード前に以下を実行
	$('#selectMovieCategory').val(999);
	$('#movieInfo').DataTable().draw();
}

function error_registMovieLink() {

}


function success_comment(data) {
	for(var movieObj of data){
		addComment(movieObj.comment);
	}
}

function error_comment() {

}

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
