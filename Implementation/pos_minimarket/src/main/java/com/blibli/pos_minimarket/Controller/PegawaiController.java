package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Pegawai;
import com.blibli.pos_minimarket.Services.PegawaiService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PegawaiController{
    private final PegawaiService pegawaiService;
//
    @Autowired //menandakan fungsi/konstruktor akan di init secara otomatis saat program di Run.
    public PegawaiController(PegawaiService pegawaiService) {
        this.pegawaiService = pegawaiService;
    }
    @RequestMapping(value = "Pegawai")
    public String showAllPegawai(Model model) {
        System.out.println(pegawaiService.showAll().size());
        model.addAttribute("pegawai", pegawaiService.showAll());
        model.addAttribute("pgw", new Pegawai());
        model.addAttribute("pegawaiUpdated", new Pegawai());
        return "Pegawai";
    }
    @PostMapping(value = "createPegawai")
    public ModelAndView addPegawai(@ModelAttribute("pgw") Pegawai pgw){
        ModelAndView mav = new ModelAndView();
        pegawaiService.add(pgw);
        mav.setViewName("redirect:/Pegawai");
        return mav;
    }

    @PostMapping(value = "updatePegawai")
    public ModelAndView updatePegawai(@ModelAttribute("pegawaiUpdated") Pegawai pegawaiUpdated){
        ModelAndView mav = new ModelAndView();
        pegawaiService.update(pegawaiUpdated);
        mav.setViewName("redirect:/Pegawai");
        return mav;
    }

    @PostMapping(value = "deletePegawai")
    public ModelAndView deletePegawai(@ModelAttribute("id") Integer id){
        ModelAndView mav = new ModelAndView();
        pegawaiService.delete(id);
        mav.setViewName("redirect:/Pegawai");
        return mav;
    }
}
