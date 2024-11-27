// 직접 입력 선택 시 입력 창 생성
document.addEventListener("DOMContentLoaded", function() {
    const deliveryRequestSelect = document.getElementById("deliveryRequest");
    const customRequestInput = document.getElementById("customRequestInput");

    deliveryRequestSelect.addEventListener("change", function() {
        if (deliveryRequestSelect.value === "직접 입력") {
            customRequestInput.style.display = "block";
        } else {
            customRequestInput.style.display = "none";
        }
    });
});


// 우편번호 검색
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#find-postcode").addEventListener("click", (e) => {
        e.preventDefault();
        utilHelper.findPostCode();
    });
});



// 기본 배송지명 초기값 설정
const nickname = '${session.memberinfo.nickname}'; // 닉네임

// 기본값 설정 (focus 시)
function setDefaultAddrName() {
    const addrNameInput = document.getElementById('addrName');
    if (addrNameInput.value.trim() === '') {
        addrNameInput.value = nickname; // 닉네임으로 기본값 설정
    }
}


document.addEventListener("DOMContentLoaded", () => {
    const deliveryRequestSelect = document.getElementById("deliveryRequest");
    const customRequestInput = document.getElementById("customRequestInput");
    const saveButton = document.querySelector(".saveButton");

    // 배송 요청사항 선택 시
    deliveryRequestSelect.addEventListener("change", () => {
        if (deliveryRequestSelect.value === "직접 입력") {
            customRequestInput.style.display = "block"; // 직접 입력 활성화
            customRequestInput.focus();
        } else {
            customRequestInput.style.display = "none"; // 직접 입력 숨김
            customRequestInput.value = ""; // 값 초기화
        }
    });

    // 저장 버튼 클릭 시
    saveButton.addEventListener("click", async (e) => {
        e.preventDefault();

        // 입력값 가져오기
        const addressName = document.getElementById("addressName").value.trim();
        const postcode = document.getElementById("postcode").value.trim();
        const addr1 = document.getElementById("addr1").value.trim();
        const addr2 = document.getElementById("addr2").value.trim();
        const addrMsg = addrMsgSelect.value;
        const custom = customInput.value.trim();
        const final = addrMsg === "직접 입력" ? custom : addrMsg;

        try {
            // 서버로 데이터 전송
            const response = await axios.post("/api/myPage/postcode-update", {
                addressName,
                postcode,
                addr1,
                addr2,
                addrMsg: final
            });

            if (response.status === 200) {
                await utilHelper.alertSuccess("저장 성공", "수정되었습니다.");
                location.reload(); // 페이지 새로고침
            } else {
                throw new Error("서버 응답 오류");
            }
        } catch (error) {
            console.error(error);
            await utilHelper.alertDanger("저장 실패", "배송지 저장 중 문제가 발생했습니다. 다시 시도해주세요.");
        }
    });
});

