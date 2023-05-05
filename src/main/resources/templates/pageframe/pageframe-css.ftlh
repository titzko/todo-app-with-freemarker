<style>
    :root {
        --main-bg-color: #161618;
        --secondary-bg-color: #202020;
        --main-color: #f2f5f4;
        --main-theme-color: #1db992;
        --secondary-theme-color: orange;
        --main-disabled-color: #aaa;
    }

    body {
        background-color: var(--main-bg-color) !important;
        color: var(--main-color) !important;
        height: 100vh;
        display: grid;
        grid-template-areas:
            "header header"
            "sidebar body";
        grid-template-columns: 200px 1fr;
        grid-template-rows: 50px 1fr;
        overflow-x: hidden;
    }

    #header {
        width: 100vw !important;
        max-width: 100vw !important;
        background-color: var(--secondary-bg-color);
        color: var(--main-theme-color);
        grid-area: header;
        padding: 10px 8px;
    }

    #body {
        grid-area: body;
        display: block;
        overflow-x: hidden;
    }

    #sidebar {
        grid-area: sidebar;
        display: block;
        background-color: var(--secondary-bg-color);
        min-width: 200px;
    }

    .header-button {
        background-color: transparent;
        color: var(--main-color);
        border: none;
        display: flex;
        height: 100%;
        justify-content: center;
        align-items: center;
        margin-left: 8px;
        font-size: 22px;
        outline: none;
        cursor: pointer;
        margin-left: 12px;
        margin-right: 12px;
    }

    .header-button:active,
    :focus {
        outline: none !important;
        box-shadow: none !important;
        color: var(--main-theme-color);
    }

    .bi::after {
        content: none !important;
    }

    .bi:hover {
        cursor: pointer !important;
    }

    #toggle-sidebar-btn {
        display: none;
    }

    .search-container {
        background-color: transparent;
        display: flex;
        border: 1px solid var(--main-color);
        border-radius: 2px;
        margin-left: 16px;
    }

    .bi-search {
        font-size: 20px;
    }

    .h2 {
        color: var(--main-theme-color);
    }

    .theme-button {
        background-color: transparent !important;
        border-color: var(--main-theme-color) !important;
        color: var(--main-color) !important;
        width: 80% !important;
    }

    .theme-button:hover {
        background-color: var(--main-bg-color) !important;
        border-color: var(--main-color) !important;
        color: var(--main-theme-color) !important;
    }

    .modal-content {
        background-color: var(--secondary-bg-color) !important;
    }

    .modal-content, .modal-content:active {
        color: var(--main-theme-color) !important;
    }

    input {
        color: var(--main-theme-color)
    }

    @media screen and (max-width: 700px) {
        body {
            display: flex !important;
            flex-direction: column;
        }

        #header {
            height: 50px;
        }

        #sidebar {
            z-index: 999;
            display: none;
        }

        #toggle-sidebar-btn {
            display: block;
        }
    }

    a:hover {
        text-decoration: none;
    }

    button:disabled {
        cursor: not-allowed;
        background-color: grey !important;
        color: white;
        border: none
    }

</style>