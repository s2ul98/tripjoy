document.addEventListener('DOMContentLoaded', () => {

  // 약관동의
  const checkAll = document.getElementById('check_all');
  const items = document.querySelectorAll('.check_item');

  checkAll.addEventListener('change', () => {
    items.forEach(item => {
      item.checked = checkAll.checked;
    });
  });

  items.forEach(item => {
    item.addEventListener('change', () => {
      const allChecked = Array.from(items).every(i => i.checked);
      checkAll.checked = allChecked;
    });
  });

  // 생년월일
  const birthInput = document.getElementById('birth');

  birthInput.addEventListener('input', (e) => {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length > 8) value = value.slice(0, 8);

    let formatted = '';
    if (value.length >= 4) {
      formatted = value.slice(0, 4) + '.';
      if (value.length >= 6) {
        formatted += value.slice(4, 6) + '.';
        formatted += value.slice(6);
      } else {
        formatted += value.slice(4);
      }
      } else {
      formatted = value;
    }

    e.target.value = formatted;
  });

  // 연락처
  const phoneInput = document.getElementById('phone');

  phoneInput.addEventListener('input', (e) => {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length > 11) value = value.slice(0, 11);

    let formatted = '';
    if (value.length >= 11) {
      formatted = value.slice(0, 3) + '-' + value.slice(3, 7) + '-' + value.slice(7);
    } else if (value.length >= 7) {
      formatted = value.slice(0, 3) + '-' + value.slice(3, 7) + '-' + value.slice(7);
    } else if (value.length >= 4) {
      formatted = value.slice(0, 3) + '-' + value.slice(3);
    } else {
      formatted = value;
    }

    e.target.value = formatted;
  });

  // 비번 눈
  document.getElementById('togglePassword1').addEventListener('click', function () {
    const input = document.getElementById('password');
    const icon = document.getElementById('eyeIcon1');
    const isHidden = input.type === 'password';
    input.type = isHidden ? 'text' : 'password';
    icon.src = isHidden ? '../imgs/icon/eye-on.svg' : '../imgs/icon/eye-off.svg';
    icon.alt = isHidden ? '비밀번호 보기' : '비밀번호 숨김';
});

  document.getElementById('togglePassword2').addEventListener('click', function () {
    const input = document.getElementById('password_confirm');
    const icon = document.getElementById('eyeIcon2');
    const isHidden = input.type === 'password';
    input.type = isHidden ? 'text' : 'password';
    icon.src = isHidden ? '../imgs/icon/eye-on.svg' : '../imgs/icon/eye-off.svg';
    icon.alt = isHidden ? '비밀번호 보기' : '비밀번호 숨김';
  });

  document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('form');

  form.addEventListener('submit', function (e) {
    alert('회원가입이 완료되었습니다!');
    window.location.href = '/html/login.html';

  });
});

});


