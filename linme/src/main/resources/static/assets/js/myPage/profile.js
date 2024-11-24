const nicknameInput = document.querySelector("#nickname");
const notice = document.querySelector(".notice");
const nicknameCheckButton = document.querySelector("#nickname_unique_check");
const nicknameCheckHidden = document.querySelector("#nickname_check");

// 메시지를 동적으로 표시하는 함수
function displayMessage(message, color) {
    notice.textContent = message;
    notice.style.color = color;
}

// 닉네임 입력 이벤트
nicknameInput.addEventListener("input", () => {
    const nickname = nicknameInput.value;

    // 닉네임 중복 여부 초기화
    nicknameCheckHidden.value = "N";

    try {
        // 공백만 입력된 경우
        regexHelper.value("#nickname", "공백은 사용할 수 없습니다.");

        // 닉네임 형식 검사 (한글, 영어, 숫자만 허용)
        regexHelper.nickName("#nickname", "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

        // 글자 수 제한 (2자 미만)
        regexHelper.minLength("#nickname", 2, "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

        // 글자 수 제한 (12자 초과)
        regexHelper.maxLength("#nickname", 12, "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

        // 유효성 검사를 모두 통과한 경우
        displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
    } catch (e) {
        displayMessage(e.message, "#ff6969"); // 오류 메시지 표시
    }
});

// 닉네임 중복 확인 버튼 클릭 이벤트
nicknameCheckButton.addEventListener("click", async (e) => {
    e.preventDefault();

    try {
        // 닉네임 공백 검사
        regexHelper.value("#nickname", "공백은 사용할 수 없습니다.");

        // 닉네임 형식 검사
        regexHelper.nickName("#nickname", "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

        // 글자 수 제한 (2자 미만, 12자 초과)
        regexHelper.minLength("#nickname", 2, "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");
        regexHelper.maxLength("#nickname", 12, "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

        // 닉네임 중복 확인 API 호출
        const nickname = nicknameInput.value.trim();
        const response = await axiosHelper.get(`/api/myPage/nickname-update`, { nickname });

        if (response.data.available) {
            nicknameCheckHidden.value = "Y";
            displayMessage("사용 가능한 닉네임입니다.", "#69A5FF");
        } else {
            displayMessage("사용 중인 닉네임입니다.", "#ff6969");
        }
    } catch (e) {
        displayMessage(e.message, "#ff6969"); // 오류 메시지 표시
    }
});



//프로필 사진 등록
const fileInput = document.getElementById('fileInput');
const profileImage = document.getElementById('profileImage');

// 사진 업로드 처리
fileInput.addEventListener('change', async (event) => {
    const file = event.target.files[0];
    if (file) {
        // 미리보기로 사진 표시
        const reader = new FileReader();
        reader.onload = (e) => {
            profileImage.src = e.target.result;
        };
        reader.readAsDataURL(file);

        // 파일 업로드 요청
        const formData = new FormData();
        formData.append('profile', file);

        try {
            const response = await axios.post('/api/myPage/profile-update', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });

            if (response.data.success) {
                Swal.fire('성공', '프로필 사진이 변경되었습니다.', 'success');
            } else {
                Swal.fire('실패', '사진 변경 중 문제가 발생했습니다.', 'error');
            }
        } catch (error) {
            Swal.fire('에러', '사진 업로드에 실패했습니다.', 'error');
        }
    }
});
