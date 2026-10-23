package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Preexistencia;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface PreexistenciaJpaRepositoryCustom {

    List<Preexistencia> solveQuery(SQLSelect query);
    
    List<Preexistencia> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);
    
    Long nextPreexistenciaIdentificador(Long aesolicitudesidsolicitud, Long usuarioUsuarioIdentificador);

    public List<Preexistencia> executeQuery(String query);

    public List<Preexistencia> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
