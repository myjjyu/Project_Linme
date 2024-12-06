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
    title: "마이페이지 1:1 문의하기 에서<br>문의 가능합니다.",
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

// 장바구니 바로구매 alert창
document.querySelector(".cart-btn").addEventListener("click", (e) => {
  e.preventDefault();
  console.log("isLoggedIn:", isLoggedIn);

  if (isLoggedIn) {
    // 로그인된 경우
    Swal.fire({
      title: `상품이 장바구니에 담겼습니다.<br>장바구니로 이동하시겠습니까?`,
      showCancelButton: true,
      cancelButtonText: "닫기",
      confirmButtonText: "이동",
      customClass: {
        cancelButton: "cartclose-button",
        confirmButton: "cart-button",
        title: "cart-text",
        popup: "custom-alert-popup",
      },
      reverseButtons: true, // 버튼 순서 변경
    }).then((result) => {
      if (result.isConfirmed) {
        document.querySelector("#cartform").submit();
        window.location.href = cartUrl;
      }
    });
  } else {
    // 로그인 안 된 경우
    Swal.fire({
      title: `로그인이 필요합니다.<br>로그인 페이지로 이동하시겠습니까?`,
      showCancelButton: true,
      cancelButtonText: "닫기",
      confirmButtonText: "이동",
      customClass: {
        cancelButton: "cartclose-button",
        confirmButton: "cart-button",
        title: "cart-text",
        popup: "custom-alert-popup",
      },
      reverseButtons: true, // 버튼 순서 변경
    }).then((result) => {
      if (result.isConfirmed) {
        document.querySelector("#cartform-not-log").submit();
        window.location.href = "/login";
      }
    });
  }
});

// 수량 증가, 감소 버튼
document.addEventListener("DOMContentLoaded", function () {
  let quantity = 1;

  const minusButton = document.querySelector(".minus");
  const plusButton = document.querySelector(".plus");
  const numberDisplay = document.querySelector(".number");
  const totalPriceDisplay = document.querySelector(".total-price");
  const quantityInputs = document.querySelectorAll(".quantity-input");
  const unitPrice = parseInt(totalPriceDisplay.getAttribute("data-unit-price")) || 0;

  console.log("Unit Price:", unitPrice);

  function updateTotal() {
    numberDisplay.textContent = quantity;
    const totalPrice = unitPrice * quantity;
    totalPriceDisplay.innerHTML = `${totalPrice.toLocaleString()} <span>원</span>`;

    quantityInputs.forEach((input) => (input.value = quantity));
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
