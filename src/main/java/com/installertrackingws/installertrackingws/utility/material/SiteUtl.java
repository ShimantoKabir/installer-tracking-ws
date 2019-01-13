package com.installertrackingws.installertrackingws.utility.material;

import com.installertrackingws.installertrackingws.bean.material.SiteBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.Material.Site;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SiteUtl {

    public Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, SiteBn siteBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Site site = new Site();
            site.setAddress(siteBn.getAddress());
            site.setIp(httpServletRequest.getRemoteAddr());
            site.setLat(siteBn.getLat());
            site.setLon(siteBn.getLon());
            site.setModifiedBy(siteBn.getModifiedBy());
            site.setName(siteBn.getName());
            session.save(site);

            response.setMsg("Site save successfully !");
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

    public Response getAllSite(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * FROM site ",Site.class);

        List<Site> siteList = query.getResultList();

        session.getTransaction().commit();
        session.close();

        if (siteList.size()>0){
            response.setCode(200);
            response.setMsg("Site list fetch successful !");
            response.setList(siteList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Site list empty !");
            return response;
        }

    }

    public Response update(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, SiteBn siteBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE site SET name=:name, address=:address, ip=:ip, lat=:lat, lon=:lon, modified_by=:modifiedBy  WHERE id = :id");
            query.setParameter("id",siteBn.getId());
            query.setParameter("name",siteBn.getName());
            query.setParameter("address",siteBn.getAddress());
            query.setParameter("ip",httpServletRequest.getRemoteAddr());
            query.setParameter("lat",siteBn.getLat());
            query.setParameter("lon",siteBn.getLon());
            query.setParameter("modifiedBy",siteBn.getModifiedBy());
            query.executeUpdate();

            response.setMsg("Site updated successfully !");
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
