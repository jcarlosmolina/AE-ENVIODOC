package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoC;
import com.keralty.aeenviodoc.persistence.AnexoMat;
import com.keralty.aeenviodoc.persistence.AnexoNeo;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodicidadJpaRepository extends JpaRepository<Periodicidad, Long>, PeriodicidadJpaRepositoryCustom {

    List<Periodicidad> findByDominios(Dominio dominios);

    List<Periodicidad> findBySolicitudes(Solicitud solicitudes);

    List<Periodicidad> findByAnexosMat(AnexoMat anexosMat);

    List<Periodicidad> findByAnexosNeo(AnexoNeo anexosNeo);

    List<Periodicidad> findByContratosC(ContratoC contratosC);

    List<Periodicidad> findByAnexosC(AnexoC anexosC);
}
