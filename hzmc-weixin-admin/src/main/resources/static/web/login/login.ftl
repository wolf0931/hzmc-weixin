<div class="hold-transition login-page">
    <div class="login-box">
        <form>
            <div class="login-box-body">
                <div class="form-group has-feedback">
                    <input type="text" class="form-control" ng-model="name" placeholder="请输入登录账号">
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input type="password" class="form-control" ng-model="password" placeholder="请输入登陆密码"
                           value="123456">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>

                <div class="row">
                    <div class="col-xs-8">
                        <div class="checkbox icheck">
                            <label>
                                <input type="checkbox"> 记住密码
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <button type="button" ng-click="submit()" class="btn btn-primary btn-block btn-flat">登陆</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

