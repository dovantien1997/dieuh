$(document).ready(function() {

	$('.nBtn, .table .eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(thuonghieu, status) {
				$('.myFormUpdate #id').val(thuonghieu.id);
				$('.myFormUpdate #tenTH').val(thuonghieu.tenTH);
				$('.myFormUpdate #dateCreated').val(thuonghieu.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #tenTH').val('');
			$('.myFormCreate #myModalCreate').modal();
		}
	});
});
$(document).ready(function() {

	$('.nLSP, .table .eLSP').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(loaisanpham, status) {
				$('.myFormUpdate #id').val(loaisanpham.id);
				$('.myFormUpdate #name').val(loaisanpham.name);
				$('.myFormUpdate #dateCreated').val(loaisanpham.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #name').val('');
			$('.myFormCreate #myModalCreate').modal();
		}
	});
});
$(document).ready(function() {

	$('.nLM, .table .eLM').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(loaimay, status) {
				$('.myFormUpdate #id').val(loaimay.id);
				$('.myFormUpdate #name').val(loaimay.name);
				$('.myFormUpdate #dateCreated').val(loaimay.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #name').val('');
			$('.myFormCreate #myModalCreate').modal();
		}
	});
});
$(document).ready(function() {

	$('.nCN, .table .eCN').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(congnghe, status) {
				$('.myFormUpdate #id').val(congnghe.id);
				$('.myFormUpdate #name').val(congnghe.name);
				$('.myFormUpdate #dateCreated').val(congnghe.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #name').val('');
			$('.myFormCreate #myModalCreate').modal();
		}
	});
});
$(document).ready(function() {

	$('.eBG').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
			$.get(href, function(banggia, status) {
				$('.myFormUpdate #id').val(banggia.id);
				$('.myFormUpdate #field1').val(banggia.field1);
				$('.myFormUpdate #field2').val(banggia.field2);
				$('.myFormUpdate #field3').val(banggia.field3);
				$('.myFormUpdate #field4').val(banggia.field4);
				$('.myFormUpdate #field5').val(banggia.field5);
				$('.myFormUpdate #field6').val(banggia.field6);
				$('.myFormUpdate #field7').val(banggia.field7);
				$('.myFormUpdate #field8').val(banggia.field8);
				$('.myFormUpdate #field9').val(banggia.field9);
				$('.myFormUpdate #field10').val(banggia.field10);
				$('.myFormUpdate #field11').val(banggia.field11);
				$('.myFormUpdate #field12').val(banggia.field12);
				$('.myFormUpdate #field13').val(banggia.field13);
				$('.myFormUpdate #field14').val(banggia.field14);
				$('.myFormUpdate #field15').val(banggia.field15);
				$('.myFormUpdate #field16').val(banggia.field16);
				$('.myFormUpdate #field17').val(banggia.field17);
				$('.myFormUpdate #field18').val(banggia.field18);
				$('.myFormUpdate #field19').val(banggia.field19);
				$('.myFormUpdate #field20').val(banggia.field20);
				$('.myFormUpdate #field21').val(banggia.field21);
				$('.myFormUpdate #field22').val(banggia.field22);
				$('.myFormUpdate #field23').val(banggia.field23);
				$('.myFormUpdate #field24').val(banggia.field24);
				$('.myFormUpdate #field25').val(banggia.field25);
				$('.myFormUpdate #field26').val(banggia.field26);
				$('.myFormUpdate #field27').val(banggia.field27);
				$('.myFormUpdate #field28').val(banggia.field28);
				$('.myFormUpdate #field29').val(banggia.field29);
				
				$('.myFormUpdate #dateCreated').val(banggia.dateCreated);
				
			});
			$('.myFormUpdate #updateModal').modal();
			
	});
});
$(document).ready(function() {

	$('.n880, .table .e880').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		// for update user
		if (text == 'Edit') {
			$.get(href, function(image, status) {
				$('.myFormUpdate #id').val(image.id);
				$('.myFormUpdate #dateCreated').val(image.dateCreated);
				$('.myFormUpdate #file880').val(image.fileImage);
				
			});
			$('.myFormUpdate #updateModal880').modal();
			
		} else {
			// for creating user
			$('.myFormCreate #myModalCreate880').modal();
		}
	});
});
$(document).ready(function() {

	ClassicEditor
	.create( document.querySelector( '#editor' ), {
		ckfinder: {
			uploadUrl: '/ckfinder/connector?command=QuickUpload&type=Files&responseType=json',
		},
		toolbar: [ 'ckfinder', 'imageUpload', '|', 'heading', '|', 'bold', 'italic', '|', 'undo', 'redo' ]
	} )
	.catch( error => {
		console.error( error );
	} );
});
$(document).ready(function() {
	CKFinder.widget( 'ckfinder-widget', {
		displayFoldersPanel: false,
		width: '100%',
		height: 700
	} );
});

$(function() {
	var images = [];
	$('.image-confirm-delete').on('click', function(e) {
		e.preventDefault();
		var id = $(this).data('id');
		var name = $(this).data('name');
		images.push(name);
		$('#removeImages').val(images);
		$('#imageindex' + id).hide();

	});

});