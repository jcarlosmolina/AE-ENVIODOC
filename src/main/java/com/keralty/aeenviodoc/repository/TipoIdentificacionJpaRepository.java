package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoC;
import com.keralty.aeenviodoc.persistence.Asesor;
import com.keralty.aeenviodoc.persistence.Contratante;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import com.keralty.aeenviodoc.persistence.Titular;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIdentificacionJpaRepository extends JpaRepository<TipoIdentificacion, Long>, TipoIdentificacionJpaRepositoryCustom {

    List<TipoIdentificacion> findByAsesores(Asesor asesores);

    List<TipoIdentificacion> findByPersonas(Persona personas);

    List<TipoIdentificacion> findByPersonasC(PersonaC personasC);

    List<TipoIdentificacion> findByUsuarioS(Usuario usuarioS);

    List<TipoIdentificacion> findByContratantes(Contratante contratantes);

    List<TipoIdentificacion> findByAnexosC(AnexoC anexosC);

    List<TipoIdentificacion> findByTitulares(Titular titulares);
}
