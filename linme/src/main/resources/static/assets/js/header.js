/*****************************/
// 고객센터 드롭다운 js///
/*****************************/
// DOMContentLoaded 이벤트 리스너를 추가해서 DOM이 완전히 돌아간 후에 실행됨
document.addEventListener("DOMContentLoaded", function () {
  const customerServiceLink = document.getElementById("customerService");
  const dropdownMenu = document.getElementById("dropdownMenu");

  //// 고객센터 링크 클릭 시 드롭다운 메뉴 보이기
  customerServiceLink.addEventListener("click", function (event) {
    event.preventDefault();
    dropdownMenu.style.display =
      dropdownMenu.style.display === "block" ? "none" : "block";
  });

  // 다시 클릭시 시 메뉴 숨기기
  document.addEventListener("click", function (event) {
    if (
      !customerServiceLink.contains(event.target) &&
      !dropdownMenu.contains(event.target)
    ) {
      dropdownMenu.style.display = "none";
    }
  });
});

/*****************************/
// 햄버거 카테고리 드롭다운///
/*****************************/
const categoryToggle = document.getElementById("categoryToggle");
const categoryList = document.getElementById("categoryList");

// 햄버거 버튼 클릭 시 카테고리 보이기
categoryToggle.addEventListener("click", function () {
  // 카테고리 목록의 화면 상태에 따라 'block' 또는 'none'으로 변경
  categoryList.style.display =
    categoryList.style.display === "block" ? "none" : "block";
});

// 다시 클릭시 햄버거 드롭다운 메뉴 숨기기
document.addEventListener("click", function (event) {
  if (
    !customerServiceLink.contains(event.target) &&
    !dropdownMenu.contains(event.target)
  ) {
    dropdownMenu.style.display = "none";
  }
});

/*****************************/
// 신상품, 베스트, 특가상품 클릭시 컬러변경
/*****************************/

// 신상품, 베스트, 특가상품 3개의 영역은 기본상태는 deactivate (현재)이고 각 영역을 클릭하면
// 컬러가 activate 로변함 선택되지 못한 영역은 계속 기본값
// 다른영역을 클릭하면 기존에 선택했던 영역activate은 제거되어 기본값으로 변경

// 메뉴 리스트 선택
const menuList = document.querySelectorAll(".menuList a");

// 클릭 이벤트 추가
menuList.forEach((link) => {
  link.addEventListener("click", function (event) {
    event.preventDefault();

    // 모든 링크에서 activate 클래스 제거하고 기본값 설정
    menuList.forEach((link) => {
      link.classList.remove("activate");
      link.classList.add("deactivate"); // 기본값
    });

    // 클릭된 링크에 activate 클래스 추가
    this.classList.add("activate");
    this.classList.remove("deactivate");
  });
});
