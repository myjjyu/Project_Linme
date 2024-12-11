/**********************************/
 /* 헤더의 드롭다운 메뉴를 제어하는 스크립트
/**********************************/
document.addEventListener("DOMContentLoaded", function () {
  const customerServiceLink = document.getElementById("customerService1");
  const dropdownMenu = document.getElementById("dropdownMenu1");

  //// 회원 링크 클릭 시 드롭다운 메뉴 보이기
  customerServiceLink.addEventListener("click", function (event) {
    event.preventDefault();
    dropdownMenu.style.display = dropdownMenu.style.display === "block" ? "none" : "block";
  });

  // 다시 클릭시 시 메뉴 숨기기
  document.addEventListener("click", function (event) {
    if (!customerServiceLink.contains(event.target) && !dropdownMenu.contains(event.target)) {
      dropdownMenu.style.display = "none";
    }
  });
});


document.addEventListener("DOMContentLoaded", function () {
  const customerServiceLink = document.getElementById("customerService2");
  const dropdownMenu = document.getElementById("dropdownMenu2");

  //// 고객센터 링크 클릭 시 드롭다운 메뉴 보이기
  customerServiceLink.addEventListener("click", function (event) {
    event.preventDefault();
    dropdownMenu.style.display = dropdownMenu.style.display === "block" ? "none" : "block";
  });

  // 다시 클릭시 시 메뉴 숨기기
  document.addEventListener("click", function (event) {
    if (!customerServiceLink.contains(event.target) && !dropdownMenu.contains(event.target)) {
      dropdownMenu.style.display = "none";
    }
  });
});




/*********************************/
/* 카테고리 드롭다운 메뉴를 제어하는 스크립트
/*********************************/
const categoryToggle = document.getElementById("categoryToggle");
const categoryList = document.getElementById("categoryList");

// 햄버거 버튼 클릭 시 카테고리 보이기
categoryToggle.addEventListener("click", function (event) {
  event.stopPropagation();
  // 카테고리 목록의 화면 상태에 따라 'block' 또는 'none'으로 변경
  categoryList.style.display = categoryList.style.display === "block" ? "none" : "block";
});

// 다시 클릭시 햄버거 드롭다운 메뉴 숨기기
document.addEventListener("click", function (event) {
  if (!categoryToggle.contains(event.target) && !categoryList.contains(event.target)) {
    categoryList.style.display = "none";
  }
});
