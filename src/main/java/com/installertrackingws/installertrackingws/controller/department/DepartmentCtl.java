package com.installertrackingws.installertrackingws.controller.department;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.model.department.Department;
import com.installertrackingws.installertrackingws.utility.department.DepartmentUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody DepartmentBn departmentBn){

         return new DepartmentUtl().save(entityManagerFactory,httpServletRequest,departmentBn);

    }

    @GetMapping("/get")
    public Response get(){

        DepartmentUtl departmentUtility = new DepartmentUtl();
        List<DepartmentBn> departmentBeans = departmentUtility.getDepartmentList(entityManagerFactory);

        Response response = new Response();

        if (departmentBeans.size()>0){
            response.setMsg("Get department successfully !");
            response.setCode(200);
            response.setList(departmentBeans);
            return response;
        }else {
            response.setMsg("Did not get any department !");
            response.setCode(200);
            return response;
        }

    }

    @PostMapping("/get-by-user")
    public Response save(@RequestBody UserBn userBn){

        Response response = new Response();
        DepartmentUtl departmentUtility = new DepartmentUtl();
        List<Department> departmentList = departmentUtility.getDepartmentByUser(entityManagerFactory,userBn);

        if (departmentList.size()>0){
            response.setCode(200);
            response.setMsg("Department list fetch successful !");
            response.setList(departmentList);
            return response;
        }else {
            response.setCode(200);
            response.setMsg("Department list fetch successful !");
            return response;
        }

    }

}
