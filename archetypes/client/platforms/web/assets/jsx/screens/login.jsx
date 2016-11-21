"use strict";

define("screens/login", (module, exports) => {

    const { FullScreenLayout, Screen } = require("../components/layout");
    const ui = require("../utils/ui");

    class Login extends Screen {
        constructor(props) {
            super(props)
        }

        login() {
            ui.navigate("/");
        }

        render() {
            return (
                <FullScreenLayout>
                    <div className="login-content">
                        <div className="lc-block toggled" id="l-login">
                            <div className="lcb-form">
                                <div className="form-group">
                                    <div className="fg-line">
                                        <input type="text" className="form-control" placeholder="Username" />
                                    </div>
                                </div>

                                <div className="form-group">
                                    <div className="fg-line">
                                        <input type="password" className="form-control" placeholder="Password" />
                                    </div>
                                </div>

                                <div className="checkbox">
                                    <label>
                                        <input type="checkbox" value="" />
                                            <i className="input-helper"></i>
                                            Keep me signed in
                                    </label>
                                </div>

                                <a href="javascript:;" className="btn btn-login btn-success" onClick={this.login.bind(this)}><i className="zmdi zmdi-arrow-forward"></i></a>
                            </div>

                            <div className="lcb-navigation">
                                <a href="" data-ma-action="login-switch" data-ma-block="#l-register"><i className="zmdi zmdi-plus"></i> <span>Register</span></a>
                                <a href="" data-ma-action="login-switch" data-ma-block="#l-forget-password"><i>?</i> <span>Forgot Password</span></a>
                            </div>
                        </div>
                    </div>
                </FullScreenLayout>
            )
        }

    }

    module.exports = Login;

})