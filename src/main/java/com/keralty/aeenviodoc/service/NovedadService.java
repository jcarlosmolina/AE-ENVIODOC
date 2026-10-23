package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.DominioConstants;
import com.keralty.aeenviodoc.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeenviodoc.global.NovedadConstants;
import com.keralty.aeenviodoc.global.SolicitudNovedadConstants;
import com.keralty.aeenviodoc.global.TipoNovedadConstants;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.CausaExclusion;
import com.keralty.aeenviodoc.persistence.Causal;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.Novedad;
import com.keralty.aeenviodoc.persistence.oid.CausaExclusionOid;
import com.keralty.aeenviodoc.persistence.oid.CausalOid;
import com.keralty.aeenviodoc.persistence.oid.DominioOid;
import com.keralty.aeenviodoc.persistence.oid.NovedadOid;
import com.keralty.aeenviodoc.persistence.oid.PlanesOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeenviodoc.persistence.oid.TipoNovedadOid;
import com.keralty.aeenviodoc.persistence.Planes;
import com.keralty.aeenviodoc.persistence.SolicitudNovedad;
import com.keralty.aeenviodoc.persistence.TipoNovedad;
import com.keralty.aeenviodoc.repository.NovedadJpaRepository;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import com.keralty.aeenviodoc.sqlutils.SQLWhereComparison;
import com.keralty.aeenviodoc.sqlutils.SQLWhereConjunction;
import com.keralty.aeenviodoc.sqlutils.SQLWhereDisjunction;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandSubQuery;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;
import com.keralty.aeenviodoc.viewmodel.FilterVariableRequest;
import com.keralty.aeenviodoc.viewmodel.InstanceResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PopulationResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PreloadResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PreloadViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class NovedadService extends AbstractService {

    NovedadJpaRepository repository;

    NovedadService novedadSrv;

    DominioService dominioSrv;

    TipoNovedadService tipoNovedadSrv;

    PlanesService planesSrv;

    CausalService causalSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    CausaExclusionService causaExclusionSrv;

    @Inject
    public NovedadService(
            NovedadService novedadSrv,
            DominioService dominioSrv,
            TipoNovedadService tipoNovedadSrv,
            PlanesService planesSrv,
            CausalService causalSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            CausaExclusionService causaExclusionSrv,
            NovedadJpaRepository repository) {
        this.novedadSrv = novedadSrv;
        this.dominioSrv = dominioSrv;
        this.tipoNovedadSrv = tipoNovedadSrv;
        this.planesSrv = planesSrv;
        this.causalSrv = causalSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.causaExclusionSrv = causaExclusionSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, NovedadConstants.CLASS_NAME);
    }

    private List<Novedad> solveNovedadTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, NovedadConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveNovedadTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, NovedadConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Novedad
     *
     * @return Population of class Novedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Novedad> get() {
        List<Novedad> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Novedad
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Novedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Novedad> instances = get(queryRequest);
        if(NovedadConstants.DSICNOVEDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICNovedad())).collect(Collectors.toList());
        }
        if(NovedadConstants.DSDSNOVEDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSNovedad())).collect(Collectors.toList());
        }
        if(NovedadConstants.DSDSNOVEDADDMDDOMINIO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSNovedadDMDDominio())).collect(Collectors.toList());
        }
        if(NovedadConstants.DSDSNOVEDADNULL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSNovedadNull())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Novedad that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Novedad matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Novedad> get(QueryRequest queryRequest) {
        return solveNovedadTypedQuery(queryRequest);
    }
    
    /*
     * Returns the list of instances that matches certain search criteria
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return List of instances that matches certain search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public QueryResponse getDynamic(QueryRequest queryRequest) {
        if (queryRequest.getDisplaySetName() == null || queryRequest.getDisplaySetName().isEmpty()) {
            return new PopulationResponseViewModel(solveNovedadTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveNovedadTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveNovedadTypedQuery(queryRequest).stream()
                    .map(instance -> buildPreloadViewModel(instance, queryRequest.getDisplaySetName()))
                    .collect(Collectors.toList()));
        }
    }

    /*
     * Returns the population of a class with a certain structure
     *
     * @param relatedInstanceJsonOid Json Oid of the related instance
     *
     * @param relatedInstancePath Path to the related instance
     *
     * @param displaySetItems DisplaySet items for each returned instance
     *
     * @return List of instances
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PopulationResponseViewModel getByRelatedDynamic(String relatedInstanceJsonOid, String relatedInstancePath, String displaySetItems) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstanceJsonOid))));
        return new PopulationResponseViewModel(solveNovedadTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Novedad by ID
     *
     * @param novedadidNovedad Identification field novedadidNovedad
     *
     * @return Instance of Novedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Novedad getById(Long novedadidNovedad) {
        return getByOID(new NovedadOid(novedadidNovedad));
    }
    
    /*
     * Returns an instance of Novedad by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Novedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Novedad getByOID(NovedadOid oid) {
        Optional<Novedad> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Novedad by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Novedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Novedad getByOIDWithHV(NovedadOid oid) {
        Optional<Novedad> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Novedad> getInstanceByOid(NovedadOid oid) {
        return oid != null && oid.getNovedadidNovedad() != null
               ? repository.findById(oid.getNovedadidNovedad())
               : Optional.empty();
    }

    /*
     * Returns an instance of Novedad by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Novedad
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Novedad getByJsonOid(String jsonOid) {
        NovedadOid oid = new NovedadOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Novedad related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Novedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Novedad> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveNovedadTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Novedad related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Novedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Novedad> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Novedad
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Novedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getNovedadDynamic(NovedadOid oid, String displaySetItems) {
        return new InstanceResponseViewModel(getByOIDWithHV(oid), displaySetItems);
    }

    /*
     * Returns the supplementary info for a given instance
     *
     * @param oid Instance OID
     *
     * @param dsName DisplaySet name
     *
     * @return Supplementary info for a given instance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public String getSuppInfo(NovedadOid oid, String dsName) {
        Novedad instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Novedad instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (NovedadConstants.DSICNOVEDAD.equals(dsName))
            return instance.buildICNovedad();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Novedad instance, String dsName) {
        if (NovedadConstants.DSICNOVEDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICNovedad());
        if (NovedadConstants.DSDSNOVEDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSNovedad());
        if (NovedadConstants.DSDSNOVEDADDMDDOMINIO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSNovedadDMDDominio());
        if (NovedadConstants.DSDSNOVEDADNULL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSNovedadNull());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (NovedadConstants.FILTER_NAME_FNOVEDAD.equalsIgnoreCase(filterRequest.getName()))
            fNovedad(filterRequest, sql);
    }

    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Dominio_TINSNOVEDAD_pt_p_evcNovedad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431701422080423FiltNav12(filterRequest, sql);
        if ("SolicitudNovedad_TCREAR_pt_p_agrNovedad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav4(filterRequest, sql);
        if ("SolicitudNovedad_TCREAR_pt_p_agrNovedad_NavFilter1".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav23(filterRequest, sql);
        if ("SolicitudNovedad_TCREAREXCEPCION_pt_p_agrNovedad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav12(filterRequest, sql);
        if ("SolicitudNovedad_TCREAREXCEPCION_pt_p_agrNovedad_NavFilter1".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav24(filterRequest, sql);
        if ("GrupoAsignacionNovedad_TCREAR_ptNovedad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1586852003840652FiltNav1(filterRequest, sql);
        if ("ConfiguracionANS_TCREAR_pt_p_agrNovedad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1586859868160432FiltNav1(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Novedad".equals(filterRequest.getName()))
            suppInfo4ICNovedad(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void fNovedad(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        TipoNovedad vfTipo = filterRequest.hasFilterVariable(NovedadConstants.VAR_NAME_FNOVEDAD_VFTIPO) ? tipoNovedadSrv.getByOID(new TipoNovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(NovedadConstants.VAR_NAME_FNOVEDAD_VFTIPO)))) : null;
        String vfNombre = filterRequest.hasFilterVariable(NovedadConstants.VAR_NAME_FNOVEDAD_VFNOMBRE) ? (String) filterRequest.getFilterVariable(NovedadConstants.VAR_NAME_FNOVEDAD_VFNOMBRE, FilterVariableRequest.STRING) : null;

        // TipoNovedad = vfTipo
        if (vfTipo != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(NovedadConstants.ROLE_NAME_TIPONOVEDAD, TipoNovedadConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(NovedadConstants.VAR_NAME_FNOVEDAD_VFTIPO, TipoNovedadConstants.CLASS_NAME, TipoNovedadConstants.CLASS_NAME, vfTipo.getOid(), NovedadConstants.VAR_NAME_FNOVEDAD_VFTIPO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // nombre LIKE "%" + vfNombre
        if (vfNombre != null) {
            // 'like' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(NovedadConstants.ATTR_NAME_NOVEDADNOMBRE, Constants.Type.STRING.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNombre, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        sql.addConjunction(conjunction);
    }

    private void clas1431701422080423FiltNav12(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        TipoNovedad dominioTINSNOVEDADptTipo = filterRequest.hasFilterVariable(DominioConstants.ARG_NAME_TINSNOVEDAD_DOMINIOTINSNOVEDADPTTIPO) ? tipoNovedadSrv.getByOID(new TipoNovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.ARG_NAME_TINSNOVEDAD_DOMINIOTINSNOVEDADPTTIPO)))) : null;

        // TipoNovedad = ptTipo
        if (dominioTINSNOVEDADptTipo != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(NovedadConstants.ROLE_NAME_TIPONOVEDAD, TipoNovedadConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.ARG_NAME_TINSNOVEDAD_DOMINIOTINSNOVEDADPTTIPO, TipoNovedadConstants.CLASS_NAME, TipoNovedadConstants.CLASS_NAME, dominioTINSNOVEDADptTipo.getOid(), DominioConstants.ARG_NAME_TINSNOVEDAD_DOMINIOTINSNOVEDADPTTIPO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1581421428736234FiltNav4(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        TipoNovedad solicitudNovedadTCREARptTipoNovedad = filterRequest.hasFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTTIPONOVEDAD) ? tipoNovedadSrv.getByOID(new TipoNovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTTIPONOVEDAD)))) : null;

        // TipoNovedad = ptTipoNovedad
        if (solicitudNovedadTCREARptTipoNovedad != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(NovedadConstants.ROLE_NAME_TIPONOVEDAD, TipoNovedadConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTTIPONOVEDAD, TipoNovedadConstants.CLASS_NAME, TipoNovedadConstants.CLASS_NAME, solicitudNovedadTCREARptTipoNovedad.getOid(), SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTTIPONOVEDAD);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1581421428736234FiltNav23(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        TipoNovedad solicitudNovedadTCREARptTipoNovedad = filterRequest.hasFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTTIPONOVEDAD) ? tipoNovedadSrv.getByOID(new TipoNovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTTIPONOVEDAD)))) : null;
        Planes solicitudNovedadTCREARptpagrPlan = filterRequest.hasFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRPLAN)))) : null;

        // TipoNovedad = ptTipoNovedad
        if (solicitudNovedadTCREARptTipoNovedad != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(NovedadConstants.ROLE_NAME_TIPONOVEDAD, TipoNovedadConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTTIPONOVEDAD, TipoNovedadConstants.CLASS_NAME, TipoNovedadConstants.CLASS_NAME, solicitudNovedadTCREARptTipoNovedad.getOid(), SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTTIPONOVEDAD);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // EXIST (Dominios) WHERE (Dominios.PlanT = ptpagrPlan) = true
        if (solicitudNovedadTCREARptpagrPlan != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator003 = new SQLWhereOperandSubQuery(repository.assocOperator003Range1Query(solicitudNovedadTCREARptpagrPlan, sql));
    conjunction.addOperand(operandSubQueryAssocOperator003);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1581421428736234FiltNav12(FilterRequest filterRequest, SQLSelect sql) {
        clas1581421428736234FiltNav4(filterRequest, sql);
    }

    private void clas1581421428736234FiltNav24(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        clas1581421428736234FiltNav23(filterRequest, sql);
    }

    private void clas1586852003840652FiltNav1(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Dominio grupoAsignacionNovedadTCREARptpagrDominio = filterRequest.hasFilterVariable(GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO) ? dominioSrv.getByOID(new DominioOid().fromJsonString(toJson(filterRequest.getFilterVariable(GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO)))) : null;

        // EXIST (Dominios) WHERE (Dominios = ptpagrDominio) = true
        if (grupoAsignacionNovedadTCREARptpagrDominio != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator005 = new SQLWhereOperandSubQuery(repository.assocOperator005Range1Query(grupoAsignacionNovedadTCREARptpagrDominio, sql));
    conjunction.addOperand(operandSubQueryAssocOperator005);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1586859868160432FiltNav1(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        clas1586852003840652FiltNav1(filterRequest, sql);
    }

    // IIU Methods

    /*
     * Returns a viewmodel for class Novedad with displayset DSNovedad for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Novedad with displayset DSNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedad iIUNovedad(NovedadOid oid) {
        return new com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedad(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUNovedad
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Novedad using displayset DSNovedad that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedad> piuPIUNovedad(QueryRequest queryRequest) {
        return solveNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedad::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUNovedad
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUNovedad
     */
    public int getCount4PIUNovedad(QueryRequest queryRequest) {
        return solveNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUNovedadDDominio
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Novedad using displayset DSNovedadDMDDominio that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedadDMDDominio> piuPIUNovedadDDominio(QueryRequest queryRequest) {
        return solveNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedadDMDDominio::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUNovedadDDominio
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUNovedadDDominio
     */
    public int getCount4PIUNovedadDDominio(QueryRequest queryRequest) {
        return solveNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUNovedadNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Novedad using displayset DSNovedadNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedadNull> piuPIUNovedadNull(QueryRequest queryRequest) {
        return solveNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedadNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUNovedadNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUNovedadNull
     */
    public int getCount4PIUNovedadNull(QueryRequest queryRequest) {
        return solveNovedadTypedQuery(queryRequest).size();
    }

    // Service: InsNovedad

    /*
     * Entry point for service InsNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insNovedad(
            com.keralty.aeenviodoc.viewmodel.novedad.siu.InsNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = novedadSrv.insNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insNovedadTxn(
            com.keralty.aeenviodoc.viewmodel.novedad.siu.InsNovedadRequest request) throws ModelException {
        request.setNovedadInsNovedadpthisNovedadInstance(novedadSrv.getByOIDWithHV(request.getNovedadInsNovedadpthisNovedad()));
        request.setNovedadInsNovedadpevcDominioInstance(dominioSrv.getByOIDWithHV(request.getNovedadInsNovedadpevcDominio()));
        return novedadSrv.insNovedadImplementation(request, true);
    }

    /*
     * Implements service InsNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insNovedadImplementation(
            com.keralty.aeenviodoc.viewmodel.novedad.siu.InsNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Novedad novedadInsNovedadpthisNovedad = request.getNovedadInsNovedadpthisNovedadInstance();
        Dominio novedadInsNovedadpevcDominio = request.getNovedadInsNovedadpevcDominioInstance();

        request.checkArguments();
        Novedad instance = novedadInsNovedadpthisNovedad;

        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.dominio.siu.InsNovedadRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.dominio.siu.InsNovedadRequest();
            relatedRequest.setDominioInsNovedadpthisDominioInstance(novedadInsNovedadpevcDominio);
            relatedRequest.setDominioInsNovedadpevcNovedadInstance(novedadInsNovedadpthisNovedad);
            dominioSrv.insNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICNovedad());

        return response;
    }

    // Service: DelNovedad

    /*
     * Entry point for service DelNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delNovedad(
            com.keralty.aeenviodoc.viewmodel.novedad.siu.DelNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = novedadSrv.delNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delNovedadTxn(
            com.keralty.aeenviodoc.viewmodel.novedad.siu.DelNovedadRequest request) throws ModelException {
        request.setNovedadDelNovedadpthisNovedadInstance(novedadSrv.getByOIDWithHV(request.getNovedadDelNovedadpthisNovedad()));
        request.setNovedadDelNovedadpevcDominioInstance(dominioSrv.getByOIDWithHV(request.getNovedadDelNovedadpevcDominio()));
        return novedadSrv.delNovedadImplementation(request, true);
    }

    /*
     * Implements service DelNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delNovedadImplementation(
            com.keralty.aeenviodoc.viewmodel.novedad.siu.DelNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Novedad novedadDelNovedadpthisNovedad = request.getNovedadDelNovedadpthisNovedadInstance();
        Dominio novedadDelNovedadpevcDominio = request.getNovedadDelNovedadpevcDominioInstance();

        request.checkArguments();
        Novedad instance = novedadDelNovedadpthisNovedad;

        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.dominio.siu.DelNovedadRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.dominio.siu.DelNovedadRequest();
            relatedRequest.setDominioDelNovedadpthisDominioInstance(novedadDelNovedadpevcDominio);
            relatedRequest.setDominioDelNovedadpevcNovedadInstance(novedadDelNovedadpthisNovedad);
            dominioSrv.delNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICNovedad());

        return response;
    }


    private void suppInfo4ICNovedad(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(NovedadConstants.ATTR_NAME_NOVEDADNOMBRE, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCNovedad(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(NovedadConstants.CLASS_NAME, NovedadConstants.ATTR_FIELD_NOVEDADIDNOVEDAD, false), true);
    }


    private void applyOCOCNovedad(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_Novedad".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(NovedadConstants.CLASS_NAME, NovedadConstants.ATTR_NAME_NOVEDADNOMBRE, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildNovedadRelatedInstance(relatedInstanceJsonOid);
        }
        if (NovedadConstants.ROLE_NAME_DOMINIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildNovedadRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        if (NovedadConstants.ROLE_NAME_CAUSALES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildNovedadRelatedInstance4Causal(relatedInstanceJsonOid, remainingPath);
        }
        if (NovedadConstants.ROLE_NAME_SOLICITUDES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildNovedadRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (NovedadConstants.ROLE_NAME_CAUSASEXCLUSION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildNovedadRelatedInstance4CausaExclusion(relatedInstanceJsonOid, remainingPath);
        }
        if (NovedadConstants.ROLE_NAME_TIPONOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildNovedadRelatedInstance4TipoNovedad(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildNovedadRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Novedad(mapper.readValue(relatedInstanceJsonOid, NovedadOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildNovedadRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildNovedadRelatedInstance4Causal(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Causal(mapper.readValue(relatedInstanceJsonOid, CausalOid.class)): Causal.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildNovedadRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildNovedadRelatedInstance4CausaExclusion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new CausaExclusion(mapper.readValue(relatedInstanceJsonOid, CausaExclusionOid.class)): CausaExclusion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildNovedadRelatedInstance4TipoNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoNovedad(mapper.readValue(relatedInstanceJsonOid, TipoNovedadOid.class)): TipoNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            NovedadOid oid = new NovedadOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(NovedadConstants.ATTR_FIELD_NOVEDADIDNOVEDAD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getNovedadidNovedad(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(NovedadService.class).error(e.getMessage());
        }
    }

}
