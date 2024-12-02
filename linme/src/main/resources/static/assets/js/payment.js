// Footer 영역의 margin을 0으로 설정
document.querySelector(".footerContainer").style.margin = "0px";

// // 배송 요청 사항 선택 시 이벤트 리스너 추가
// document.getElementById("selectDeliveryMsgOption").addEventListener("change", (event) => {
//   const inputContainer = document.getElementById("inputContainer");
//   inputContainer.innerHTML = ""; // 기존 input 태그 제거

//   // '직접입력' 옵션이 선택되었을 때 input 태그 추가
//   if (event.target.value === "directInput") {
//     const input = document.createElement("input");
//     input.type = "text";
//     input.placeholder = "직접 입력";
//     inputContainer.appendChild(input);
//   }
// });

// 배송 요청 사항 선택 시 이벤트 리스너 추가
document.querySelector(".selectDeliveryMsgOption").addEventListener("change", (event) => {
  const inputContainer = document.querySelector(".inputContainer");
  inputContainer.innerHTML = ""; // 기존 input 태그 제거

  // '직접입력' 옵션이 선택되었을 때 input 태그 추가
  if (event.target.value === "directInput") {
    const input = document.createElement("input");
    input.type = "text";
    input.placeholder = "직접 입력";
    inputContainer.appendChild(input);
  }
});
