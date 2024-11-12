// 팝업 열기
document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('.inquiryButton').addEventListener('click', function() {
        document.getElementById('inquiryPopup').style.display = 'flex';
    });
});

// 팝업 닫기
function closePopup() {
    document.getElementById('inquiryPopup').style.display = 'none';
}

// 글자 수 세기
const textarea = document.getElementById('inquiryContent');
const charCount = document.querySelector('.charCount');

textarea.addEventListener('input', function() {
    charCount.textContent = `${textarea.value.length} / 300자`;
});
