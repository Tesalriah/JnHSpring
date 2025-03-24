document.addEventListener("DOMContentLoaded", function() {
    const message = document.getElementById("alert-message");

    if (message && message.innerText.trim() !== "") {
        alert(message.innerText.trim());
    }
});