const successAlert = document.getElementById('success-alert'); // Updated to get the success alert by ID
setTimeout(function () {
    successAlert.style.display = 'none';
}, 3000);

function isCheckEmpty(event) {
    const userName = document.getElementById('username');
    const password = document.getElementById('password');
    const userValid = document.getElementById('uservalid');
    const passwordvalid = document.getElementById('passwordvalid');

    //reset message and style
    userName.style.border = 'none';
    userValid.classList.add('d-none');
    password.style.border = 'none';
    passwordvalid.classList.add('d-none');

    // Lắng nghe sự kiện khi người dùng nhập vào trường input
    userName.addEventListener('input', function () {
        // Kiểm tra nếu giá trị trường input không còn là chuỗi rỗng
        if (userName.value !== '') {
            // Nếu có nội dung, thay đổi màu viền thành màu mong muốn
            userName.style.border = '1px solid #3b5d50';
            // Ẩn thông báo lỗi
            userValid.classList.add('d-none');
        } else {
            userName.style.border = '1px solid red';
            userValid.classList.remove('d-none');
            event.preventDefault();
        }
    });

    if (userName.value === '') {
        userName.style.border = '1px solid red';
        userValid.classList.remove('d-none');
        event.preventDefault();
    }

    password.addEventListener('input', function () {
        // Kiểm tra nếu giá trị trường input không còn là chuỗi rỗng
        if (password.value.length > 0) {
            password.style.border = '1px solid #3b5d50';
            passwordvalid.classList.add('d-none');
        }
        else {
            // Nếu là chuỗi rỗng, giữ màu viền mặc định và hiển thị thông báo lỗi
            password.style.border = '1px solid red'; // Có thể đặt lại màu viền mặc định
            password.classList.remove('d-none');
            event.preventDefault();
        }
    });

    if (password.value === "") {
        password.style.border = '1px solid red';
        passwordvalid.classList.remove('d-none');
        event.preventDefault();
    }

    // success login
}