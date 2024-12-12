// Footer 영역의 margin을 0으로 설정
document.querySelector(".footerContainer").style.margin = "0px";

const formData = new FormData();

// ============ '+' 버튼 클릭시 수량 증가 ====================
document.querySelectorAll(".plus").forEach((v, i) => {
  v.addEventListener("click", (e) => {
    e.preventDefault();

    const current = e.currentTarget;
    const parent = current.closest(".productCountBox");
    const qty = parent.querySelector(".productCount");

    qty.value++;

    const eve = new Event("change");
    qty.dispatchEvent(eve);

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
  });
});

// =============== '-' 버튼 클릭시 수량 감소 =======================
document.querySelectorAll(".minus").forEach((v, i) => {
  v.addEventListener("click", (e) => {
    e.preventDefault();

    const current = e.currentTarget;
    const parent = current.closest(".productCountBox");
    const qty = parent.querySelector(".productCount");

    if (qty.value > 1) {
      qty.value--;

      const eve = new Event("change");
      qty.dispatchEvent(eve);

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
});

/* =================== Checkbox 전체 체크 제어 =================== */
const checkAll = document.querySelector(".cartSelectAll");

checkAll.addEventListener("click", (e) => {
  // 전체 체크시, 모든 체크박스 체크
  document.querySelectorAll(".productCheckbox").forEach((v) => (v.checked = checkAll.checked));
});

const checkboxes = document.querySelectorAll(".productCheckbox");

checkboxes.forEach((v, i) => {
  v.addEventListener("click", (e) => {
    const totalCnt = checkboxes.length;
    const checkedCnt = document.querySelectorAll(".productCheckbox:checked").length;
    // 체크박스수와 체크된 체크박스수가 같으면 전체체크박스 체크
    checkAll.checked = totalCnt == checkedCnt ? true : false;
  });
});
