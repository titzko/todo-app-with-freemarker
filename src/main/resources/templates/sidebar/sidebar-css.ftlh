<style>
    .category {
        display: block;
        padding: 10px 15px;
        font-size: 16px;
        color: #333;
        text-decoration: none;
        cursor: pointer;
        color: var(--main-theme-color);
    }

    .category:hover {
        background-color: var(--main-theme-color);
        color: var(--secondary-bg-color);
    }

    .category-active {
        background-color: var(--main-bg-color) !important;
        color: var(--main-theme-color) !important;
        box-shadow: 0 0 8px;
    }


    .category.selected {
        font-weight: bold;
        color: #007bff;
    }

    .seperator {
        border-bottom: 1px solid var(--main-theme-color);
        border-top: 1px solid var(--main-theme-color);
        height: 10px;
        margin: 12px 0;
    }

    ul {
        list-style-type: disclosure-closed;
        margin: 0;
        padding: 0;
    }

    li {
        margin: 0 10px;
        padding: 10px;
    }
</style>