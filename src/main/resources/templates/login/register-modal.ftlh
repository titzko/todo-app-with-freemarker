<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerModalLabel">Register</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" class="text-white">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row py-3 mr-4">
                    <label class="col-4" for="register-username-input">Username</label>
                    <input id="register-username-input" class="col" type="text">
                </div>
                <div class="row py-3 mr-4">
                    <label class="col-4" for="register-username-input">Password</label>
                    <input id="register-password-input" class="col" type="password">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary theme-button btn-lg btn-block m-auto"
                        onclick="registerUser()">Register User
                </button>
            </div>
        </div>
    </div>
    <#include "../tasks/task/modal-css.ftlh">
</div>
