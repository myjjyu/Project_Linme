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