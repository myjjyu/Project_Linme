/**
 * 상품 상세 페이지 스크립트
 */
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

/**
 * 문의하기 alert창
 */
document.querySelector("#inquiry-alert").addEventListener("click", (e) => {
  Swal.fire({
    title: `1:1 문의하기에서 가능합니다. <br>마이페이지로 이동하시겠습니까?`,
    showCancelButton: true, // 취소 버튼 표시
    cancelButtonText: "닫기",
    confirmButtonText: "이동",
    width: 350,
    // height: 145,
    customClass: {
      cancelButton: "cartclose-button",
      confirmButton: "cart-button",
      title: "cart-text",
      popup: "custom-alert-popup",
    },
    reverseButtons: true, // 버튼 순서 변경
  }).then((result) => {
    if (result.isConfirmed) {
// "이동" 버튼 클릭시 마이페이지로 
      window.location.href = "/myPage/inquiry";
    }
  });
});

/**
 * 필수 표기정보 alert창
 */
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

/**
 * 장바구니 버튼 클릭 이벤트
 */
document.querySelector(".cart-btn").addEventListener("click", (e) => {
  e.preventDefault();
  console.log("isLoggedIn:", isLoggedIn);

  if (isLoggedIn) {
    /** 로그인 했을때 */
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
        // AJAX 요청을 통해 장바구니에 아이템을 추가
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/cart/cart/add", true);
        xhr.setRequestHeader(
          "Content-Type",
          "application/x-www-form-urlencoded"
        );
        console.log(xhr);
        xhr.onreadystatechange = function () {
          if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
              var response = JSON.parse(xhr.responseText);
              // if (response.cart) {
              //   // 성공 시 리디렉션
              //   window.location.href = response.redirectUrl;
              // } else {
              //   console.error("Error adding item to cart:", response.message);
              // }
              window.location.href = response.redirectUrl;
            } else {
              console.error("Error adding item to cart:", xhr.statusText);
            }
          }
        };

        // 폼 데이터를 수집하여 URL 인코딩된 문자열로 변환
        var form = document.querySelector("#cartform");
        var formData = new FormData(form);
        var data = new URLSearchParams(formData).toString();
        xhr.send(data);
      }
    });
  } else {
    /** 로그아웃 상태일때 */
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

/**
 * 바로구매 버튼 클릭 이벤트
 */
document.querySelector(".buy-btn").addEventListener("click", (e) => {
  e.preventDefault();
  console.log("isLoggedIn:", isLoggedIn);

  if (isLoggedIn) {
    Swal.fire({
      title: `바로 구매하시겠습니까?`,
      showCancelButton: true,
      cancelButtonText: "닫기",
      confirmButtonText: "구매",
      customClass: {
        cancelButton: "cartclose-button",
        confirmButton: "cart-button",
        title: "cart-text",
        popup: "custom-alert-popup",
      },
      reverseButtons: true, // 버튼 순서 변경
    }).then((result) => {
      if (result.isConfirmed) {
        // AJAX 요청을 통해 바로구매에 아이템을 추가
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/payment/payment/", true);
        xhr.setRequestHeader(
          "Content-Type",
          "application/x-www-form-urlencoded"
        );

        xhr.onreadystatechange = function () {
          if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
              var response = JSON.parse(xhr.responseText);
              //   if (response.cart) {
              //     // 성공 시 리디렉션
              //     window.location.href = response.redirectUrl;
              //   } else {
              //     console.error("Error adding item to cart:", response.message);
              //   }
              // } else {
              //   console.error("Error adding item to cart:", xhr.statusText);
              // }

              window.location.href = response.redirectUrl;
            }
          }
        };

        /**
         * 폼 데이터를 수집하여 URL 인코딩된 문자열로 변환
         */
        var form = document.querySelector("#paymentform");
        if (!form) {
          console.error("폼 요소를 찾을수없음: #paymentform");
        } else {
          var formData = new FormData(form);
          var data = new URLSearchParams();

          formData.forEach((value, key) => {
            data.append(key, value);
          });

          console.log("폼 데이터:", data.toString());

          // xhr 객체가 올바르게 초기화되었는지 확인
          if (xhr) {
            console.log("XHR 객체 초기화 완료");
            xhr.send(data.toString());
          } else {
            console.error("XHR 객체 초기화 실패");
          }
        }
      }
    });
  } else {
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
        window.location.href = "/login";
      }
    });
  }
});

/**
 * 수량 증감 버튼 이벤트
 */
document.addEventListener("DOMContentLoaded", function () {
  let quantity = 1; // 초기 수량 설정

  const minusButton = document.querySelector(".minus");
  const plusButton = document.querySelector(".plus");
  const numberDisplay = document.querySelector(".number");
  const totalPriceDisplay = document.querySelector(".total-price");
  const quantityInputs = document.querySelectorAll(".quantity-input");
  const unitPrice =
    parseInt(totalPriceDisplay.getAttribute("data-unit-price")) || 0;

  console.log("Unit Price:", unitPrice);

  // 총 가격 업데이트 함수
  function updateTotal() {
    numberDisplay.textContent = quantity;
    const totalPrice = unitPrice * quantity;
    totalPriceDisplay.innerHTML = `${totalPrice.toLocaleString()} <span>원</span>`;

    // 수량 입력 필드에 수량 업데이트
    quantityInputs.forEach((input) => (input.value = quantity));
  }

  // 마이너스 버튼 클릭 이벤트
  if (minusButton) {
    minusButton.addEventListener("click", function () {
      if (quantity > 1) {
        quantity--;
        updateTotal(); // 총 가격 업데이트
      } else {
        // 최저 수량 미만일 때 알림창
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

  // 플러스 버튼 클릭 이벤트
  if (plusButton) {
    plusButton.addEventListener("click", function () {
      quantity++;
      updateTotal(); // 총 가격 업데이트
    });
  }

  updateTotal(); // 초기 총 가격 설정
});
