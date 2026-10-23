package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.CuestionarioP;
import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Preexistencia;
import com.keralty.aeenviodoc.persistence.PregCuesMedC;
import com.keralty.aeenviodoc.persistence.PreguntaP;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.Usuario;
import com.keralty.aeenviodoc.sqlutils.SQLExists;
import com.keralty.aeenviodoc.sqlutils.SQLGetOne;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLPopulation;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface PersonaCJpaRepositoryCustom {

    List<PersonaC> solveQuery(SQLSelect query);
    
    List<PersonaC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(PersonaC instance) throws SystemException;

    Boolean assocOperator000Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(PersonaC instance) throws SystemException;

    String assocOperator001(PersonaC instance) throws SystemException;

    String assocOperator001Range1(PersonaC instance) throws SystemException;
    
    SQLGetOne assocOperator001Range1Query(PersonaC instance) throws SystemException;

    Boolean assocOperator002(PersonaC instance) throws SystemException;

    Boolean assocOperator002Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator002Range1Query(PersonaC instance) throws SystemException;

    String assocOperator003(PersonaC instance) throws SystemException;

    String assocOperator003Range1(PersonaC instance) throws SystemException;
    
    SQLGetOne assocOperator003Range1Query(PersonaC instance) throws SystemException;

    Boolean assocOperator004(PersonaC instance) throws SystemException;

    Boolean assocOperator004Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator004Range1Query(PersonaC instance) throws SystemException;

    String assocOperator005(PersonaC instance) throws SystemException;

    Collection<DireccionC> assocOperator005Range1(PersonaC instance) throws SystemException;
    
    SQLPopulation assocOperator005Range1Query(PersonaC instance) throws SystemException;

    Boolean assocOperator006(PersonaC instance) throws SystemException;

    Boolean assocOperator006Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator006Range1Query(PersonaC instance) throws SystemException;

    String assocOperator007(PersonaC instance) throws SystemException;

    Collection<DirSucursalC> assocOperator007Range1(PersonaC instance) throws SystemException;
    
    SQLPopulation assocOperator007Range1Query(PersonaC instance) throws SystemException;

    Boolean assocOperator008(PersonaC instance) throws SystemException;

    Boolean assocOperator008Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator008Range1Query(PersonaC instance) throws SystemException;

    Boolean assocOperator009(PersonaC instance) throws SystemException;

    Boolean assocOperator009Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator009Range1Query(PersonaC instance) throws SystemException;

    Boolean assocOperator010(PersonaC instance) throws SystemException;

    Boolean assocOperator010Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator010Range1Query(PersonaC instance) throws SystemException;

    Boolean assocOperator011(PersonaC instance) throws SystemException;

    Boolean assocOperator011Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator011Range1Query(PersonaC instance) throws SystemException;

    Collection<PregCuesMedC> assocOperator013(PersonaC instance) throws SystemException;

    Collection<PregCuesMedC> assocOperator013Range1(PersonaC instance) throws SystemException;
    
    SQLPopulation assocOperator013Range1Query(PersonaC instance) throws SystemException;

    Collection<PreguntaP> assocOperator014(PersonaC personaCTCREAROCUESDEAEpthisPersonaC, CuestionarioP personaCTCREAROCUESDEAEpCuestionarioP) throws SystemException;

    Collection<PreguntaP> assocOperator014Range1(CuestionarioP personaCTCREAROCUESDEAEpCuestionarioP) throws SystemException;
    
    SQLPopulation assocOperator014Range1Query(CuestionarioP personaCTCREAROCUESDEAEpCuestionarioP) throws SystemException;

    Boolean assocOperator015(PersonaC instance) throws SystemException;

    Boolean assocOperator015Range1(PersonaC instance) throws SystemException;
    
    SQLExists assocOperator015Range1Query(PersonaC instance) throws SystemException;

    Collection<Direccion> assocOperator022(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Collection<Direccion> assocOperator022Range1(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;
    
    SQLPopulation assocOperator022Range1Query(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Collection<Direccion> assocOperator023(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Collection<Usuario> assocOperator025(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Collection<Usuario> assocOperator025Range1(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;
    
    SQLPopulation assocOperator025Range1Query(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Boolean assocOperator026(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Boolean assocOperator026Range1(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;
    
    SQLExists assocOperator026Range1Query(Solicitud personaCTNEWCONTRADESDEAEptSolicitud, SQLSelect superQuery) throws SystemException;

    Collection<Usuario> assocOperator027(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Collection<Usuario> assocOperator027Range1(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;
    
    SQLPopulation assocOperator027Range1Query(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Boolean assocOperator028(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;

    Boolean assocOperator028Range1(Solicitud personaCTNEWCONTRADESDEAEptSolicitud) throws SystemException;
    
    SQLExists assocOperator028Range1Query(Solicitud personaCTNEWCONTRADESDEAEptSolicitud, SQLSelect superQuery) throws SystemException;

    Collection<Direccion> assocOperator029(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Collection<Direccion> assocOperator029Range1(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;
    
    SQLPopulation assocOperator029Range1Query(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Collection<Direccion> assocOperator030(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Collection<Usuario> assocOperator032(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Collection<Usuario> assocOperator032Range1(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;
    
    SQLPopulation assocOperator032Range1Query(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Boolean assocOperator033(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Boolean assocOperator033Range1(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;
    
    SQLExists assocOperator033Range1Query(Solicitud personaCTNEWTITULARDESDEAEptSolicitud, SQLSelect superQuery) throws SystemException;

    Collection<Usuario> assocOperator034(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Collection<Usuario> assocOperator034Range1(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;
    
    SQLPopulation assocOperator034Range1Query(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Boolean assocOperator035(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;

    Boolean assocOperator035Range1(Solicitud personaCTNEWTITULARDESDEAEptSolicitud) throws SystemException;
    
    SQLExists assocOperator035Range1Query(Solicitud personaCTNEWTITULARDESDEAEptSolicitud, SQLSelect superQuery) throws SystemException;

    Collection<Direccion> assocOperator036(Usuario personaCTNEWBENEFDESDEAEptUsuario) throws SystemException;

    Collection<Direccion> assocOperator036Range1(Usuario personaCTNEWBENEFDESDEAEptUsuario) throws SystemException;
    
    SQLPopulation assocOperator036Range1Query(Usuario personaCTNEWBENEFDESDEAEptUsuario) throws SystemException;

    Collection<CuestionarioP> assocOperator038(Usuario personaCTNEWBENEFDESDEAEptUsuario) throws SystemException;

    Collection<CuestionarioP> assocOperator038Range1(Usuario personaCTNEWBENEFDESDEAEptUsuario) throws SystemException;
    
    SQLPopulation assocOperator038Range1Query(Usuario personaCTNEWBENEFDESDEAEptUsuario) throws SystemException;

    Boolean assocOperator039(Usuario personaCTNEWBENEFDESDEAEptUsuario) throws SystemException;

    Boolean assocOperator039Range1(Usuario personaCTNEWBENEFDESDEAEptUsuario) throws SystemException;
    
    SQLExists assocOperator039Range1Query(Usuario personaCTNEWBENEFDESDEAEptUsuario, SQLSelect superQuery) throws SystemException;

    Collection<Preexistencia> assocOperator040(PersonaC personaCTCOPIARPREEXDEUSUpthisPersonaC, Usuario personaCTCOPIARPREEXDEUSUpUsuario) throws SystemException;

    Collection<Preexistencia> assocOperator040Range1(Usuario personaCTCOPIARPREEXDEUSUpUsuario) throws SystemException;
    
    SQLPopulation assocOperator040Range1Query(Usuario personaCTCOPIARPREEXDEUSUpUsuario) throws SystemException;

    public List<PersonaC> executeQuery(String query);

    public List<PersonaC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
