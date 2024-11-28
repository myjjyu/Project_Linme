// const nicknameInput = document.querySelector("#nickname");
// const notice = document.querySelector(".notice");
// const nicknameCheckButton = document.querySelector("#nickname_unique_check");
// const nicknameCheckHidden = document.querySelector("#nickname_check");

// // 메시지를 동적으로 표시하는 함수
// function displayMessage(message, color) {
//     notice.textContent = message;
//     notice.style.color = color;
// }

// // 닉네임 입력 이벤트
// nicknameInput.addEventListener("click", () => {
//     const nickname = nicknameInput.value;

//     // 닉네임 중복 여부 초기화
//     nicknameCheckHidden.value = "N";

//     try {
//         // 공백만 입력된 경우
//         regexHelper.value("#nickname", "공백은 사용할 수 없습니다.");

//         // 닉네임 형식 검사 (한글, 영어, 숫자만 허용)
//         regexHelper.nickName("#nickname", "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

//         // 글자 수 제한 (2자 미만)
//         regexHelper.minLength("#nickname", 2, "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

//         // 글자 수 제한 (12자 초과)
//         regexHelper.maxLength("#nickname", 12, "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

//         // 유효성 검사를 모두 통과한 경우
//         displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
//     } catch (e) {
//         displayMessage(e.message, "#ff6969"); // 오류 메시지 표시
//     }
// });

// // 닉네임 중복 확인 버튼 클릭 이벤트
// nicknameCheckButton.addEventListener("click", async (e) => {
//     e.preventDefault();

//     try {
//         // 닉네임 공백 검사
//         regexHelper.value("#nickname", "공백은 사용할 수 없습니다.");

//         // 닉네임 형식 검사
//         regexHelper.nickName("#nickname", "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

//         // 글자 수 제한 (2자 미만, 12자 초과)
//         regexHelper.minLength("#nickname", 2, "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");
//         regexHelper.maxLength("#nickname", 12, "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");

//         // 닉네임 중복 확인 API 호출
//         const nickname = nicknameInput.value.trim();
//         const response = await axiosHelper.get(`/api/myPage/nickname-update`, { nickname });

//         if (response.data.available) {
//             nicknameCheckHidden.value = "Y";
//             displayMessage("사용 가능한 닉네임입니다.", "#69A5FF");
//         } else {
//             displayMessage("사용 중인 닉네임입니다.", "#ff6969");
//         }
//     } catch (e) {
//         displayMessage(e.message, "#ff6969"); // 오류 메시지 표시
//     }
// });

document.addEventListener("DOMContentLoaded", () => {
    const nicknameInput = document.querySelector("#nickname");
    const noticeAlarm = document.querySelector(".noticeAlarm .notice");
    const nicknameCheckButton = document.querySelector("#nickname_unique_check");
    const nicknameCheckHidden = document.querySelector("#nickname_check");
    const profileImage = document.querySelector("#profileImage");
    const fileInput = document.querySelector("#fileInput");
    const deleteProfileButton = document.querySelector("#delete-profile");
    const confirmButton = document.querySelector(".confirmButton");

    // 메시지를 동적으로 표시하는 함수
    function displayMessage(message, color) {
        noticeAlarm.textContent = message;
        noticeAlarm.style.color = color;
    }

    // 닉네임 입력 이벤트
    nicknameInput.addEventListener("input", () => {
        nicknameCheckHidden.value = "N"; // 닉네임 중복 여부 초기화
        displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
        confirmButton.disabled = true; // 완료 버튼 비활성화
    });

    // 닉네임 중복 확인 버튼 클릭 이벤트
    nicknameCheckButton.addEventListener("click", async () => {
        try {
            const nickname = nicknameInput.value.trim();

            // 닉네임 유효성 검사
            if (!nickname) throw new Error("닉네임을 입력해주세요.");
            if (nickname.length < 2 || nickname.length > 12)
                throw new Error("닉네임은 2자 이상 12자 이하로 입력해주세요.");

            // 서버에 닉네임 중복 확인 요청
            const response = await axiosHelper.get(`/api/myPage/nickname-check`, { nickname });

            if (response.data.available) {
                nicknameCheckHidden.value = "Y"; // 중복 검사 통과
                displayMessage("사용 가능한 닉네임입니다.", "#69A5FF");
                confirmButton.disabled = false; // 완료 버튼 활성화
            } else {
                displayMessage("이미 사용 중인 닉네임입니다.", "#ff6969");
                confirmButton.disabled = true; // 완료 버튼 비활성화
            }
        } catch (error) {
            displayMessage(error.message, "#ff6969");
        }
    });

    // 프로필 이미지 변경 이벤트
    fileInput.addEventListener("change", () => {
        const file = fileInput.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                profileImage.src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    });

    // 프로필 이미지 삭제 이벤트
    deleteProfileButton.addEventListener("click", async () => {
        try {
            const confirmed = await utilHelper.confirmDanger(
                "프로필 이미지 삭제",
                "프로필 이미지를 삭제하시겠습니까?",
                "삭제",
                "취소"
            );

            if (confirmed.isConfirmed) {
                // 서버에 삭제 요청
                const response = await axiosHelper.put(`/api/myPage/profile-update`, {
                    delete_profile: "Y",
                });

                if (response.data.success) {
                    profileImage.src = "/assets/img/myPage/profileimg.png"; // 기본 이미지로 변경
                    await utilHelper.alertSuccess("삭제 성공", "프로필 이미지가 삭제되었습니다.");
                }
            }
        } catch (error) {
            await utilHelper.alertDanger("삭제 실패", "프로필 이미지 삭제 중 오류가 발생했습니다.");
        }
    });

    // 완료 버튼 클릭 이벤트
    confirmButton.addEventListener("click", async (e) => {
        e.preventDefault();

        const nickname = nicknameInput.value.trim();

        if (nicknameCheckHidden.value !== "Y") {
            displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
            return;
        }

        // 서버에 프로필 업데이트 요청
        const formData = new FormData();
        formData.append("nickname", nickname);
        if (fileInput.files[0]) {
            formData.append("profile", fileInput.files[0]);
        }

        try {
            const response = await axiosHelper.putMultipart(`/api/myPage/profile-update`, formData);
            if (response.data.success) {
                await utilHelper.alertSuccess("수정 성공", "프로필이 성공적으로 업데이트되었습니다.");
                location.reload(); // 페이지 새로고침
            }
        } catch (error) {
            await utilHelper.alertDanger("수정 실패", "프로필 수정 중 오류가 발생했습니다.");
        }
    });
});




// //프로필 사진 등록
// const fileInput = document.getElementById('fileInput');
// const profileImage = document.getElementById('profileImage');
// const deleteProfileButton = document.getElementById('deleteProfileButton');

// // 사진 업로드 처리
// fileInput.addEventListener('change', async (event) => {
//     const file = event.target.files[0];
//     if (file) {
//         // 미리보기로 사진 표시
//         const reader = new FileReader();
//         reader.onload = (e) => {
//             profileImage.src = e.target.result;
//             deleteProfileButton.style.display = 'block'; 
//         };
//         reader.readAsDataURL(file);

//         // 파일 업로드 요청
//         const formData = new FormData();
//         formData.append('profile', file);

//         try {
//             const response = await axios.post('/api/myPage/profile-update', formData, {
//                 headers: {
//                     'Content-Type': 'multipart/form-data',
//                 },
//             });

//             if (response.data.success) {
//                 Swal.fire('성공', '프로필 사진이 변경되었습니다.', 'success');
//             } else {
//                 Swal.fire('실패', '사진 변경 중 문제가 발생했습니다.', 'error');
//             }
//         } catch (error) {
//             Swal.fire('에러', '사진 업로드에 실패했습니다.', 'error');
//         }
//     }
// });

// // 삭제 버튼 클릭 이벤트
// document.getElementById('deleteProfileButton')?.addEventListener('click', async () => {

//     try {
//         const response = await axios.post('/api/myPage/profile-delete', {});

//         if (response.data.success) {
//             alert('프로필 사진이 삭제되었습니다.');
//             // 기본 이미지로 변경
//             document.getElementById('userProfileImage').src = '/assets/img/myPage/profileimg.png';
//             // 삭제 버튼 숨기기
//             document.getElementById('deleteProfileButton').style.display = 'none';
//         } else {
//             alert('프로필 사진 삭제에 실패했습니다.');
//         }
//     } catch (error) {
//         console.error('프로필 사진 삭제 중 오류:', error);
//         alert('프로필 사진 삭제 중 문제가 발생했습니다.');
//     }
// });
