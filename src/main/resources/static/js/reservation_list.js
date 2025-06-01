document.addEventListener("DOMContentLoaded", () => {

  const tabs = document.querySelectorAll('.tab-menu button');
  const reservationLists = document.querySelectorAll('.reservation-list');

  tabs.forEach((tab, index) => {
    tab.addEventListener('click', () => {

      tabs.forEach(btn => btn.classList.remove('active'));
      tab.classList.add('active');


      reservationLists.forEach((list, listIndex) => {
        list.style.display = index === listIndex ? 'block' : 'none';
      });
    });
  });
  });
