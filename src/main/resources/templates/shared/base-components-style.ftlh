<style>

    .base-label {
        font-weight: bold;
        font-size: 12px;
        color: var(--secondary-theme-color);
        margin-bottom: 0;
        padding-left: 4px;
    }

    .base-input {
        color: var(--main-theme-color);
    }

    .base-input input {
        background-color: var(--secondary-bg-color);
        padding: 4px 8px;
        border: none;
    }


    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    /* Firefox */
    input[type=number] {
        -moz-appearance: textfield;
    }


    .base-btn {
        background-color: var(--main-theme-color) !important;
        color: var(--secondary-theme-color) !important;
        border: var(--secondary-theme-color) !important;
    }


    .base-btn:hover {
        color: var(--main-theme-color) !important;
        border: var(--main-theme-color) !important;
        background-color: var(--secondary-theme-color) !important;
    }

    .base-btn:active {
        transition: all 0.2s ease-in-out;
        transform: translateY(2px);
        box-shadow: none;
    }

    .base-btn:focus {
        outline: none;
        box-shadow: 0 0 0 2px var(--secondary-theme-color);
    }

</style>