/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zbo.done.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.zbo.done.model.Owner;
import org.zbo.done.model.Owners;
import org.zbo.done.service.OwnerService;
import org.zbo.done.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }


    @RequestMapping(value = "/owners/{ownerId}", method = RequestMethod.GET)
    public void showOwner(@PathVariable("ownerId") int ownerId) throws IOException {
        Owner owner = this.ownerService.findOwnerById(ownerId);
        this.response.setContentType("application/json;charset=UTF-8");
        this.response.getWriter().write((JsonUtil.toJson(owner)));
    }
    @RequestMapping(value = "bootstrap/owners", method = RequestMethod.GET)
    public String get_owner(Map<String, Object> model) {
        return "bootstrap/pages/owners.jsp";
    }

    @RequestMapping(value = "bootstrap/owners/new", method = RequestMethod.GET)
    public ModelAndView new_owner() {
        ModelAndView mav = new ModelAndView("bootstrap/pages/ownerDetails.jsp");
        Owner owner= new Owner();
        mav.addObject("owner", owner);
        return mav;
    }

    @RequestMapping(value = "bootstrap/owners/{ownerId}", method = RequestMethod.GET)
    public ModelAndView show_owner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("bootstrap/pages/ownerDetails.jsp");
        mav.addObject(this.ownerService.findOwnerById(ownerId));
        return mav;
    }

    @RequestMapping(value = "bootstrap/owners/{ownerId}", method = RequestMethod.POST)
    public String edit_owner(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
        owner.setId(ownerId);
        this.ownerService.saveOwner(owner);
        return "redirect:/bootstrap/owners.jsp";

    }

    @RequestMapping(value = "bootstrap/owners/new", method = RequestMethod.POST)
    public String create_owner(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "bootstrap/pages/ownerDetails.jsp";
        } else {
            this.ownerService.saveOwner(owner);
            return "redirect:/bootstrap/owners/" + owner.getId();
        }
    }

    @RequestMapping("/owners.json")
    public
    @ResponseBody
    void showResourcesOwnerList() throws IOException {
        Owners ownerList = new Owners();
        ownerList.getOwnerList().addAll(this.ownerService.findAll());
        this.response.setContentType("application/json;charset=UTF-8");
        this.response.getWriter().write((JsonUtil.toJson(ownerList)));
    }
//
//    @RequestMapping("/owners_and_pets.json")
//    public
//    @ResponseBody
//    Owners showResourcesOwnerPetList() {
//        Owners owners = new Owners();
//        owners.getOwnerList().addAll(this.clinicService.findOwnersAndPets());
//        return owners;
//    }

}
