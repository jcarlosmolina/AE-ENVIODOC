package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.Pais;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisJpaRepository extends JpaRepository<Pais, String>, PaisJpaRepositoryCustom {

    List<Pais> findByDireccionesC(DireccionC direccionesC);

    List<Pais> findByDirecciones(Direccion direcciones);

    List<Pais> findByDirSucursalC(DirSucursalC dirSucursalC);
}
