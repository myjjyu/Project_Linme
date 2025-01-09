// 체크박스를 클릭했을 때 탈퇴 버튼 활성화/비활성화
// document.getElementById("confirmWithdrawal").addEventListener("change", function () {
//     const withdrawalButton = document.getElementById("withdrawalButton");
//     withdrawalButton.disabled = !this.checked; // 체크박스가 체크되었는지 확인
// });

document.addEventListener("DOMContentLoaded", () => {
  const checkbox = document.getElementById("confirmWithdrawal");
  const button = document.getElementById("withdrawalButton");

  checkbox.addEventListener("change", () => {
    if (checkbox.checked) {
      button.classList.add("enabled");
      button.disabled = false;
    } else {
      button.classList.remove("enabled");
      button.disabled = true;
    }
  });
});

// 탈퇴 버튼 클릭 시 회원 탈퇴 처리
document.getElementById("delete-form").addEventListener("submit", async (e) => {
  e.preventDefault();
  const confirmCheckbox = document.getElementById("confirmWithdrawal");

  if (!confirmCheckbox.checked) {
    alert("탈퇴 유의사항을 확인 후 체크박스를 클릭해주세요.");
    return;
  }

  const formData = new FormData(e.currentTarget);
  const data = await axiosHelper.deleteMultipart(e.currentTarget.action, formData);
  try {
  } catch (err) {
    await utilHelper.alertDanger(err.message);

    setTimeout(() => err.element.focus(), 300);
    return false;
  }
  if (data) {
    await utilHelper.alertSuccess("탈퇴가 완료되었습니다.");
    window.location = "/";
  }
});
