document.addEventListener("DOMContentLoaded", function() {
    const deliveryRequestSelect = document.getElementById("deliveryRequest");
    const customRequestInput = document.getElementById("customRequestInput");

    deliveryRequestSelect.addEventListener("change", function() {
        if (deliveryRequestSelect.value === "직접 입력") {
            customRequestInput.style.display = "block";
        } else {
            customRequestInput.style.display = "none";
        }
    });
});
