package com.example.controller.tool;

import com.example.controller.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author barry.jt.huang
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController
{
//    @PreAuthorize("hasAuthority('tool:swagger:view')")
    @GetMapping()
    public String index()
    {
        return redirect("/swagger-ui.html");
    }
}
