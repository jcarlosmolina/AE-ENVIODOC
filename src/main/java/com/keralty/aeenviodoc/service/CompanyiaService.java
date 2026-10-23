package com.keralty.aeenviodoc.service;

import com.keralty.aeenviodoc.global.CompanyiaConstants;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.persistence.Companyia;
import com.keralty.aeenviodoc.persistence.oid.CompanyiaOid;
import com.keralty.aeenviodoc.repository.CompanyiaJpaRepository;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import com.keralty.aeenviodoc.sqlutils.SQLWhereComparison;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathAttribute;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class CompanyiaService extends AbstractService {

    CompanyiaJpaRepository repository;

    CompanyiaService companyiaSrv;

    @Inject
    public CompanyiaService(
            CompanyiaService companyiaSrv,
            CompanyiaJpaRepository repository) {
        this.companyiaSrv = companyiaSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of Companyia by ID
     *
     * @param companyiaCodigo Identification field companyiaCodigo
     *
     * @return Instance of Companyia
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Companyia getById(Long companyiaCodigo) {
        return getByOID(new CompanyiaOid(companyiaCodigo));
    }
    
    /*
     * Returns an instance of Companyia by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Companyia
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Companyia getByOID(CompanyiaOid oid) {
        Optional<Companyia> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Companyia by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Companyia
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Companyia getByOIDWithHV(CompanyiaOid oid) {
        Optional<Companyia> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Companyia> getInstanceByOid(CompanyiaOid oid) {
        return oid != null && oid.getCompanyiaCodigo() != null
               ? repository.findById(oid.getCompanyiaCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of Companyia by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Companyia
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Companyia getByJsonOid(String jsonOid) {
        CompanyiaOid oid = new CompanyiaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            CompanyiaOid oid = new CompanyiaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CompanyiaConstants.ATTR_FIELD_COMPANYIACODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getCompanyiaCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(CompanyiaService.class).error(e.getMessage());
        }
    }

}
