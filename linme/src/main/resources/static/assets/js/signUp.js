// 비밀번호 눈아이콘 //
document.getElementById("pwShowHide1").addEventListener("click", function () {
  const userPW = document.getElementById("pw");
  const passwordIcon = document.getElementById("pwShowHide1");

  // 패스워드가 표시된 상태인지 확인
  if (userPW.type === "password") {
    // 패스워드 보이기
    userPW.type = "text";
    passwordIcon.setAttribute("src", "/assets/img/login/password(2).png");
  } else {
    // 패스워드 숨기기
    userPW.type = "password";
    passwordIcon.setAttribute("src", "/assets/img/login/password.png");
  }
});

// 비밀번호 확인 눈아이콘
document.getElementById("pwShowHide2").addEventListener("click", function () {
  const userPW = document.getElementById("pwCheck");
  const passwordIcon = document.getElementById("pwShowHide2");

  // 패스워드가 표시된 상태인지 확인
  if (userPW.type === "password") {
    // 패스워드 보이기
    userPW.type = "text";
    passwordIcon.setAttribute("src", "/assets/img/login/password(2).png");
  } else {
    // 패스워드 숨기기
    userPW.type = "password";
    passwordIcon.setAttribute("src", "/assets/img/login/password.png");
  }
});
