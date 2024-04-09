package com.shopee.ecommer.configs;

import com.shopee.ecommer.constants.AuthorityConstant;
import com.shopee.ecommer.constants.MessageErrors;
import com.shopee.ecommer.exceptions.UnauthorizedRequestException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
//User For check Permission in Graphql
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private static final String FIELD_AUTHORITIES = "authorities";

    @Override
    public boolean hasPermission(
            Authentication auth, Object targetDomainObject, Object permission) {
        return checkPermission(auth, permission);
    }

    @Override
    public boolean hasPermission(
            Authentication auth, Serializable targetId, String targetType, Object permission) {
        return checkPermission(auth, permission);
    }

    private Boolean checkPermission(Authentication auth, Object permission) {
        //Get List Role inside system
        Set<String> getListPermissions = StringUtils.commaDelimitedListToSet((String) permission);
        boolean isPublicApi = getListPermissions.parallelStream().anyMatch(permissionValue -> permissionValue.equalsIgnoreCase(AuthorityConstant.ANONYMOUS));
        if(isPublicApi){
            return true;
        }

        //Parse to get role from Token
        Jwt credentialParse = (Jwt) auth.getCredentials();
        Map<String, Object> claimsParse = credentialParse.getClaims();
        List<String> authoritiesParse = (List<String>) claimsParse.get(FIELD_AUTHORITIES);

        return getListPermissions.stream().anyMatch(permissionValue -> authoritiesParse
                .parallelStream().anyMatch(authValue -> authValue.equalsIgnoreCase(permissionValue)));
    }

}
