package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Aesolicitudes;
import com.keralty.aeenviodoc.persistence.AreaMedica;
import com.keralty.aeenviodoc.persistence.Contratante;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.DocumentoG;
import com.keralty.aeenviodoc.persistence.Firma;
import com.keralty.aeenviodoc.persistence.FormaPago;
import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.persistence.GrupoAsociado;
import com.keralty.aeenviodoc.persistence.LogAsignaciones;
import com.keralty.aeenviodoc.persistence.LogEstado;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.ProteccionDatos;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.TipoFactura;
import com.keralty.aeenviodoc.persistence.Titular;
import com.keralty.aeenviodoc.persistence.Usuario;
import com.keralty.aeenviodoc.persistence.UsuarioReg;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudJpaRepository extends JpaRepository<Solicitud, Long>, SolicitudJpaRepositoryCustom {

    List<Solicitud> findByDireccion(Direccion direccion);

    List<Solicitud> findByProteccionDatos(ProteccionDatos proteccionDatos);

    List<Solicitud> findByUsuarios(Usuario usuarios);

    List<Solicitud> findByContratante(Contratante contratante);

    List<Solicitud> findByFirma(Firma firma);

    List<Solicitud> findBySegundaFirma(Firma segundaFirma);

    List<Solicitud> findByFirmaContratanteIncl(Firma firmaContratanteIncl);

    List<Solicitud> findByUsuariosRegistrados(UsuarioReg usuariosRegistrados);

    List<Solicitud> findByTitular(Titular titular);

    List<Solicitud> findByDocumentosGestor(DocumentoG documentosGestor);

    List<Solicitud> findByLogEstado(LogEstado logEstado);

    List<Solicitud> findByLogAsignaciones(LogAsignaciones logAsignaciones);

    List<Solicitud> findByAesolicitudes(Aesolicitudes aesolicitudes);

    List<Solicitud> findByContratantePersona(Persona contratantePersona);

    List<Solicitud> findByTitularPersona(Persona titularPersona);

    List<Solicitud> findByGrupoAsociado(GrupoAsociado grupoAsociado);

    List<Solicitud> findByAgente(Funcionario agente);

    List<Solicitud> findByFormaPago(FormaPago formaPago);

    List<Solicitud> findByPeriodicidad(Periodicidad periodicidad);

    List<Solicitud> findByContratoC(ContratoC contratoC);

    List<Solicitud> findByUsrAreaMedica(AreaMedica usrAreaMedica);

    List<Solicitud> findByTipoFactura(TipoFactura tipoFactura);

    List<Solicitud> findByUsrAfiliacion(Funcionario usrAfiliacion);
}
