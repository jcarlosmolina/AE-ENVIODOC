package com.keralty.aeenviodoc.service;

import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.EstadoContratoConstants;
import com.keralty.aeenviodoc.persistence.EstadoContrato;
import com.keralty.aeenviodoc.persistence.oid.EstadoContratoOid;
import com.keralty.aeenviodoc.repository.EstadoContratoJpaRepository;
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
public class EstadoContratoService extends AbstractService {

    EstadoContratoJpaRepository repository;

    EstadoContratoService estadoContratoSrv;

    @Inject
    public EstadoContratoService(
            EstadoContratoService estadoContratoSrv,
            EstadoContratoJpaRepository repository) {
        this.estadoContratoSrv = estadoContratoSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of EstadoContrato by ID
     *
     * @param estadoContratoCodigo Identification field estadoContratoCodigo
     *
     * @return Instance of EstadoContrato
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoContrato getById(Long estadoContratoCodigo) {
        return getByOID(new EstadoContratoOid(estadoContratoCodigo));
    }
    
    /*
     * Returns an instance of EstadoContrato by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of EstadoContrato
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoContrato getByOID(EstadoContratoOid oid) {
        Optional<EstadoContrato> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of EstadoContrato by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of EstadoContrato
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public EstadoContrato getByOIDWithHV(EstadoContratoOid oid) {
        Optional<EstadoContrato> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<EstadoContrato> getInstanceByOid(EstadoContratoOid oid) {
        return oid != null && oid.getEstadoContratoCodigo() != null
               ? repository.findById(oid.getEstadoContratoCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of EstadoContrato by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of EstadoContrato
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoContrato getByJsonOid(String jsonOid) {
        EstadoContratoOid oid = new EstadoContratoOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            EstadoContratoOid oid = new EstadoContratoOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(EstadoContratoConstants.ATTR_FIELD_ESTADOCONTRATOCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getEstadoContratoCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(EstadoContratoService.class).error(e.getMessage());
        }
    }

}
