package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.FormaPago;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagoJpaRepository extends JpaRepository<FormaPago, Long>, FormaPagoJpaRepositoryCustom {

    List<FormaPago> findByDominios(Dominio dominios);

    List<FormaPago> findBySolicitudes(Solicitud solicitudes);

    List<FormaPago> findByContratosC(ContratoC contratosC);
}
