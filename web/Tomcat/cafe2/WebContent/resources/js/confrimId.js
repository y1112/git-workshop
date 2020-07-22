$(document).ready(function() {
	var checkIdDuplicated = 0;
	
	// 아이디 중복 체크
	$('#confirmId').click(function() {
		if($('#mem_id').val() == '') {
			alert('아이디를 입력하세요!');
			$('#mem_id').focus();
			return;
		}
		
		// 메세지 초기화
		$('#message_id').text('');
		// 로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{mem_id:$('#mem_id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data) {
				// 로딩 이미지 감추기
				$('#loading').hide();
				
				if(data.result == 'idNotFound') {
					$('#message_id').css('color', 'blue').text('등록가능ID');
					checkIdDuplicated = 1;
				} else if(data.result == 'idDuplicated') {
					$('#message_id').css('color', 'red').text('중복된ID');
					$('#mem_id').val('').focus();
					checkIdDuplicated = 0;
				} else {
					alert('ID 중복체크 오류!');
				}
				
			},
			error:function() {
				// 로딩 이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류발생!');
			}
		});
	});
	
	// 아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #mem_id').keyup(function() {
		checkIdDuplicated = 0;
		$('#message_id').text('');
	});
	
	// submit 이벤트 발생시 아이디 중복 체크 여부 확인 및 유효성 체크
	$('#register_form').submit(function() {
		if(checkIdDuplicated == 0) {
			alert('아이디 중복 체크 필수!');
			if($('#mem_id').val() == '') {
				$('#mem_id').focus();
			} else {
				$('#confirmId').focus();
			}
			return false;
		}
	});
	
});