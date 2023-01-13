/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalExam.FinalExam;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SavanaRizqiSukmaA(20200140088)
 */
@RestController
@CrossOrigin
@RequestMapping("/data")
public class tryController {
     Person mydata = new Person();
     PersonJpaController ctrl = new PersonJpaController();

    @GetMapping("/{id}")
    public List<Person> getNameById(@PathVariable("id") int id) {
        List<Person> dummy = new ArrayList<>(); 
        try {
            mydata = ctrl.findPerson(id);
            dummy.add(mydata); 
        } catch (Exception e) {
            dummy = List.of(); 
        }
        return dummy;
    }
     @GetMapping
    public List<Person> getAll() {
        List<Person> dummy = new ArrayList<>();
        try {
            dummy = ctrl.findPersonEntities();
        } catch (Exception e) {
            dummy = List.of();
        }
        return dummy;
    }
    @PostMapping()
    public String createData(HttpEntity<String> paket) {
        String message = "";

        try {
            String json_receive = paket.getBody();

            ObjectMapper map = new ObjectMapper();

            Person data = new Person();

            data = map.readValue(json_receive, Person.class);

            ctrl.create(data);
            message = data.getNamaPerson() + " Data Saved";

        } catch (Exception e) {
            message = "Failed";
        }

        return message;
    }
     @PutMapping()
    public String editData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Person newdata = new Person();

            newdata = mapper.readValue(json_receive, Person.class);
            ctrl.edit(newdata);
            message = newdata.getNamaPerson() + " has been Updated";
        } catch (Exception e) {
        }
        return message;
    }
     @DeleteMapping()
    public String deleteData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Person newdata = new Person();

            newdata = mapper.readValue(json_receive, Person.class);
            ctrl.destroy(newdata.getId());

            message = "Data has been Deleted";
        } catch (Exception e) {
        }
        return message;
    }
}