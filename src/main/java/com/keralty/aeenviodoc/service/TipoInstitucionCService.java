package com.keralty.aeenviodoc.service;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.TipoInstitucionCConstants;
import com.keralty.aeenviodoc.persistence.oid.TipoInstitucionCOid;
import com.keralty.aeenviodoc.persistence.TipoInstitucionC;
import com.keralty.aeenviodoc.repository.TipoInstitucionCJpaRepository;
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
public class TipoInstitucionCService extends AbstractService {

    TipoInstitucionCJpaRepository repository;

    TipoInstitucionCService tipoInstitucionCSrv;

    @Inject
    public TipoInstitucionCService(
            TipoInstitucionCService tipoInstitucionCSrv,
            TipoInstitucionCJpaRepository repository) {
        this.tipoInstitucionCSrv = tipoInstitucionCSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of TipoInstitucionC by ID
     *
     * @param tipoInstitucionCCodigo Identification field tipoInstitucionCCodigo
     *
     * @return Instance of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoInstitucionC getById(Long tipoInstitucionCCodigo) {
        return getByOID(new TipoInstitucionCOid(tipoInstitucionCCodigo));
    }
    
    /*
     * Returns an instance of TipoInstitucionC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoInstitucionC getByOID(TipoInstitucionCOid oid) {
        Optional<TipoInstitucionC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TipoInstitucionC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TipoInstitucionC getByOIDWithHV(TipoInstitucionCOid oid) {
        Optional<TipoInstitucionC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TipoInstitucionC> getInstanceByOid(TipoInstitucionCOid oid) {
        return oid != null && oid.getTipoInstitucionCCodigo() != null
               ? repository.findById(oid.getTipoInstitucionCCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of TipoInstitucionC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TipoInstitucionC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoInstitucionC getByJsonOid(String jsonOid) {
        TipoInstitucionCOid oid = new TipoInstitucionCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TipoInstitucionCOid oid = new TipoInstitucionCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoInstitucionCConstants.ATTR_FIELD_TIPOINSTITUCIONCCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTipoInstitucionCCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TipoInstitucionCService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
