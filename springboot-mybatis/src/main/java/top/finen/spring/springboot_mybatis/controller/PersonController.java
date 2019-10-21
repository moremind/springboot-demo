package top.finen.spring.springboot_mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.finen.spring.springboot_mybatis.bean.Person;
import top.finen.spring.springboot_mybatis.service.impl.PersonServiceImpl;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    // TODO: 2019/10/21 使用统一的返回信息

    @Autowired
    PersonServiceImpl personService;

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Boolean addPerson(@RequestBody Person person) {
        return personService.addOnePerson(person);
    }

    @ResponseBody
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public Person getPersonInfo(@RequestParam String id) {
        return personService.getPersonInfoByIdCard(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public String getUserName(@RequestParam String id) {
        return personService.getPersonName(id);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUsername", method = RequestMethod.POST)
    public Boolean updateUserName(@RequestParam String name, @RequestParam String id) {
        return personService.updatePersonNameByIdCard(id, name);
    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public Boolean deletePerson(@RequestParam String id){
        return personService.deletePersonByIdCard(id);
    }
}
