<script defer>
    async function registerUser() {
        const username = $("#register-username-input").val();
        const password = $("#register-password-input").val();
        const myUser = {username, password};

        const url = "/register"
        const response = await fetch(url, {
            body: JSON.stringify(myUser),
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        if (response.ok) {
            $('#registerModal').modal('toggle');
            const msg = "Creating user, please wait a second..."
            handleRegisterSuccess(msg);
        } else {
            $('#registerModal').modal('toggle');
            let errorMsg = await response.text();
            handleRegisterError(errorMsg);
        }
    }

    function handleRegisterSuccess(msg) {
        $("#success-toast").text(msg);
        $("#success-toast").show();
        setTimeout(() => {
            window.location.href = "/login";
        }, 3000);
    }


    function handleRegisterError(msg) {
        $("#fail-toast").text(msg);
        $("#fail-toast").show();
        setTimeout(() => {
            $("#fail-toast").hide();
        }, 4000);
    }
</script>