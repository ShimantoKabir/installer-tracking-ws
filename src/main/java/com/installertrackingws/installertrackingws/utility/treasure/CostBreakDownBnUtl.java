package com.installertrackingws.installertrackingws.utility.treasure;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.treasure.CostBreakDownBn;
import com.installertrackingws.installertrackingws.model.treasure.CostBreakDown;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CostBreakDownBnUtl {


    public static Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();
        CostBreakDownBn costBreakDownBn = request.getCostBreakDownBn();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            CostBreakDown costBreakDown = new CostBreakDown();
            costBreakDown.setName(costBreakDownBn.getName());
            costBreakDown.setIp(httpServletRequest.getRemoteAddr());
            costBreakDown.setModifiedBy(costBreakDownBn.getModifiedBy());
            session.save(costBreakDown);

            response.setMsg("Cost break down save successfully !");
            response.setCode(200);

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg("Exception occurred !");
            response.setCode(400);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;

    }

    public static Response getAllCostBreakDown(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * from cost_break_down",CostBreakDown.class);
        List<CostBreakDown> costBreakDownList = query.getResultList();

        session.getTransaction().commit();
        session.close();

        if (costBreakDownList.size()>0){
            response.setCode(200);
            response.setMsg("Cost break down list fetch successful !");
            response.setList(costBreakDownList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Cost break down list empty !");
            return response;
        }

    }

    public static Response update(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();
        CostBreakDownBn costBreakDownBn = request.getCostBreakDownBn();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE cost_break_down SET name = :name , modified_by = :modifiedBy, ip = :ip WHERE id = :id");
            query.setParameter("name",costBreakDownBn.getName());
            query.setParameter("modifiedBy",costBreakDownBn.getModifiedBy());
            query.setParameter("ip",httpServletRequest.getRemoteAddr());
            query.setParameter("id",costBreakDownBn.getId());
            query.executeUpdate();

            response.setMsg("Cost break down update successfully !");
            response.setCode(200);

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg("Exception occurred !");
            response.setCode(400);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;

    }
}
