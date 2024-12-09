// 체크박스를 클릭했을 때 탈퇴 버튼 활성화/비활성화
document.getElementById("confirmWithdrawal").addEventListener("change", function () {
    const withdrawalButton = document.getElementById("withdrawalButton");
    withdrawalButton.disabled = !this.checked; // 체크박스가 체크되었는지 확인
});

// 탈퇴 버튼 클릭 시 회원 탈퇴 처리
document.getElementById("delete-form").addEventListener('submit', async e =>{
    e.preventDefault();
    const confirmCheckbox = document.getElementById("confirmWithdrawal");

    if (!confirmCheckbox.checked) {
        alert("탈퇴 유의사항을 확인 후 체크박스를 클릭해주세요.");
        return;
    }

    // try {
    //     const result = await utilHelper.confirmWarning("탈퇴 확인","정말로 탈퇴하시겠습니까? 탈퇴 후에는 복구할 수 없습니다.");

    //     if (result.value) {
    //         const data = await axiosHelper.delete("[[@{/api/myPage/out}]]");

    //         if (data) {
    //             await utilHelper.alertSuccess("탈퇴가 완료되었습니다.");
    //             window.location = "[[@{/main}]]";
    //         }
    //     }
    // } catch (e) {
    //     console.error("탈퇴 처리 중 오류 발생:", e);
    //     alert("탈퇴 처리 중 문제가 발생했습니다. 다시 시도해주세요.");
    // }
    try {
        const formData = new FormData(e.currentTarget);
        const data = await axiosHelper.deleteMultipart(e.currentTarget.action, formData);

        if (data) {
            await utilHelper.alertSuccess("탈퇴가 완료되었습니다.");
            window.location = "[[@{/main/main}]]";
        }
    } catch (error) {
        console.error("탈퇴 처리 중 오류 발생:", error);
        alert("탈퇴 처리 중 문제가 발생했습니다. 다시 시도해주세요.");
    }
});
