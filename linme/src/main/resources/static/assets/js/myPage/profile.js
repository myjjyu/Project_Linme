document.querySelector("#nickname_unique_check").addEventListener("click",async(e)=>{
    e.preventDefault();

    try {
        regexHelper.value("#nickname","• 닉네임은 2자 이상 12자 이하의 한글, 영문, 숫자로 입력해 주세요.");
    } catch (e) {
        await utilHelper.alertDanger(e.message);
        return;
    }
    const nickname=document.querySelector("#nickname").value;
    const data=await axiosHelper.get(`/api/account/id_unique_check`,{
        nickname:nickname
    });
    if(data){
        await utilHelper.alertSuccess("사용 가능한 닉네임 입니다.");
        document.querySelector("#nickname_check").value="Y";
    }
});
document.querySelector("#nickname").addEventListener("change",e=>{
    document.querySelector("#nickname_check").value="N";
});

document.getElementById("signup-form").addEventListener("submit",async(e)=>{
    e.preventDefault();

    /** 입력값 유효성 검사*/
    try {
        regexHelper.value("#nickname","• 닉네임은 2자 이상 12자 이하의 한글, 영문, 숫자로 입력해 주세요.");
        regexHelper.maxLength("#nickname",12,"닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");
        regexHelper.minLength("#nickname",2,"닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.");
    } catch (e) {
        await utilHelper.alertDanger(e.message);

        setTimeout(()=>e.element.focus(),1);
        return;
    }
    const nicknameCheck=document.querySelector("#nickname_check").value;
    if(nicknameCheck==="N"){
        utilHelper.alertWarning("닉네임 중복 여부를 확인해주세요.");
        return;
    }
    const formData=new FormData(e.currentTarget);

    const data=await axiosHelper.postMultipart("[[@{/api/account/join}]]",formData);
    if(data){
        window.location="[[@{/account/join_result}]]";
    }
}); 



document.querySelector("#nickname_unique_check").addEventListener("click", async (e) => {
    e.preventDefault();

    const errorMessage = document.querySelector("#nickname-error");
    errorMessage.textContent = ""; 

    try {
        regexHelper.value(
            "#nickname",
            "• 닉네임은 2자 이상 12자 이하의 한글, 영문, 숫자로 입력해 주세요."
        );
    } catch (e) {
        errorMessage.textContent = e.message;
        return;
    }

    const nickname = document.querySelector("#nickname").value;
    const data = await axiosHelper.get(`/api/account/id_unique_check`, {
        nickname: nickname,
    });
    if (data) {
        document.querySelector("#nickname_check").value = "Y";
        errorMessage.textContent = "사용 가능한 닉네임 입니다.";
        errorMessage.style.color = "blue";
    } else {
        errorMessage.textContent = "사용 중인 닉네임입니다.";
        errorMessage.style.color = "red";
    }
});

document.querySelector("#nickname").addEventListener("change", (e) => {
    document.querySelector("#nickname_check").value = "N";
    document.querySelector("#nickname-error").textContent = ""; 
});

document.getElementById("signup-form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const errorMessage = document.querySelector("#nickname-error");
    errorMessage.textContent = "";  

    /** 입력값 유효성 검사*/
    try {
        regexHelper.value(
            "#nickname",
            "• 닉네임은 2자 이상 12자 이하의 한글, 영문, 숫자로 입력해 주세요."
        );
        regexHelper.maxLength(
            "#nickname",
            12,
            "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다."
        );
        regexHelper.minLength(
            "#nickname",
            2,
            "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다."
        );
    } catch (e) {
        errorMessage.textContent = e.message;
        return;
    }

    const nicknameCheck = document.querySelector("#nickname_check").value;
    if (nicknameCheck === "N") {
        errorMessage.textContent = "닉네임 중복 여부를 확인해주세요.";
        return;
    }

    const formData = new FormData(e.currentTarget);
    const data = await axiosHelper.postMultipart("[[@{/api/account/join}]]", formData);

    if (data) {
        window.location = "[[@{/account/join_result}]]";
    }
});






document.querySelector("#nickname_unique_check").addEventListener("click", (e) => {
    e.preventDefault();

});

document.querySelector("#nickname").addEventListener("change", (e) => {
    document.querySelector("#nickname_check").value = "N";
});

document.getElementById("signup-form").addEventListener("submit", (e) => {
    e.preventDefault();

    /** 입력값 유효성 검사 */
    try {
        regexHelper.value(
            "#nickname",
            "• 닉네임은 2자 이상 12자 이하의 한글, 영문, 숫자로 입력해 주세요."
        );
        regexHelper.maxLength(
            "#nickname",
            12,
            "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다."
        );
        regexHelper.minLength(
            "#nickname",
            2,
            "닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다."
        );
    } catch (e) {
        alert(e.message);
        setTimeout(() => e.element.focus(), 1);
        return;
    }

    const nicknameCheck = document.querySelector("#nickname_check").value;
    if (nicknameCheck === "N") {
        alert("닉네임 중복 여부를 확인해주세요.");
        return;
    }
});

