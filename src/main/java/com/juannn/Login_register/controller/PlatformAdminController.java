package com.juannn.Login_register.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/platform")
@PreAuthorize("hasAuthority('SUPER_ADMIN')")
public class PlatformAdminController {
}
