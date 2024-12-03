// 직접 입력 선택 시 입력 창 생성
document.getElementById('addrMsg').addEventListener('change', function () {
    const customInputContainer = document.querySelector('.customInputContainer');
    if (this.value === '직접 입력') {
        customInputContainer.classList.remove('hidden'); // 입력창 보이기
    } else {
        customInputContainer.classList.add('hidden'); // 입력창 숨기기
    }
});


// 우편번호 검색
document.querySelector("#findPostcode").addEventListener("click", (e) => {
    e.preventDefault();
    utilHelper.findPostCode();
});


document.getElementById("address-form").addEventListener("submit",async(e)=>{
    e.preventDefault();

    const formData=new FormData(e.currentTarget);
    const data=await axiosHelper.putMultipart(e.currentTarget.action,formData);
    const addrMsg = formData.get("addrMsg");

    // "직접 입력" 선택 시 customInput 값을 addrMsg에 추가
    if (formData.get("addrMsg") === "직접 입력") {
        const customAddrMsg = formData.get("customAddrMsg")?.trim();
        if (customAddrMsg) {
            formData.set("addrMsg", customAddrMsg); 
        } else {
            alert("배송 요청사항을 입력해주세요."); 
            return;
        }
    }

    if(data){
        await utilHelper.alertSuccess("수정되었습니다.");
        location.reload();
    }
    
});