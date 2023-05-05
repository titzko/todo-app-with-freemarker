<style>
    .task {
        border-radius: 15px;
        background-color: var(--secondary-bg-color);
        margin: 16px auto;
        transition: background-color 0.2s;
        box-shadow: none;
        transition: box-shadow 0.2s;
    }

    .task:hover {
        box-shadow: 0 0 8px;
    }

    .task-left {
        display: flex;
        align-items: center;
    }

    input[type="checkbox"] {
        appearance: none;
        -webkit-appearance: none;
        -moz-appearance: none;
        outline: none;
        box-shadow: none;
        border-radius: 15px;
        border: 2px solid #333;
        width: 20px;
        height: 20px;
        margin-top: -5px;
        margin-right: 0 !important;
        cursor: pointer;
    }

    /* Style the checkmark */
    input[type="checkbox"]:checked::before {
        content: "";
        display: block;
        width: 20px;
        height: 20px;
        background: #333;
        border-radius: 30px;
        margin-top: -2px;
    }

    /* Add a green check sign to the checkmark */
    input[type="checkbox"]:checked::before {
        content: "✓";
        color: var(--main-theme-color);
        font-size: 20px;
        line-height: 20px;
        text-align: center;
    }

    input[type="checkbox"]:disabled {
        border-color: #ccc !important;
        opacity: 0.5;
        cursor: not-allowed;
        text-decoration: line-through;
    }

    .task-checkbox-label {
        color: var(--main-theme-color);
        cursor: pointer;
        margin-left: 0 !important;
        padding-left: 8px !important;
    }

    .task-status {
        width: 12px;
        height: 12px;
        border-radius: 50%;
        display: inline-block;
        z-index: 1000;
    }

    .todo {
        background-color: yellow;
    }

    .done {
        background-color: green;
    }

    .HIGH {
        color: red;
        text-decoration: underline;
    }

    .LOW {
        color: deepskyblue;
        text-decoration: none;
    }

    .MEDIUM {
        color: greenyellow;
        text-decoration: none;
    }

    .modal-header {
        border-bottom: 1px solid var(--main-bg-color) !important;
    }

    .modal-footer {
        border-top: 1px solid var(--main-bg-color) !important;
    }

</style>