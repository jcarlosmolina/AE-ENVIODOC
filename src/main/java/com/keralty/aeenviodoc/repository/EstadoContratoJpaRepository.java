package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Aesolicitudes;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.EstadoContrato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoContratoJpaRepository extends JpaRepository<EstadoContrato, Long>, EstadoContratoJpaRepositoryCustom {

    List<EstadoContrato> findByAESOLICITUDES(Aesolicitudes aESOLICITUDES);

    List<EstadoContrato> findByContratosC(ContratoC contratosC);
}
