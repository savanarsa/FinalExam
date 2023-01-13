/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalExam.FinalExam;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @authorSavanaRizqiSukmaA(20200140088)
 */
public class PersonController {
    
    Person prs = new Person();
    PersonJpaController ctrl = new PersonJpaController();
    
    List<Person> PersonList = new ArrayList<Person>();
    
    @GetMapping()
    public List<Person> viewAll() {
        try {
            return ctrl.findPersonEntities(); 
        } catch (Exception e) {
            return List.of(); 
        }

    }
     @PostMapping()
    public String postPerson (@RequestBody Person person) 
    {
        try {
            ctrl.create(person);
            return "Data Saved";
        } catch (Exception e) {
            return "Failed to save data";
        }

    }
      @GetMapping("/{id}")
    public List<Person> viewDatabyId(@PathVariable("id") int id) {
        try {
           prs = ctrl.findPerson(id); 
            PersonList.clear(); 
            PersonList.add(prs);
            return PersonList; 
        } catch (Exception e) {
            return List.of();
        }
    }
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public String editData(@PathVariable("id") int id, @RequestBody Person data) {
        String rslt = "Data has been updated";
        try {
            data.setId(id); 
            ctrl.edit(data); 
        } catch (Exception e) {
            rslt = e.toString() + " Update Failed";
        }

        return rslt;

    }
    
 @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") int id) {
        String rslt = "Data has been deleted";
        try {
            ctrl.destroy(id); 
        } catch (Exception e) {
            rslt = "Delete Failed";
        }

        return rslt;
    }
}