document.addEventListener("DOMContentLoaded", () => {
    // 각 토글 버튼과 해당 비밀번호 필드를 매핑
    const toggles = [
        { inputId: "user_pw", toggleId: "toggle-current-password" },
        { inputId: "new_user_pw", toggleId: "toggle-new-password" },
        { inputId: "new_user_pw_confirm", toggleId: "toggle-confirm-password" },
    ];

    toggles.forEach(({ inputId, toggleId }) => {
        const passwordInput = document.getElementById(inputId);
        const toggleButton = document.getElementById(toggleId);

        // 클릭 이벤트 추가
        toggleButton.addEventListener("click", () => {
            // 현재 비밀번호 타입 확인 후 평문/숨김 전환
            if (passwordInput.type === "password") {
                passwordInput.type = "text"; // 평문으로 전환
                toggleButton.src = "/assets/img/myPage/original.jpg"; // 눈 아이콘으로 변경
            } else {
                passwordInput.type = "password"; // 숨김으로 전환
                toggleButton.src = "/assets/img/myPage/secret.jpg"; // 닫힌 눈 아이콘으로 변경
            }
        });
    });
});


document.getElementById("password-form").addEventListener("submit",async(e)=>{
    e.preventDefault();

    /** 입력값 유효성 검사*/
    try {
        
        // 공백 입력 검사 함수
        const checkspace = (selector, message) => {
            const val = document.querySelector(selector).value;
            if (val.trim() === "") {
                throw new Error(message);
            }
        };
        
        //현재 비밀번호에 대한 입력 여부 확인
        regexHelper.value("#user_pw","현재 비밀번호를 입력하세요.");

        //변경할 비밀번호와 변경할 비밀번호의 확인값
        const newUserPw=document.querySelector("#new_user_pw").value;
        const newUserPwConfirm=document.querySelector("#new_user_pw_confirm").value;
        
        //새로운 비밀번호가 입력되었을 때만 확인값 검사
        if(newUserPw || newUserPwConfirm){
            regexHelper.compareTo("#new_user_pw","#new_user_pw_confirm","비밀번호가 일치하지 않습니다.");
        }
        
        regexHelper.value("#user_pw", "필수 입력항목입니다.");
        regexHelper.value("#new_user_pw","필수 입력항목입니다.");
        regexHelper.value("#new_user_pw_confirm","필수 입력항목입니다.");
        checkspace("#user_pw", "공백은 사용할 수 없습니다.");
        checkspace("#new_user_pw", "공백은 사용할 수 없습니다.");
        checkspace("#new_user_pw_confirm", "공백은 사용할 수 없습니다.");
        regexHelper.equalTo("#user_pw","#new_user_pw","현재 비밀번호와 새 비밀번호가 동일합니다.")
        regexHelper.minLength("#user_pw", 8, "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.");
        regexHelper.minLength("#new_user_pw", 8, "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.");
        regexHelper.minLength("#new_user_pw_confirm", 8, "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.");
        regexHelper.pwLinme("#user_pw","비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.");
        regexHelper.pwLinme("#new_user_pw","비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.");
        regexHelper.pwLinme("#new_user_pw_confirm","비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.");


    } catch (e) {
        await utilHelper.alertDanger(e.message);

        setTimeout(()=>e.element.focus(),300);
        return;
    }

    // // 현재 비밀번호 DB 유효성 검증
    // try {
    //     const currentPw = document.querySelector("#user_pw").value;

    //     // 서버에 현재 비밀번호 확인 요청
    //     const data = await axiosHelper.post("[[@{/api/myPage/password-check}]]", {
    //         user_pw: currentPw,
    //     });

    //     if (!data.valid) {
    //         // 입력한 현재 비밀번호가 DB와 다를 경우
    //         await utilHelper.alertDanger("현재 비밀번호가 올바르지 않습니다.");
    //         return;
    //     }
    // } catch (e) {
    //     // 서버 통신 오류 처리
    //     await utilHelper.alertDanger("비밀번호 확인 중 오류가 발생했습니다.");
    //     return;
    // }

    // DB 비밀번호가 일치하면 변경 요청 진행
    const formData = new FormData(e.currentTarget);
    const data=await axiosHelper.putMultipart("[[@{/api/myPage/password-update}]]", formData);
    
    if(data){
        await utilHelper.alertSuccess("수정되었습니다.");
        location.reload();
    }
});




