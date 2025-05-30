document.addEventListener("DOMContentLoaded", () => {
  // 선택하기 버튼 클릭 시 펼/닫
  const selectButtons = document.querySelectorAll(".price_select button");
  selectButtons.forEach(button => {
    button.addEventListener("click", () => {
      const reservationSection = button.closest(".package_card").querySelector(".reservation_section");
      if (reservationSection.style.display === "block") {
        reservationSection.style.display = "none";
      } else {
        reservationSection.style.display = "block";
      }
    });
  });

  // 수량 + / - 및 총 금액
  const packageCards = document.querySelectorAll(".package_card");
  packageCards.forEach(card => {
    const minusBtn = card.querySelector(".minus_btn");
    const plusBtn = card.querySelector(".plus_btn");
    const quantityEl = card.querySelector(".quantity");
    const totalPriceEl = card.querySelector(".total_price strong");

    // 기본 가격
    const basePriceText = card.querySelector(".price_select span").textContent;
    const basePrice = parseInt(basePriceText.replace(/[^\d]/g, ''), 10);

    let quantity = 1;

    minusBtn.addEventListener("click", () => {
      if (quantity > 1) {
        quantity--;
        updateDisplay();
      }
    });

    plusBtn.addEventListener("click", () => {
      quantity++;
      updateDisplay();
    });

    function updateDisplay() {
      quantityEl.textContent = quantity;
      const newTotal = basePrice * quantity;
      totalPriceEl.textContent = `₩ ${newTotal.toLocaleString()}`;
    }
  });
});
