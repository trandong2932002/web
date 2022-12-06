<div class="modal fade" id="otp-static" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Xác thực</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div id="message">

                </div>

                <div id="otp" class="inputs d-flex flex-row justify-content-center mt-2">
                    <input class="m-2 text-center form-control rounded" type="text" id="otp-first" maxlength="1"/>
                    <input class="m-2 text-center form-control rounded" type="text" id="otp-second" maxlength="1"/>
                    <input class="m-2 text-center form-control rounded" type="text" id="otp-third" maxlength="1"/>
                    <input class="m-2 text-center form-control rounded" type="text" id="otp-fourth" maxlength="1"/>
                    <input class="m-2 text-center form-control rounded" type="text" id="otp-fifth" maxlength="1"/>
                    <input class="m-2 text-center form-control rounded" type="text" id="otp-sixth" maxlength="1"/>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" id="create-new-otp" class="btn btn-warning">Lấy mã mới</button>
                <button type="button" id="verify-otp" class="btn btn-primary">Xác thực</button>
            </div>
        </div>
    </div>
</div>