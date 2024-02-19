// const TechVortex = angular.module('TechVortex', []);

// TechVortex.controller('homeController', function ($http, $scope) {
//     $http.get('/getqty').then(Response => {
//         $scope.num = Response.data;
//     }).catch(error => error);
// });
$(document).ready(function () {
    // Add product detail into cart
    $('.addToCartButton').click(function () {
        var productId = $(this).data('productdetail-id');
        var quantity = $('#quantityInput').val();
        $.get("/addToCart/" + productId + "?quantity=" + quantity, function (data) {
        });
        swal("Thành công!", "Sản phẩm đã được thêm vào giỏ hàng!", "success",
            {
                buttons: {},
            }
        );
        setTimeout(() => {
            location.reload();
        }, 1000);
    });

    $('.qty').on('input', function () {
        var cartId = $(this).data('cart-id');
        let qty = $(this).val();
        UpdateQtyToCart(cartId, qty);
    });

    function UpdateQtyToCart(cartId, currentValue) {
        if (currentValue === '') {
            $('.loading').addClass('d-none');
            return false;
        }

        $.get("/cart/" + cartId + "?qty=" + currentValue, function (data) { });
        $('.loading').removeClass('d-none');
        location.reload();
        showLoading();
    };

    $('.removeProduct').click(function () {
        swal("Bạn muốn xóa bỏ 1 sản phẩm này?", {
            buttons: {
                cancel: "Hủy",
                defeat: "Đồng ý",
            },
        }).then((value) => {
            switch (value) {
                case "defeat":
                    let cartId = $(this).data('cart-id');
                    $.get('/removeProduct/' + cartId, function (data) {
                        location.reload();
                    });
                    break;
            };
        });
    });

    $('.removeProductAll').click(function () {
        swal({
            text: "Bạn muốn xóa tất cả sản phẩm?",
            icon: "warning",
            buttons: {
                cancel: "Hủy",
                defeat: "Đồng ý",
            },
            dangerMode: true,
        }).then((value) => {
            switch (value) {
                case "defeat":
                    $.get('/removeProductAll/', function (data) {
                        location.reload();
                    });
                    break;
            };
        });
    });

});

function showLoading() {
    $('.loading').show(); // Hiển thị loading
    setTimeout(function () {
        $('.loading').hide(); // Ẩn loading sau 3 giây
    }, 10000);
}

function validateInput(input) {
    let minValue = parseInt(input.getAttribute('min'));
    let maxValue = parseInt(input.getAttribute('max'));
    let currentValue = input.value;
    currentValue.replace(/[^0-9]/g, "");

    if (currentValue < minValue) {
        input.value = minValue;
    } else if (currentValue > maxValue) {
        input.value = maxValue;
    }

    if (currentValue === '0') {
        input.value = '1';
    }
}

$(document).ready(function () {
    $('img').click(function () {
        $(this).addClass('border-success');
        $('img').removeClass('border-success');
    });

    $('.carousel-desc .carousel-image').eq(0).addClass('border-success');

    $('#carouselExampleIndicators').on('slid.bs.carousel', function () {
        const index = $('.carousel-item.active').index();
        $('.carousel-desc .carousel-image').removeClass('border-success');
        $('.carousel-desc .carousel-image').eq(index).addClass('border-success');
    });

});

const all = document.getElementById('all');
const selectAll = document.getElementById('selectAll');
const checkboxes = document.querySelectorAll('.form-check-input');
const spcheck = document.querySelectorAll('.spcheck');
const total = document.getElementById('total');
const totalsp = document.getElementById('total-sp');

function checkAll() {
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = all.checked;
        if (checkbox.checked = all.checked) {
            total.innerText = spcheck.length;
            totalsp.innerText = spcheck.length;
            $('.removeAll').removeClass('d-none');
        } else {
            totalsp.innerText = '0'
            total.innerText = '0'
        }

    });
}

function selectAlls() {
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = selectAll.checked;
        if (checkbox.checked = selectAll.checked) {
            total.innerText = spcheck.length;
            totalsp.innerText = spcheck.length;
            $('.removeAll').removeClass('d-none');
        } else {
            total.innerText = '0'
            totalsp.innerText = '0'
        }

    });
}

checkboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', function () {
        const selectedCount = document.querySelectorAll('.spcheck:checked').length;

        // Cập nhật số lượng sản phẩm được chọn vào thẻ span
        total.textContent = selectedCount;
        totalsp.textContent = selectedCount;
    });
});

// Biến đếm số lượng checkbox được chọn
let selectedCount = 0;
// ngăn chuyển trang khi chưa chọn sp
$('#buy').on('click', function (e) {

    // Lặp qua tất cả các checkbox
    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            // Nếu checkbox được chọn, tăng biến đếm lên 1
            selectedCount++;
        }
    });

    // Kiểm tra nếu không có sản phẩm nào được chọn
    if (selectedCount === 0) {
        // Hiển thị cảnh báo
        swal("Vui lòng chọn ít nhất một sản phẩm");
        // Ngăn chặn hành động mặc định của nút 'buy'
        e.preventDefault();
    }
});

$('.cart-hover').on("mouseover", function () {
    $('.cart-item').show()
});
$('.cart-hover').on("mouseout", function () {
    $('.cart-item').hide()
});

$('.spcheck').on('click', function () {
    const inputIdCheck = $(this).data('input-id');
    $.get('/checkout/' + inputIdCheck, function (data) {
    });
    // Xóa sản phẩm khi người dùng click lần 2
    $(this).on('change', function () {
        if ($('.spcheck').prop('checked') === false) {
            $.get('/removecheckout/' + inputIdCheck, function (data) {
            });
        } else {
            $.get('/checkout/' + inputIdCheck, function (data) {
            });
        }
    })
});

$(all).on('click', function () {
    spcheck.forEach(function (input) {
        const inputIdCheck = $(input).data('input-id');
        $.get('/checkout/' + inputIdCheck, function (data) {
        });

        // Xóa sản phẩm khi người dùng click lần 2
        $(this).on('change', function () {
            if ($('.spcheck').prop('checked') === false) {
                $.get('/removecheckout/' + inputIdCheck, function (data) {
                });
            } else {
                $.get('/checkout/' + inputIdCheck, function (data) {
                });
            }
        });
    });
});

$(selectAll).on('click', function () {
    spcheck.forEach(function (input) {
        const inputIdCheck = $(input).data('input-id');
        $.get('/checkout/' + inputIdCheck, function (data) {
        });

        // Xóa sản phẩm khi người dùng click lần 2
        $(this).on('change', function () {
            if ($('.spcheck').prop('checked') === false) {
                $.get('/removecheckout/' + inputIdCheck, function (data) {
                });
            } else {
                $.get('/checkout/' + inputIdCheck, function (data) {
                });
            }
        });
    });
});

// Js choose file review
$('#chooseFile').bind('change', function () {
    var filename = $("#chooseFile").val();
    if (/^\s*$/.test(filename)) {
        $(".file-upload").removeClass('active');
        $("#noFile").text("No file chosen...");
    }
    else {
        $(".file-upload").addClass('active');
        $("#noFile").text(filename.replace("C:\\fakepath\\", ""));
    }
});