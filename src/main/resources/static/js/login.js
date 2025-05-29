document.addEventListener('DOMContentLoaded', () => {

    const pwInput = document.getElementById('pw');
    const toggleBtn = document.getElementById('togglePassword');
    const eyeIcon = document.getElementById('eyeIcon');

    const loginBtn = document.getElementById('log.login');
    const idInput = document.getElementById('id');


    toggleBtn.addEventListener('click', () => {
        const isHidden = pwInput.type === 'password';

        pwInput.type = isHidden ? 'text' : 'password';

        eyeIcon.src = isHidden ? '../imgs/icon/eye-on.svg' : '../imgs/icon/eye-off.svg';
        eyeIcon.alt = isHidden ? '비밀번호 보기' : '비밀번호 숨김';
    });

    loginBtn.addEventListener('click', (e) => {

        const idValue = idInput.value.trim();
        const pwValue = pwInput.value.trim();

        if (idValue === '') {
            alert('아이디 또는 이메일을 입력해 주세요.');
            idInput.focus();
            return;
        }

        if (pwValue === '') {
            alert('비밀번호를 입력해 주세요.');
            pwInput.focus();
            return;
        }
    });
});
 