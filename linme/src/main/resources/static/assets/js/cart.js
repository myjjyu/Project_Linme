// Footer 영역의 margin을 0으로 설정
document.querySelector(".footerContainer").style.margin = "0px";

/* 전체 선택/해제 함수 */
const toggleSelectAll = (v) => {
  const checkboxes = document.getElementsByClassName("productCheckbox");
  for (let i = 0; i < checkboxes.length; i++) {
    checkboxes[i].checked = v.checked;
  }
};

/* 선택된 항목 삭제 함수 */
const deleteSelected = () => {
  const selectedItems = document.querySelectorAll(".productCheckbox:checked");
  selectedItems.forEach((item) => {
    item.closest("li").remove();
  });
};

/* 수량 감소 함수 */
const decreaseQuantity = (button) => {
  const quantityInput = button.nextElementSibling;
  let currentQuantity = parseInt(quantityInput.value);
  if (currentQuantity > 1) {
    quantityInput.value = currentQuantity - 1;

    const productCount = document.querySelector(".productCount");

    // 수량 변경 이벤트 발생 . 강제 이벤트 발생
    const eve = new Event("change");

    if (productCount) {
      const eve = new Event("change");
      productCount.dispatchEvent(eve);
    } else {
      console.error("productCount 요소를 찾을 수 없습니다.");
    }

    alert("수량이 변경되었습니다.");
  } else {
    alert("최저 수량 미만으로 설정할 수 없습니다.");
  }
};

/* 수량 증가 함수 */
const increaseQuantity = (button) => {
  const quantityInput = button.previousElementSibling;
  let currentQuantity = parseInt(quantityInput.value);
  quantityInput.value = currentQuantity + 1;

  const productCount = document.querySelector(".productCount");

  // 수량 변경 이벤트 발생 . 강제 이벤트 발생
  const eve = new Event("change");
  productCount.dispatchEvent(eve);

  alert("수량이 변경되었습니다.");
};

Swal.fire({
  text: "수량이 변경되었습니다.",
  footer: '<a href="#">확인</a>',
});

// // 수량 + 클릭시 증가 - 클릭시 감소 에 따른 함계금액 값 구하기
// document.addEventListener("DOMContentLoaded", function () {
//   // 초기 값
//   let quantity = 1;
//   const unitPrice = 12300;

//   const minusButton = document.querySelector(".minus");
//   const plusButton = document.querySelector(".plus");
//   const numberDisplay = document.querySelector(".number");
//   const totalPriceDisplay = document.querySelector(".total-price");

//   // 수량과 총 금액 업데이트 함수
//   function updateTotal() {
//     numberDisplay.textContent = quantity;
//     const totalPrice = unitPrice * quantity;
//     totalPriceDisplay.innerHTML = `${totalPrice.toLocaleString()} <span>원</span>`;
//   }

//   // - 버튼 클릭
//   minusButton.addEventListener("click", function () {
//     if (quantity > 1) {
//       quantity--;
//       updateTotal();
//     } else {
//       Swal.fire({
//         title: "최저 수량 미만으로 선택할 수 없습니다.",
//         confirmButtonText: "확인",
//         width: 300,
//         height: 145,
//         customClass: {
//           confirmButton: "alert-button",
//           title: "title-text",
//         },
//       });
//     }
//   });
//   // + 버튼 클릭
//   plusButton.addEventListener("click", function () {
//     quantity++;
//     updateTotal();
//   });

//   updateTotal();
// });
