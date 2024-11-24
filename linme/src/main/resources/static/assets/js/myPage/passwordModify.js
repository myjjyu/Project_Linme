// 비밀번호 보기/숨기기 토글 기능
document.addEventListener("DOMContentLoaded", () => {
    // 각 토글 버튼과 해당 비밀번호 필드를 매핑
    const toggles = [
        { inputId: "currentPassword", toggleId: "toggle-current-password" },
        { inputId: "newPassword", toggleId: "toggle-new-password" },
        { inputId: "confirmPassword", toggleId: "toggle-confirm-password" },
    ];

    toggles.forEach(({ inputId, toggleId }) => {
        const passwordInput = document.getElementById(inputId);
        const toggleButton = document.getElementById(toggleId);

        // 클릭 이벤트 추가
        toggleButton.addEventListener("click", () => {
            // 현재 비밀번호 타입 확인 후 평문/숨김 전환
            if (passwordInput.type === "password") {
                passwordInput.type = "text"; // 평문으로 전환
                toggleButton.src = "/assets/img/myPage/original.png"; // 눈 아이콘
            } else {
                passwordInput.type = "password"; // 숨김으로 전환
                toggleButton.src = "/assets/img/myPage/secret.png"; // 닫힌 눈 아이콘
            }
        });
    });
});

