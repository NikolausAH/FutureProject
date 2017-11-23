package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Pegawai;
import com.blibli.pos_minimarket.Services.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PegawaiController{
    private final PegawaiService pegawaiService;

    @Autowired //menandakan fungsi/konstruktor akan di init secara otomatis saat program di Run.
    public PegawaiController(PegawaiService pegawaiService) {
        this.pegawaiService = pegawaiService;
    }
    @RequestMapping(value = "Pegawai")
    public ModelAndView showAll(@ModelAttribute("pegawai") Pegawai pegawai){
        ModelAndView mav = new ModelAndView();
        pegawaiService.showAll();
        return mav;
    }
}
