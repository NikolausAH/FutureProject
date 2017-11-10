package com.blibli.pointofsales.pegawai.controller;

import com.blibli.pointofsales.pegawai.model.entity.Pegawai;
import com.blibli.pointofsales.pegawai.model.entity.Role;
import com.blibli.pointofsales.pegawai.service.PegawaiService;
import com.blibli.pointofsales.pegawai.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PegawaiController {

    @Autowired//semua annotation cuman 1 buat bawahnya doang
    private PegawaiService pegawaiService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/pegawai", method = RequestMethod.GET)
    public ModelAndView pegawai(){
        ModelAndView modelAndView = new ModelAndView("Pegawai");
        List<Pegawai> pegawais = pegawaiService.findAll();
        List<Role> roles = roleService.findAll();
        modelAndView.addObject("pegawais", pegawais);
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    //@valid supaya pegawainya harus benar
    @RequestMapping(value = "/pegawai", method = RequestMethod.POST)
    public Pegawai create(@Valid Pegawai pegawai){
        return pegawaiService.create(pegawai);
    }
}
