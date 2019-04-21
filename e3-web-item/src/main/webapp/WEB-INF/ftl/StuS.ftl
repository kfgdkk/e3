<meta charset="UTF-8">
<table border="1">
    <tr>
        <td>id</td>
        <td>姓名</td>
        <td>年龄</td>
    </tr>
    <#list list as li>
    <tr>
        <td>${li.id}</td>
        <td>${li.name}</td>
        <td>${li.age}</td>
    </tr>
    </#list>
</table>