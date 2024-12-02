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



// document.addEventListener("DOMContentLoaded", () => {
    
//     const saveButton = document.querySelector(".saveButton");

    

//     // 저장 버튼 클릭 시
//     saveButton.addEventListener("submit", async (e) => {
//         e.preventDefault();

//         // 입력값 가져오기
//         const postcode = document.getElementById("postcode").value.trim();
//         const addr1 = document.getElementById("addr1").value.trim();
//         const addr2 = document.getElementById("addr2").value.trim();
//         const addrMsg = addrMsgSelect.value;
//         const custom = customInput.value.trim();
//         const final = addrMsg === "직접 입력" ? custom : addrMsg;

//         try {
//             // 서버로 데이터 전송
//             const response = await axios.post("/api/myPage/postcode-update", {
//                 addressName,
//                 postcode,
//                 addr1,
//                 addr2,
//                 addrMsg: final
//             });

//             if (response.status === 200) {
//                 await utilHelper.alertSuccess("저장 성공", "수정되었습니다.");
//                 location.reload(); // 페이지 새로고침
//             } else {
//                 throw new Error("서버 응답 오류");
//             }
//         } catch (error) {
//             console.error(error);
//             await utilHelper.alertDanger("저장 실패", "배송지 저장 중 문제가 발생했습니다. 다시 시도해주세요.");
//         }
//     });
// });

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