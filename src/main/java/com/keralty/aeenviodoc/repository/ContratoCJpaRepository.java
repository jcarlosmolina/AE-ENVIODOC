package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Asesor;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.FormaPago;
import com.keralty.aeenviodoc.persistence.GrupoAsociado;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.ObservacionesC;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoCJpaRepository extends JpaRepository<ContratoC, Long>, ContratoCJpaRepositoryCustom {

    List<ContratoC> findBySolicitud(Solicitud solicitud);

    List<ContratoC> findByPersonaCContratante(PersonaC personaCContratante);

    List<ContratoC> findByDireccionesC(DireccionC direccionesC);

    List<ContratoC> findByObservacionesC(ObservacionesC observacionesC);

    List<ContratoC> findByBeneficiariosC(BeneficiarioC beneficiariosC);

    List<ContratoC> findByFamiliasC(FamiliaC familiasC);

    List<ContratoC> findByPeriodicidadC(Periodicidad periodicidadC);

    List<ContratoC> findByFormaPagoC(FormaPago formaPagoC);

    List<ContratoC> findByProductoC(Productos productoC);

    List<ContratoC> findByGrupoAsociadoC(GrupoAsociado grupoAsociadoC);

    List<ContratoC> findByMunicipioC(Municipio municipioC);

    List<ContratoC> findByAsesorC(Asesor asesorC);
}
