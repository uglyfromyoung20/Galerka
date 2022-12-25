<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Смена пароля</h1>
    <hr>
    <form action="/change" method="post">

        <div class="form-group">
            <label for="password">Введите новый пароль</label>
            <input type="password"  id="password" name="password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Изменить пароль</button>
    </form>
    <hr>
</@t.template>