// 상품이미지
var swiper = new Swiper(".mySwiper", {
  loop: true,
  spaceBetween: 10,
  slidesPerView: 4,
  freeMode: true,
  watchSlidesProgress: true,
});
var swiper2 = new Swiper(".mySwiper2", {
  loop: true,
  spaceBetween: 10,
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  thumbs: {
    swiper: swiper,
  },
});

// 상품설명, 리뷰, 상품문의 클릭시 컬러변경 이벤트
const tabs = document.querySelectorAll(".tab-link");

tabs.forEach((tab) => {
  tab.addEventListener("click", function () {
    // 모든 탭에서 'on' 클래스 제거
    tabs.forEach((t) => t.classList.remove("on"));
    // 클릭한 탭에만 'on' 클래스 추가
    this.classList.add("on");
  });
});

//문의하기 alert창
document.querySelector(".button").addEventListener("click", (e) => {
  Swal.fire({
    title: "로그인이 필요합니다.",
    confirmButtonText: "확인",
    width: 300,
    height: 145,
    customClass: {
      confirmButton: "alert-button",
      title: "title-text",
    },
  });
});

// 필수 표기정보 alert창//
document.querySelector(".required-Info").addEventListener("click", (e) => {
  Swal.fire({
    title: "상품고시정보",
    html: document.querySelector(".required-box").innerHTML,
    confirmButtonText: "확인",
    customClass: {
      confirmButton: "required-button",
      title: "required-text",
    },
  });
});

document.addEventListener("DOMContentLoaded", function () {
  let quantity = 1;

  const minusButton = document.querySelector(".minus");
  const plusButton = document.querySelector(".plus");
  const numberDisplay = document.querySelector(".number");
  const totalPriceDisplay = document.querySelector(".total-price");
  const unitPrice = parseInt(totalPriceDisplay.getAttribute("data-unit-price")) || 0;

  console.log("Unit Price:", unitPrice);

  function updateTotal() {
    numberDisplay.textContent = quantity;
    const totalPrice = unitPrice * quantity;
    totalPriceDisplay.innerHTML = `${totalPrice.toLocaleString()} <span>원</span>`;

    quantityInputs.forEach(input => input.value = quantity);
  }

  if (minusButton) {
    minusButton.addEventListener("click", function () {
      if (quantity > 1) {
        quantity--;
        updateTotal();
      } else {
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
    });
  }

  if (plusButton) {
    plusButton.addEventListener("click", function () {
      quantity++;
      updateTotal();
    });
  }

  updateTotal();
});
