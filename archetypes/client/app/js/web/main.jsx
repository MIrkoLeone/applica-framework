import {Index} from "./components/layout"
import Login from "./screens/login"
import Register from "./screens/register"
import Recover from "./screens/recover"
import Home from "./screens/home"
import RegistrationOk from "./screens/registrationOk"
import Confirm from "./screens/confirm"
import * as ui from "./utils/ui"
import * as plugins from "./pluginsimpl"
import {resumeSession, setupMenu} from "../actions"
import {EntitiesGrid, EntityForm} from "./screens/admin"
import strings from "../strings"

function ifAdmin(fn, ...params) {
    if (true) {
        fn.apply(this, params)
    }
}

/* Register plugins */
plugins.register()

/* Admin routes */
ui.addRoute("/admin/entities/:entity/", params => ifAdmin(ui.changeScreen, <EntitiesGrid entity={params.entity} grid={params.grid} />))
ui.addRoute("/admin/entities/:entity/edit", params => ifAdmin(ui.changeScreen, <EntityForm entity={params.entity} form={params.form} />))

/* Account routes */
ui.addRoute("/login", params => ui.changeScreen(<Login />))
ui.addRoute("/register", params => ui.changeScreen(<Register />))
ui.addRoute("/recover", params => ui.changeScreen(<Recover />))
ui.addRoute("/registrationComplete", params => ui.changeScreen(<RegistrationOk />))
ui.addRoute("/confirm", params => ui.changeScreen(<Confirm activationCode={params.activationCode}/>))

/* home route */
ui.addRoute("/", params => ui.changeScreen(<Home />))

/* render main index page into dom */
ReactDOM.render(<Index />, document.getElementById("entry-point"))

/* Setup menu voices */
setupMenu({menu: [
    {
        icon: "zmdi zmdi-shield-security",
        text: strings.security,
        children: [
            {
                icon: "zmdi zmdi-accounts-alt",
                text: strings.users,
                href: "/#/admin/entities/user?grid=users"
            },
            {
                icon: "zmdi zmdi-key",
                text: strings.roles,
                href: "/#/admin/entities/role?grid=roles"
            }
        ]
    },
    {
        icon: "zmdi zmdi-wrench",
        text: strings.setup,
        children: [
            {
                icon: "zmdi zmdi-labels",
                text: strings.categories,
                href: "/#/admin/entities/category?grid=categories"
            }
        ]
    }
]})

/* automatic login, if possible */
resumeSession()

/* starts navigation demon */
ui.startNavigation()