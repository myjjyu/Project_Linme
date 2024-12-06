// 파일 미리보기
const fileInput = document.querySelector("#profile");
const profileImage = document.querySelector("#profileImage");
const deleteField = document.querySelector("#delete-profile-hidden");
fileInput.addEventListener("change", (e) => {
    const file = e.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            profileImage.src = e.target.result;
        };
        reader.readAsDataURL(file);

        // 삭제 플래그 초기화
        deleteField.value = "N";
        
    }
});

const deleteProfileButton = document.querySelector("#delete-profile");
// 파일 초기화 (X 버튼 클릭 시)
deleteProfileButton.addEventListener("click", () => {
    fileInput.value = ""; // 선택된 파일 초기화
    profileImage.src = "/assets/img/profileimg.jpg"; // 기본 이미지로 변경

    let deleteField = document.querySelector("#delete-profile-hidden");
    if (!deleteField) {
        deleteField = document.createElement("input");
        deleteField.type = "hidden";
        deleteField.name = "delete-profile";
        deleteField.id = "delete-profile-hidden";
        document.querySelector("#profile-form").appendChild(deleteField);
    }
    deleteField.value = "Y";
});


// 버튼 클릭 이벤트 처리
document.querySelectorAll("*[data-disabled]").forEach((button) => {
    button.addEventListener("click", (e) => {
        e.preventDefault();
        const targetSelector = button.dataset.disabled; 
        const targetElement = document.querySelector(targetSelector); 

        if (targetElement) {
            targetElement.disabled = !targetElement.disabled;
        }

        button.classList.toggle("active"); 
    });
});


document.querySelector("#nickname_unique_check").addEventListener("click",async(e)=>{
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
        regexHelper.value("#nickname","닉네임을 입력하세요");
        checkspace("#nickname", "공백은 사용할 수 없습니다.");
        regexHelper.minLength("#nickname", 1, "닉네임은 2자 이상 입력해주세요.");
        regexHelper.maxLength("#nickname", 13, "닉네임은 12자 이하로 입력해주세요.");
        regexHelper.nickName("#nickname", "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다.");

    } catch (e) {
        await utilHelper.alertDanger(e.message);

        setTimeout(()=>e.element.focus(),300);
        return;
    }

    const nickname=document.querySelector("#nickname").value;
    const da=await axiosHelper.get(`/api/myPage/nickname_unique_check`,{
        nickname:nickname
    });
    if(da){
        await utilHelper.alertSuccess("사용 가능한 닉네임 입니다.");
        document.querySelector("#nickname_check").value="Y";
    } else {
        await utilHelper.alertDanger("사용 중인 닉네임입니다.");
        document.querySelector("#nickname_check").value = "N";
    }
});
document.querySelector("#nickname").addEventListener("change",e=>{
    document.querySelector("#nickname_check").value="N";
});
 
document.getElementById("profile-form").addEventListener("submit", async (e) => {
    e.preventDefault();


    // 중복검사 여부 확인
    const nicknameCheck = document.querySelector("#nickname_check").value;
    if (nicknameCheck !== "Y") {
        await utilHelper.alertDanger("닉네임 중복 검사를 진행해주세요.");
        return; 
    }

    const formData = new FormData(e.currentTarget);

    const data=await axiosHelper.putMultipart(e.currentTarget.action,formData);
    if(data){
        await utilHelper.alertSuccess("프로필 정보가 수정되었습니다.");
        location.reload();
    }
});

// 취소 버튼 클릭 시 페이지 새로고침
document.querySelector(".cancelButton").addEventListener("click", () => {
    location.reload(); // 페이지 새로고침
});


