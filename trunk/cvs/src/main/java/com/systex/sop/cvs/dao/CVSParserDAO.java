package com.systex.sop.cvs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.systex.sop.cvs.dto.Tbsoptcvsmap;
import com.systex.sop.cvs.dto.Tbsoptcvsver;
import com.systex.sop.cvs.helper.CVSLog;
import com.systex.sop.cvs.util.SessionUtil;
import com.systex.sop.cvs.util.StringUtil;

@SuppressWarnings("unchecked")
public class CVSParserDAO {
	
	public Tbsoptcvsmap queryMapByRcsfile(String rcsfile) {
		Session session = null;
		try {
			session = SessionUtil.openSession();
			Criteria cri = session.createCriteria(Tbsoptcvsmap.class);
			cri.add(Restrictions.eq("rcsfile", rcsfile));
			return (Tbsoptcvsmap) cri.uniqueResult();
		}catch(Exception e){
			CVSLog.getLogger().error(this, e);
			throw new RuntimeException(e);
		}finally{
			SessionUtil.closeSession(session);
		}
	}
	
	public Tbsoptcvsver queryVerByVer(Long m_SID, BigDecimal version) {
		Session session = null;
		try {
			session = SessionUtil.openSession();
			String hql = StringUtil.concat("from Tbsoptcvsver where m_sid = ", m_SID, " and version = ", version);
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			List<Tbsoptcvsver> list = query.list();
			if (list != null && list.size() > 0) return list.get(0);
		}catch(Exception e){
			CVSLog.getLogger().error(this, e);
			throw new RuntimeException(e);
		}finally{
			SessionUtil.closeSession(session);
		}
		
		return null;
	}
}
