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
  selectedItems.forEach((v, i) => {
    v.closest("li").remove();
  });
};

/* 수량 감소 함수 */
const decreaseQuantity = (button) => {
  const quantityInput = button.nextElementSibling;
  // console.log(quantityInput);
  const quantityControl = quantityInput.closest(".quantityControl");
  let currentQuantity = parseInt(quantityInput.value);
  if (currentQuantity > 1) {
    quantityInput.value = currentQuantity - 1;

    const productCount = quantityControl.querySelector(".productCount");
    console.log(productCount);

    // 수량 변경 이벤트 발생 . 강제 이벤트 발생
    const eve = new Event("change");

    if (productCount) {
      const eve = new Event("change");
      productCount.dispatchEvent(eve);
    } else {
      console.error("productCount 요소를 찾을 수 없습니다.");
    }

    // alert("수량이 변경되었습니다.");
    Swal.fire({
      title: "수량이 변경되었습니다.",
      confirmButtonText: "확인",
      width: 300,
      height: 145,
      customClass: {
        confirmButton: "alert-button",
        title: "title-text",
      },
    });
  } else {
    // alert("최저 수량 미만으로 설정할 수 없습니다.");
    Swal.fire({
      title: "최저 수량 미만으로 선택할 수 없습니다.",
      confirmButtonText: "확인",
      width: 300,
      height: 145,
      customClass: {
        confirmButton: "alert-button",
        title: "title-text",
      },
    });
  }
};

/* 수량 증가 함수 */
const increaseQuantity = (button) => {
  const quantityInput = button.previousElementSibling;
  const quantityControl = quantityInput.closest(".quantityControl");

  let currentQuantity = parseInt(quantityInput.value);
  quantityInput.value = currentQuantity + 1;

  const productCount = quantityControl.querySelector(".productCount");

  // 수량 변경 이벤트 발생 . 강제 이벤트 발생
  const eve = new Event("change");
  productCount.dispatchEvent(eve);

  // alert("수량이 변경되었습니다.");
  Swal.fire({
    title: "수량이 변경되었습니다.",
    confirmButtonText: "확인",
    width: 300,
    height: 145,
    customClass: {
      confirmButton: "alert-button",
      title: "title-text",
    },
  });
};

/** 장바구니에 담긴 상품이 없을 때 주문하기 버튼 클릭 시 스윗알럿 */
const orderBtn = document.querySelector(".orderBtn");
orderBtn.addEventListener("click", () => {
  Swal.fire({
    title: "장바구니에 담긴 상품이 없습니다.",
    confirmButtonText: "확인",
    width: 300,
    height: 145,
    customClass: {
      confirmButton: "alert-button",
      title: "title-text",
    },
  });
});
