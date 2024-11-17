document.querySelector("#nickname_unique_check").addEventListener("click",async(e)=>{
    e.preventDefault();

    try {
        regexHelper.value("#nickname","닉네임은 한글, 영문, 숫자로 2자 이상 12자 이하여야 합니다.")
    } catch (e) {
        await utilHelper.alertDanger(e.message);
    }
})