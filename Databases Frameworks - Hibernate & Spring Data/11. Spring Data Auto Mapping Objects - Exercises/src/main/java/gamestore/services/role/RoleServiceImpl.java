package gamestore.services.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleService roleService;

    @Autowired
    public RoleServiceImpl(RoleService roleService) {
        this.roleService = roleService;
    }
}
