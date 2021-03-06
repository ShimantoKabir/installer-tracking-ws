package com.installertrackingws.installertrackingws.utility.department;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.department.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class DepartmentUtl {

    public List<DepartmentBn> getDepartmentList(EntityManagerFactory entityManagerFactory){

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from department",Department.class);

        List<DepartmentBn> departmentBnList = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return departmentBnList;

    }

    public Response save(EntityManagerFactory entityManagerFactory, HttpServletRequest httpServletRequest,Request request){

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createNativeQuery("SELECT count(id) FROM department WHERE rk = :rk");
            query.setParameter("rk",request.getDepartmentBn().getRk());

            if (query.getResultList().get(0).toString().equals("0")){

                BigInteger oId = (BigInteger) session.createNativeQuery("SELECT IFNULL(MAX(o_id),100)+1 AS o_id FROM department").getResultList().get(0);

                Department department = new Department();
                department.setIp(httpServletRequest.getRemoteAddr());
                department.setName(request.getDepartmentBn().getName());
                department.setRk(request.getDepartmentBn().getRk());
                department.setoId(oId.intValue());
                department.setModifiedBy(request.getUserBn().getId());
                session.save(department);

                response.setMsg("Department save successfully !");
                response.setCode(200);

            }else {

                response.setMsg("This rk already exist !");
                response.setCode(400);

            }

            transaction.commit();

        } catch (Exception e) {
            response.setCode(400);
            response.setMsg("Exception occurred");
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }

        return response;

    }

    public Response getDepartmentByUser(EntityManagerFactory entityManagerFactory, Request request){

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query rkQuery = session.createNativeQuery("SELECT * FROM department WHERE o_id = :deptId",Department.class);
        rkQuery.setParameter("deptId",request.getUserBn().getDeptId());

        Department department = (Department) rkQuery.getSingleResult();
        int rk = department.getRk();

        Query departmentListQeury = session.createNativeQuery("SELECT * FROM department WHERE rk >= :rk",Department.class);
        departmentListQeury.setParameter("rk",rk);

        List<Department> departmentList = departmentListQeury.getResultList();

        session.getTransaction().commit();
        session.close();

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

    public Response update(EntityManagerFactory entityManagerFactory, HttpServletRequest httpServletRequest, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE department SET name=:name,rk=:rk,ip=:ip,modified_by=:modifiedBy,modify_date=:modifyDate WHERE id=:id");
            query.setParameter("rk",request.getDepartmentBn().getRk());
            query.setParameter("name",request.getDepartmentBn().getName());
            query.setParameter("modifiedBy",request.getUserBn().getId());
            query.setParameter("modifyDate",new Date());
            query.setParameter("ip",httpServletRequest.getRemoteAddr());
            query.setParameter("id",request.getDepartmentBn().getId());
            query.executeUpdate();

            response.setMsg("Department update successful !");
            response.setCode(200);

            transaction.commit();

        } catch (Exception e) {
            response.setCode(400);
            response.setMsg("Exception occurred");
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }

        return response;

    }
}
