const renderOptionProvince = (obj) => {
    return `<option value="${obj.province_id}">${obj.province_name}</option>`;
}

const renderOptionDistrict = (obj) => {
    return `<option value="${obj.district_id}">${obj.district_name}</option>`;
}

const renderOptionWard = (obj) => {
    return `<option value="${obj.ward_id}">${obj.ward_name}</option>`;
}

const getAllDistrictsByProvinceId = (provinceId, elem, callback) => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/district/' + provinceId
    }).done(data => {
        const districts = data.results;
        elem.empty();
        $.each(districts, (index, item) => {
            const str = renderOptionDistrict(item);
            elem.append(str);
        });
        if (callback) {
            callback(districts);
        }
    }).fail((error) => {
        console.log(error);
    });
}

const getAllWardsByDistrictId = (districtId, elem) => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/ward/' + districtId
    }).done((data) => {
        const wards = data.results;

        elem.empty();

        $.each(wards, (index, item) => {
            const str = renderOptionWard(item);
            elem.append(str);
        });
    }).fail((error) => {
        console.log(error);
    });
}

const getAllProvinces = () => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/'
    }).done(data => {
        const provinces = data.results;
        $("#provinceCre").empty().append('<option value=""></option>');
        $.each(provinces, (index, item) => {
            const str = renderOptionProvince(item);
            $("#provinceCre").append(str);
            $("#provinceUp").append(str);
        });
    }).fail((error) => {
        console.log(error);
    });
}

$("#provinceCre").on("change", function() {
    const provinceId = $(this).val();
    if (provinceId === "") {
        $("#districtCre").empty();
        $("#wardCre").empty();
    }
    getAllDistrictsByProvinceId(provinceId, $("#districtCre"), function(data) {
        const districtId = $("#districtCre").val()
        getAllWardsByDistrictId(districtId, $("#wardCre"));
    });
});

$("#districtCre").on("change", function() {
    const districtId = $(this).val();
    getAllWardsByDistrictId(districtId, $("#wardCre"));
});

$("#provinceUp").on("change", function() {
    const provinceId = $(this).val();
    getAllDistrictsByProvinceId(provinceId, $("#districtUp"), function(data) {
        const districtId = $("#districtUp").val()
        getAllWardsByDistrictId(districtId, $("#wardUp"));
    });
});


$("#districtUp").on('change', function () {
    const districtId = $(this).val();
    getAllWardsByDistrictId(districtId, $("#wardUp"));
})

getAllProvinces();


const btnRegister = $("#register");
btnRegister.on('click', () => {
    event.preventDefault();

    const invalidElements = document.getElementsByClassName("is-invalid");
    if (invalidElements.length > 0) {
        swal.fire({
            icon: 'error',
            title: 'Error',
            text: "There are errors on the form. Please correct them before submitting.",
        });
    } else if ($("#provinceCre").val() === "") {
        swal.fire({
            icon: 'error',
            title: 'Error',
            text: "Please select a province before submitting.",
        });
    } else {
        btnRegister.attr("disabled", true);
        const obj = {
            username: $("#username").val(),
            password: $("#password").val(),
            fullName: $('#fullName').val(),
            phone: $('#phone').val(),
            locationRegion: {
                provinceId: $("#provinceCre").val(),
                provinceName: $("#provinceCre").find("option:selected").text(),
                districtId: $("#districtCre").val(),
                districtName: $("#districtCre").find("option:selected").text(),
                wardId: $("#wardCre").val(),
                wardName: $("#wardCre").find("option:selected").text(),
                address: $('#address').val()
            }
        }

        setTimeout(() => {
            $.ajax({
                headers: {
                    'accept': 'application/json',
                    'content-type': 'application/json'
                },
                method: 'POST',
                url: 'http://localhost:8080/api/customers',
                dataType: 'json',
                data: JSON.stringify(obj),
            }).done(data => {
                console.log(data)
                swal.fire({
                    icon: 'success',
                    title: 'Success',
                    text: "Đăng ký thành công !!!"
                }).then(() => {
                    window.location.href = "http://localhost:8080/home/login";
                });
                $("#formCre").trigger("reset")
                $("#districtCre").empty()
                $("#wardCre").empty()
            }).always(() => {
                btnRegister.attr('disabled', false);
            })
        })
    }


})

let isError = false;
function checkInput(input) {
    let value = input.value;
    let regex;
    let message;
    switch (input.id) {
        case "username":
            if (value === "") {
                message = "Username can't be empty";
                return {
                    error: false,
                    message
                }
            } else if (value.length < 5 || value.length > 15) {
                message = "Username must be between 5 and 15 characters long";
                return {
                    error: false,
                    message
                }
            } else {
                regex = /^[a-zA-Z0-9]{1,15}$/;
                message = "Username can't have special characters"
                return {
                    error: regex.test(value),
                    message
                }
            }
        case "password":
            if (value === "") {
                message = "Password can't be empty";
                return {
                    error: false,
                    message
                }
            } else if (value.length < 5 || value.length > 15) {
                message = "Password must be between 5 and 15 characters long";
                return {
                    error: false,
                    message
                }
            } else {
                regex = /^[a-zA-Z0-9]{1,15}$/;
                message = "Password can't have special characters"
                return {
                    error: regex.test(value),
                    message
                };
            }
        case "fullName":
            if (value === "") {
                message = "Full name can't be empty";
                return {
                    error: false,
                    message
                }
            } else {
                regex = /^[a-zA-ZÀ-Ỹà-ỹ]+( [a-zA-ZÀ-Ỹà-ỹ]+)*$/;
                message = "Full name can't have special characters";
                return {
                    error: regex.test(value),
                    message
                }
            }
        case "phone":
            if (value === "") {
                message = "Phone can't be empty";
                return {
                    error: false,
                    message
                }
            } else {
                regex = /^0[0-9]{9}$/;
                message = "Phone number must have 10 digits and start with 0";
                return {
                    error: regex.test(value),
                    message
                }
            }
        case "address":
            if (value === "") {
                message = "Address can't be empty";
                return {
                    error: false,
                    message
                }
            }
        case "provinceCre":
            if (value === "") {
                message = "Province can't be empty";
                return {
                    error: false,
                    message
                }
            }

    }
}



const onBlur = (input) => {
    let errorStatus = checkInput(input)
    if (!errorStatus.error) {
        isError = true;
        document.getElementById(input.id + '-error').innerText = errorStatus.message;
    } else {
        isError = false;
        document.getElementById(input.id + '-error').innerText = "";
    }
    input.classList.remove("is-invalid", "is-valid");
    input.classList.add(isError ? "is-invalid" : "is-valid");
}

const onInput = (input) => {
    let errorStatus = checkInput(input)
    isError = !errorStatus.error;
    input.classList.remove("is-invalid", "is-valid");
    input.classList.add(isError ? "is-invalid" : "is-valid");
}

const onFocus = (input) => {
    document.getElementById(input.id + '-error').innerText = "";
    // document.getElementById(input.id + '-error-submit').innerText = "";
}

const onClick = (input) => {
    input.classList.remove("is-invalid", "is-valid");
    document.getElementById(input.id + '-error').innerText = "";
    // document.getElementById(input.id + '-error-submit').innerText = "";
}
