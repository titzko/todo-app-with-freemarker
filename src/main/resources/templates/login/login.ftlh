<#import "../pageframe/pageframe.ftlh" as pageframe/>
<#include "./login-js.ftlh">
<#include "./register-modal.ftlh">


<@pageframe.pageframe>
    <div></div>
    <div class="container py-5 my-auto">
        <#if failedLogin!false >
            <div id="fail-toast" class="alert alert-danger" role="alert" style="display: block">
                Bad Credentials, try again or register to create an account
            </div>
        </#if>
        <div id="fail-toast" class="alert alert-danger" role="alert" style="display: none">
            This is a danger alert—check it out!
        </div>
        <div id="success-toast" class="alert alert-success" role="alert" style="display: none">
            This is a success alert—check it out!
        </div>
        <form name='f' action="/login" method='POST' id="login-form" class="d-flex justify-content-center">
            <div class="row ml-2 ml-md-0" style="width: clamp(45ch, 50%, 75ch);">
                <h2 class="d-flex justify-content-center w-100 py-5">Login</h2>
                <div class="col-12 row py-2">
                    <div class="col-3 pl-0">User:</div>
                    <div class="col-9 px-0" ><input style="width: inherit" type='text' name='username' value=''></div>
                </div>
                <div class="col-12 row py-2">
                    <div class="col-3 pl-0">Password:</div>
                    <div class="col-9 px-0" ><input style="width: inherit" type='password' name='password'/></div>
                </div>
                <div class="col-12 row py-4">
                    <button class="col-6 btn btn-secondary theme-button" type="button" data-toggle="modal" data-target="#registerModal">
                        Create Account
                    </button>
                    <button name="submit" type="submit" class="col-6 btn btn-secondary theme-button">Login</button>
                </div>
            </div>
        </form>
    </div>
</@pageframe.pageframe>