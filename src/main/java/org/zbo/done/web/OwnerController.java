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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.zbo.done.service.OwnerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.ownerService.findOwnerById(ownerId));
        return mav;
    }
//
//    @RequestMapping("/owners.json")
//    public
//    @ResponseBody
//    void showResourcesOwnerList() throws IOException {
//        Owners ownerList = new Owners();
//        ownerList.getOwnerList().addAll(this.clinicService.findOwners());
//        this.response.setContentType("application/json;charset=UTF-8");
//        this.response.getWriter().write((JsonUtil.toJson(ownerList)));
//    }
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
