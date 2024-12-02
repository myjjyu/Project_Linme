// document.addEventListener("DOMContentLoaded", () => {
//     const nicknameInput = document.querySelector("#nickname");
//     const noticeAlarm = document.querySelector(".noticeAlarm .notice");
//     const nicknameCheckButton = document.querySelector("#nickname_unique_check");
//     const nicknameCheckHidden = document.querySelector("#nickname_check");
//     const profileImage = document.querySelector("#profileImage");
//     const fileInput = document.querySelector("#fileInput");
//     const deleteProfileButton = document.querySelector("#delete-profile");
//     const confirmButton = document.querySelector(".confirmButton");

//     // 메시지를 동적으로 표시하는 함수
//     function displayMessage(message, color) {
//         noticeAlarm.textContent = message;
//         noticeAlarm.style.color = color;
//     }

//     // 닉네임 입력 이벤트
//     nicknameInput.addEventListener("input", () => {
//         nicknameCheckHidden.value = "N"; // 닉네임 중복 여부 초기화
//         displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
//         confirmButton.disabled = true; // 완료 버튼 비활성화
//     });

//     // 닉네임 중복 확인 버튼 클릭 이벤트
//     nicknameCheckButton.addEventListener("click", async () => {
//         try {
//             const nickname = nicknameInput.value.trim();

//             // 닉네임 유효성 검사
//             if (!nickname) throw new Error("닉네임을 입력해주세요.");
//             if (nickname.length < 2 || nickname.length > 12)
//                 throw new Error("닉네임은 2자 이상 12자 이하로 입력해주세요.");

//             // 서버에 닉네임 중복 확인 요청
//             const response = await axiosHelper.get(`/api/myPage/nickname-update`, { nickname });

//             if (response.data.success) {
//                 nicknameCheckHidden.value = "Y"; // 중복 검사 통과
//                 displayMessage("사용 가능한 닉네임입니다.", "#69A5FF");
//                 confirmButton.disabled = false; // 완료 버튼 활성화
//             } else {
//                 displayMessage("이미 사용 중인 닉네임입니다.", "#ff6969");
//                 confirmButton.disabled = true; // 완료 버튼 비활성화
//             }
//         } catch (error) {
//             displayMessage(error.message, "#ff6969");
//         }
//     });

//     // 닉네임 입력 이벤트
//     nicknameInput.addEventListener("input", () => {
//         nicknameCheckHidden.value = "N"; // 닉네임 중복 여부 초기화
//         displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
//         confirmButton.disabled = true; // 완료 버튼 비활성화
//     });

//     // 닉네임 중복 확인 버튼 클릭 이벤트
//     nicknameCheckButton.addEventListener("click", async () => {
//         try {
//             //const nickname = nicknameInput.value.trim();
//             // 공백 입력 검사 함수
//             const checkspace = (selector, message) => {
//                 const val = document.querySelector(selector).value;
//                 if (val.trim() === "") {
//                     throw new Error(message);
//                 }
//             };

//             // 닉네임 유효성 검사
//             checkspace("#nickname", "공백은 사용할 수 없습니다.");
//             regexHelper.minLength("#nickname", 2, "닉네임은 2자 이상 입력해주세요.");
//             regexHelper.maxLength("#nickname", 13, "닉네임은 12자 이하로 입력해주세요.");
//             regexHelper.nickName("#nickname", "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다.");

//             // 서버에 닉네임 중복 확인 요청
//             const response = await axiosHelper.get(`/api/myPage/nickname-update`, { nickname });

//             if (response.data.success) {
//                 nicknameCheckHidden.value = "Y"; // 중복 검사 통과
//                 displayMessage("사용 가능한 닉네임입니다.", "#69A5FF");
//                 confirmButton.disabled = false; // 완료 버튼 활성화
//             } else {
//                 displayMessage("이미 사용 중인 닉네임입니다.", "#ff6969");
//                 confirmButton.disabled = true; // 완료 버튼 비활성화
//             }
//         } catch (error) {
//             if (error instanceof StringFormatException) {
//                 displayMessage(error.message, "#ff6969");
//                 if (error.element) {
//                     setTimeout(() => error.element.focus(), 300);
//                 }
//             } else {
//                 displayMessage(error.message || "닉네임 중복 확인 중 오류가 발생했습니다.", "#ff6969");
//             }
//         }
//     });


//     // 프로필 이미지 변경 이벤트
//     fileInput.addEventListener("change", () => {
//         const file = fileInput.files[0];
//         if (file) {
//             const reader = new FileReader();
//             reader.onload = (e) => {
//                 profileImage.src = e.target.result;
//             };
//             reader.readAsDataURL(file);
//         }
//     });

//     // 프로필 이미지 삭제 이벤트
//     deleteProfileButton.addEventListener("click", async () => {
//         try {
//             const confirmed = await utilHelper.confirmDanger(
//                 "프로필 이미지 삭제",
//                 "프로필 이미지를 삭제하시겠습니까?",
//                 "삭제",
//                 "취소"
//             );

//             if (confirmed.isConfirmed) {
//                 // 서버에 삭제 요청
//                 const response = await axiosHelper.put(`/api/myPage/profile-update`, {
//                     delete_profile: "Y",
//                 });

//                 if (response.data.success) {
//                     profileImage.src = "/assets/img/myPage/profileimg.jpg"; // 기본 이미지로 변경
//                     await utilHelper.alertSuccess("삭제 성공", "프로필 이미지가 삭제되었습니다.");
//                 }
//             }
//         } catch (error) {
//             await utilHelper.alertDanger("삭제 실패", "프로필 이미지 삭제 중 오류가 발생했습니다.");
//         }
//     });

//     // 완료 버튼 클릭 이벤트 (닉네임 저장 및 프로필 이미지 업로드)
//     confirmButton.addEventListener("click", async (e) => {
//         e.preventDefault();

//         const nickname = nicknameInput.value.trim();
//         if (nicknameCheckHidden.value !== "Y") {
//             displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
//             return;
//         }

//         try {
//             // 닉네임 저장 API 호출
//             const nicknameResponse = await axiosHelper.put(`/api/myPage/nickname-update`, { nickname });
//             if (!nicknameResponse.data.success) {
//                 throw new Error("닉네임 저장 중 오류가 발생했습니다.");
//             }

//             // 프로필 이미지 저장 API 호출
//             const formData = new FormData();
//             const data=await axiosHelper.postMultipart("[[@{/api/myPage/profile-update}]]",formData);
//             // 성공 메시지 표시
//             await utilHelper.alertSuccess("수정 성공", "닉네임과 프로필 이미지가 성공적으로 저장되었습니다.");
//             location.reload(); // 페이지 새로고침
//         } catch (error) {
//             await utilHelper.alertDanger("수정 실패", error.message || "수정 중 오류가 발생했습니다.");
//         }
//     });
// });

// document.addEventListener("DOMContentLoaded", () => {
//     const nicknameInput = document.querySelector("#nickname");
//     const noticeAlarm = document.querySelector(".noticeAlarm .notice");
//     const nicknameCheckButton = document.querySelector("#nickname_unique_check");
//     const nicknameCheckHidden = document.querySelector("#nickname_check");
//     const profileImage = document.querySelector("#profileImage");
//     const fileInput = document.querySelector("#fileInput");
//     const deleteProfileButton = document.querySelector("#delete-profile");
//     const confirmButton = document.querySelector(".confirmButton");

//     /**
//      * 메시지를 동적으로 표시하는 함수
//      */
//     function displayMessage(message, color) {
//         noticeAlarm.textContent = message;
//         noticeAlarm.style.color = color;
//     }

//     /**
//      * 닉네임 입력 이벤트
//      */
//     nicknameInput.addEventListener("input", () => {
//         nicknameCheckHidden.value = "N"; // 닉네임 중복 여부 초기화
//         displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
//         confirmButton.disabled = true; // 완료 버튼 비활성화
//     });

//     /**
//      * 닉네임 중복 확인 버튼 클릭 이벤트
//      */
//     nicknameCheckButton.addEventListener("click", async () => {
//         try {
//             // 공백 입력 검사
//             const checkspace = (selector, message) => {
//                 const val = document.querySelector(selector).value;
//                 if (val.trim() === "") {
//                     throw new Error(message);
//                 }
//             };

//             // 닉네임 유효성 검사
//             checkspace("#nickname", "공백은 사용할 수 없습니다.");
//             regexHelper.minLength("#nickname", 2, "닉네임은 2자 이상 입력해주세요.");
//             regexHelper.maxLength("#nickname", 12, "닉네임은 12자 이하로 입력해주세요.");
//             regexHelper.nickName("#nickname", "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다.");

//             // 서버에 닉네임 중복 확인 요청
//             const response = await axiosHelper.get(`/api/myPage/nickname-update`, { nickname: nicknameInput.value.trim() });

//             if (response.data.success) {
//                 nicknameCheckHidden.value = "Y"; // 중복 검사 통과
//                 displayMessage("사용 가능한 닉네임입니다.", "#69A5FF");
//                 confirmButton.disabled = false; // 완료 버튼 활성화
//             } else {
//                 displayMessage("이미 사용 중인 닉네임입니다.", "#ff6969");
//                 confirmButton.disabled = true; // 완료 버튼 비활성화
//             }
//         } catch (error) {
//             if (error instanceof StringFormatException) {
//                 displayMessage(error.message, "#ff6969");
//                 if (error.element) {
//                     setTimeout(() => error.element.focus(), 300);
//                 }
//             } else {
//                 displayMessage(error.message || "닉네임 중복 확인 중 오류가 발생했습니다.", "#ff6969");
//             }
//         }

//         // 파일 미리보기
//         fileInput.addEventListener("change", (e) => {
//             const file = e.target.files[0];
//             if (file) {
//                 const reader = new FileReader();
//                 reader.onload = (e) => {
//                     profileImage.src = e.target.result;
//                 };
//                 reader.readAsDataURL(file);
//             }
//         });
//     });
    
    

//     /**
//      * 완료 버튼 클릭 이벤트 (닉네임 저장 및 프로필 이미지 업로드)
//      */
//     confirmButton.addEventListener("submit", async (e) => {
//         e.preventDefault();

//         const nickname = nicknameInput.value.trim();
//         if (nicknameCheckHidden.value !== "Y") {
//             displayMessage("닉네임 중복 여부를 확인해주세요.", "#ff6969");
//             return;
//         }

//         try {
//             // 닉네임 저장 API 호출
//             const nicknameResponse = await axiosHelper.put(`/api/myPage/nickname-update`, { nickname });
//             if (!nicknameResponse.data.success) {
//                 throw new Error("닉네임 저장 중 오류가 발생했습니다.");
//             }

//             // 프로필 이미지 저장 API 호출
//             const formData = new FormData();
//             if (fileInput.files[0]) {
//                 formData.append("profile", fileInput.files[0]);
//                 const profileResponse = await axiosHelper.postMultipart("[[@{/api/myPage/profile-update}]]", formData);
//                 if (!profileResponse.data.success) {
//                     throw new Error("프로필 이미지 저장 중 오류가 발생했습니다.");
//                 }
//             }
//             // 성공 메시지 표시
//             await utilHelper.alertSuccess("수정 성공", "닉네임과 프로필 이미지가 성공적으로 저장되었습니다.");
//             location.reload(); // 페이지 새로고침
//         } catch (error) {
//             await utilHelper.alertDanger("수정 실패", error.message || "수정 중 오류가 발생했습니다.");
//         }
//     });
// });

document.querySelectorAll("*[data-disabled]").forEach((v,i)=>{
    v.addEventListener("change",e=>{
        e.preventDefault();

        const current=e.currentTarget;
        document.querySelector(current.dataset.disabled).disabled=!current.checked;
    });
});

document.querySelector("#nickname_unique_check").addEventListener("click",async(e)=>{
    e.preventDefault();

    try {
        regexHelper.value("#nickname","닉네임을 입력하세요");
    } catch (e) {
        await utilHelper.alertDanger(e.message);
        e.element.focus();
        return;
    }
    const nickname=document.querySelector("#nickname").value;
    const da=await axiosHelper.get(`/api/myPage/nickname_unique_check`,{
        nickname:nickname
    });
    if(da){
        await utilHelper.alertSuccess("사용 가능한 닉네임 입니다.");
        document.querySelector("#nickname_check").value="Y";
    }
});
document.querySelector("#nickname").addEventListener("change",e=>{
    document.querySelector("#nickname_check").value="N";
});


document.getElementById("profile-form").addEventListener("submit",async(e)=>{
    e.preventDefault();

    /** 입력값 유효성 검사*/
    try {
        // 공백 입력 검사 함수
        const checkspace = (selector, message) => {
            const space = document.querySelector(selector).value;
            if (space.trim() === "") {
                throw new Error(message);
            }
        };

        // 닉네임 유효성 검사
        checkspace("#nickname", "공백은 사용할 수 없습니다.");
        regexHelper.minLength("#nickname", 2, "닉네임은 2자 이상 입력해주세요.");
        regexHelper.maxLength("#nickname", 13, "닉네임은 12자 이하로 입력해주세요.");
        regexHelper.nickName("#nickname", "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다.");

    } catch (e) {
        await utilHelper.alertDanger(e.message);

        setTimeout(()=>e.element.focus(),300);
        return;
    }

    const nicknameCheck=document.querySelector("#nickname_check").value;

    if(nicknameCheck==="N"){
        await utilHelper.alertWarning("닉네임 중복 검사를 진행해주세요.");
        return;
    }
    const formData=new FormData(e.currentTarget);
    const data=await axiosHelper.putMultipart(e.currentTarget.action,formData);
    if(data){
        await utilHelper.alertSuccess("프로필 정보가 수정되었습니다.");
        location.reload();
    }
});


