package applica._APPNAME_.api.controllers;

import applica._APPNAME_.domain.data.RolesRepository;
import applica._APPNAME_.domain.data.UsersRepository;
import applica._APPNAME_.domain.model.Role;
import applica._APPNAME_.domain.model.User;
import applica.framework.Query;
import applica.framework.library.SimpleItem;
import applica.framework.library.responses.ValueResponse;
import applica.framework.security.authorization.Permissions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Applica (www.applica.guru)
 * User: bimbobruno
 * Date: 3/3/13
 * Time: 11:11 PM
 */
@RestController
@RequestMapping("/values")
public class ValuesController {

    @Autowired
    RolesRepository rolesRepository;

    @RequestMapping("/roles")
    public ValueResponse roles(String keyword) {
        List<Role> roles = rolesRepository.find(
                Query.build()
                        .like("role", keyword)
        ).getRows();

        return new ValueResponse(SimpleItem.createList(roles, "role", "id"));
    }

    @RequestMapping("/permissions")
    public ValueResponse permissions(String keyword) {
        return new ValueResponse(
            SimpleItem.createList(Permissions.instance().allPermissions(), (p) -> (String) p, (p) -> (String) p)
                .stream()
                .filter(l -> StringUtils.isEmpty(keyword) || (l.getLabel() != null && l.getLabel().toLowerCase().contains(keyword)))
                .collect(Collectors.toList())
        );
    }

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping("/users")
    public List<User> users() {
        return usersRepository.find(null).getRows();
    }

}
