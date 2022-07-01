package jp.kobespiral.shoda.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jp.kobespiral.shoda.todo.dto.MidForm;
import jp.kobespiral.shoda.todo.dto.ToDoForm;
import jp.kobespiral.shoda.todo.entity.ToDo;
import jp.kobespiral.shoda.todo.service.ToDoService;

@Controller
public class ToDoController {
   @Autowired
   ToDoService tService;
   
   @GetMapping("/")
   String showUserForm(Model model) {
       MidForm form = new MidForm();
       model.addAttribute("MidForm", form);
       return "index";
   }

   @GetMapping("/login")
   String checkMidForm(@ModelAttribute(name = "MidForm") MidForm form, Model model){
        return "redirect:/list/" + form.getMid();
   }


   @PostMapping("/create/{mid}")
   String postToDo(@ModelAttribute(name = "ToDoForm") ToDoForm form, @PathVariable String mid, Model model) {
      ToDo t =  tService.createToDo(mid,form);
      model.addAttribute("ToDoForm", t);
      return "redirect:/list/" + mid;
   }

   @GetMapping("/list/{mid}")
   String showList(@PathVariable String mid, Model model) {
      ToDoForm form = new ToDoForm();
      List<ToDo> toDos = tService.getToDoList(mid);
      List<ToDo> dones = tService.getDoneList(mid);
      model.addAttribute("mid", mid);
      model.addAttribute("ToDoForm", form);
      model.addAttribute("todos", toDos);
      model.addAttribute("dones", dones);
      return "list";
   }

   @GetMapping("/done/{seq}/{mid}")
   String done(@PathVariable Long seq, @PathVariable String mid, Model model) {
       tService.done(seq);
       return "redirect:/list/" + mid;
   }

   @GetMapping("/delete/{seq}/{mid}")
   String deleteUser(@PathVariable Long seq, @PathVariable String mid, Model model) {
       tService.deleteToDo(seq);
       return "redirect:/list/" + mid;
   }

   @GetMapping("/alllist/{mid}")
   String showAllList(@PathVariable String mid, Model model) {
      List<ToDo> toDos = tService.getToDoList();
      List<ToDo> dones = tService.getDoneList();
      model.addAttribute("mid", mid);
      model.addAttribute("todos", toDos);
      model.addAttribute("dones", dones);
      return "alllist";
   }

}