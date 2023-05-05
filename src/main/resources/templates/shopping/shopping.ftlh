<#-- @ftlvariable name="shoppingItemList" type="com.titzko.freemarkertodo.model.ShoppingItemList" -->


<#import "../pageframe/pageframe.ftlh" as pageframe/>
<#import "../shared/base-components.ftlh" as cmp>
<@pageframe.pageframe>
    <#include "./shopping-css.ftlh">
    <#include "./shopping-js.ftlh">
    <div class="container m-auto">
        <h1 class="d-flex justify-content-center my-5 h2">Shopping</h1>
        <div class="w-100 d-flex justify-content-center mt-5 mb-5">
            <button class="btn base-btn btn-lg btn-block" id="add-shopping-item-btn">
                +
            </button>
        </div>
        <form method="post" action="/shopping">
            <table id="shopping-table" class="cell-border" style="width:100%">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Amount</th>
                    <th>Price</th>
                    <th>Prio</th>
                    <th>Purchasing Date</th>
                </tr>
                </thead>
                <tbody>
                <#list shoppingItemList.shoppingItems as item>
                    <tr>
                        <td>
                            <#assign property = "shoppingItemList.shoppingItems[" + item_index + "].name">
                            <@cmp.inputText model=property style="font-weight: bold;" />
                            <div class="d-none">
                                <#assign property = item.id???then("shoppingItemList.shoppingItems[" + item_index + "].id","")>
                                <@cmp.inputText model=property type="number"  />
                                <#assign property = "shoppingItemList.shoppingItems[" + item_index + "].userId">
                                <@cmp.inputText model=property type="number"  />
                            </div>
                        </td>
                        <td>
                            <#assign property = "shoppingItemList.shoppingItems[" + item_index + "].amount">
                            <@cmp.inputText model=property type="number" step="1" min=0 />
                        </td>
                        <td>
                            <#assign property = "shoppingItemList.shoppingItems[" + item_index + "].actualPrice">
                            <@cmp.inputText model=property type="number" step="0.01" min=0/>
                        </td>
                        <td>
                            <#assign property = "shoppingItemList.shoppingItems[" + item_index + "].priority">
                            <@cmp.inputText model=property type="text" />
                        </td>
                        <td>
                            <#assign property = "shoppingItemList.shoppingItems[" + item_index + "].purchasingDate">
                            <@cmp.inputText model=property type="date" />
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div class="w-100 d-flex justify-content-end">
                <button type="submit" class="btn btn-primary base-btn mt-5">Save</button>
            </div>
        </form>
    </div>
</@pageframe.pageframe>

