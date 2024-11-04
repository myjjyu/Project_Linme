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
    // 카테고리 목록의 display 상태에 따라 'block' 또는 'none'으로 변경
    categoryList.style.display = categoryList.style.display === "block" ? "none" : "block";
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