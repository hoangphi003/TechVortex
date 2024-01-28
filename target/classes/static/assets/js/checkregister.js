function ValidateLogin(event) {
    const userName = document.getElementById('userName');
    const password = document.getElementById('password');
    const email = document.getElementById('email');
    const userValid = document.getElementById('uservalid');
    const passwordvalid = document.getElementById('passwordvalid');
    const emailValid = document.getElementById('emailvalid');
    const alert = document.getElementsByClassName(".alert");
    const formatUserName = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    const formatEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    //reset message and style
    userName.style.border = '';
    userValid.classList.add('d-none');
    emailValid.classList.add('d-none');
    password.style.border = '';
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

    if (formatUserName.test(userName.value)) {
        userName.style.border = '1px solid red';
        userValid.classList.remove('d-none');
        userValid.innerText = "Không có ký tự đặc biệt"
        event.preventDefault();
    }

    email.addEventListener('input', function () {
        // Kiểm tra nếu giá trị trường input không còn là chuỗi rỗng
        if (formatEmail.test(email.value)) {
            // Nếu có nội dung, thay đổi màu viền thành màu mong muốn
            email.style.border = '1px solid #3b5d50';
            // Ẩn thông báo lỗi
            emailValid.classList.add('d-none');
        } else {
            email.style.border = '1px solid red';
            emailValid.classList.remove('d-none');
            emailValid.innerText = "Email không hợp lệ"
            event.preventDefault();
        }
    });

    if (email.value === '') {
        email.style.border = '1px solid red';
        emailValid.classList.remove('d-none');
        event.preventDefault();
    }
    if (email.value.indexOf('@') === -1) {
        email.style.border = '1px solid red';
        emailValid.classList.remove('d-none');
        emailValid.innerText = "Email không hợp lệ"
        event.preventDefault();
    }

    password.addEventListener('input', function () {
        // Kiểm tra nếu giá trị trường input không còn là chuỗi rỗng
        if (password.value.length >= 6) {
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

    if (password.value.length < 7) {
        password.style.border = '1px solid red';
        passwordvalid.classList.remove('d-none');
        passwordvalid.innerText = "Mật khẩu trên 6 ký tự"
        event.preventDefault();
    }

    // success for login
}
// 
const successAlert = document.getElementById('success-alert'); // Updated to get the success alert by ID
setTimeout(function () {
    successAlert.style.display = 'none';
}, 3000);