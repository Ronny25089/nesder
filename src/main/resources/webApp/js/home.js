// function getModal() {
  // $.ajax({
  //   url: 'page/signing.html',
  //   dataType: 'text',
  //   success: function(data) {
  //     alert(data);
  //   }
  // });
// }

//　共通，点击打开modal，
//  目标路径为href所指定路径
$('.li-modal').on('click', function(e){
  e.preventDefault();
  $('#theModal').modal('show').find('.modal-content').load($(this).attr('href'));
});
